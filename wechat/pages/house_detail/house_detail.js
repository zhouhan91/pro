// pages/house_detail/house_detail.js
//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');
// var QQMapWX = require('../common/qqmap-wx-jssdk.js');
// var qqmapsdk;

var communityDetailUrl = config.idxCommunityDetailUrl;  //读取房源详情
var communityCheckMarkUrl = config.idxCommunityCheckMarkUrl;  //检查是否搜藏此房源
var communityMarkUrl = config.idxCommunityMarkUrl;  //搜藏此房源
var communityCancelMarkUrl = config.idxCommunityCancelMarkUrl; //取消收藏

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

//收藏房源方法
var communityMarked = function (that, communityId, doType){
  var doUrl = null;
  if (doType == 1) { //检查搜藏
    doUrl = communityCheckMarkUrl;
  } else if (doType == 2) { //搜藏
    doUrl = communityMarkUrl;
  } else if (doType == 3) { //取消搜藏
    doUrl = communityCancelMarkUrl;
  }
  qcloud.login({
    success(result) {
      that.setData({
        userInfo: result.userInfo,
        sess_id: result.sess_id
      });

      var params = "?communityId=" + communityId + "&userKey=" + result.sess_id;
      //检查是否收藏
      wx.request({
        url: doUrl + params,
        header: {
          'content-type': 'application/json;charset=UTF-8'
        },
        method: "POST",
        success: function (res) {
          var data = res.data;
          console.log(data);
          var isMarked = false;
          if (data.resultCode == "999999") {
            
            if (data.resultData && data.resultData == "Y") {
              isMarked = true;
            }
            if (doType == 2) { //搜藏
              isMarked = true;
            }
            if (doType == 3) {  //取消
              isMarked = false;
            }
            that.setData({
              isMarked: isMarked,
            });
          } else {
            that.setData({
              isMarked: isMarked,
            });
          }
          wx.hideLoading();
        },
        fail: function (error) {
          console.log("[" + error + "]");
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
};

// var getGeoInfo = function(that, address) {
//   console.log(address);
//   if (address && address != ''){
//     // 调用接口
//     qqmapsdk.geocoder({
//       address: address,
//       success: function (res) {
//         console.log(res);
//         if (res.status == 0) {
//           var loc = res.result.location;
//           console.log(loc);
//           that.setData({
//             location: loc,
//             markers: [{
//               latitude: loc.lat,
//               longitude: loc.lng,
//               name: address
//             }],
//           });
//         } else {
//           loc = {
//             lat: 48.52,
//             lng: 2.2
//           }
//           that.setData({
//             location: loc,
//             markers: [{
//               latitude: loc.lat,
//               longitude: loc.lng,
//               name: address
//             }],
//           });
//         }
//       },
//       fail: function (res) {
//         console.log(res);
//       },
//       complete: function (res) {
//         console.log(res);
//       }
//     });
//   }
// }

//根据房源id获取房源详情
var loadCommunityDetail = function (that, communityId) {

  that.setData({isLoading: true});

  if (communityId && communityId.length > 0){
    wx.showLoading({
      title: "正在加载",
      mask: true
    });

    //使用关键词搜索商品
    wx.request({
      url: communityDetailUrl + communityId,
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      method: "GET",
      success: function (res) {
        var data = res.data;
        if (data.resultCode == "999999" && data.resultData) {
          console.log(data.resultData);
          var owner = {};
          owner.owner = data.resultData.owner;
          owner.ownerEmail = data.resultData.ownerEmail;
          owner.ownerPhone = data.resultData.ownerPhone;
          owner.ownerWechat = data.resultData.ownerWechat;
          app.globalData.owner = owner;
          var markers = [{
            id: 0,
            latitude: data.resultData.latitude,
            longitude: data.resultData.longitude,
            scale: 13,
            iconPath: "../resource/map/marker_red.png",
            width: 25,
            height: 25
          }];
          that.setData({
            videoUrl:data.resultData.videoUrl,
            house_detail: data.resultData,
            markers: markers,
            hasDetail: true,
            isLoading: false,
          });
          communityMarked(that, communityId, 1);
          // getGeoInfo(that, data.resultData.address);
        } else {
          that.setData({
            house_detail: null,
            hasDetail: false,
            isLoading: false,
          });
          wx.hideLoading();
        }
      },
      fail: function (res) {
        that.setData({
          house_detail: null,
          hasDetail: false,
          isLoading: false,
        });
        wx.hideLoading();
      },
    });

  } else {
    that.setData({
      house_detail: null,
      hasDetail: false,
      isLoading: false,
    });
    showModel("加载失败", "没有房源详情");
  }
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasDetail: true,  //是否获取到房源详情
    isLoading: true,  //正在加载

    house_summ: null, //房源概要信息
    house_detail: null, //房源详情信息

    indicatorDots: false, //是否显示指示点
    autoplay: true,       //是否自动播放
    interval: 3000,       //播放时间间隔
    duration: 1000,       //轮转时间间隔
    currentIndex: 1,    //当前第几张图片

    privateFacilityNum: 6,  //房间设施列数
    publicFacilityNum: 2,   //公用设备列数
    rentFacilityNum: 2,     //租金包含列数

    isShowRoomDetail: false,  //是否显示房型图片
    roomDetailImages: [],  //房型图片
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('onLoad');
    //设置房源参数
    if (app.globalData.communityItem && app.globalData.communityItem.id){
      this.setData({
        house_summ: app.globalData.communityItem,
      });
    }
    if (options.communityId && options.communityId.length > 0) {
      this.setData({
        communityId: options.communityId,
      });
    } else if (this.data.house_summ){
      this.setData({
        communityId: this.data.house_summ.communityId,
      });
 
    }
    //获取房源详情
    loadCommunityDetail(this, this.data.communityId);

    // 实例化API核心类
    // qqmapsdk = new QQMapWX({
    //   key: 'TAHBZ-UZOKU-EVZVY-BSEKO-OPHC7-PUBCM'
    // });
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
    // var that = this;
    // qcloud.login({
    //   success(result) {
    //     var isNewUser = result.userData.newUser;
    //     that.setData({
    //       userInfo: result.userInfo,
    //       sess_id: result.sess_id
    //     });
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
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 顶部滑块改变监听
   */
  onTopSwiperChange: function(e){
    this.setData({
      currentIndex: e.detail.current + 1
    });
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (res) {
    console.log("onShareAppMessage");
    console.log(this.data.house_detail);
    console.log(this.data.house_detail.routeDescription);
    console.log(res);
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }

    return { 
      // imageUrl: '/pages/resource/kind/fangxing_01.png',
      title: this.data.house_detail.cityName + ': ' + this.data.house_detail.name,
      desc:this.data.house_detail.routeDescription,
      path: '/pages/house_detail/house_detail?communityId=' + this.data.communityId,
      success: function (res) {
        // 转发成功
        console.log('分享成功');
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },

  /**
   *浏览房型图片
   */
  onShowRoomDetail: function(e) {
    var room_index = parseInt(e.currentTarget.dataset.roomId);
    console.log(room_index);
    console.log(this.data.house_detail.rooms[room_index]);
    this.setData({
      isShowRoomDetail: true,
      roomDetailImages: this.data.house_detail.rooms[room_index].images,
    });
  },

  /**
   * 隐藏房型浏览
   */
  hideHomeDetail: function(e){
    this.setData({
      isShowRoomDetail: false,
      roomDetailImages: [],
    });
  },

  /**
   * 预定房间
   */
  onReservationClick: function(e) {
    
    var order = {};
    var index = parseInt(e.currentTarget.dataset.roomId);
    order.communityId = this.data.communityId;
    order.address = this.data.house_detail.address;
    order.name = this.data.house_detail.name;
    order.cityId = this.data.house_detail.cityId;
    order.houseType = this.data.house_detail.type;
    order.leaseModel = this.data.house_detail.leaseModel;
    order.roomInfo = this.data.house_detail.rooms[index];
    app.globalData.orderInfo = order;
    console.log('订单数据')
    console.log(order);
    wx.navigateTo({
      url: '../order/order',
    })
  },

  //点击客服触发
  onCSClick: {

  },
  //点击分享触发
  onShareClick: function(e) {
    var that = this;

  },
  //点击收藏触发
  onMarkClick: function(e){
    var that = this;
    if (this.data.isMarked) {
      communityMarked(that, this.data.communityId, 3);
    } else {
      communityMarked(that, this.data.communityId, 2);
    }
  },
  //点击立即选房触发
  onSublimitClick: function(e){
    var query = wx.createSelectorQuery();
    query.select('#header_info').fields({
      rect: true,
      size: true,
    }, function(res){
      console.log(res);
      wx.pageScrollTo({
        scrollTop: res.height
      });
      }).exec();
  }
  
})