// pages/error/error.js
//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

var communityOrdersUrl = config.iCommunityOrdersUrl; //查询租房订单列表
var communityPayUrl = config.idxCommunityPayUrl;

// 显示失败提示
var showModel = (title, content) => {
  wx.hideToast();

  wx.showModal({
    title,
    content: JSON.stringify(content),
    showCancel: false
  });
};

// arrA + arrB 去重
var duplicateRemoval = function(arrA, arrB){
  var newArray = []
  if (arrB == null || arrB.length == 0) {
    return arrA;
  }
  if (arrA && arrA.length == 0) {
    return arrB;
  }
  arrB.forEach(function (itemB, indexB) {
    var isHave = false;
    arrA.forEach(function (itemA, indexA) {
      if (itemB.code == itemA.code) { //已经存在
        isHave = true;
      }
    });
    if (!isHave){
      newArray.push(itemB);
    }
  });
  arrA = arrA.concat(newArray);

  return arrA
}

var loadOrderList = function(that) {
  that.setData({
    isLoading: true,
    loadingStatus: 0,
  });

  wx.showLoading({
    title: "正在处理",
    mask: true
  });

  var len = that.data.orderList.length;
  var pagenum = parseInt(len / 10) + 1;
  qcloud.login({
    success(result) {
      //获取用户优惠券
      wx.request({
        url: communityOrdersUrl + pagenum,
        header: {
          'content-type': 'application/json;charset=UTF-8'
        },
        data:{
          pageNum: pagenum,
          userKey: result.sess_id
        },
        method: "POST",
        success: function (res) {
          var data = res.data;
          if (data.resultCode == "999999" && data.resultData.length > 0) {

            var oldLen = that.data.orderList.length;
            var orderList = duplicateRemoval(that.data.orderList, data.resultData);
            console.log(orderList);
            var newLen = orderList.length;
            that.setData({
              // orderList: that.data.orderList.concat(data.resultData),
              orderList: orderList,
              isLoading: false,
              loadingStatus: oldLen < newLen ? 1 : 2
            });

          } else {
            that.setData({
              isLoading: false,
              loadingStatus: 2
            });
          }
          wx.hideLoading();
        },
        fail: function (error) {
          that.setData({
            isLoading: false,
            loadingStatus: 2
          });
          wx.hideLoading();
          showModel('操作失败', error);
        },
      });
    },
    fail(error) {
      that.setData({
        isLoading: false,
        loadingStatus: 2
      });
      wx.hideLoading();
      showModel('登录失败', error);
    }
  });
};


/**
 * 发起微信支付
 */
var wxPayRequest = function (wxOrderInfo) {
  var that = this;
  //调用微信支付接口
  wx.requestPayment({
    timeStamp: wxOrderInfo.timeStamp,
    nonceStr: wxOrderInfo.nonceStr,
    package: wxOrderInfo.package,
    signType: wxOrderInfo.signType,
    paySign: wxOrderInfo.paySign,
    success: function () {
      that.setData({
        isPaying: false
      });
      wx.navigateTo({
        url: '../ordersucc/ordersucc?nickName=' + that.data.userInfo.nickName,
      })
    },
    fail: function (error) {
      that.setData({
        isPaying: false
      });
      console.log(error);
      if (error.errMsg == "requestPayment:fail cancel") {
        showModel("支付失败", "取消支付");
      } else {
        showModel("支付失败", "调用支付失败");
      }
    }
  });
}

/**
 * 递交订单信息
 */
var submitOrderInfo = function (that, orderInfo) {
  //小程序订单，发起支付
  if (orderInfo.orderSource == "program") {
    //communityPayUrl
    qcloud.login({
      success(result) {
        //检查是否收藏
        wx.request({
          url: communityPayUrl,
          header: {
            'content-type': 'application/json;charset=UTF-8'
          },
          data: {
            userKey: result.sess_id,
            communityId: orderInfo.communityId,
            roomId: orderInfo.roomId,
            inDate: orderInfo.inDate,
            outDate: orderInfo.outDate,
            realName: orderInfo.realName,
            phone: orderInfo.phone,
            email: orderInfo.email,
            couponId: orderInfo.coupon,
            payType: "wxpay",
            orderSource: "program" //租房
          },
          method: "POST",
          success: function (res) {
            var data = res.data;
            console.log(data);
            // var wxOrderInfo = {};
            // var paySign = 0;
            if (data.resultCode == "999999" && data.resultData) {
              // wxOrderInfo = data.resultData;
              // paySign = wxOrderInfo.paySign;
              //发起微信支付
              wxPayRequest(data.resultData);
            }
            that.setData({
              // paySign: paySign,
              // wxOrderInfo: wxOrderInfo
            });
          },
          fail: function (error) {
            that.setData({
              // paySign: 0,
              // wxOrderInfo: {}
            });
            showModel('操作失败', error);
          },
        });
      },
      fail(error) {
        showModel('登录失败', error);
      }
    });
  } else {
    showModel('操作失败', "只支持小程序订单支付");
  }
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderList: [],
    hasOrder: false,
    loadingStatus: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          mobileSystem: res.system.toLowerCase()
        });
        console.log(that.data.mobileSystem);
        loadOrderList(that);
      }
    });
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    this.setData({
      isLoading: true
    });
    loadOrderList(this);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  //点击支付
  onPayClick: function(e){
    if (!this.data.isPaying) {
      this.setData({
        isPaying: true
      });
      var item = e.currentTarget.dataset.payOrder;
      submitOrderInfo(this, item);
    }
  }
})