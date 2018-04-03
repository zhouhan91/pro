// pages/error/error.js

//获取应用实例
const app = getApp();
const config = require('../../config');

var getNavListUrl = config.newsGetNavigations; //获取头条一级导航栏
var getSubNavListUrl = config.newsGetSubNavigationList; //根据一级导航获取二级导航
var getIndexListUrl = config.newsGetIndexList;  //获取首页新闻信息，返回3条

var getSubNavNewsListUrl = config.newsGetNewsList;  //获取二级导航下的新闻列表
var queryBabietaListUrl = config.newsQueryBabietaList;  //获取巴别塔列表

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
};
 

/**
 * 加载首页新闻信息，每个子导航首页有3条新闻
 */
var indexNewsList = function (that, subNavList){
  var subNavIndex = that.data.subNavIndex;
  var activeIndex = that.data.activeIndex;
  var pageNum = that.data.pageNum+1;
  console.log('二级导航名字是'); 
  console.log(pageNum);
  console.log(that.data.pages);
  if (subNavList){
    var url = "";
    if (subNavList != "babieta") {
      url = getSubNavNewsListUrl + subNavList + '/' + pageNum;
    } else {
      url = queryBabietaListUrl + pageNum;
    }
    wx.request({
      url: url,
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      method: "GET",
      success: function (res) {
        var data = res.data;
        if (data.resultCode == "999999" && data.resultData.length > 0) {
          var subNavDataList = that.data.subNavDataList;
          var bbtNavDataList = that.data.subNavDataList;
  
          var babielist = [];
          var babielists=[];
   
          for (var i = 0; i < data.resultData.length; i++) {
            if (data.resultData[i].navigationCode == 'babieta') {
              babielist.push(data.resultData[i]);
              bbtNavDataList[that.data.activeIndex] = duplicateRemoval(that.data.firstlist, data.resultData);
              console.log('巴别塔');
              console.log(bbtNavDataList[that.data.activeIndex]);
              that.setData({
                hostguo: true, 
                firstlist: bbtNavDataList[that.data.activeIndex],
              })
            
            } 
           
            if (data.resultData[i].navigationCode != 'babieta') {
              that.setData({
                hostguo: false,
              })
            }
          } 
          subNavDataList[that.data.activeIndex] = duplicateRemoval(subNavDataList[that.data.activeIndex], data.resultData);       
          that.setData({
            pages: data.pages,
            pageNum: pageNum,
            newslist: subNavDataList[that.data.activeIndex],

            subNavDataList: subNavDataList,
            isLoading: false,
            searchStatus: pageNum < that.data.pages ? 1 : 2,
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
          subNavIndex: subNavIndex + 1,
          isLoading: false,
          searchStatus: 2,
        });    
        wx.hideLoading();
        showModel('操作失败', error); 
      },
    });
  } 
};

/*** 根据一级导航获取二级导航列表 */
var loadSubNavList = function(that, navCode){
  wx.showLoading({
    title: "正在加载",
    mask: true
  });
  wx.request({
    url: getSubNavListUrl + navCode,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;  
      console.log('子栏目目录');
      console.log(data.resultData);  
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        if (navCode != 'babieta' && navCode != 'lanmu') {
          that.setData({
            hostguo: false,
            lanmudisplay: false,
            zlbox:true,
          })
          console.log('这不是巴别塔！！！');
          console.log(that.data.hostguo)
          console.log(navCode);
        } else if (navCode == 'babieta'){
          that.setData({
            hostguo: true,
            lanmudisplay:false,
            zlbox:false,
          })
        }
        if (navCode=='lanmu'){
          that.setData({
            lanmu: data.resultData,
            lanmudisplay:true,
            hostguo:false,
            zlbox:false,
          })
          console.log('栏目的内容');
          console.log(that.data.lanmu);
        }

        var subNavList = that.data.subNavList;       
        subNavList[that.data.activeIndex] = data.resultData;
        that.setData({
          subNavList: subNavList,
          searchStatus: 1,
        });
        indexNewsList(that, navCode);
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

/**
 * 获取一级导航列表
 */
var loadNavList = function(that){
  wx.showLoading({
    title: "正在加载",
    mask: true
  });

  wx.request({
    url: getNavListUrl,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('主新闻内容');
      console.log(data);
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        var subNavList = [];
        var subNavDataList = [];
        var onepush=[];
        for (var i = 0; i < data.resultData.length; i++){
          subNavList.push([]);
          subNavDataList.push([]);
          onepush.push(data.resultData[i].code);
        };
        console.log(onepush);
        that.setData({
          onepush:onepush,
          navList: data.resultData,
          subNavList: subNavList,
          subNavDataList: subNavDataList,
          activeIndex: 0,
          isLoading: false,
          searchStatus: 1,
        });
        console.log(that.data.subNavList);

        //默认加载第一个栏目的子栏目
        loadSubNavList(that, data.resultData[0].code);
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
    navList:[],   //一级导航列表
    activeIndex: -1, //一级导航列表被选中的下标
    subNavList:[],  //二级导航列表
    pageNum:0,//当前页码
    subNavIndex: 0, //加载每个子导航的下标
    isLoading: false, //正在加载
    pages:1,//总页码
    searchStatus: 0,  //请求状态，0，初始状态；1，有数据返回；2，没有数据返回
    firstlist:[],//巴比塔新闻列表hostguo
    hostguo:true,//巴别塔内容
    lanmu:[],//栏目二级导航
    lanmudisplay:false,//栏目的内容
    newslist:[],//指南专栏内容
    isdiplayload:true,//是否显示加载
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //1，加载一级导航列表，默认第一个被选中
  
    loadNavList(this);
    //2，默认加载第一个一级导航列表下的二级导航列表
    //3，默认加载第一个一级导航下各二级导航下的三条新闻
  },
     
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function (options) {
    this.setData({
      isLoading: true,
      isdiplayload: true,
    })
    //loadNavList(this);返回新闻第一个栏目

    //loadSubNavList(this, 'babieta');
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
      isLoading: true,
    });

    indexNewsList(this,this.data.onepush[this.data.activeIndex])
    //加载二级导航下的新闻列表
 
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 一级导航列表项点击事件
   */
  onNavItemClick: function(e){
    wx.pageScrollTo({
      scrollTop: 0
    })
    console.log('一级导航内容');
    var navCode = e.currentTarget.dataset.navCode; //一级导航的值
    var navIndex = e.currentTarget.dataset.navIndex; //一级导航的下标
    console.log(navCode); 
    console.log(navIndex);
    if (navCode=='lanmu'){
      this.setData({
        isdiplayload:false,
      })
    }else{
      this.setData({
        isdiplayload: true,
      })
    }
    this.setData({
      pageNum:0,//清除页码
      activeIndex: navIndex,  //当前被选中的一级导航下标
      subNavIndex: 0,  //遍历二级导航的下标，每次点击一级导航时置0
    });

    //加载二级导航列表，以及各二级导航新闻3条
    loadSubNavList(this, navCode);
  },

  /**
   * 点击二级导航，跳转到新闻列表页
   */
  onSubNavItemClick: function(e){
    
    var navCode = e.currentTarget.dataset.navCode;
    var subNavCode = e.currentTarget.dataset.subNavCode;
    var subNavList = this.data.subNavList[this.data.activeIndex];
    var erlist=[]
    for (var i = 0; i < this.data.lanmu.length;i++){
      erlist.push(this.data.lanmu[i].code)
    }
    app.globalData.newsSubNavList = subNavList;
    console.log('列表内容');
    console.log(navCode);
    console.log(subNavCode);
    wx.navigateTo({
      url: '../newlist/newlist?navCode=' + navCode + '&subNavCode=' + subNavCode,
    })
  }
})