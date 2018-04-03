//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

const readRestInfoUrl = config.readRestInfoUrl; //读取店铺信息
const queryShopLocListUrl = config.queryShopLocListUrl; //获取店铺配送地址信息
const saveShopLocListUrl = config.saveShopLocListUrl; //保存店铺配送信息

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

//加载店铺信息
var loadRestInfo = function (that) {
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  wx.request({
    url: readRestInfoUrl + that.data.restaurantId,
    method: "GET",
    success: function (res) {
      if (res.data.resultCode == "999999") {
        console.log(res.data);
        that.setData({
          restInfo: res.data.resultData
        });
        wx.hideLoading();
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
};

//加载配送地址信息
var loadLocationList = function(that){
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  wx.request({
    url: queryShopLocListUrl + that.data.restaurantId,
    method:"GET",
    success: function(res){
      if (res.data.resultCode == "999999") {
        console.log(res.data);
        that.setData({
          locationList: res.data.resultData
        });
        wx.hideLoading();
      } else {
        wx.hideLoading();
        showModel('系统提示', res.data.resultDesc);
      }
    },
    fail: function(err){
      wx.hideLoading();
      showModel('系统提示', "操作失败");
    }
  })
};

//保存店铺配送信息
var submitForm = function(that, params){
  wx.request({
    url: saveShopLocListUrl,
    data: params,
    method: "POST",
    success: function(res){
      if(res.data.resultCode == "999999"){
        showSuccess("保存成功");
        that.setData({
          addr: "",
        });
        loadLocationList(that);
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail: function(err){

    }
  })
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    locationList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    if (options.restaurantId) {
      this.setData({
        restaurantId: options.restaurantId,
        managerId: options.managerId
      });
    }

    //加载店铺信息
    loadRestInfo(this);
    //加载配送地址
    loadLocationList(this);
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
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },

  /**
   * 编辑地址光标离开时触发
   */
  onItemBlur: function(e){
    var id = e.currentTarget.dataset.id;
    var addr = e.detail.value;
    if (id == 0) {
      //新增条目
      if (addr == "") {
        //不能为空
        showModel("系统提示", "新增地址不能为空");
      } else {
        var locationNew = {
          id: 0,
          address: addr
        };
        this.setData({
          locationNew: locationNew
        });
      }
    } else {
      //修改条目
      if (addr == "") {
        //为空视为删除
        showModel("系统提示", "地址为空视为删除");
      }
      var locationList = this.data.locationList;
      locationList.forEach(function (item, idx) {
        if (item.id == id) {
          item.address = addr;
        }
      });
      this.setData({
        locationList: locationList
      });
      // console.log(locationList);
    }
  },

  /**
   * 提交表单
   */
  onDistFormSubmit: function(e){
    console.log(e.detail.value);

    var formInfo = e.detail.value;
    console.log(parseFloat(formInfo.distributionAmount));
    var msg = "";
    if (formInfo.distributionAmount == "" || parseFloat(formInfo.distributionAmount) == NaN) {
      msg = "配送费不能为空，或不合法";
    } else if (formInfo.amountLimit == "" || !parseFloat(formInfo.amountLimit) == NaN) {
      msg = "起送金额不能为空，或不合法";
    }

    if(msg != ""){
      showModel("系统提示", msg);
    } else {
      var lstLocation = [];
      var locationList = this.data.locationList;
      if (locationList && locationList.length > 0) {
        locationList.forEach(function (item, idx) {
          var loc = {
            id: item.id,
            address: item.address
          };
          lstLocation.push(loc);
        });
      }
      if (this.data.locationNew) {
        lstLocation.push(this.data.locationNew);
      }
      formInfo.lstLocation = lstLocation;
      formInfo.restaurantId = this.data.restaurantId;
      formInfo.managerId = this.data.managerId;
      console.log(formInfo);
      submitForm(this, formInfo);

      // //测试代码，模拟添加新地址
      // if (locationList.length <= 7){
      //   if (this.data.locationNew) {
      //     locationList.push(this.data.locationNew);
      //     this.setData({
      //       locationList: locationList
      //     });
      //   }
      // }
    }
  },


})