    //index.js
//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

var readRestInfoUrl = config.readRestInfoUrl; //读取店铺信息
var queryTodayOrderStatInfoUrl = config.queryTodayOrderStatInfoUrl; //统计当天订单数据


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

//加载店铺信息
var loadRestInfo = function(that){
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  wx.request({
    url: readRestInfoUrl + that.data.id,
    method:"GET",
    success:function(res){
      if (res.data.resultCode == "999999") {
        that.setData({
          restInfo: res.data.resultData
        });
        wx.hideLoading();
      } else {
        wx.hideLoading();
        showModel('系统提示', res.data.resultDesc);
      }
    },
    fail:function(err){
      wx.hideLoading();
      showModel('系统提示', "操作失败");
    }
  });
};

//加载店铺当天订单信息
var loadRestTodayOrderInfo = function(that){
  if (that.data.id) {
    wx.request({
      url: queryTodayOrderStatInfoUrl + that.data.id,
      method: "GET",
      success: function(res){
        console.log(res);
        if (res.data.resultCode == '999999') {
          var orderPaidCount = res.data.resultData.orderPaidCount;
          var currOrderPaidCount = that.data.currOrderPaidCount;
          currOrderPaidCount = currOrderPaidCount ? currOrderPaidCount : 0;
          if (orderPaidCount > currOrderPaidCount) {
            //有新订单播放音频
            const backgroundAudioManager = wx.getBackgroundAudioManager();
            backgroundAudioManager.title = '新订单提示语音'
            backgroundAudioManager.epname = '新订单提示语音'
            backgroundAudioManager.singer = '微米城市'
            backgroundAudioManager.coverImgUrl = '../images/logo.gif'
            backgroundAudioManager.src = 'http://www.wemecity.net/mp3/cateringOrderNotice.m4a' // 设置了 src 之后会自动播放关于腾讯 文档中心 辟谣中心 客服中心 联系邮箱 Copyright © 2012 - Tencent.All Rights Reserved.
          }

          that.setData({
            todayOrderStatInfo: res.data.resultData,
            currOrderPaidCount: orderPaidCount
          });
        } else {
          showModel('系统提示', res.data.resultDesc);
        }
      },
      fail:function(err){
        showModel('系统提示', "操作失败");
      }
    })
  }
};

var timerFunc = function(that){
  setTimeout(function(){
    loadRestTodayOrderInfo(that);
    timerFunc(that);
  }, 60000);
}


Page({

  /**
   * 页面的初始数据
   */
  data: {
    todayOrderStatInfo: {}, //店铺当天的订单统计数据
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.id) {
      this.setData({
        id: options.id,
        managerId: options.managerId,
        name: options.ame
      });
    }
    //读取当天订单统计数据
    loadRestTodayOrderInfo(this);

    timerFunc(this);
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
    //加载店铺信息
    loadRestInfo(this);

    //读取当天订单统计数据
    loadRestTodayOrderInfo(this);
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
   * 点击 待确认订单数 挑至 待确认订单列表
   */
  onOrderPaidClick: function(e){
    wx.navigateTo({
      url: '../manageOrder/manageOrder?restaurantId=' + this.data.restInfo.id + "&managerId=" + this.data.restInfo.managerId,
    })
  },

  /**
   * 点击 待配送订单数，跳至 待配送订单列表
   */
  onOrderConfirmClick: function(e){
    wx.navigateTo({
      url: '../manageOrder/manageOrder?restaurantId=' + this.data.restInfo.id + "&managerId=" + this.data.restInfo.managerId + "&currentTab=1",
    })
  },

  /**
   * 点击进入菜品管理页面
   */
  onManageFondClick: function(e){
    wx.navigateTo({
      url: '../manageMenu/manageMenu?restaurantId=' + this.data.restInfo.id + "&managerId=" + this.data.restInfo.managerId,
    });
  },

  /**
   * 点击进入活动管理页面
   */
  onManageActivityClick: function(e){
    wx.navigateTo({
      url: '../manageActivity/manageActivity?restaurantId=' + this.data.restInfo.id + "&managerId=" + this.data.restInfo.managerId,
    })
  },

  /**
   * 点击进入订单管理页面
   */
  onManageOrderClick: function(e){
    wx.navigateTo({
      url: '../manageOrder/manageOrder?restaurantId=' + this.data.restInfo.id + "&managerId=" + this.data.restInfo.managerId,
    })
  },

  /**
   * 点击进入设置页面
   */
  onEmplaceClick:function(e){
    wx.navigateTo({
      url: '../emplace/emplace?id=' + this.data.restInfo.id + "&managerId=" + this.data.restInfo.managerId + "&name=" + this.data.restInfo.name,
    })
  },

  /**
   * 点击进入经营统计页面
   */
  onManageDataClick: function(e){
    wx.navigateTo({
      url: '../manageData/manageData?restaurantId=' + this.data.restInfo.id,
    })
  },

  /**
   * 点击进入配送管理页面
   */
  onDistributionClick: function(e){
    wx.navigateTo({
      url: '../distribution/distribution?restaurantId=' + this.data.restInfo.id + '&managerId=' + this.data.restInfo.managerId,
    })
  },

  /**
   * 点击进入订单明细页面
   */
  onOrderDetailClick:function(e){
    wx.navigateTo({
      url: '../orderDetail/orderDetail',
    })
  },

  /**
   * 点击进入店铺公告设置页面
   */
  onNewsClick: function(e){
    console.log(this.data.restInfo);
    wx.navigateTo({
      url: '../news/news?id=' + this.data.restInfo.id + '&managerId=' + this.data.restInfo.managerId + "&notice=" + (this.data.restInfo.notice ? this.data.restInfo.notice : ""),
    });
  },

  /**
   * 点击进入配送人员管理 页面
   */
  onManagePersonnelClick: function(e){
    console.log(this.data.restInfo);
    wx.navigateTo({
      url: '../managePersonnel/managePersonnel?restaurantId=' + this.data.restInfo.id + '&managerId=' + this.data.restInfo.managerId,
    });
  }
})
