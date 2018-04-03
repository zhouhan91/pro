//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');
var md5Util = require('../../utils/md5.js')    


var loginUrl = config.iLoginUrl;

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // var saveFlag = util.loadStorageData("SAVEFLAG");
    var saveFlag = wx.getStorageSync("SAVEFLAG");
    if (saveFlag) {
      //加载用户密码
      // var loginInfo = util.loadStorageData("LOGININFO");
      var loginInfo = wx.getStorageSync("LOGININFO");
      this.setData({
        loginInfo: loginInfo,
        userName: loginInfo.userName,
        password: loginInfo.password,
        saveFlag: true
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
   * 点击跳转注册页面
   */
  onRegisterClick: function(e){
    wx.navigateTo({
      url: '../register/register',
    })
  },

  /**
   * 切换记住密码
   */
  switch2Change: function(e){
    console.log(e.detail.value);
    //保存状态，写入缓存
    // util.saveStorageData("SAVEFLAG", e.detail.value);
    wx.setStorageSync("SAVEFLAG", e.detail.value);
    this.setData({
      saveFlag: e.detail.value
    });
  },

  /**
   * 登录
   */
  loginFormSubmit: function(e) {
    wx.showLoading({
      title: "登录中",
      mask: true
    });
    console.log("password:"+e.detail.value.password)
    var loginInfo = e.detail.value;
    console.log(loginInfo);
    if (this.data.saveFlag) {
      wx.setStorageSync("LOGININFO", loginInfo);
    }
    wx.request({
      url: loginUrl,
      data: loginInfo,
      method: "POST",
      success: function(res) {
        console.log(res.data);
        if (res.data.resultCode == '999999'){
          app.globalData.restaurantInfo = res.data.resultData;
          wx.hideLoading();
          //登录成功,跳转到主页面
          wx.navigateTo({
            url: '../index/index?id=' + res.data.resultData.id + "&managerId=" + res.data.resultData.managerId + "&name=" + res.data.resultData.name,
          });

        } else {
          wx.hideLoading();
          util.showModel("系统提示", res.data.resultDesc);
        }
      },
      fail: function(err){
        wx.hideLoading();
        util.showModel("系统提示", "登录失败");
      }
    })
  },

  /**
   * 点击 协议
   */
  onXieyiClick: function(e){
    wx.navigateTo({
      url: '../xieyi/xieyi',
    })
  },

  onChangePasswordClick: function(e){
    wx.showModal({
      title: '系统提示',
      content: '请联系客服，电话   0033786838105',
      confirmText: '拨号',
      success: function(res){
        if (res.confirm) {
          console.log('用户点击确定');
          wx.makePhoneCall({
            phoneNumber: '0033786838105' //仅为示例，并非真实的电话号码
          });
        } else if (res.cancel) {
          console.log('用户点击取消');
        }
      }
    });
  }
})
