//获取应用实例
const app = getApp();
const config = require('../../config');
var footindex = config.footindex;//获取一级导航
var footindexzi = config.footindexzi;//获取子分类
var getfootbanner = config.getfootbanner;//获取广告
var getshoplistURL=config.getshoplist//获取首页商户

var QQMapWX = require('../common/qqmap-wx-jssdk.js');
var qqmapsdk;

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


var getGeoInfo = function (that, location) {
  console.log(location);
  if (location){
    // 调用接口
    qqmapsdk.reverseGeocoder({
      location: location,
      success: function (res) {
        console.log(res);
        if (res.status == 0) {
          var address_component = res.result.address_component;
          console.log(address_component);
          that.setData({
            address_component: res.result.address_component,
          });
        }
      },
      fail: function (res) {
        console.log(res);
      },
      complete: function (res) {
        console.log(res);
      }
    });
  }
};

var getGeoInfoWithGoogle = function (that, location) {
  console.log(location);
  if (location) {
    // 调用接口
    wx.request({
      url: "https://hkservice.wemecity.net/components/googleMap/geoCode/latlng/" + location.longitude + "/" + location.latitude,
      method:"GET",
      success: function(res){
        console.log(res.data);
        if (res.data.resultCode == '999999') {
          var jsonStr = res.data.resultData;
          var results = null;
          jsonStr = jsonStr.replace(" ", "");
          jsonStr = jsonStr.replace("\n", "");
          console.log(jsonStr);
          if (typeof jsonStr != 'object') {
            jsonStr = jsonStr.replace(/\ufeff/g, "");//重点
            var jj = JSON.parse(jsonStr);
            results = jj.results;
          }
          console.log(results[1]);
          console.log(results.length);
          var address_component = null;
          if (results.length > 1 && results[0].address_components.length > 3) {
            if (results.length > 2) {
              address_component = results[1].address_components;
            } else {
              address_component = results[0].address_components;
            }
            address_component = results[0].address_components;
            console.log(address_component);
            that.setData({
              address_component: address_component,
            });
            //获取推荐商户
            // getshoplist(that, address_component[2].short_name, true);
          } else {
            showModel("系统提示", "定位失败");
          }
          
        }
      },
      fail: function(err){

      }
    })
  }
};

//获取一级导航
var foottop=function(that){
  wx.request({
    url: footindex,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res){
      var data = res.data;
      console.log('一级导航')
      console.log(data);    
      if (data.resultCode == "999999" && data.resultData) {
         that.setData({
           firstfoot: data.resultData
         })
      }
      //获取一级导航子分类
      firstfootzifen(that);
    },
    fail: function (error) {

    }
  })
};
//获取一级导航子分类
var firstfootzifen=function(that){
  wx.request({
    url: footindexzi + that.data.firstfoot[0].id,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('一级导航子分类')
      console.log(data);
      if (data.resultCode == "999999" && data.resultData) {
        that.setData({
          firstfootzi: data.resultData
        });
      }
    },
    fail: function (error) {

    }
  })
};

//获取推荐商户
var getshoplist = function (that, cityName, reload){
  wx.showLoading({
    title: "正在加载",
    mask: true
  });

  var indexshop = that.data.indexshop;
  var len = indexshop.length;
  var pageNum = parseInt(len / 10) + 1;
  if (reload) {
    pageNum = 1;
  }

  wx.request({
    url: getshoplistURL + '/' + that.data.pageNum + '?cityName=' + cityName,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success:function(res){
      var data=res.data;
      console.log('获取推荐的商户')
      console.log(data);
      if (data.resultCode == "999999" && data.resultData && data.resultData.length > 0) {
        var oldLen = indexshop.length;
        
        if (reload) {
          indexshop = data.resultData;
        } else {
          indexshop = duplicateRemoval(indexshop, data.resultData);
        }
        var newLen = indexshop.length;
        
        that.setData({
          indexshop: indexshop,
          isLoading: false,
          searchStatus: oldLen < newLen ? 1 : 2,
        });
      } else {
        showModel("操作失败", data.resultDesc);
      }
      wx.hideLoading();
    },
    fail: function (error) {
      wx.hideLoading();
      showModel("操作失败", error);
    }
  })
}

