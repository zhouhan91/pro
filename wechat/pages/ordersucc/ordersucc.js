// pages/error/error.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    owner:'',//房东姓名
    ownerEmail: '',//房东邮箱
    ownerPhone: '',//房东电话
    ownerWechat:'' //房东微信
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      owner: app.globalData.owner.owner,
      ownerEmail: app.globalData.owner.ownerEmail,
      ownerPhone: app.globalData.owner.ownerPhone,
      ownerWechat: app.globalData.owner.ownerWechat,
    })
    if (options.nickName && options.nickName.length > 0) {
      this.setData({
        nickName: options.nickName,
      });
    } else {
      var that = this;
      qcloud.login({
        success(result) {
          that.setData({
            nickName: result.userInfo.nickName
          });
        },
        fail(error) {
        }
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

  //点击跳转至租房首页
  onOkClick:function(e){
    console.log("onOkClick");
    wx.switchTab({
      url: '../index/index',
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    });
  }
})