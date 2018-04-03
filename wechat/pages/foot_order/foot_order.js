//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

const getDetail = config.getshopdetails;//获取商户主页信息
const queryMyOrderListUrl = config.iQueryMyOrderListUrl; //查询用户好吃订单列表
const checkNewMemberUrl = config.checkNewMemberUrl; //检查是不是新会员
const queryDefCateringContactsUrl = config.queryDefCateringContactsUrl;//读取用户默认收货人信息
const cateringOrderPayUrl = config.cateringOrderPayUrl; //立即支付
//获取当前时间
var gettime = function (that) {
  //东1区，东时区记做正数
  var zoneOffset = 1;
  var offset = new Date().getTimezoneOffset() * 60 * 1000;
  //算出现在的时间：
  var nowDate = new Date().getTime();
  //算出对应的格林位置时间
  var GMTDate = new Date(nowDate + offset + zoneOffset * 60 * 60 * 1000);//Wed Apr 20 2016 22:27:02 GMT+0800 (CST)
  //转换成本地时间格式
  var GMTDateInLocalString = GMTDate.toLocaleString();//2016/4/20 下午10:27:02
  console.log("东1区现在是：" + GMTDateInLocalString);
  that.setData({
    GMTDateInLocalString: GMTDateInLocalString
  })
};
// 显示成功提示
var showSuccess = text => wx.showToast({
  title: text,
  icon: 'success'
});

// 显示失败提示
var showModel = (title, content) => {
  wx.hideToast();

  wx.showModal({
    title,
    content: JSON.stringify(content),
    showCancel: false
  });
};

//获取商户详情信息
/*var getshopdetail = function (that) {
  console.log(that.data.shopId)
  wx.request({
    url: getDetail + that.data.shopId,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('商户详情')
      console.log(data);
      if (data.resultCode == "999999" && data.resultData) {
        that.setData({
          shopdetail: data.resultData,
        })
      }
    },
    fail: function (error) {

    }
  })
};*/

var checkNewMember = function (that) {
  that.setData({
    isLoading: true,
    loadingStatus: 0,
  });

  wx.showLoading({
    title: "正在处理",
    mask: true
  });

  /*qcloud.login({
    success(result) {
      console.log('find the resulte --------------- sessid');
      console.log(result);
      that.setData({
        sess_id: result.sess_id
      });
      //获取好吃订单列表
      wx.request({
        url: checkNewMemberUrl + that.data.sess_id + "/" + that.data.shopId,
        header: {
          'content-type': 'application/json;charset=UTF-8'
        },
        method: "GET",
        success: function (res) {
          var data = res.data;
          if (data.resultCode == "999999") {
            var isNew = data.resultData;
            that.setData({
              isNew: data.resultData == "Y" ? true : false,
            });
          } else {
            that.setData({
              isNew: false,
            });
          }
          wx.hideLoading();
        },
        fail: function (error) {
          that.setData({
            isNew: false,
          });
          wx.hideLoading();
          showModel('操作失败', error);
        },
      });
    },
    fail(error) {
      that.setData({
        isNew: false,
      });
      wx.hideLoading();
      showModel('登录失败', error);
    }
  });*/
};

//读取默认收货人信息
var queryDefCateringContacts = function(that){
  wx.request({
    url: queryDefCateringContactsUrl + that.data.sess_id,
    method:"GET",
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        that.setData({
          defCateringContact: res.data.resultData
        });
      }
    },
    fail: function(err){

    }
  })
};

/**
 * 发起微信支付
 */
var wxPayRequest = function (that, wxOrderInfo) {

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
        url: '../ordersucc/ordersucc?nickName=' + app.globalData.userInfo.nickName,
      });
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
    },
    
  });
}

/**
 * 递交订单信息
 */
