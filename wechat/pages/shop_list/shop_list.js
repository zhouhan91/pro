//获取应用实例
const app = getApp();
const config = require('../../config');
var footindexzi = config.footindexzi;//获取子分类
var getshoplistsj = config.getshoplistsj;//获取商户列表
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

//获取商户列表
var getshoplist = function (that, reload){
  that.setData({
    isLoading: true,
    loadingStatus: 0,
  });

  wx.showLoading({
    title: "正在处理",
    mask: true
  });

  var activeCateIndex = that.data.activeCateIndex;
  var shopList = that.data.shopList;
  var len = shopList[activeCateIndex].length;
  var pageNum = parseInt(len / 10) + 1;
  if (reload) {
    pageNum = 1;
  }

  var params = {};
  params.cityName=null;             //城市名
  params.districtName = null;       //区县名
  params.parentCategoryCode = that.data.parentCateCode; //分类编码
  params.categoryCode = that.data.cateList[that.data.activeCateIndex].code;       //子分类编码
  params.locationFlag = 'N';       //是否获取到位置
  if (that.data.location) {
    params.longitude = that.data.location.longitude;         //经度
    params.latitude = that.data.location.latitude;          //纬度
  }
  
  console.log('获取商户列表传的参数')
  console.log(params); 
  wx.request({
    url: getshoplistsj + pageNum,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "post",
    data: params,
    success:function(res){
      var data = res.data;
      console.log('商户列表');
      console.log(res.data);
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        var oldLen = shopList[activeCateIndex].length;
        shopList[activeCateIndex] = duplicateRemoval(shopList[activeCateIndex], data.resultData);
        if (reload) {
          oldLen = 0;
          shopList[activeCateIndex] = data.resultData;
        }
        var newLen = shopList[activeCateIndex].length;
        that.setData({
          // orderList: that.data.orderList.concat(data.resultData),
          shopList: shopList,
          isLoading: false,
          loadingStatus: oldLen < newLen ? 1 : 2
        });
        console.log(shopList);
      } else {
        that.setData({
          isLoading: false,
          loadingStatus: 2
        });
      }
      wx.hideLoading();

    },
    fail:function(error){
      that.setData({
        isLoading: false,
        loadingStatus: 2
      });
      wx.hideLoading();
      showModel('操作失败', error);
    }
  });
}
//获取一级导航子分类
var firstfootzi = function (that, hasCate) {
  wx.request({ 
    url: footindexzi + that.data.parentCateId,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('一级导航子分类')
      console.log(data);
      console.log(data.resultData.length);
      if (data.resultData.length==0){
        that.setData({
            distext:true
        });
      }
      if (data.resultCode == "999999" && data.resultData.length>0) {
        var idx = 0;
        var shopList = [];
        data.resultData.forEach(function (item, index) {
          if (hasCate && item.code == that.data.cateCode) {
            idx = index;
          }
          shopList.push([]);
        });

        that.setData({
          cateList: data.resultData,
          activeCateIndex: idx,
          shopList: shopList
        });
        getshoplist(that, true);//获取商户列表
      }
    },
    fail: function (error) {

    }
  })
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    parentCateCode: null,
    parentCateId: null,
    parentCateName: null,
    cateCode: null,
    cateId: null,
    cateName: null,
    distext:false,//如果没有内容显示提示
    cateList:[],//一级菜单子分类
    activeCateIndex:'',//当前展示的二级菜单的下标
    pageNum:1,//当前页码
    isnone: false,//是否显示城市城区框
    shopList: []//商户列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('带入商品列表参数')
    console.log(options);
    wx.showToast({
      title: '加载中',
      icon: 'loading'
    });
    //{parentcode: "canyin", parentid: "1", parentname: "餐饮", code: "chuancai", id: "5", name:"川菜"}
    this.setData({
      parentCateCode: options.parentcode,
      parentCateId: options.parentid,
      parentCateName: options.parentname,

      dist: options.dist,
      city: options.city,
      location: app.globalData.dangqian,
    });


    if (!options.code) {
      firstfootzi(this, false);//获取一级导航子菜单
    } else {
      this.setData({
        cateCode: options.code,
        cateId: options.id,
        cateName: options.name,
      });
      firstfootzi(this, true);//获取一级导航子菜单,跳转到当前子分类
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
    getshoplist(this, false);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },

  /**
  * 显示城区选择
  */
  onGoBackClick: function () {
    this.setData({
      isnone: true
    })
  },

  //点击二级分类列表项，搜索对应商户列表
  onCateItemClick: function (e) {
    var cateIndex = e.currentTarget.dataset.cateIndex;
    this.setData({
      activeCateIndex: cateIndex
    });
    getshoplist(this, true);
  },

  /**
   * 点击跳转对应商户
   */
  onShopItemClick: function (e) {
    var shopItem = e.currentTarget.dataset.item;
    app.globalData.lstCateringDiscount = shopItem.lstCateringDiscount;
    // console.log('../shop_details/shop_details?shopId=' + shopItem.id + "&parentCateName=" + this.data.parentCateName + "&cateName=" + this.data.cateList[this.data.activeCateIndex].name);
    wx.navigateTo({
      url: '../shop_details/shop_details?shopId=' + shopItem.id + "&parentCateName=" + this.data.parentCateName + "&cateName=" + this.data.cateList[this.data.activeCateIndex].name,
    })
  },


  /**
 * 隐藏城区选择
 */
  isnone: function () {
    this.setData({
      isnone: false
    })
  },
  onOkClick: function (e) {
    wx.navigateBack({
      delta:1, // 回退前 delta(默认为1) 页面 
      success: function (res) {
        // success 
      },
      fail: function () {
        // fail 
      },
      complete: function () {
        // complete 
      }
    })
  }
})