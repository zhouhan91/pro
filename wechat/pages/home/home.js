// pages/home/home.js


//获取应用实例
const app = getApp();

var qcloud = require('../common/index');

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

var updateUserInfo = (that, session) => {
  wx.request({
    url: updateUserInfoUrl,
    data: {
      userKey: session.sess_id,
      nickname: session.userInfo.nickName,
      portrait: session.userInfo.avatarUrl,
      sex: session.userInfo.gender,
      country: session.userInfo.country,
      province: session.userInfo.province,
      city: session.userInfo.city,
    },
    dataType: "json",
    method: "POST",
    header: {
      // 'content-type': 'application/x-www-form-urlencoded', // 默认值
    },
    success: res => {
      if (res.data.resultCode == "999999") {
        //使用箭头的方法可以使用
        // showSuccess("操作成功");
      } else {
        // showModel("系统提示", "操作失败");
      }
    },
    fail: function () {

    }
  })
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: "正在登录",
      mask: true
    });
    console.log("onLoad");
    var that = this;
    qcloud.login({
      success(result) {
        var isNewUser = result.userData.newUser;
        that.setData({
          userInfo: result.userInfo,
          sess_id: result.sess_id
        });

        if (!isNewUser || isNewUser == 'Y') {
          //新用户上报数据
          // updateUserInfo(this, result);
        }
        wx.hideLoading();
        showSuccess('登录成功');
      },

      fail(error) {
        wx.hideLoading();
        showModel('登录失败', error);
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // console.log("onReady")
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // var that = this;
    // qcloud.login({
    //   success(result) {
    //     var isNewUser = result.userData.newUser;
    //     that.setData({
    //       userInfo: result.userInfo,
    //       sess_id: result.sess_id
    //     });

    //     if (isNewUser && isNewUser == 'Y') {
    //       //新用户更新
    //       updateUserInfo(this, result);
    //     }
    //   },

    //   fail(error) {
    //     showModel('登录失败', error);
    //   }
    // });
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
    // console.log("onPullDownRefresh")
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    // console.log("onReachBottom")
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  //点击设置或头像进入用户信息页
  bindSettingViewTap: function(e){
    wx.navigateTo({
      url: '../setting_userinfo/setting_userinfo',
    })
  }
})