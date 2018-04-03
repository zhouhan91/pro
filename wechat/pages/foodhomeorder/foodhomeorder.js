// pages/foodhomeorder/foodhomeorder.js
//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

var queryMyOrderListUrl = config.iQueryMyOrderListUrl; //查询用户好吃订单列表
const cateringOrderPayUrl = config.cateringOrderPayUrl; //立即支付
const cancelMyOrderUrl = config.iCancelMyOrderUrl; //用户取消好吃订单
const publishCommentUrl = config.iPublishCommentUrl;//发表好吃评论
const readCommentUrl = config.iReadCommentUrl; //读取用户好吃评论信息

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
var duplicateRemoval = function (arrA, arrB) {
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
    if (!isHave) {
      newArray.push(itemB);
    }
  });
  arrA = arrA.concat(newArray);

  return arrA
}

var loadOrderList = function (that, reload) {
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

  if (reload) {
    pagenum = 1;
  }
  qcloud.login({
    success(result) {
      console.log(result);
      that.setData({
        sess_id: result.sess_id
      });
      //获取好吃订单列表
      wx.request({
        url: queryMyOrderListUrl + that.data.sess_id + "/" + pagenum,
        header: {
          'content-type': 'application/json;charset=UTF-8'
        },
        method: "GET",
        success: function (res) {
          var data = res.data;
          if (data.resultCode == "999999" && data.resultData.length > 0) {

            var oldLen = that.data.orderList.length;
            var orderList = duplicateRemoval(that.data.orderList, data.resultData);
            if (reload) {
              oldLen = 0;
              orderList = data.resultData;
            }
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
      //支付成功，重新加载订单列表
      loadOrderList(that, true);
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
  console.log(that.data.isPaying);
  //小程序订单，发起支付
  if (orderInfo.orderSource == "program") {
    that.setData({
      isPaying: true
    });

    var goodsIdStr = "";
    var countStr = "";
    orderInfo.lstDetail.forEach(function (item, idx) {
      if (item.fen > 0) {
        if (goodsIdStr == "") {
          goodsIdStr = goodsIdStr + item.goodsId;
          countStr = countStr + item.count;
        } else {
          goodsIdStr = goodsIdStr + "," + item.goodsId;
          countStr = countStr + "," + item.count;
        }
      }
    });
    qcloud.login({
      success(result) {
        //检查是否收藏
        wx.request({
          url: cateringOrderPayUrl,
          header: {
            'content-type': 'application/json;charset=UTF-8'
          },
          data: {
            contactsId: orderInfo.contactsId,
            locationId: orderInfo.locationId,
            restaurantId: orderInfo.restaurantId,
            userKey: result.sess_id,
            payType: orderInfo.payType,
            goodsIdStr: goodsIdStr,
            countStr: countStr,
            remark: orderInfo.remark,
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
                wx.navigateTo({
                  url: '../foot_paysucc/foot_paysucc?nickName=' + that.data.userInfo.nickName,
                });
              } else {
                if (data.resultData.payFlag && data.resultData.payFlag == "Y") {
                  //发起微信支付
                  wxPayRequest(that, data.resultData);
                } else {
                  that.setData({
                    isPaying: false
                  });

                  //操作成功，重新加载订单列表
                  loadOrderList(that, true);
                }
              }
            } else {
              that.setData({
                isPaying: false
              });
              showModel('操作失败', data.resultDesc);
            }
          },
          fail: function (error) {
            that.setData({
              isPaying: false
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

/**
 * 取消订单
 */
var cancelOrder = function(that, orderId){
  console.log(that.data.isPaying);
  
  that.setData({
    isPaying: true
  });

  qcloud.login({
    success(result) {
      //取消订单
      wx.request({
        url: cancelMyOrderUrl + result.sess_id + "/" + orderId,
        header: {
          'content-type': 'application/json;charset=UTF-8'
        },
        data: {
          userKey: result.sess_id,
          orderId: orderId,
        },

        method: "POST",
        success: function (res) {
          var data = res.data;
          console.log(data);

          if (data.resultCode == "999999") {
            //操作成功，重新加载订单列表
            loadOrderList(that, true);
          } else {
            that.setData({
              isPaying: false
            });
            showModel('操作失败', data.resultDesc);
          }
        },
        fail: function (error) {
          that.setData({
            isPaying: false
          });
          showModel('操作失败', error);
        },
      });
    },
    fail(error) {
      showModel('登录失败', error);
    }
  });
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
    loadOrderList(this, true);
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
    loadOrderList(this, false);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  /**
   * 点击支付
   */
  onPayClick: function(e){
    var orderInfo = e.currentTarget.dataset.order;
    if (!this.data.isPaying) {
      this.setData({
        isPaying: true
      });
      var orderInfo = e.currentTarget.dataset.order;
      submitOrderInfo(this, orderInfo);
    }
  },

  /**
   * 点击取消
   */
  onCancelClick: function(e){
    var orderInfo = e.currentTarget.dataset.order;
    //小程序订单
    if (orderInfo.orderSource == "program") {
      if (!this.data.isPaying) {
        this.setData({
          isPaying: true
        });
        cancelOrder(this, orderInfo.id);
      }
    } else {
      showModel('操作失败', "只支持小程序订单取消");
    }
  },

  /**
   * 点击评论
   */
  onCommentClick: function(e){
    var orderInfo = e.currentTarget.dataset.order;
    
    if (orderInfo.commentFlag == "Y") {
      wx.navigateTo({
        url: '../foodcoment/foodcoment?orderId=' + orderInfo.id + "&commentFlag=" + orderInfo.commentFlag + "&restaurantId=" + orderInfo.restaurant.id,
      })
    } else {
      wx.navigateTo({
        url: '../foodcoment/foodcoment?orderId=' + orderInfo.id + "&commentFlag=" + orderInfo.commentFlag,
      })
    }
    
  }
})