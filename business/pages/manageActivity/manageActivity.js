// pages/manageActivity/manageActivity.js
//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

const queryDiscountListUrl = config.queryDiscountListUrl; //根据店铺id读取活动信息列表
const updateDiscountListUrl = config.updateDiscountListUrl; //修改店铺活动信息


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

var loadActivityList = function(that, withLoading) {
  if (withLoading) {
    wx.showLoading({
      title: "提交中",
      mask: true
    });
  }

  wx.request({
    url: queryDiscountListUrl + that.data.restaurantId,
    method:"GET",
    success: function(res) {
      console.log(res);
      if (res.data.resultCode == "999999") {
        if (res.data.resultData.length > 0) {
          var list = res.data.resultData;
          list.forEach(function(item, index){
            if (item.type == "manjian") {
              that.setData({
                oneActObj: item
              });
              if (item.status == "Y"){
                that.setData({
                  activityOneType: "manjian",
                })
              }
            } else if (item.type == "quanchangzhekou"){
              that.setData({
                twoActObj: item
              });
              if (item.status == "Y") {
                that.setData({
                  activityOneType: "quanchangzhekou",
                })
              }
            } else if (item.type == "xinren") {
              that.setData({
                threeActObj: item
              });
            }
          });
          wx.hideLoading();
        }else{
          wx.hideLoading();
        }
      } else {
        wx.hideLoading();
        showModel('系统提示', res.data.resultDesc);
      }

    },
    fail: function(err){
      wx.hideLoading();
    }
  })
};

//保存活动信息
var saveActivityInfo = function(that, params){
  wx.showLoading({
    title: "提交中",
    mask: true
  });
  console.log(params);
  wx.request({
    url: updateDiscountListUrl,
    data: params,
    method:"POST",
    success: function(res){
      if (res.data.resultCode == '999999'){
        //加载店铺活动列表
        loadActivityList(that, false);
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


Page({

  /**
   * 页面的初始数据
   */
  data: {
    activityOneChecked: false,  //活动一选择状态
    activityTwoChecked: false,  //活动二选择状态

    //活动对象
    oneActObj:{}, //满减活动对象
    twoActObj:{}, //折扣活动对象
    threeActObj:{}, //新人活动对象
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.restaurantId) {
      this.setData({
        restaurantId: options.restaurantId,
        managerId: options.managerId
      });
      //加载店铺活动列表
      loadActivityList(this, true);
    } else {
      //测试
      this.setData({
        restaurantId: 2,
        managerId: 4,
      });
      //加载店铺活动列表
      loadActivityList(this, true);
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 勾选活动一触发的事件
   */
  oneCheckboxChange: function(e){
    console.log(e.detail.value);
    if (e.detail.value.length == 1) { //选中
      this.setData({
        activityOneChecked: true
      });
      if (this.data.oneActObj.type == 1 && this.data.oneActObj.status == "Y") {
        this.setData({
          defaultSelected: false,
          activityOneType: "manjian"
        });
      } else if (this.data.twoActObj.type == 2 && this.data.twoActObj.status == "Y") {
        this.setData({
          defaultSelected: false,
          activityOneType: "quanchangzhekou"
        });
      } else {
        this.setData({
          defaultSelected: true,
          activityOneType: "manjian"
        });
      }
    } else {  //未选中
      this.setData({
        activityOneChecked: false,
        defaultSelected: false,
        activityOneType: -1,
      });
    }
  },

  /**
   * 单选按钮变化时触发
   */
  twoRadioChange: function(e){
    if (e.detail.value == 1) {
      this.setData({
        activityOneType: "manjian",
        activityOneChecked: true
      });
    } else if (e.detail.value == 2) {
      this.setData({
        activityOneType: "quanchangzhekou",
        activityOneChecked: true
      });
    }
  },

  /**
   * 勾选活动二时触发
   */
  twoCheckboxChange: function(e){
    if (e.detail.value.length == 1){  //活动二选中
      this.setData({
        activityTwoChecked: true
      });
    }
  },

  /**
   * 满 光标离开时触发
   */
  onTargetBlur: function(e){
    if (this.data.activityOneType == "manjian") { //满减
      var oneActObj = this.data.oneActObj;
      var target = parseFloat(e.detail.value);
      if (target && target > oneActObj.discount) {
        oneActObj.target = target;
        this.setData({
          oneActObj: oneActObj
        });
      } else {
        showModel('系统提示', "满 金额不合法");
      } 
    }
  },

  /**
   * 减，折扣光标离开时触发
   */
  onDiscountBlue: function(e){
    if (this.data.activityOneType == "manjian") { //满减
      var oneActObj = this.data.oneActObj;
      var discount = parseFloat(e.detail.value);
      if (discount && discount >= 0 && discount < oneActObj.target) {
        oneActObj.discount = discount;
        this.setData({
          oneActObj: oneActObj
        });
      } else {
        showModel('系统提示', "减 金额不合法");
      }
    } else if (this.data.activityOneType == "quanchangzhekou") { //折扣
      var twoActObj = this.data.twoActObj;
      var discount = parseFloat(e.detail.value);
      if (discount && discount >= 0 && discount < 1) {
        twoActObj.discount = discount;
        this.setData({
          twoActObj: twoActObj
        });
      } else {
        showModel('系统提示', "折扣不合法");
      }
    }
  },

  /**
   * 新人立减光标离开时触发
   */
  onXinRenBlue: function(e){
    var threeActObj = this.data.threeActObj;
    var discount = parseFloat(e.detail.value);
    console.log(discount);

    if (discount && discount > 0) {
      threeActObj.discount = discount;
    } else {
      showModel('系统提示', "首单立减金额不合法");
    }
    this.setData({
      threeActObj: threeActObj
    });
  },

  /**
   * 保存活动信息
   */
  onSaveActivityClick: function(e){
    var params = [];
    var oneActObj = this.data.oneActObj;
    var twoActObj = this.data.twoActObj;
    var threeActObj = this.data.threeActObj;
    if (this.data.activityTwoChecked) {
      threeActObj.status = "Y";
    } else {
      threeActObj.status = "N";
    }

    if (this.data.activityOneType == "manjian") { //满减
      oneActObj.status = "Y";
      twoActObj.status = "N";
    } else if (this.data.activityOneType == "quanchangzhekou") {  //折扣
      twoActObj.status = "Y";
      oneActObj.status = "N";
    } else {
      oneActObj.status = "N";
      twoActObj.status = "N";
    }
    oneActObj.modifyBy = this.data.managerId;
    twoActObj.modifyBy = this.data.managerId;
    threeActObj.modifyBy = this.data.managerId;
    params.push(oneActObj);
    params.push(twoActObj);
    params.push(threeActObj);

    //保存活动信息
    saveActivityInfo(this, params);
  }
})