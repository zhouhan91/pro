// pages/error/error.js

//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

var comCityDetailUrl = config.comCityDetailUrl;
var userCouponsUrl = config.iUsableCouponsUrl;
var communityPayUrl = config.idxCommunityPayUrl;
var comGetExchangeRateUrl = config.comGetExchangeRateUrl;


// 显示成功提示
var showSuccess = text => wx.showToast({
  title: text,
  icon: 'success'
});

// 显示失败提示
var showFail = text => wx.showToast({
  title: text,
  icon: 'error'
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

var diffDate = function (that, startDate, endDate){
  //计算日期差值
  var sDate = new Date(startDate);
  var eDate = new Date(endDate);
  // console.log(sDate.getTime());
  // console.log(eDate.getTime());
  var diffTimes = (eDate.getTime() - sDate.getTime()) / 1000;
  var diffDays = diffTimes / 60 / 60 / 24;
  var diffMonths = parseInt(diffDays / 30);
  // console.log(diffDays);
  // console.log(diffMonths);
  if (diffDays == 0) {
    diffDays = 1;
  }
  that.setData({
    diffDays: diffDays,
    diffMonths: diffMonths,
    isDateOk: true
  });
};

var getFormatDate = function(targetTime){
  var dateObj = null;
  if (targetTime) {
    dateObj = new Date(targetTime);
  } else {
    dateObj = new Date();
  }

  var year = dateObj.getFullYear();
  var month = dateObj.getMonth() + 1;
  var day = dateObj.getDate();
  var dateStr = year + "-" + month + "-" + day;

  return dateStr;
}

var stepDayDate = function(dateStr, days){
  var date = new Date(dateStr);
  var d = date.getTime() + days * 24 * 3600 * 1000;
  return getFormatDate(d);
};

var stepYearDate = function(dateStr, years){
  var dateObj = new Date(dateStr);
  var year = dateObj.getFullYear() + years;
  var month = dateObj.getMonth() + 1;
  var day = dateObj.getDate();
  var newDateStr = year + "-" + month + "-" + day;
  return newDateStr
};

var calcOrderCost = function(that) {
  var fee = 0.0;
  if (that.data.orderInfo.roomInfo.leaseModel == "日租") {
    var days = that.data.diffDays;
    var discountPrice = that.data.orderInfo.roomInfo.discountPrice;
    //var tipPrice = that.data.orderInfo.roomInfo.tipPrice;
    //fee = parseFloat(days) * discountPrice + tipPrice;
    fee = parseFloat(days) * discountPrice;
    //计算费用
    console.log(days);
    console.log(discountPrice);
    console.log(fee);
  } else if (that.data.orderInfo.roomInfo.leaseModel == "月租") {
    /*
    order.roomId = this.data.house_detail.rooms[index].id;
    if (this.data.house_detail.rooms[index].images.length > 0)
      order.image = this.data.house_detail.rooms[index].images[0].path;
    order.roomType = this.data.house_detail.rooms[index].type;
    order.discountPrice = this.data.house_detail.rooms[index].discountPrice;
    order.tipPrice = this.data.house_detail.rooms[index].tipPrice;
    */
    var roomInfo = that.data.orderInfo.roomInfo;
    var tipPrice = roomInfo.tipPrice;   //服务费
    var depositPrice = roomInfo.depositPrice; //押金
    var firstRentMonth = roomInfo.firstRentMonth; //首次支付几个月租金
    var firstDepositMonth = roomInfo.firstDepositMonth; //首次支付几个月押金
    var firstAmount = roomInfo.firstAmount; //首次支付总金额
    var payFlag = roomInfo.payFlag;   //是否需要支付
    var tipFlag = roomInfo.tipFlag;   //是否需要定金
    var leaseLimit = roomInfo.leaseLimit; //最短租期
    var leaseUnit = roomInfo.leaseUnit; //最短租期单位
    var dateAllowedStart = roomInfo.dateAllowedStart; //可租期-开始
    var dateAllowedEnd = roomInfo.dateAllowedEnd; //可租期=结束
    if (payFlag == "Y") { //订单需要支付
      if (tipFlag == "Y") { //需要交定金
        //订单只计算定金
        fee = tipPrice;
      } else {  //订单按付几押几计算
        fee = firstAmount;
      }
    }
  }
  if (parseFloat(that.data.couponAmount)) {
    fee = fee - parseInt(that.data.couponAmount);
  } else {
  }

  return fee;
};

//调用API向本地缓存存入数据,如果keyword为空，则情况列表
var saveStorageData = function (that, key, value) {
  var storageData = wx.getStorageSync(key) || []
  if (value) {
    if (value != '' && storageData.indexOf(value) < 0) {
      storageData.push(value);
      wx.setStorageSync(key, storageData);
    }
  } else {
    wx.setStorageSync(key, []);
  }
};

//加载指定key的缓存数据
var loadStorageData = function (key) {
  var storageData = wx.getStorageSync(key) || []
  return storageData;
}

//获取汇率信息
var loadExchangeRate = function(that) {
  wx.request({
    url: comGetExchangeRateUrl,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function(res) {
      console.log(res.data.resultData);
      var euros = res.data.resultData.euros;
      that.setData({
        euros: euros,
      });
    },
    fail: function(error) {

    }
  });
}

//根据城市ID获取城市名称
var loadCityDetail = function(that, cityId){
  that.setData({ isLoading: true });
  wx.showLoading({
    title: "正在加载",
    mask: true
  });

  wx.request({
    url: comCityDetailUrl + cityId,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('这是');
      var address=app.globalData.orderInfo.address;
      that.setData({
        cityName: address
      });
      console.log(address);
      console.log(data);
      if (data.resultCode == "999999" && data.resultData) {

      }
      that.setData({
        isLoading: false,
      });
      wx.hideLoading();
    },
    fail: function(msg){
      that.setData({
        isLoading: false,
      });
      wx.hideLoading();
    }
  });
};

//获取用户优惠券列表
var loadUserCoupons = function(that) {
  qcloud.login({
    success(result) {
      var params = "?userKey=" + result.sess_id + "&busiCode=community";
      that.setData({
        userInfo: result.userInfo
      })
      //获取用户优惠券
      wx.request({
        url: userCouponsUrl + params,
        header: {
          'content-type': 'application/json;charset=UTF-8'
        },
        method: "GET",
        success: function (res) {
          var data = res.data;
          console.log(data);
          var hasCoupon = false;
          var couponList = [];
          if (data.resultCode == "999999") {
            if (data.resultData && data.resultData.length > 0) {
              hasCoupon = true;
              couponList = data.resultData;
            } else {

            }
          } else {

          }
          that.setData({
            hasCoupon: hasCoupon,
            couponList: couponList,
          });
          wx.hideLoading();
        },
        fail: function (error) {
          that.setData({
            hasCoupon: false,
            couponList: [],
          });
          wx.hideLoading();
          showModel('操作失败', error);
        },
      });
    },
    fail(error) {
      wx.hideLoading();
      showModel('登录失败', error);
    }
  });
};

/**
 * 发起微信支付
 */
var wxPayRequest = function (that, wxOrderInfo){

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
      if (error.errMsg == "requestPayment:fail cancel"){
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
var submitOrderInfo = function(that){
  console.log(that.data.isOrderOk);
  if ((that.data.isOrderOk & 3) != 3) {
    showModel("提示", "请完善入住时间");
  } else if ((that.data.isOrderOk & 28) != 28) {
    showModel("提示", "请完善入住人信息");
  }
  console.log(that.data.isPaying);
  if ((that.data.isOrderOk == 31 || that.data.isOrderOk == 63) && !that.data.isPaying){
    that.setData({
      isPaying:true
    });
    //communityPayUrl
    var value = that.data.months.replace(/[^0-9]/ig, ""); 
    console.log(that.data.Weixin)
    console.log(that.data.University)
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
            communityId: that.data.orderInfo.communityId,
            roomId: that.data.orderInfo.roomInfo.id,
            inDate: that.data.startDate,
            outDate: that.data.endDate,
            //outDate: that.data.startDate,
            leaseMonth: value,
            realName: that.data.renterName,
            phone: that.data.renterPhone,
            email: that.data.renterEmail,
            couponId: that.data.couponId,
            school: that.data.University,
            wechat:that.data.Weixin,
            payType: "wxpay",
            orderSource: "program" //租房
          },
          
          method: "POST",
          success: function (res) {
            var data = res.data;
            console.log(data);
            // var wxOrderInfo = {};
            // var paySign = 0;
            if (data.resultCode == "999999") {
              if( data.resultData.payFlag && data.resultData.payFlag == "Y") {
                // wxOrderInfo = data.resultData;
                // paySign = wxOrderInfo.paySign;
                //发起微信支付
                wxPayRequest(that, data.resultData);
              } else {
                that.setData({
                  isPaying: false
                });
                wx.navigateTo({
                  url: '../ordersucc/ordersucc?nickName=' + that.data.userInfo.nickName,
                })
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
        that.setData({
          isPaying: false
        });
        showModel('登录失败', error);
      }
    });
  }
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderInfo: null,  //订单信息
    cityName: "", //城市名称
    hasCoupon: false, //用户是否有优惠券
    couponList: [],   //用户优惠券列表

    isDateOk: false,  //入住时间已选好
    startDate: null,  //入住日期
    endDate: null,  //离开日期
    monthList: ["1个月", "2个月", "3个月", "4个月", "5个月", "6个月", "7个月", "8个月", "9个月", "10个月", "11个月","12个月"],
    months:"",

    serviceCharge: 100, //服务费
    couponAmount: 0, //选择使用的优惠券金额

    isOrderOk: false, //订单信息是否完整
    paySign: 0,   //订单实际金额

    isShowCoupon: false,  //是否显示优惠券列表
    notUseCouponDesc: "不使用代金券",
    notUseCouponIdx: 0,
    couponId: 0,

    renterName: "", //
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("onLoad");
  
    wx.hideShareMenu({
      success: function () {
        //隐藏转发菜单成功
      }
    });
    //设置订单参数
    if (app.globalData.orderInfo) {
      app.globalData.orderInfo.roomInfo.leaseModel = app.globalData.orderInfo.roomInfo.leaseModel.trim();
      this.setData({
        orderInfo: app.globalData.orderInfo,
      });
    }

    var currDate = getFormatDate(); //返回当前日期字符串
    //currDate = this.data.orderInfo.roomInfo.dateAllowedStart ? this.data.orderInfo.roomInfo.dateAllowedStart.substring(0, 10) : currDate;
    var roomInfo = this.data.orderInfo.roomInfo;
    var step = 0;
    if (roomInfo.leaseLimit && roomInfo.leaseLimit > 0) {
      step = roomInfo.leaseLimit;
    }
    var minDate = stepDayDate(currDate, step);
    var maxDate = stepYearDate(currDate, 10);
    //maxDate = this.data.orderInfo.roomInfo.dateAllowedEnd ? this.data.orderInfo.roomInfo.dateAllowedEnd.substring(0, 10) : maxDate;
    
    console.log(this.data.orderInfo);
    
    // console.log(test);
    this.setData({
      // startDate: currDate,
      // endDate: minDate,
      currDate: currDate,
      minDate: minDate,
      maxDate: maxDate,
    });
    // console.log(currDate);
    // console.log(minDate);
    // console.log(maxDate);

    //加载入住人缓存数据
    // var renterInfo = loadStorageData('OrderRenterInfo');
    // console.log(renterInfo);
    // if (renterInfo) {
    // } else {
      // renterInfo = {
      //   renterName: "",
      //   renterPhone: "",
      //   renterEmail: "",
      // }
    // }
    this.setData({
      // renterInfo: renterInfo,
      renterName: "",
      renterPhone: "",
      renterEmail: "",
    });

    //加载当前汇率信息
    loadExchangeRate(this);

    //根据城市id获取城市名
    loadCityDetail(this, this.data.orderInfo.cityId);
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
    //获取用户优惠券
    loadUserCoupons(this);
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
   * 开始日期选择监听
   */
  bindStartDateChange: function (e) {
    console.log("bindStartDateChange");
    console.log(e.detail.value);
    if (e.detail.value && e.detail.value != null){
      var roomInfo = this.data.orderInfo.roomInfo;
      var step = 0;
      if (roomInfo.leaseLimit && roomInfo.leaseLimit > 0) {
        step = roomInfo.leaseLimit;
      }
      console.log(roomInfo.leaseLimit);
      console.log(step);
      var startDateNext = stepDayDate(e.detail.value, step);
      console.log(startDateNext);
      this.setData({
        startDate: e.detail.value,
        startDateNext: startDateNext,
        isOrderOk: this.data.isOrderOk | 1
      });
      if (this.data.endDate && this.data.endDate != '') {
        diffDate(this, e.detail.value, this.data.endDate);
      }
    } else {
      console.log("debug");
      this.setData({
        isDateOk: false,
        isOrderOk: this.data.isOrderOk & 62
      });
    }
    if (this.data.isDateOk) {
      var fee = calcOrderCost(this);
      this.setData({
        actualCost: fee
      });
    }
    // submitOrderInfo(this);
  },

  /**
   * 入住时间选择监听
   */
  bindTimeChange:function(e){
    console.log("bindTimeChange");
    console.log(e.detail.value);
    // var renter_info = this.data.renterInfo;
    var time = "";
    if (e.detail.value && e.detail.value != '') {
      time = e.detail.value;
      this.setData({
        isOrderOk: this.data.isOrderOk | 32
      });
    } else {
      this.setData({
        isOrderOk: this.data.isOrderOk & 31
      });
    }
    this.setData({
      // renterInfo: renter_info,
      time: time
    });
  },

  /**
   * 离开日期选择监听
   */
  bindEndDateChange: function (e) {
    console.log("bindEndDateChange");
    console.log(e.detail.value);
    if (e.detail.value && e.detail.value != null) {
      var roomInfo = this.data.orderInfo.roomInfo;
      var step = 0;
      if (roomInfo.leaseLimit && roomInfo.leaseLimit > 0) {
        step = roomInfo.leaseLimit;
      }
      var endDatePre = stepDayDate(e.detail.value, -step);
      console.log(endDatePre);
      this.setData({
        endDate: e.detail.value,
        endDatePre: endDatePre,
        isOrderOk: this.data.isOrderOk | 2
      });
      if (this.data.startDate && this.data.startDate != '') {
        diffDate(this, this.data.startDate, e.detail.value);
      }
    } else {
      this.setData({
        isDateOk: false,
        isOrderOk: this.data.isOrderOk & 61
      });
    }
    if (this.data.isDateOk){
      var fee = calcOrderCost(this);
      this.setData({
        actualCost: fee
      });
    } else {

    }
    // submitOrderInfo(this);
  },

  bindMonthPickerChange:function(e){
    console.log("bindMonthPickerChange");
    console.log(e.detail.value);
    var roomInfo = this.data.orderInfo.roomInfo;
    var value = this.data.monthList[e.detail.value].replace(/[^0-9]/ig, ""); 
    if (e.detail.value && e.detail.value != null) {
      if (roomInfo.leaseLimit > value) {
        this.setData({
          //months: this.data.monthList[e.detail.value],
          isDateOk: false,
          isOrderOk: this.data.isOrderOk & 61
        });
        showModel('提示', roomInfo.leaseLimit + "个月起租");
      } else {
        this.setData({ 
          months: this.data.monthList[e.detail.value],
          isOrderOk: this.data.isOrderOk | 2
        });
        if (this.data.startDate && this.data.startDate != '') {
          this.setData({
            isDateOk: true,
          });
        }
      }
    } else {
      this.setData({
        isDateOk: false,
        isOrderOk: this.data.isOrderOk & 61
      });
    }
    if (this.data.isDateOk) {
      var fee = calcOrderCost(this);
      this.setData({
        actualCost: fee
      });
    } else {
    }
  },

  /**
   * 入住人姓名光标离开触发
   */
  onRenterNameBlur: function(e){
    console.log("onRenterNameBlur");
    // var renter_info = this.data.renterInfo;
    var renterName = ""
    if(e.detail.value && e.detail.value != ''){
      console.log(e.detail.value);
      // renter_info.renterName = e.detail.value;
      renterName = e.detail.value;
      this.setData({
        isOrderOk: this.data.isOrderOk | 4
      });
    } else {
      // renter_info.renterName = "";
      this.setData({
        isOrderOk: this.data.isOrderOk & 59
      });
    }
    this.setData({
      // renterInfo: renter_info,
      renterName: renterName
    });
    // console.log(renter_info);
    // console.log(this.data.renterInfo);
    // submitOrderInfo(this);
  },

  /**
   * 入住人电话光标离开触发
   */
  onRenterPhoneBlur: function(e){
    console.log("onRenterPhoneBlur");
    //((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)
    var a = new RegExp("^[0-9]*$");
    if (a.test(e.detail.value)){
      console.log(e.detail.value);
      // var renter_info = this.data.renterInfo;
      var renterPhone = "";
      if (e.detail.value && e.detail.value != '') {
        // renter_info.  renterPhone = e.detail.value;
        renterPhone = e.detail.value;
        this.setData({
          isOrderOk: this.data.isOrderOk | 8
        });
      } else {
        // renter_info.renterPhone = "";
        this.setData({
          isOrderOk: this.data.isOrderOk & 55
        });
      }
      this.setData({
        // renterInfo: renter_info,
        renterPhone: renterPhone
      });
    } else {
      showModel('提示', "请输入正确的电话");
    }
  },

  /**
   * 入住人Email光标离开触发
   */
  onRenterEmailBlur: function(e){
    console.log("onRenterEmailBlur");
    //^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$
    var a = new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$");
    if (a.test(e.detail.value)) {
      var renterEmail = "";
      //邮箱有效
      // var renter_info = this.data.renterInfo;
      if (e.detail.value && e.detail.value != '') {
        // renter_info.renterEmail = e.detail.value;
        renterEmail = e.detail.value;
        this.setData({
          isOrderOk: this.data.isOrderOk | 16
        });
      } else {
        // renter_info.renterEmail = "";
        this.setData({
          isOrderOk: this.data.isOrderOk & 47
        });
      }
      this.setData({
        // renterInfo: renter_info,
        renterEmail: renterEmail
      });
    } else {
      //邮箱无效
      showModel('提示', "请输入正确的邮箱");
    }
    console.log(a.test(e.detail.value));

    
  },

  /**
     * 入住人学校光标离开触发
     */
  onRenterUniversity: function (e) {
    this.setData({
      University: e.detail.value,
    });
  },
  /**
     * 入住人微信光标离开触发
     */
  onRenterWeixin: function (e) {
    this.setData({
      Weixin: e.detail.value,
    });
  },
  /**
   * 优惠券输入框获取焦点时触发显示优惠券列表
   */
  onCouponFocus: function (e) {
    console.log("onCouponFocus");
    this.setData({
      isShowCoupon: true
    });
  },

  /**
   * 优惠券点击事件
   */
  onCouponClick: function(e){
    console.log("onCouponClick");
    this.setData({
      isShowCoupon: true
    });
  },

  /**
   * 优惠券选择
   */
  onCouponChangeClick: function(e){
    console.log("onCouponChangeClick");
    console.log(e.detail.value);
    if (e.detail.value && e.detail.value != '') {
      var amount = 0;
      this.data.couponList.forEach(function(item,idex){
        console.log(item);
        if (item.id == parseInt(e.detail.value)){
          amount = item.amount
        }
      });
      this.setData({
        couponAmount: amount,
        couponId: parseInt(e.detail.value),
        isShowCoupon: false,
        // isOrderOk: this.data.isOrderOk | 32
      });
    } else {
      this.setData({
        couponAmount: 0,
        couponId: parseInt(notUseCouponIdx),
        isShowCoupon: false,
        // isOrderOk: this.data.isOrderOk & 31
      });
    }
    if (this.data.isDateOk) {
      var fee = calcOrderCost(this);
      this.setData({
        actualCost: fee
      });
    }
    // submitOrderInfo(this);
  },

  /**
   * 优惠券光标离开触发
   */
  onCouponBlur: function(e){
    console.log("onCouponFocus");
    // if (e.detail.value && e.detail.value != '') {
    //   this.setData({
    //     isOrderOk: this.data.isOrderOk | 32
    //   });
    // } else {
    //   this.setData({
    //     isOrderOk: this.data.isOrderOk & 31
    //   });
    // }
    // submitOrderInfo(this);
  },

  /**     
   * 隐藏优惠券列表
   */
  onHideUserCouponClick: function(e){
    console.log("onCouponFocus");
    this.setData({
      isShowCoupon: false
    });
  },

  /**
   * 点击支付按钮
   */
  onPayClick: function() {
    //检查登录，并且请求”立即支付“接口
    submitOrderInfo(this);

    //保存入住人信息到缓存
    // saveStorageData("OrderRenterInfo", this.data.renterInfo);
  }

})