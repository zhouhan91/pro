// pages/ collection/ collection.js

//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

var markedListUrl = config.iMarkedListUrl;  //查询用户收藏的房源列表
var markedNewsListUrl = config.iMarkedNewsUrl;  //查询用户搜藏的头条

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
  if (arrB == null || arrB.length == 0){
    return arrA;
  }
  if (arrA && arrA.length == 0) {
    return arrB;
  }
  arrB.forEach(function (itemB, indexB) {
    var isHave = false;
    arrA.forEach(function (itemA, indexA) {
      if (itemB.communityId == itemA.communityId) { //已经存在
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

//加载房源收藏列表
var loadMarkedList = (that, isMore, isFresh) => {
  wx.showLoading({
    title: "正在加载",
    mask: true
  });
  var activeIndex = that.data.activeIndex;
  var len = that.data.tabData[activeIndex].length;

  if (that.data.tabData[activeIndex].length == 0 || isMore || isFresh) {
    qcloud.login({
      success(result) {
        var pagenum = parseInt(len / 10) + 1;
        var params = "?pageNum=" + pagenum + "&userKey=" + result.sess_id;
        var url = "";

        if (activeIndex == 0) {
          url = markedNewsListUrl + result.sess_id + "/" + pagenum;
        } else {
          url = markedListUrl + pagenum + params;
        }

        //获取用户收藏房源列表
        wx.request({
          url: url,
          header: {
            'content-type': 'application/json;charset=UTF-8'
          },
          method: "GET",
          success: function (res) {
            var data = res.data;
            console.log(data.resultData);
            if (data.resultCode == "999999" && data.resultData.length > 0) {
              var tabData = that.data.tabData;
              if (isFresh) {
                tabData[activeIndex] = data.resultData;
              } else {
                tabData[activeIndex] = duplicateRemoval(tabData[activeIndex], data.resultData);
              }
              var newLen = tabData[activeIndex].length;
              that.setData({
                tabData: tabData,
                isLoading: false,
                searchStatus: len < newLen ? 1 : 2,
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
      },
      fail(error) {
        wx.hideLoading();
        showModel('登录失败', error);
      }
    });
  } else {
    that.setData({
      isLoading: false,
      searchStatus: 1,
    });
    wx.hideLoading();
  }
};

var sliderWidth = 96; // 需要设置slider的宽度，用于计算中间位置

Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabs: ["头条", "房源"],
    activeIndex: 1,
    sliderOffset: 0,
    sliderLeft: 0,

    isLoading: false,
    searchStatus: 0,
    pageNum: 1,

    tabData: [[],[]],

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
          sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
        });
      }
    });
    //加载房源收藏列表
    loadMarkedList(this, false, false);
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
    //加载房源收藏列表
    loadMarkedList(this, false, true);
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
    console.log("onPullDownRefresh");
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    console.log("onReachBottom");
    this.setData({
      isLoading: true
    });
    loadMarkedList(this, true, false);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  
  onTabClick: function (e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id,
      isLoading: true
    });
    loadMarkedList(this, false, false);
  },

  onListItemClick: function(e){

    var item = e.currentTarget.dataset.communityItem;
    //将跳转的房源项保存到全局变量中
    app.globalData.communityItem = item
    var url = "";
    if (this.data.activeIndex == 0) {
      var newsId = e.currentTarget.dataset.newsId;
      url = '../newdetail/newdetail?newsId=' + newsId;
    } else {
      var item = e.currentTarget.dataset.communityItem;
      //将跳转的房源项保存到全局变量中
      app.globalData.communityItem = item
      url = '../house_detail/house_detail?communityId=' + item.communityId;
    }
    //跳转至详情页
    wx.navigateTo({
      url: url
    });
  }
})