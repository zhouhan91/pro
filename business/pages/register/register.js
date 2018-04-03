//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

var registerUrl = config.iRegisterUrl;  //注册
var comDistrictListUrl = config.comDistrictListByCityIdUrl;  //根据城市id查询区列表
var comCitysByCountryIdUrl = config.comCitysByCountryIdUrl;//根据国家id获取城市列表
var comCountryListUrl = config.comCountryListUrl;//获取国家列表


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

var loadDistrictList = function (that, cityId) {
  wx.showLoading({
    title: "正在加载",
    mask: true
  });
  if (that.data.districtList == null || that.data.districtList.length == 0) {
    wx.request({
      url: comDistrictListUrl + cityId,
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        var data = res.data;
        console.log(data);
        if (data.resultCode == "999999" && data.resultData.length > 0) {
          var districtNameList = [];
          data.resultData.forEach(function (item, idx) {
            districtNameList.push(item.chineseName);
          });
          that.setData({
            districtList: data.resultData,
            districtNameList: districtNameList
          });
          wx.hideLoading();
        } else {
          wx.hideLoading();
          showModel('加载失败', "没有数据");
        }
      },
      fail: function (error) {
        wx.hideLoading();
      }
    });
  } else {
    wx.hideLoading();
  }
};

//根据国家id获取城市列表
var loadCityListByCountryId = function (that, countryId) {
  wx.showLoading({
    title: "正在加载",
    mask: true
  });
  if (that.data.cityList == null || that.data.cityList.length == 0) {
    //根据国家数组第一个数字获取城市列表
    wx.request({
      url: comCitysByCountryIdUrl + countryId,
      dota: {},
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        var data = res.data;
        if (data.resultCode == "999999" && data.resultData.length > 0) {
          var cityNameList = [];
          data.resultData.forEach(function (item, idx) {
            cityNameList.push(item.chineseName);
          });
          that.setData({
            cityList: data.resultData,
            cityNameList: cityNameList
          });
          wx.hideLoading();
        } else {
          wx.hideLoading();
          showModel('加载失败', "没有数据");
        }
      },
      fail: function (msg) {
        wx.hideLoading();
        showModel('加载失败', "请求没有包含会话响应，请确保服务器处理");
      }
    });
  } else {
    wx.hideLoading();
  }
};

//加载国家列表
var loadCountryList = function (that) {
  wx.showLoading({
    title: "正在加载",
    mask: true
  });
  var countryList = that.data.countryList || [];
  if (countryList.length > 0) {
    //onLoad中加载完成，不需要重新加载
    wx.hideLoading();
  } else {
    //获取国家列表
    wx.request({
      url: comCountryListUrl,
      data: {},
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        var data = res.data;
        if (data.resultCode == "999999" && data.resultData.length > 0) {
          var countryNameList = [];
          data.resultData.forEach(function (item, idx) {
            countryNameList.push(item.chineseName);
          });
          that.setData({
            countryList: data.resultData,
            countryNameList: countryNameList
          });
          console.log(that.data.countryList);
          wx.hideLoading();
        } else {
          wx.hideLoading();
          showModel('加载失败', "没有数据");
        }
      },
      fail: function (msg) {
        wx.hideLoading();
        showModel('加载失败', "请求没有包含会话响应，请确保服务器处理");
      }
    });
  }
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    countryNameList: [],  //国家名称列表
    cityNameList:[],      //国家名称列表
    districtNameList: [], //区县名称列表

    countryList:[],       //国家列表
    cityList: [],         //城市列表
    districtList: [],     //区县列表
    
    //picker选择对应的下标
    countryPickerIndex: -1,
    cityPickerIndex: -1,
    districtPickerIndex: -1,


  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //加载国家列表信息
    loadCountryList(this);
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
   * 点击国家picker组件时触发
   */
  onCountryPickerClick: function(e){
    //加载国家列表信息
    loadCountryList(this);
  },

  /**
   * 国家picker组件值改变时触发，加载国家对应下的城市列表
   */
  bindCountryPickerChange: function(e){
    // console.log(e.detail.value);
    if (this.data.countryPickerIndex != e.detail.value) {
      this.setData({
        countryPickerIndex: e.detail.value,
        cityList: []
      });
      //加载所选国家下的城市列表
      loadCityListByCountryId(this, this.data.countryList[e.detail.value].id);
    }
  },

  /**
   * 点击城市picker组件时触发
   */
  onCityPickerClick: function(e){
    //加载所选国家下的城市列表
    loadCityListByCountryId(this, this.data.countryList[this.data.countryPickerIndex].id);
  },

  /**
   * 城市picker组件值改变时触发，加载城市对应下的区县列表
   */
  bindCityPickerChange: function(e){
    if (this.data.cityPickerIndex != e.detail.value) {
      // console.log(e.detail.value);
      this.setData({
        cityPickerIndex: e.detail.value,
        districtList: []
      });
      loadDistrictList(this, this.data.cityList[e.detail.value].id);
    }
  },

  /**
   * 点击城市picker组件时触发
   */
  onDistrictPickerClick: function(e){
    loadDistrictList(this, this.data.cityList[this.data.cityPickerIndex].id);
    // wx.chooseAddress({
    //   success: function (res) {
    //     console.log(res.userName)
    //     console.log(res.postalCode)
    //     console.log(res.provinceName)
    //     console.log(res.cityName)
    //     console.log(res.countyName)
    //     console.log(res.detailInfo)
    //     console.log(res.nationalCode)
    //     console.log(res.telNumber)
    //   }
    // });
  },

  /**
   * 
   */
  bindDistrictPickerChange: function(e){
    // console.log(e.detail.value);
    this.setData({
      districtPickerIndex: e.detail.value
    });
  },

  /**
   * 用户注册表单提交
   */
  registerFormSubmit: function(e) {
    console.log("registerFormSubmit");
    console.log(e.detail.value);
    var formInfo = e.detail.value;
    var msg = "";
    if (formInfo.realName == "") {
      msg = "店主姓名不能为空";
    } else if (formInfo.userName == "") {
      msg = "昵称不能为空";
    } else if (formInfo.password.length < 6 || formInfo.password.length > 16) {
      msg = "密码长度为：6~16";
    } else if (formInfo.password != formInfo.password2) {
      msg = "确认密码不一致";
    } else if (formInfo.restaurantName == "") {
      msg = "店铺名称不能为空";
    } else if (formInfo.phone == "") {
      msg = "店主电话不能为空";
    } else if (formInfo.countryName == "") {
      msg = "国家不能为空";
    } else if (formInfo.cityName == "") {
      msg = "城市不能为空";
    // } else {
    //   var a = new RegExp("(^(\d{3,4}-)?\d{7,8})$|(1[0-9]{9})");
    //   if (!a.test(formInfo.phone)) {
    //     msg = "请输入合法电话";
    //   }
    }

    if (msg.length > 0) {
      util.showModel("系统提示", msg);
    } else {
      formInfo.password2 = null;
      //提交注册信息
      wx.request({
        url: registerUrl,
        data: formInfo,
        method: "POST",
        success: function(res) {
          console.log(res);
          if (res.data.resultCode && res.data.resultCode == "999999") {
            util.showSuccess("注册成功");
            //跳转到登录页
            wx.navigateTo({
              url: '../login/login',
            })
          } else {
            util.showModel("系统提示", "注册失败");
          }
        },
        fail: function(err) {
          util.showModel("系统提示", err.errMsg);
        }
      });
    }
  }
})