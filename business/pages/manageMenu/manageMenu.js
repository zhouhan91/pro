//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

const queryGoodsCateListUrl = config.queryGoodsCateListUrl; //根据店铺id获取商品分类信息
const queryGoodsListUrl = config.queryGoodsListUrl;         //根据店铺内商品分类获取商品列表
const deleteGoodsUrl = config.deleteGoodsUrl;    //删除商品
const putOnLineUrl = config.putOnLineUrl;       //上架商品
const putOffLineUrl = config.putOffLineUrl;     //下架商品
const clearStockUrl = config.clearStockUrl;     //估清商品


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
};


//加载分类下菜品列表
var loadGoodsList = function (that, isRefrush){
  var currentTab = that.data.currentTab;
  var goodsCategoryId = that.data.goodsCateList[that.data.currentTab].id;
  console.log(goodsCategoryId)
  var goodsListItem = [];
  var oldLen = 0;
  var pageNum = 1;
  if (!isRefrush) {
    goodsListItem = that.data.goodsList[that.data.currentTab];
    pageNum = parseInt(goodsListItem.length / 10) + 1;
    oldLen = goodsListItem.length;
  }

  var status = "all";
  wx.request({
    url: queryGoodsListUrl + goodsCategoryId + "/" + status + "/" + pageNum,
    method:"GET",
    success: function(res){
      console.log('我是分类菜品')
      console.log(res.data);
      if (res.data.resultCode == "999999" && res.data.resultData.length > 0) {
        if (isRefrush) {
          goodsListItem = res.data.resultData;
        } else {
          console.log("debug")
          goodsListItem = duplicateRemoval(goodsListItem, res.data.resultData);
        }
        var newLen = goodsListItem.length;
        var goodsList = that.data.goodsList;
        goodsList[that.data.currentTab] = goodsListItem;
        that.setData({
          goodsList: goodsList,
          isLoading: false,
          loadStatus: oldLen < newLen ? 1 : 2,
        });
      } else {
        // var goodsList = that.data.goodsList;
        // goodsList[that.data.currentTab] = goodsListItem;
        that.setData({
         oodsList: goodsList,
          isLoading: false,
          loadStatus: 2,
        });
      }
    },
    fail: function(err){
      that.setData({
        isLoading: false,
        loadStatus: 2,
      });
    }
  })
};

//加载菜品分类列表
var loadGoodsCateList = function (that) {
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  if (that.data.restaurantId) {
    wx.request({
      url: queryGoodsCateListUrl + that.data.restaurantId,
      method: "GET",
      success: function (res) {
        console.log(res.data);
        if (res.data.resultCode == "999999") {
          if (res.data.resultData && res.data.resultData.length > 0) {
            that.setData({
              goodsCateList: res.data.resultData,
              currCateIndex: -1
            });
            //分类列表长度发生变化时，重置菜品列表
            if (that.data.goodsCateList.length != that.data.goodsList.length) {
              var goodsList = [];
              for (var i = 0; i < that.data.goodsCateList.length; i++){
                // console.log(i);
                goodsList.push([]);
              }
              that.setData({
                goodsList: goodsList,
                currentTab: 0,
              });
              
            }
            //获取当前tab下的菜品列表（默认重置时currentTab=0）
            loadGoodsList(that, true);

            wx.hideLoading();
          } else {
            wx.hideLoading();
            showModel('系统提示', "没有菜品信息");
          }
        } else {
          wx.hideLoading();
          showModel('系统提示', res.data.resultDesc);
        }
      },
      fail: function (err) {
        wx.hideLoading();
        showModel('系统提示', "操作失败");
      }
    });
  } else {
    wx.hideLoading();
  }
};

//删除菜品
var delGoodsItem = function(that, goods){
  wx.request({
    url: deleteGoodsUrl + goods.id + "/" + that.data.managerId,
    data: {
      goodsId: goods.id,
      managerId: that.data.managerId
    },
    method: "POST",
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        //删除成功，重新加载列表
        loadGoodsList(that, true);
      }
    },
    fail: function(err){

    }
  })
};

//上架菜品
var upGoodsItem = function(that, goodsId){
  wx.request({
    url: putOnLineUrl + goodsId + "/" + that.data.managerId,
    data: {
      goodsId: goodsId,
      managerId: that.data.managerId
    },
    method: "POST",
    success: function(res){
      console.log(res.data);
      if(res.data.resultCode == "999999"){
        //上架成功，更新列表状态值
        var goodsList = that.data.goodsList;
        var currGoodsList = goodsList[that.data.currentTab];
        //上架成功，更新列表状态值
        currGoodsList.forEach(function (item, idx) {
          if (item.id == goodsId) {
            item.status = "Y";
            // break;
          }
        });
        goodsList[that.data.currentTab] = currGoodsList;
        that.setData({
          goodsList: goodsList
        });
      }
    },
    fail: function(err){

    }
  });
};

