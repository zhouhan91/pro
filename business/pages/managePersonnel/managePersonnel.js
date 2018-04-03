//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

var queryCourierListUrl = config.queryAllCourierListUrl;    //根据店铺id查询所有配送员列表【不分页】
var saveCateringCourierListUrl = config.saveCateringCourierListUrl; //保存配送员列表
var deleteCateringCourierUrl = config.deleteCateringCourierUrl; //删除配送员信息

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


//获取配送员列表（不分页）
var loadCourierList = function (that) {
  wx.request({
    url: queryCourierListUrl + that.data.restaurantId,
    data: {
      restaurantId: that.data.restaurantId
    },
    method: "GET",
    success: function (res) {
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        that.setData({
          courierList: res.data.resultData
        });
        that.setData({
          name: "",
          phone: ""
        });
      } else {

      }
    },
    fail: function (err) {
      showModel("系统提示", "加载失败，请稍后重试");
    }
  })
};

var submitForm = function(that, params) {
  wx.request({
    url: saveCateringCourierListUrl,
    data: params,
    method:"POST",
    success: function(res){
      if (res.data.resultCode == "999999") {
        //保存成功，重新加载列表
        loadCourierList(that);
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail: function(err) {
      showModel("系统提示", "保存失败，请稍后重试");
    }
  })
};

var delPersonById = function(that, id) {
  wx.request({
    url: deleteCateringCourierUrl + id + "/" + that.data.managerId,
    method:"POST",
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999"){
        showSuccess("操作成功");
        //保存成功，重新加载列表
        loadCourierList(that);
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail: function(err) {
      showModel("系统提示", "操作失败，请稍后重试");
    }
  })
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    listData: [
      { "code": "1", "text": "爱上", "type": "132585412356" },
      { "code": "2", "text": "阿萨爱上", "type": "132585412356" },
      { "code": "3", "text": "阿爱上", "type": "132585412356" },
      { "code": "4", "text": "阿萨德爱上", "type": "132585412356" },
      { "code": "5", "text": "阿爱上", "type": "132585412356" },
      { "code": "6", "text": "阿萨德爱上", "type": "132585412356" },
      { "code": "7", "text": "阿萨德爱上", "type": "132585412356" }
    ]
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
    } else {
      //测试数据
      this.setData({
        restaurantId: 2,
        managerId: 4
      });
    }
    loadCourierList(this);
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
   * 删除配送员信息
   */
  onDelPersonClick: function(e) {
    var person = e.currentTarget.dataset.item;
    console.log(person);
    delPersonById(this, person.id);
  },

  /**
   * 提交表单
   */
  onPersonFormSubmit: function(e){
    var formInfo = e.detail.value;
    var msg = "";
    if (formInfo.name == "") {
      msg = "姓名不能为空";
    } else if ( formInfo.phone == ""){
      msg = "手机号不能为空";
    }

    if (msg == "") {
      var params = {
        restaurantId: this.data.restaurantId,
        managerId: this.data.managerId,
        name: formInfo.name,
        phone: formInfo.phone,
        lstCourier: [formInfo]
      };
      console.log(params);
      submitForm(this, params);
    } else {
      showModel("系统提示", msg);
    }
  }
})