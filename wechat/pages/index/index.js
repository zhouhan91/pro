//index.js

//获取应用实例
const app = getApp()

const config = require('../../config')
var recommendCitysUrl = config.idxRecommendCitysUrl;
var qcloud = require('../common/index')
const loadDataUrl = require('../../config').loadDataUrl

//获取当前位置
wx.getLocation({
  type: 'wgs84',
  success: function (res) {
    //将当前位置保存到全局变量
    app.globalData.dangqian = res;
  }
});
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

var loadRecommendCityList = (that) => {
  wx.request({
    url: recommendCitysUrl,
    dota: {},
    header: {
      'content-type': 'application/json'
    },
    success: function (res) {
      var data = res.data;
      wx.hideToast();
      console.log(data);
      if (data.resultCode == '999999') {
        that.setData({
          recommendCityList: data.resultData,
          hasData: 1
        });
      } else {
        that.setData({
          hasData: 2
        });
      }
      
    },
    fail: function (msg) {
      that.setData({
        hasData: 2
      });
    }
  });
}

Page({
  data: {
    hasData: 0, //0,默认值；1,有数据；2,无数据
    recommendCityList: [],
  },

  onLoad: function (options) {
    var that = this;
    wx.showToast({
      title: '加载中',
      icon: 'loading'
    });
    
    loadRecommendCityList(that);

    // 页面初始化 options为页面跳转所带来的参数
    console.debug("onLoad")
    // var that = this;
    // qcloud.login({
    //   success(result) {
    //     that.setData({
    //       userInfo: result.userInfo,
    //       sess_id: result.sess_id
    //     });
    //     // showSuccess('登录成功');
    //     // console.log('登录成功', result);
    //   },

    //   fail(error) {
    //     showModel('登录失败', error);
    //     // console.log('登录失败', error);
    //   }
    // });

  },

  onReady: function () {
    // 页面渲染完成
    console.log("onReady")
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },

  navigateDetail: function (e) {
    wx.navigateTo({
      url: '../search/search'
    })
  },


})