//下架菜品
var downGoodsItem = function(that, goodsId) {
  wx.request({
    url: putOffLineUrl + goodsId + "/" + that.data.managerId,
    data: {
      goodsId: goodsId,
      managerId: that.data.managerId
    },
    method: "POST",
    success: function (res) {
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        //上架成功，更新列表状态值
        var goodsList = that.data.goodsList;
        var currGoodsList = goodsList[that.data.currentTab];
        //上架成功，更新列表状态值
        currGoodsList.forEach(function (item, idx) {
          if (item.id == goodsId) {
            item.status = "N";
            // break;
          }
        });
        goodsList[that.data.currentTab] = currGoodsList;
        that.setData({
          goodsList: goodsList
        });
      }
    },
    fail: function (err) {

    }
  });
};

//估清菜品
var clearGoodsItem = function(that, goodsId){
  wx.request({
    url: clearStockUrl + goodsId + "/" + that.data.managerId,
    data: {
      goodsId: goodsId,
      managerId: that.data.managerId
    },
    method: "POST",
    success: function (res) {
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        var goodsList = that.data.goodsList;
        var currGoodsList = goodsList[that.data.currentTab];
        //上架成功，更新列表状态值
        currGoodsList.forEach(function(item, idx){
          if(item.id == goodsId) {
            item.stock = 0;
            // break;
          }
        });
        goodsList[that.data.currentTab] = currGoodsList;
        that.setData({
          goodsList: goodsList
        });
      }
    },
    fail: function (err) {

    }
  });
}



Page({

  /**
   * 页面的初始数据
   */
  data: {
    winWidth: 0,
    winHeight: 0,
    // tab切换  
    currentTab: 0,   //当前菜品下标
    loadStatus: 0,    //加载状态值，0,默认暂未加载；1,加载完成且有数据；2,加载完成且没有数据
    goodsCateList: [], //菜品列表
    goodsList:[],     //分类下的商品列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;

    /** 
     * 获取系统信息 
     */
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    }); 

    if (options.restaurantId) {
      this.setData({
        restaurantId: options.restaurantId,
        managerId: options.managerId
      });

      //加载店铺菜品列表
      loadGoodsCateList(this);
    } else {
      //测试
      this.setData({
        restaurantId: 2,
        managerId: 4,
      });
      //加载店铺菜品列表
      loadGoodsCateList(this);
    } 
  },
 
  /** 
   * 点击tab切换 
   */
  swichNav: function (e) {

    var that = this;

    console.log(e.target.dataset.current);
    console.log(that.data.currentTab);
    if (that.data.currentTab === e.target.dataset.current) {
      console.log('走了这里')
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
    loadGoodsList(that, true);
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
    //加载店铺菜品列表
    loadGoodsCateList(this);
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
    //加载当前分类的商品列表
    loadGoodsList(this, false);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },

  /**
   * 点击 菜品管理 进入菜品管理页
   */
  onManageFoodClick: function(e){
    wx.navigateTo({
      url: '../manageFond/manageFond?restaurantId=' + this.data.restaurantId + "&managerId=" + this.data.managerId,
    });
  },

  /**
   * 点击 添加新材 进入添加菜品页
   */
  onAddFoodClick: function(e){
    var categoryId = this.data.goodsCateList[this.data.currentTab];
    console.log("onAddFoodClick");
    // console.log(this.data.restaurantId);  //2
    console.log(this.data.managerId);   //4
    wx.navigateTo({
      url: '../addFond/addFond?restaurantId=' + this.data.restaurantId + "&managerId=" + this.data.managerId + "&isAdd=1",
    });
  },

  /**
   * 点击修改菜品，跳转到菜品详情页
   */
  onUpdGoodsClick: function(e){
    var goods = e.currentTarget.dataset.goods;
    wx.navigateTo({
      url: '../addFond/addFond?id=' + goods.id + "&restaurantId=" + this.data.restaurantId + "&managerId=" + this.data.managerId + "&isAdd=0",
    })
  },

  /**
   * 点击删除菜品，弹窗确认
   */
  onDelGoodsClick: function(e){
    var goods = e.currentTarget.dataset.goods;
    var that = this;
    wx.showModal({
      title: "系统提示",
      content: "确认删除菜品：" + goods.name,
      showCancel: true,
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          //删除菜品
          delGoodsItem(that, goods);
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      },
      fail: function(err){

      }
    });
  },

  /**
   * 上架开关
   */
  switchUpChange: function(e){
    var upStatus = e.detail.value;
    var goods = e.currentTarget.dataset.goods;
    if (upStatus) {
      //菜品上架
      upGoodsItem(this, goods.id);
    } else {
      //菜品下架
      downGoodsItem(this, goods.id);
    }
  },

  /**
   * 估清开关
   */
  switchClearChange: function(e){
    //待确认字段
    var clearStatus = e.detail.value;
    var goods = e.currentTarget.dataset.goods;
    if (clearStatus) {
      //菜品估清，更新列表商品库存为0
      clearGoodsItem(this, goods.id);
    }
  }
})