var submitOrderInfo = function (that, payType) {
  console.log(that.data.isPaying);
  
  wx.showLoading({
    title: "正在处理",
    mask: true
  });

  if (that.data.cateringContact && that.data.shopId && that.data.orderfood && that.data.foodmoney > 0 && !that.data.isPaying) {
    that.setData({
      isPaying: true
    });

    var goodsIdStr = "";
    var countStr = "";
    that.data.orderfood.forEach(function(item, idx){
      if (item.fen > 0){
        if (goodsIdStr == ""){
          goodsIdStr = goodsIdStr + item.id;
          countStr = countStr + item.fen;
        } else {
          goodsIdStr = goodsIdStr + "," + item.id;
          countStr = countStr + "," + item.fen;
        }
      }
    });

    qcloud.login({
      success(result) {
        //检查是否收藏
        console.log(that.data.cateringContact);
        wx.request({
          url: cateringOrderPayUrl,
          header: {
            'content-type': 'application/json;charset=UTF-8'
          },
          
          data: {
            contactsId: that.data.cateringContact.id,
            locationId: that.data.restLocation ? that.data.restLocation.id : null,
            restaurantId: that.data.shopId,
            userKey: result.sess_id,
            payType: payType,
            goodsIdStr: goodsIdStr,
            countStr: countStr,
            remark: that.data.remark,
            createTime: that.data.GMTDateInLocalString,
            distributionNotes: app.globalData.peisong,
            orderSource: "program" //微信小程序       
          },

          method: "POST",
          success: function (res) {
            var data = res.data;
            console.log(data);
            // var wxOrderInfo = {};
            // var paySign = 0;
            if (data.resultCode == "999999") {
              if (payType == "offLine") {
                that.setData({
                  isPaying: false
                });
                wx.hideLoading();
                wx.navigateTo({
                  url: '../foot_paysucc/foot_paysucc?nickName=' + app.globalData.userInfo.nickName,
                });
              } else {
                // if (data.resultData.payFlag && data.resultData.payFlag == "Y") {
                  //发起微信支付
                  wxPayRequest(that, data.resultData);
                  wx.hideLoading();
                // } else {
                //   that.setData({
                //     isPaying: false
                //   });
                //   wx.navigateTo({
                //     url: '../foot_paysucc/foot_paysucc?nickName=' + that.data.userInfo.nickName,
                //   });
                // }
              }
            } else {
              that.setData({
                isPaying: false
              });
              wx.hideLoading();
              showModel('操作失败', data.resultDesc);
            }
          },
          fail: function (error) {
            that.setData({
              isPaying: false
            });
            wx.hideLoading();
            showModel('操作失败', error);
          },
        });
      },
      fail(error) {
        that.setData({
          isPaying: false
        });
        wx.hideLoading();
        showModel('登录失败', error);
      }
    });
  } else {
    that.setData({
      isPaying: false
    });
    wx.hideLoading();
    showModel("系统提示", "请完善订单信息");
  }
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    remark:"",  //订单备注信息
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    gettime(this);
    console.log(options);
    var that = this;
    if (options.shopname){
      this.setData({
        shopname: options.shopname,
        shopId: options.shopId
      });
    }
    if (app.globalData.orderfood){
      console.log(app.globalData.orderfood);
      console.log(app.globalData.foodmoney);
      var disc = 0;
      var youhui = 0;
      var fee = 0;

      if (app.globalData.lstCateringDiscount.length > 0){
        app.globalData.lstCateringDiscount.forEach(function(item, idx){
          if (item.type == "quanchangzhekou" && item.status == "Y"){
            disc = ((1 - item.discount) * app.globalData.foodmoney).toFixed(2);
            youhui = youhui + parseFloat(disc);
          }
          else if (item.type == "manjian" && item.status == "Y" && app.globalData.foodmoney > item.target) {
            youhui = youhui + parseFloat(item.discount.toFixed(2));
          }
          //收货人列表为空时，视为新人
          else if (item.type == "xinren" && item.status == "Y" && that.data.isNew){
            youhui = youhui + parseFloat(item.discount.toFixed(2));
          }
        });
      }
      // fee = (this.data.foodmoney - youhui + this.data.shopdetail.distributionAmount).toFixed(2);
      this.setData({
        orderfood: app.globalData.orderfood,
        foodmoney: app.globalData.foodmoney,
        lstCateringDiscount: app.globalData.lstCateringDiscount,
        disc: disc,
        youhui: youhui,
        fee: fee,
        shopdetail: app.globalData.shopdetail,
        isNew: app.globalData.isNew
      })
    }

    /*qcloud.login({
      success(result) {
        var params = "?userKey=" + result.sess_id + "&busiCode=community";
        that.setData({
          userInfo: result.userInfo,
          sess_id: result.sess_id
        });
        //获取商户详情
        getshopdetail(that);

        //读取用户默认收货人信息
        // queryDefCateringContacts(that);
        //判断是不是店铺新会员
        checkNewMember(that);
      },
      fail(error) {
        wx.hideLoading();
        showModel('登录失败', error);
      }
    });*/
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
    if (this.data.sess_id) {
      //读取用户默认收货人信息
      // queryDefCateringContacts(this);
    }
    if (app.globalData.person) {
      this.setData({
        cateringContact: app.globalData.person,
        restLocation: app.globalData.address
      });
      if (app.globalData.address){
        this.setData({
          tjaddress: app.globalData.address.address
        })
      }
    }
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
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },

  /**
   * 点击跳转到收货人信息页
   */
  onChoiceAddrClick: function(e){
    if (this.data.shopdetail.distributionRemark) {
      wx.navigateTo({
        url: '../foodaddress/foodaddress?restaurantId=' + this.data.shopId + "&distributionRemark=" + this.data.shopdetail.distributionRemark,
      })
    } else {
      wx.navigateTo({
        url: '../foodaddress/foodaddress?restaurantId=' + this.data.shopId,
      })
    }
    
  },

  /**
   * 现金支付
   */
  onCashPayClick: function(e){
    console.log("onCashPayClick");
    submitOrderInfo(this, "offLine");
  },

  /**
   * 在线支付
   */
  onOnlinePayClick: function(e){
    console.log("onOnlinePayClick");
    submitOrderInfo(this, "wxpay");
    // showModel("系统提示", "在线买单功能暂未开放");
  },

  /**
   * 备注光标离开时触发
   */
  onRemarkBlur: function(e){
    var remark = e.detail.value;
    this.setData({
      remark: remark
    });
  },

  /**
   * 点击返回
   */
  onBackClick: function(e){
    wx.navigateBack({
      
    });
  }
})