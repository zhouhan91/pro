//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

var statTodayOrderUrl = config.queryTodayOrderStatInfoUrl;
var orderListUrl = config.queryOrderListUrl;
var confirmOrderUrl = config.comfirmOrderUrl;
var cancelOrderUrl = config.cancelOrderUrl;
var distributeOrderUrl = config.distributeOrderUrl;
var settlingOrderUrl = config.settingOrderUrl;
var queryCourierListUrl = config.queryAllCourierListUrl;

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

// arrA + arrB 去重
var duplicateRemoval = function (arrA, arrB) {
  var newArray = []
  if (arrB == null || arrB.length == 0) {
    return arrA;
  }
  if (arrA == null || arrA.length == 0) {
    return arrB;
  }

  arrB.forEach(function (itemB, indexB) {
    console.log("----> itemB.id:" + itemB.id);
    var isHave = false;
    arrA.forEach(function (itemA, indexA) {
      if (itemB.id == itemA.id) { //已经存在
        console.log("     ----> itemA.id:" + itemA.id);
        isHave = true;
      }
    });
    if (!isHave) {
      newArray.push(itemB);
    }
  });

  arrA = arrA.concat(newArray);

  return arrA
};

var stopBgAudio = function(){
  const backgroundAudioManager = wx.getBackgroundAudioManager();
  backgroundAudioManager.stop();
};

var pauseBgAudio = function () {
  const backgroundAudioManager = wx.getBackgroundAudioManager();
  backgroundAudioManager.pause();
};

var playBgAudio = function() {
  const backgroundAudioManager = wx.getBackgroundAudioManager();
  backgroundAudioManager.play();
}

//获取配送员列表（不分页）
var loadCourierList = function(that){
  wx.request({
    url: queryCourierListUrl + that.data.restaurantId,
    data:{
      restaurantId: that.data.restaurantId
    },
    method:"GET",
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999"){
        that.setData({
          courierList: res.data.resultData
        });
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail:function(err){

    }
  })
};

var loadTodayOrderStat = function(that) {
  wx.request({
    url: statTodayOrderUrl + that.data.restaurantId,
    method:"GET",
    success:function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        that.setData({
          orderInfo: res.data.resultData
        });
      }
      
    },
    fail:function(err){
      console.log(err);
    }
  })
};

// 加载订单数据列表
var loadOrderDataList = function(that, isReload){
  console.log(isReload);
  console.log(isReload);
  var oldLen = that.data.tabDataList[that.data.currentTab].length;
  var pageNum = parseInt(oldLen / 10) + 1;
  if (isReload) {
    pageNum = 1;
  }
  var params = {
    restaurantId: that.data.restaurantId,
    status: that.data.tabList[that.data.currentTab].status,
    pageNum: pageNum
  };
  wx.request({
    url: orderListUrl,
    data: params,
    method:"POST",
    success:function(res){
      console.log(res.data.resultData);
      if (res.data.resultCode == "999999") {
        if (that.data.currentTab == 0) {
          playBgAudio();
        }
        var tabDataList = that.data.tabDataList;
        if(isReload){
          tabDataList[that.data.currentTab] = res.data.resultData;
        } else {
          tabDataList[that.data.currentTab] = duplicateRemoval(tabDataList[that.data.currentTab], res.data.resultData);
        }
        
        console.log(that.data.currentTab);
        that.setData({
          tabDataList: tabDataList
        });
      } else {
        if (that.data.currentTab == 0) {
          pauseBgAudio();
        }
      }
    },
    fail: function(err){
    }
  });
};

