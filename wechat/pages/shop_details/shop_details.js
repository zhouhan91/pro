//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');
const getDetail = config.getshopdetails;//获取商户主页信息
const getfoodmenu = config.getshopfoodmenu;//获取菜单列表
const getfood = config.getshopfood;//获取菜品列表
const queryCommentListByRestIdUrl = config.queryCommentListByRestIdUrl; //读取店铺评论列表

// 显示成功提示
var showSuccess = text => wx.showToast({
  title: text,
  icon: 'success'
});

//获取商户详情信息
var getshopdetail=function(that){
  console.log(that.data.shopId)
  wx.request({
    url: getDetail + that.data.shopId,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('商户详情')
      console.log(data);
      if (data.resultCode == "999999" && data.resultData) {
        that.setData({
          shopdetail: data.resultData,
        })
      }
    },
    fail: function (error) {

    }
  })
}

//获取商户评价信息
var loadRestCommentList = function (that, reload) {
  console.log(that.data.shopId)
  that.setData({
    isLoading: true,
    loadingStatus: 0,
  });

  wx.showLoading({
    title: "正在加载",
    mask: true
  });

  var restCommentList = that.data.restCommentList;
  var len = restCommentList.length;
  var pageNum = parseInt(len / 10) + 1;
  if (reload) {
    pageNum = 1;
  }

  wx.request({
    url: queryCommentListByRestIdUrl + that.data.shopId + "/" + pageNum,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('商户评价列表')
      console.log(data);
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        var oldLen = len;
        restCommentList = duplicateRemoval(restCommentList, data.resultData);
        if (reload) {
          oldLen = 0;
          restCommentList = data.resultData;
        }
        var newLen = restCommentList.length;
        that.setData({
          restCommentList: restCommentList,
          isLoading: false,
          loadingStatus: oldLen < newLen ? 1 : 2
        });
      } else {
        that.setData({
          isLoading: false,
          loadingStatus: 2
        });
      }
      wx.hideLoading();
    },
    fail: function (error) {
      that.setData({
        isLoading: false,
        loadingStatus: 2
      });
      wx.hideLoading();
      showModel('操作失败', error);
    }
  })
};

//获取菜单列表
var getfoodlist=function(that){
  wx.request({
    url: getfoodmenu + that.data.shopId,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('菜单列表')
      console.log(data.resultData);
      if (data.resultCode == "999999" && data.resultData) {
        that.setData({
          foodmenulist: data.resultData,
          foodId: data.resultData[0].id,
          suoding: true,
        })
        getfoods(that, that.data.foodId, that.data.pageNum);//获取第一个菜品列表
      }
    },
    fail: function (error) {

    }
  })
}
//获取菜品列表
var getfoods=function(that,foodmenuid,page){
  console.log(page)
  wx.request({
    url: getfood + foodmenuid + '/' + that.data.foodstatus + '/' + page,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('菜品列表')
      console.log(data);
      if (data.resultCode == "999999" && data.resultData && that.data.suoding) {
        var restCommentList = duplicateRemoval(that.data.foodlist, data.resultData);
        console.log('现在的')
        console.log(restCommentList)
        for (var i = 0; i < data.resultData.length; i++) {
          data.resultData[i].fen = 0;
        }  
         that.setData({
           foodlist: restCommentList,
           suoding:false,
         })
      }
    },
    fail: function (error) {

    }
  })
}
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

/**
 * 获取店铺评价列表
 */
var load

