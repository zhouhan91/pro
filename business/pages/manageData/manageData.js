//index.js
//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

var queryOperationAnalysisUrl = config.queryOperationAnalysisUrl; //读取店铺信息

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
var loadRestAnalysisInfo = function (that) {
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  wx.request({
    url: queryOperationAnalysisUrl + that.data.restaurantId,
    method: "GET",
    success: function (res) {
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
    fail: function (err) {
      wx.hideLoading();
      showModel('系统提示', "操作失败");
    }
  });
};


Page({

  /**
   * 页面的初始数据
   */
  data: {
    listData: [
      { "code": "1", "text": "爱上", "type": "132585412356" },
      { "code": "2", "text": "阿萨爱上", "type": "132585412356" },
      { "code": "3", "text": "阿爱上", "type": "132585412356" },
      { "code": "4", "text": "阿萨德爱上", "type": "132585412356" },
      { "code": "5", "text": "阿爱上", "type": "132585412356" },
      { "code": "6", "text": "阿萨德爱上", "type": "132585412356" },
      { "code": "7", "text": "阿萨德爱上", "type": "132585412356" }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.restaurantId) {
      this.setData({
        restaurantId: options.restaurantId
      });
      loadRestAnalysisInfo(this);
    }
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
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})