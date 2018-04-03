// pages/foodaddress/foodaddress.js
//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

const queryDefCateringContactsUrl = config.queryDefCateringContactsUrl;//读取用户默认收货人信息
const queryRestLocListUrl = config.queryRestLocListUrl; //获取店铺统一配送地址
const queryCateringContactsListUrl = config.queryCateringContactsListUrl; //查询用户收货人列表
const readCateringContactsUrl = config.readCateringContactsUrl; //读取收货人信息
const insertCateringContactsUrl = config.insertCateringContactsUrl;//新增收货人信息
const updateCateringContactsUrl = config.updateCateringContactsUrl; //修改收货人信息
const deleteCateringContactsUrl = config.deleteCateringContactsUrl;//删除收货人信息


// 显示成功提示
var showSuccess = text => wx.showToast({
  title: text,
  icon: 'success'
});

// 显示失败提示
var showFail = text => wx.showToast({
  title: text,
  icon: 'error'
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

//读取默认收货人信息
var queryDefCateringContacts = function (that) {
  wx.request({
    url: queryDefCateringContactsUrl + that.data.sess_id,
    method: "GET",
    success: function (res) {
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        that.setData({
          defCateringContact: res.data.resultData
        });
      }
    },
    fail: function (err) {

    }
  })
};


//读取店铺统一配送地址列表
var queryRestLocList = function(that){
  wx.request({
    url: queryRestLocListUrl + that.data.restaurantId,
    method:"GET",
    success: function(res){
      if(res.data.resultCode == "999999"){
        that.setData({
          restLocList: res.data.resultData
        });
        if (that.data.restLocList.length != 0) {
          that.setData({
            nullHouse: false, //弹窗显示
          })
          setTimeout(function () {
            that.setData({
              nullHouse: true, //弹窗显示
            })
          }, 3000)
        }   
      }
    },
    fail:function(err){

    }
  })
}


//读取收货人列表
var queryCateringContactsList = function (that, params) {
  wx.request({
    url: queryCateringContactsListUrl + that.data.sess_id + "/1",
    method: "GET",
    success: function (res) {
      if (res.data.resultCode == "999999") {
        that.setData({
          cateringContactsList: res.data.resultData
        });
        console.log(that.data.cateringContactsList);
        //新增联系人后默认选中
        if (params) {
          res.data.resultData.forEach(function (item, idx) {
            if(item.name == params.name && item.phone == params.phone && item.address == params.address){
              that.setData({
                personIndex : idx
              });
            }
          });
          //新增成功，返回订单页
          app.globalData.person = that.data.cateringContactsList[that.data.personIndex];
          app.globalData.address = that.data.restLocList[that.data.addressIndex];
          wx.navigateBack({
            delta: 1
          });
        }
        
      }
    },
    fail: function (err) {

    }
  });
};

//新增配送地址
var insertCateringContacts = function(that, params) {
  wx.request({
    url: insertCateringContactsUrl,
    data: params,
    method: "POST",
    success: function(res){
      if (res.data.resultCode == "999999") {
        //新增成功
        queryCateringContactsList(that, params);
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
    nullHouse: true,  //先设置隐藏
    time:'请选择明天配送时间，今天配送可不填',
    addressIndex: -1, //商家配送地址选中下标
    personIndex: -1,  //联系人下标
  },

  //  点击时间组件确定事件  
  bindTimeChange: function (e) {
    this.setData({
      time: e.detail.value
    })
    console.log(this.data.time)
  },  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    
    if (options.restaurantId){
      this.setData({
        restaurantId: options.restaurantId,
      });
    }
    if (options.distributionRemark) {
      this.setData({
        distributionRemark: options.distributionRemark
      });
    }
    qcloud.login({
      success(result) {
        var params = "?userKey=" + result.sess_id + "&busiCode=community";
        that.setData({
          userInfo: result.userInfo,
          sess_id: result.sess_id
        });
        //读取收货人列表
        queryCateringContactsList(that);
        //读取店铺配送地址
        queryRestLocList(that);
      },
      fail(error) {
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
    console.log('时间')
    app.globalData.peisong=null;
    console.log(app.globalData.peisong);
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
  
  
  radioContactsChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value);
    this.setData({
      personIndex: e.detail.value
    });
  },

  radioLocationChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value);
    this.setData({
      addressIndex: e.detail.value
    });
  },
 
  /**
   * 点击保存配送地址信息
   */
  addressFormSubmit: function(e){
    var formInfo = e.detail.value;
    if (this.data.sess_id){
      var msg = "";
      if (formInfo.name == "") {
        msg = "收货人姓名不能为空";
      } else if (formInfo.phone == "") {
        msg = "手机号不能为空";
      } else if (formInfo.address == "") {
        msg = "配送地址不能为空";
      }
      if (this.data.time != '请选择明天配送时间，今天配送可不填') {
        formInfo.distributionNotes = this.data.time;
        app.globalData.peisong = this.data.time;
      }
      console.log('保存的数据')
      console.log(formInfo)
      if (this.data.addressIndex != -1) {
        app.globalData.address = this.data.restLocList[this.data.addressIndex];
      } else {
        app.globalData.address = null;
      }
   
      if (msg == "") {
        //未选中历史联系人时，新增配送地址信息,并返回订单页
        if (this.data.personIndex == -1) {
          formInfo.userKey = this.data.sess_id;
          insertCateringContacts(this, formInfo);
        } else {
          app.globalData.person = this.data.cateringContactsList[this.data.personIndex];
          wx.navigateBack({
            delta: 1
          });
        }
      } else {
        if (this.data.personIndex == -1) {
          //老板配送地址未选，或者新增信息不全
          showModel("系统提示", msg);
        } else {
          app.globalData.person = this.data.cateringContactsList[this.data.personIndex];
          wx.navigateBack({
            delta: 1
          });
         
        }
      }
    }
  },
 
})