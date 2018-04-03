// pages/news/news.js
//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

const updateRestInfoUrl = config.updateRestInfoUrl; //修改店铺信息


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

//修改店铺公告信息
var saveRestNotice = function(that){
  wx.showLoading({
    title: "提交中",
    mask: true
  });
  wx.request({
    url: updateRestInfoUrl,
    method:"POST",
    data:{
      id:that.data.id,
      managerId: that.data.managerId,
      notice: that.data.updateNotice
    },
    success:function(res){
      console.log(res.data);
      if (res.data.resultCode == '999999') {
        //更新本地数据
        that.setData({
          notice: that.data.updateNotice
        });
        app.globalData.restaurantInfo.notice = that.data.updateNotice;
        wx.hideLoading();
        showSuccess("操作成功");
      } else {
        wx.hideLoading();
        showModel('系统提示', res.data.resultDesc);
      }
    },
    fail: function(err){
      wx.hideLoading();
      showModel('系统提示', "操作失败");
    }
  })
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    noticeLength: 200
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.id) {
      this.setData({
        id: options.id,
        managerId: options.managerId,
        notice: options.notice
      });
    } else {
      //测试
      this.setData({
        id: 2,
        managerId: 4,
        notice: options.notice
      });
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

  },

  /**
   * 公告光标离开时触发
   */
  onNoticeBlur: function(e){
    if (e.detail.value.length > 0) {
      this.setData({
        updateNotice: e.detail.value
      });
    } else {
      // showModel('系统提示', "公告为空");
    }
  },

  /**
   * 保存公告修改
   */
  onSaveNoticeClick: function(e){
    saveRestNotice(this);
  }
})