// pages/setting_userinfo/setting_userinfo.js

//获取应用实例
const app = getApp();

var qcloud = require('../common/index');

const userInfoUrl = require('../../config').iUserInfoUrl;
const updateUserInfoUrl = require('../../config').iUpdateUserInfoUrl;


// 显示繁忙提示
var showBusy = text => wx.showToast({
  title: text,
  icon: 'loading',
  duration: 10000
});

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

var loadUserInfo = (that, session) => {
  wx.request({
    url: userInfoUrl + session.sess_id,
    data: {
      userKey: session.sess_id,
    },
    dataType: "json",
    method: "get",
    header: {
      'content-type': 'application/x-www-form-urlencoded', // 默认值
    },
    success: res => {
      var dt = res.data;
      // 成功地响应会话信息
      if (dt && dt.resultCode == "999999") {
        that.setData({
          userData: dt.resultData
        });
        wx.hideLoading();
      } else {
        wx.hideLoading();
        showModel('加载失败', "请求没有包含会话响应，请确保服务器处理");
      }
      
    },
    fail: function () {
      wx.hideLoading();
    }
  })
};

var updateUserInfo = (that, params) => {
  wx.request({
    url: updateUserInfoUrl,
    data: params,
    dataType: "json",
    method: "POST",
    header: {
      // 'content-type': 'application/x-www-form-urlencoded', // 默认值
    },
    success: res => {
      if (res.data.resultCode == "999999") {
        //使用箭头的方法可以使用
        showSuccess("操作成功");
      } else {
        showModel("系统提示", "操作失败");
      }
    },
    fail: function () {
      showModel("系统提示", "操作失败");
    }
  })
};

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
    wx.showLoading({
      title: "正在加载",
      mask: true
    });

    var that = this;
    qcloud.login({
      success(result) {
        var isNewUser = result.userData.newUser;
        that.setData({
          userInfo: result.userInfo,
          sess_id: result.sess_id
        });

        //加载用户信息
        loadUserInfo(that, result);
      },

      fail(error) {
        wx.hideLoading();
        showModel('加载失败', error);
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
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },


  onLogout:function(e) {
    qcloud.clearSession();
    showModel("系统提示", "会话已关闭");
    wx.switchTab({
      url: '../index/index',
    })
  },


  onUserFormSubmit: function(e){
    var formInfo = e.detail.value;
    wx.showLoading({
      title: "正在处理",
      mask: true
    });
    if (this.data.sess_id){
      formInfo.userKey = this.data.sess_id;
      updateUserInfo(this, formInfo);
    } else {
      var that = this;
      qcloud.login({
        success(result) {
          var isNewUser = result.userData.newUser;
          that.setData({
            userInfo: result.userInfo,
            sess_id: result.sess_id
          });

          formInfo.userKey = this.data.sess_id;
          updateUserInfo(this, formInfo);
        },

        fail(error) {
          wx.hideLoading();
          showModel('加载失败', error);
        }
      });

    }
    
  }
})