//取消订单
var cancelOrder = function(that, params){
  wx.request({
    url: cancelOrderUrl,
    data: params,
    method: "POST",
    success: function(res){
      console.log(res);
      if (res.data.resultCode == "999999") {
        //取消订单成功，从新订单列表中移除
        // 加载当天订单统计数据
        loadTodayOrderStat(that);
        // 加载订单列表
        loadOrderDataList(that, true);

        that.setData({
          isShowCancelReason: false,
        });
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail: function(err){
      that.setData({
        isShowCancelReason: false,
      });
    }
  })
};

//确认订单
var confirmOrder = function(that, orderId){
  wx.request({
    url: confirmOrderUrl + that.data.managerId + "/" + orderId,
    method:"POST",
    data:{
      managerId: that.data.managerId,
      orderId: orderId
    },
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999"){
        //提交订单成功，从新订单列表中移除
        // 加载当天订单统计数据
        loadTodayOrderStat(that);
        // 加载订单列表
        loadOrderDataList(that, true);
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail: function(err){

    }
  })
};

//结算订单
var settlingOrder = function (that, params){
  wx.request({
    url: settlingOrderUrl,
    data: params,
    method:"POST",
    success:function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        //结算成功，从结算列表移除
        // 加载当天订单统计数据
        loadTodayOrderStat(that);
        // 加载订单列表
        loadOrderDataList(that, true);

        that.setData({
          isShowSettling: false,
        });
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
      
    },
    fail:function(err){

    }
  })
};

//配送订单
var distributeOrder = function(that){
  wx.request({
    url: distributeOrderUrl,
    data:{
      managerId: that.data.managerId,
      orderId: that.data.orderInfo.id,
      courierId: that.data.courierList[that.data.courierIndex].id
    },
    method:"POST",
    success:function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        //配送成功，从结算列表移除
        // 加载当天订单统计数据
        loadTodayOrderStat(that);
        // 加载订单列表
        loadOrderDataList(that, true);
        that.setData({
          isShowCourier: false,
          courierIndex: -1
        });
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail:function(err){
      that.setData({
        isShowCourier: false,
        courierIndex: -1
      });
    }
  })
};


Page({

  /**
   * 页面的初始数据
   */
  data: {
    winWidth: 0,
    winHeight: 0,
    // tab切换  
    currentTab: 0,
    // tab列表
    tabList: [{"status":"30","tab":"新订单"},{"status":"50","tab":"待配送"},{"status":"60","tab":"待结算"},{"status":"70","tab":"已完成"},{"status":"80","tab":"已取消"}],
    // tab数据列表
    tabDataList:[{},{},{},{},{}],
    // 取消原因
    cancelResonList:["客户电话过来取消","没货估清"],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if (options.restaurantId) {
      that.setData({
        restaurantId: options.restaurantId,
        managerId: options.managerId
      });
    } else {
      //测试数据
      that.setData({
        restaurantId: 2,
        managerId: 4
      });
    }
    if (options.currentTab) {
      this.setData({
        currentTab: options.currentTab
      });
    }

    // 加载配送员列表
    loadCourierList(this);

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
    // 加载当天订单统计数据
    loadTodayOrderStat(this);

    // 加载订单列表
    loadOrderDataList(this, true);
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
    // 加载当天订单统计数据
    loadTodayOrderStat(this);
    // 加载订单列表
    loadOrderDataList(this, true);
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    // 加载订单列表
    loadOrderDataList(this, false);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /** 
     * 滑动切换tab 
     */
  bindChange: function (e) {
    console.log("bindChange");
    var that = this;
    that.setData({ currentTab: e.detail.current });
    // 加载订单列表
    loadOrderDataList(this, true);
  },

  /** 
   * 点击tab切换 
   */
  swichNav: function (e) {

    var that = this;

    if (this.data.currentTab === e.currentTarget.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.currentTarget.dataset.current
      });
      // 加载订单列表
      // loadOrderDataList(this);
    }
  },

  /**
   * 确认订单
   */
  onConfirmOrderClick: function(e){
    var orderInfo = e.currentTarget.dataset.order;
    confirmOrder(this, orderInfo.id);
  },

  /**
   * 取消订单
   */
  onCancelClick: function(e){
    var orderInfo = e.currentTarget.dataset.order;
    this.setData({
      orderInfo: orderInfo,
      isShowCancelReason: true
    });
  },

  /**
   * 配送订单
   */
  onDistributeOrderClick: function(e){
    var orderInfo = e.currentTarget.dataset.order;
    this.setData({
      orderInfo: orderInfo,
      isShowCourier: true
    });
  },

  /**
   * 确认结算订单
   */
  onSettlingOrderClick: function(e){
    var orderInfo = e.currentTarget.dataset.order;
    this.setData({
      orderInfo: orderInfo,
      isShowSettling: true
    });
  },

  /**
   * 选择配送员
   */
  bindCourierChange: function(e){
    var courierIndex = e.detail.value;
    this.setData({
      courierIndex: courierIndex
    });
    console.log(courierIndex);
  },

  /**
   * 确认取消订单
   */
  formCanelSubmit: function(e){
    var formInfo = e.detail.value;
    var msg = ""
    if (formInfo.cancelReason == ""){
      msg = "取消原因不能为空";
    } else if (formInfo.cancelRemark == "") {
      msg = "取消备注不能为空";
    }
    if(msg == ""){
      formInfo.managerId = this.data.managerId
      formInfo.orderId = this.data.orderInfo.id
      console.log(formInfo);
      cancelOrder(this, formInfo);
    } else {
      showModel("系统提示", msg);
    }
    
  },

  /**
   * 取消取消订单
   */
  formCancelReset: function(e){
    this.setData({
      isShowCourier: false,
      isShowCancelReason: false,
      isShowSettling: false
    });
  },

  /**
   * 确认配送
   */
  onCourierConfirmClick: function(e){
    if (this.data.courierIndex && this.data.courierIndex >= 0){
      var courierInfo = this.data.courierList[this.data.courierIndex];
      console.log(courierInfo);
      //订单配送
      distributeOrder(this);
    } else {
      showModel("系统提示", "请选择配送员");
    }
    
  },

  /**
   * 取消配送
   */
  onCourierCancelClick: function(e){
    this.setData({
      isShowCourier: false,
      courierIndex: -1
    });
  },

  /**
   * 取消弹窗
   */
  hideHomeDetail: function(e){
    this.setData({
      isShowCourier: false,
      isShowCancelReason: false
    });
  },

  /**
   * 取消原因当选变化
   */
  radioReasonChange: function(e){

  },

  /**
   * 确认结算表单
   */
  formSettingSubmit: function(e){
    var formInfo = e.detail.value;
    console.log(formInfo);
    var msg = "";
    if (!parseFloat(formInfo.settlementAmount)) {
      msg = "结算金额不合法";
    } else if (formInfo.settlementAmount == "") {
      msg = "结算备注信息不能为空";
    }

    if (msg == ""){
      formInfo.managerId = this.data.managerId;
      formInfo.orderId = this.data.orderInfo.id;

      settlingOrder(this, formInfo);
    } else {
      showModel("系统提示", msg);
    }
  }
})