//获取首页banner
var getbanner=function(that){
  wx.request({
    url: getfootbanner+'/'+'巴黎',
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('广告图')
      console.log(data);
    
      if (data.resultCode == "999999" && data.resultData && data.resultData.length!=0) {
        that.setData({
         // banner: data.resultData
        });
      }
    },
    fail: function (error) {

    }
  })
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    firstfoot:[],//一级菜单
    firstfootzi: [],//一级菜单子分类
    pageNum:1,//页码
    isnone:false,//是否显示城市城区框
    banner:  //商户广告
        [
          {
            "id":25,
            "cityCode":"PARIS",
            "cityName": '巴黎',
            "districtCode": '1em',
            "districtName": '1em-区',
            "title": '广告名字',
            "goodsName": '甜甜圈',
            "img": '../resource/kind/guanggao.jpg',
            "price":258,
            "url": '',
            "status": 'Y',
            "sortNum": '',
            "isDeleted": '',
            "createBy": '',
            "createTime": '',
            "modifyBy": '',
            "modifyTime": '',
          }
        ],
    //首页推荐商户列表
    bannershop:[
      {
        "id": 91,
        "lstCateringDiscount": [],
        "parentCateName": '餐饮',
        "cateName": '川湘菜',
        "cityCode": "PARIS",
        "cityName": '巴黎',
        "districtCode": '1em',
        "districtName": '1em-区',
        "title": '广告名字',
        "goodsName": '俄罗斯烤肉',
        "img": '../resource/kind/guanggao1.jpg',
        "price": 258,
        "url": '',
        "status": 'Y',
        "sortNum": '',
        "isDeleted": '',
        "createBy": '',
        "createTime": '',
        "modifyBy": '',
        "modifyTime": '',
      },
      {
        "id": 53,
        "parentCateName": '餐饮',
        "lstCateringDiscount": [],
        "cateName": '川湘菜',
        "cityCode": "PARIS",
        "address": " 13 Rue Philibert Lucot, 75013 Paris",
        "categoryCode": "fenmian",
        "cityName": '巴黎',
        "countryName": "法国",
        "createBy": 20,
        "createTime": "2018-02-09 13:06:26",
        "parentCategoryCode": "canyin",
        "countryCode": "France",
        "districtCode": '1em',
        "districtName": '1em-区',
        "title": '广告名字',
        "goodsName": '俄罗斯烤肉',
        "img": '../resource/kind/guanggao2.jpg',
        "price": 258,
        "status": "20"
      },
    ],
    indexshop: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showToast({
      title: '加载中',
      icon: 'loading'
    });
    //获取一级分类
    foottop(this); 
    //获取广告
    getbanner(this);
 
    console.log('这是当前位置');
    console.log(app.globalData.dangqian);
    var that = this;
    if (!app.globalData.dangqian) {
      //获取当前位置
      wx.getLocation({
        type: 'wgs84',
        success: function (res) {
          //将当前位置保存到全局变量
          app.globalData.dangqian = res;
          getGeoInfoWithGoogle(that, app.globalData.dangqian);
        }
      });
    } else {
      getGeoInfoWithGoogle(this, app.globalData.dangqian);
    }
    //实例化API核心类
    // qqmapsdk = new QQMapWX({
    //   key: 'TAHBZ-UZOKU-EVZVY-BSEKO-OPHC7-PUBCM'
    // });
    // getGeoInfo(this, app.globalData.dangqian);
    //获取推荐商户
    if (this.data.address_component && this.data.address_component.lenght > 2) {
      // getshoplist(that, address_component[2].short_name, true);
      getshoplist(that, "", true);
    } else {
      getshoplist(that, "", true);
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
 
  },
  /**
   * 显示城区选择
   */
  onGoBackClick: function () {
    this.setData({
      isnone:true
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
    //获取推荐商户
    getshoplist(this, this.data.address_component[2].short_name, false);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },

  /**
   * 点击推荐商家，跳转到商家首页
   */
  onShopItemClick: function (e) {
    var shopItem = e.currentTarget.dataset.item;
    app.globalData.lstCateringDiscount = shopItem.lstCateringDiscount;
    wx.navigateTo({
      url: '../shop_details/shop_details?shopId=' + shopItem.id,
    })
  },
})