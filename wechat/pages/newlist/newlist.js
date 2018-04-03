// pages/error/error.js

//获取应用实例
const app = getApp();
const config = require('../../config');

var getSubNavListUrl = config.newsGetSubNavigationList; //根据一级导航获取二级导航
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
  if (arrA == null || arrA.length == 0) {
    return arrB;
  }

  arrB.forEach(function (itemB, indexB) {
    console.log("----> itemB.id:" + itemB.id);
    var isHave = false;
    arrA.forEach(function (itemA, indexA) {
      if (itemB.id == itemA.id) { //已经存在
        console.log("     ----> itemA.id:" + itemA.id);
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
 * 初始化新闻列表
 */
var initSubNavNewsList = function(that) {
  var subNavNewsList = [];
  var len = that.data.subNavList.length;
  for (var i = 0; i < len; i++) {
    subNavNewsList.push([]);
  }
  that.setData({
    subNavNewsList: subNavNewsList
  });
};

/**
 * 获取二级导航下的新闻列表
 */
var loadSubNavDataList = function(that, subNavCode){
  var dataList = that.data.subNavNewsList[that.data.subNavIndex];
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
        var oldLen = subNavNewsList[that.data.subNavIndex].length;
        subNavNewsList[that.data.subNavIndex] = duplicateRemoval(subNavNewsList[that.data.subNavIndex], data.resultData)
        var newLen = subNavNewsList[that.data.subNavIndex].length;
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

/**
 * 根据一级导航获取二级导航列表
 */
var loadSubNavList = function (that, navCode) {
  console.log(loadSubNavList);
  console.log(navcode);
  wx.showLoading({
    title: "正在加载",
    mask: true
  });

  wx.request({
    url: getSubNavListUrl + navCode,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    data: {
      navigationCode: navCode
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
       
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        that.setData({
          subNavList: data.resultData,
          searchStatus: 1,
        });

        //初始化新闻列表
        initSubNavNewsList(that);

        //默认加载二级导航下的新闻列表
        console.log(data.resultData[that.data.subNavIndex]);
        loadSubNavDataList(that, data.resultData[that.data.subNavIndex].code);

      } else {
        that.setData({
          isLoading: false,
          searchStatus: 2,
        });
        wx.hideLoading();
      }
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
    subNavList:[],  //二级导航列表
    subNavIndex: 0, //当前导航下标

    subNavNewsList:[],  //二级导航下新闻列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.navCode) {
      this.setData({
        navCode: options.navCode,
        subNavCode: options.subNavCode,
      });
    }

    //设置二级导航列表
    if (app.globalData.newsSubNavList) {
      this.setData({
        subNavList: app.globalData.newsSubNavList,
      });


      //设置默认显示的二级导航类目
      var that = this;
      app.globalData.newsSubNavList.forEach(function (item, index) {
        console.log(item);
        if (item.code == options.subNavCode) {
          console.log(index);
          that.setData({
            subNavIndex: index
          });
        }
      });

      //初始化新闻列表
      initSubNavNewsList(this);

      //默认新闻列表
      loadSubNavDataList(this, this.data.subNavList[this.data.subNavIndex].code);
    } else {
      //根据一级导航code获取二级导航列表
      loadSubNavList(this, this.data.navCode);
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
    this.setData({
      isLoading: true
    });
    //加载二级导航下的新闻列表
    loadSubNavDataList(this, this.data.subNavList[this.data.subNavIndex].code);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 点击顶部二级导航事件
   */
  onSubNavItemClick: function(e){
    var navCode = e.currentTarget.dataset.navCode;
    var subNavCode = e.currentTarget.dataset.subNavCode;
    var subNavIndex = e.currentTarget.dataset.subNavIndex;

    this.setData({
      subNavIndex: subNavIndex,  //点击二级导航的下标
    });

    //加载二级导航下的新闻列表
    loadSubNavDataList(this, subNavCode);
  }
})