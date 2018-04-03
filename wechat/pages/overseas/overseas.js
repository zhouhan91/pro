// pages/overseas/overseas.js

//获取应用实例
const app = getApp();
const config = require('../../config');

var getSubNavNewsListUrl = config.newsGetNewsList;  //获取二级导航下的新闻列表

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
  if (arrA && arrA.length == 0) {
    return arrB;
  }
  arrB.forEach(function (itemB, indexB) {
    var isHave = false;
    arrA.forEach(function (itemA, indexA) {
      if (itemB.id == itemA.id) { //已经存在
        isHave = true;
      }
    });
    if (!isHave) {
      newArray.push(itemB);
    }
  });
  arrA = arrA.concat(newArray);

  return arrA
}

/**
 * 获取二级导航下的新闻列表
 */
var loadSubNavDataList = function (that, subNavCode) {
  var dataList = that.data.subNavNewsList;
  var len = dataList.length;
  var pagenum = parseInt(len / 10) + 1;

  wx.request({
    url: getSubNavNewsListUrl + subNavCode + "/" + pagenum,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    data: {
      // navigationCode: navCode
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log(data.resultData);
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        var subNavNewsList = that.data.subNavNewsList;
        var oldLen = subNavNewsList.length;
        subNavNewsList = duplicateRemoval(subNavNewsList, data.resultData)
        var newLen = subNavNewsList.length;
        that.setData({
          subNavNewsList: subNavNewsList,
          isLoading: false,
          searchStatus: oldLen < newLen ? 1 : 2,
        });
      } else {
        that.setData({
          isLoading: false,
          searchStatus: 2,
        });
      }
      wx.hideLoading();
    },
    fail: function (error) {
      that.setData({
        isLoading: false,
        searchStatus: 2,
      });
      wx.hideLoading();
      showModel('操作失败', error);
    },
  });
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    navCode: "studyAbroad",   //留学一级导航
    subNavCode: "studyAbroad", // "zhuanlan", //留学二级导航
    subNavNewsList:[]         //留学新闻列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //默认新闻列表
    loadSubNavDataList(this, this.data.subNavCode);
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
    this.setData({
      isLoading: true
    });
    //加载二级导航下的新闻列表
    loadSubNavDataList(this, this.data.subNavCode);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})