Page({

  /**
   * 页面的初始数据
   */
  data: {
    foodId:null,//菜单ID
    foodmenulist:[],//菜单列表
    winWidth: 0,
    winHeight: 0,
    // tab切换  
    currentTab: 0,
    pageNum:1,//当前页
    shopId:'',//商户ID
    foodstatus: 'Y',//商品分类状态all：所有Y：已上架N：未上架
    foodlist:[],//菜品列表
    shopdetail:{},//商户详情
   foodlistshu:[],//存
   foodlistqu:[],//取
   displaycar:false,//显示购物车红点及图片
   carnub:0,//购物车数量
   foodmoney:0,//显示价格
   carbox:false,//购物车弹出层
   restCommentList: [],//店铺评价列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    this.setData({
      shopId: options.shopId,
      parentCateName: options.parentCateName,
      cateName: options.cateName
    });
    //测试
    // this.setData({
    //   shopId: 2,
    //   parentCateName: "餐饮",
    //   cateName: "川菜"
    // });
    getshopdetail(this); //获取商户详情
    loadRestCommentList(this, true);// 加载商铺评价列表
    getfoodlist(this); //获取菜单列表
    wx.showToast({
      title: '加载中',
      icon: 'loading'
    });
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
  },
     /** 
     * 拨打电话 
     */
  bdphone:function(){
    wx.makePhoneCall({
      phoneNumber: this.data.shopdetail.managerPhone //仅为示例，并非真实的电话号码
    })
  },
    /** 
     * 切换菜单 
     */
  onfoodlist:function(e){
    var that = this;
    var foodmenulist = e.currentTarget.dataset.foodMenuid;
    console.log('菜单ID');
    console.log(foodmenulist);
    var cuchu = [];
    for (var i = 0; i < that.data.foodlistshu.length;i++){ 
      if (that.data.foodlistshu[i].categoryId==foodmenulist){
        cuchu.push(that.data.foodlistshu[i]);
      }
    }
    that.setData({
      foodlistqu: cuchu,
      suoding: true,
      foodlist:[],
    })
    console.log(that.data.foodmenulist)
    console.log(that.data.foodlistqu);
    if (that.data.foodlistqu.length>0){
      console.log('当前的数据');
      console.log(that.data.foodlistqu);
      that.setData({
        foodlist: that.data.foodlistqu
      })
    }else{
      getfoods(that, foodmenulist,that.data.pageNum)//根据菜单ID获取菜品
    }  
    that.setData({
      foodId: foodmenulist
    })
    
  },
  /** 
     * 滑动切换tab 
     */
  bindChange: function (e) {
    var that = this;
    that.setData({ currentTab: e.detail.current });
    console.log(e.detail.current)
  },
 /*** 添加商品，并到购物车计算金额*/
  addfoodlist(e){
    var that = this;
    if (that.data.shopdetail.pauseFlag == 'N') {
      console.log('暂停接单')
      return false;
    }
    var categoryid = e.currentTarget.dataset.cateGoryid;//菜单ID
    var thisindex = e.currentTarget.dataset.parentIndex;//当前的菜品在菜单的下标
    var up = "foodlist[" + thisindex + "].fen";
    that.setData({
      [up]: that.data.foodlist[thisindex].fen+1, 
      foodlistcucun: that.data.foodlist[thisindex],//把当前点击的菜单下面的菜品数量保存
      foodlistshu: duplicateRemoval(that.data.foodlist, that.data.foodlistshu)
    })
 
    console.log(categoryid);//菜单ID
    console.log(that.data.foodlist[thisindex].fen);//当前菜品的份数
    console.log(that.data.foodlistcucun);
    console.log('购物车清单');
    console.log(that.data.foodlistshu); 
    var fennub = 0;//数量
    var foodmy = 0;//价格
    for (var i = 0; i < that.data.foodlistshu.length;i++){
      fennub += that.data.foodlistshu[i].fen; 
      if (that.data.foodlistshu[i].fen>0){
        var sl = that.data.foodlistshu[i].discountPrice * that.data.foodlistshu[i].fen;
        foodmy += sl;
        that.setData({
          displaycar:true
        });
      }
    }
    console.log(fennub)
    console.log(foodmy);
    that.setData({
      carnub: fennub,//记录当前份数
      foodmoney: foodmy.toFixed(2)//当前价格
    });
  },
  /***减去并计算金额和数量*/
  deletefoodlist(e){
    var that = this;
    var categoryid = e.currentTarget.dataset.cateGoryid;//菜单ID
    var thisindex = e.currentTarget.dataset.parentIndex;//当前的菜品在菜单的下标
    var up = "foodlist[" + thisindex + "].fen";
    that.setData({
      [up]: that.data.foodlist[thisindex].fen - 1,
      foodlistcucun: that.data.foodlist[thisindex],//把当前点击的菜单下面的菜品数量保存
      foodlistshu:duplicateRemoval(that.data.foodlist, that.data.foodlistshu)
    })
    console.log(categoryid);//菜单ID
    console.log(that.data.foodlist[thisindex].fen);//当前菜品的份数
    console.log(that.data.foodlistcucun);
    console.log('购物车清单');
    console.log(that.data.foodlistshu);  
    that.setData({
      carnub: that.data.carnub-1,
      foodmoney: (that.data.foodmoney - that.data.foodlist[thisindex].discountPrice).toFixed(2)
    })
    if (that.data.carnub==0){
      that.setData({
        displaycar: false
      })
    }
  },
     /** 
   * 清空购物车 
   */
  clearcar:function(){
    var that=this;
    that.setData({
      foodlistshu:[],
      carnub: 0,
      foodmoney: 0,
    })
    var cleararr=[]
    for (var i = 0; i < that.data.foodlist.length;i++){
      that.data.foodlist[i].fen=0;
      cleararr.push(that.data.foodlist[i]);
    }
    console.log('清空后的数组')
    console.log(cleararr)
    that.setData({
      foodlist: cleararr,
      displaycar: false,
      carbox:false,
    })
  },
   /** 
   * 弹出购物车 
   */
  discarbox:function(){
     var that=this;
     var fan = !that.data.carbox;
     that.setData({
       carbox: fan
     }) 
     console.log(that.data.foodlistshu)
  },
     /** 
   * 收起购物车 
   */
  nonecarbox:function() {
    var that = this;
    var fan = !that.data.carbox;
    that.setData({
      carbox: fan
    })
  },
  /** 
   * 点击tab切换 
   */
  swichNav: function (e) {

    var that = this;

    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },
    /** 
   * 下拉加载菜单 
   */
  loadlist:function(){
    var that=this;
    console.log('已到底部')
    if (that.data.foodlist.length==10){
      var page = that.data.pageNum + 1;
    } else if (that.data.foodlist.length == 20){
      var page = that.data.pageNum + 2;
    } else if (that.data.foodlist.length == 30){
      var page = that.data.pageNum + 3;
    }
    getfoods(that, that.data.foodId, page);
    that.setData({
      suoding:true,
    })
  },
    /**
   * 订单结算
   */
  goorder:function(){
    var that=this;
    app.globalData.orderfood = that.data.foodlistshu;
    app.globalData.foodmoney = that.data.foodmoney;
    console.log(app.globalData);

    

    qcloud.login({
      success(result) {
        that.setData({
          sess_id: result.sess_id
        });
        app.globalData.userInfo = result.userInfo;
        //获取好吃订单列表
        wx.request({
          url: config.checkNewMemberUrl + that.data.sess_id + "/" + that.data.shopId,
          header: {
            'content-type': 'application/json;charset=UTF-8'
          },
          method: "GET",
          success: function (res) {
            var data = res.data;
            if (data.resultCode == "999999") {
              var isNew = data.resultData;
              app.globalData.isNew = data.resultData == "Y" ? true : false;
            } else {
              app.globalData.isNew = false;
            }

            wx.request({
              url: config.getshopdetails + that.data.shopdetail.id,
              header: {
                'content-type': 'application/json;charset=UTF-8'
              },
              method: "GET",
              success: function (res) {
                var data = res.data;
                app.globalData.shopdetail = data.resultData;
                wx.hideLoading();
                wx.navigateTo({
                  url: '../foot_order/foot_order?shopname=' + that.data.shopdetail.name + '&shopId=' + that.data.shopdetail.id,
                })
              },
              fail: function (error) {

              }
            })

            
          },
          fail: function (error) {
            app.globalData.isNew = false;
            /*that.setData({
              isNew: false,
            });*/
            wx.hideLoading();
            showModel('操作失败', error);
          },
        });
      },
      fail(error) {
        that.setData({
          isNew: false,
        });
        wx.hideLoading();
        showModel('登录失败', error);
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
    loadRestCommentList(this, false);// 加载商铺评价列表
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
 
})