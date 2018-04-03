// pages/manageFond/manageFond.js
//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

const queryGoodsCateListUrl = config.queryGoodsCateListUrl; //根据店铺id获取商品分类信息
const insertGoodsCateUrl = config.insertGoodsCateUrl; //新增商品分类信息
const deleteGoodsCateUrl = config.deleteGoodsCateUrl; //删除商品分类信息
const updateGoodsCateUrl = config.updateGoodsCateUrl; //修改商品分类信息

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

var loadGoodsCateList = function(that){
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  if (that.data.restaurantId) {
    wx.request({
      url: queryGoodsCateListUrl + that.data.restaurantId,
      method: "GET",
      success: function(res){
        console.log(res.data);
        if (res.data.resultCode == "999999") {
          if (res.data.resultData && res.data.resultData.length > 0) {
            that.setData({
              goodsCateList: res.data.resultData,
              enableEditorIndex: -1
            });
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
      fail:function(err){
        wx.hideLoading();
        showModel('系统提示', "操作失败");
      }
    });
  } else {
    wx.hideLoading();
  }
};

//新增菜品
var addGoodsCate = function(that, params){
  wx.showLoading({
    title: "提交中",
    mask: true
  });
  var url = "";
  if (that.data.isAddLine) {
    url = insertGoodsCateUrl;
  } else if (that.data.enableEditorIndex > -1) {
    url = updateGoodsCateUrl;
  }

  wx.request({
    url: url,
    method: "POST",
    data: params,
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        if (params.createBy) {
          //新增成功，添加到队列中
          var goodsCateList = that.data.goodsCateList;
          goodsCateList.push(res.data.resultData);
          that.setData({
            goodsCateList: goodsCateList,
            isAddLine: false
          });
        } else {
          console.log(res);
          var goodsCateList = that.data.goodsCateList;
          goodsCateList[that.data.enableEditorIndex].name = that.data.name;
          goodsCateList[that.data.enableEditorIndex].sortNum = that.data.sortNum;
          that.setData({
            goodsCateList: goodsCateList,
            enableEditorIndex: -1
          });
        }
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

//删除菜品项
var delGoodsCate = function(that, index) {
  wx.showLoading({
    title: "提交中",
    mask: true
  });

  var goodsCategoryId = that.data.goodsCateList[index].id;
  wx.request({
    url: deleteGoodsCateUrl + goodsCategoryId + "/" + that.data.managerId,
    method: "POST",
    data:{
      goodsCategoryId: goodsCategoryId,
      managerId: that.data.managerId
    },
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        //从列表中移除
        var goodsCateList = that.data.goodsCateList;
        goodsCateList.splice(index, 1);
        that.setData({
          goodsCateList: goodsCateList
        });
        wx.hideLoading();
        showSuccess("操作成功");
      } else {
        wx.hideLoading();
        showModel('系统提示', res.data.resultDesc);
      }
    },
    fail: function(err){
      wx.hideLoading();
      showModel('系统提示', "操作失败");
    }
  });
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsCateList:[], //菜品列表
    enableEditorIndex: -1, //可编辑行下标
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
   * 名称光标离开时触发
   */
  onNameBlur: function(e){
    if (e.detail.value != '') {
      this.setData({
        name: e.detail.value
      });
    } else {
      showModel('系统提示', "名称不能为空");
    }
  },

  /**
   * 排序光标离开时触发
   */
  onsortNumBlur: function(e){
    if (e.detail.value != '') {
      this.setData({
        sortNum: e.detail.value
      });
    } else {
      showModel('系统提示', "排序号不能为空");
    }
  },

  /**
   * 点击编辑该菜品
   */
  onEditorClick: function(e){
    var index = e.currentTarget.dataset.index;
    var name = this.data.goodsCateList[index].name;
    var sortNum = this.data.goodsCateList[index].sortNum;
    console.log(index);
    if (!this.data.isAddLine){
      this.setData({
        enableEditorIndex: index,
        name: name,
        sortNum: sortNum
      });
    } else {
      var that = this;
      wx.showModal({
        title: '系统提示',
        content: '是否放弃添加操作？',
        showCancel: true,
        cancelText: '取消',
        confirmText: '放弃',
        success: function (res) {
          if (res.cancel) {
            //取消编辑
          } else if (res.confirm) {
            //放弃添加，确认编辑
            that.setData({
              isAddLine: false,
              enableEditorIndex: index,
              name: name,
              sortNum: sortNum
            });
          }
        },
        fail: function (res) { },
      });
    }
    
  },

  /**
   * 点击删除改菜品
   */
  onDeleteClick: function(e){
    var index = e.currentTarget.dataset.index;
    console.log(index);
    var that = this;
    wx.showModal({
      title: '系统提示',
      content: '确定要删除菜品项（' + this.data.goodsCateList[index].name +'）吗',
      showCancel: true,
      cancelText: '取消',
      confirmText: '确定',
      success: function (res) {
        if (res.cancel) {
          //取消编辑
        } else if (res.confirm) {
          //放弃添加，确认编辑
          delGoodsCate(that, index);
        }
      },
      fail: function (res) { },
    });
  },

  /**
   * 增加一行
   */
  onAddLineClick: function(e){
    console.log("onAddLineClick");
    if (this.data.enableEditorIndex == -1){
      this.setData({
        isAddLine: true,
        name: "",
        sortNum: ""
      });
    } else {
      var that = this;
      wx.showModal({
        title: '系统提示',
        content: '是否放弃编辑项：' + this.data.goodsCateList[this.data.enableEditorIndex].name,
        showCancel: true,
        cancelText: '取消',
        confirmText: '放弃',
        success: function(res) {
          if (res.cancel) {
            //取消添加
          } else if (res.confirm) {
            //放弃编辑，确认添加
            that.setData({
              isAddLine: true,
              enableEditorIndex: -1,
              name: "",
              sortNum: ""
            });
          }
        },
        fail: function(res) {},
      });
    }
    
  },

  /**
   * 保存当前编辑的菜品信息
   */
  onSaveClick:function(e){
    if (this.data.isAddLine || this.data.enableEditorIndex != -1) {
      var params = {};
      var msg = "";
      if (this.data.isAddLine) {  //新增菜品
        if (this.data.name == null || this.data.name == '') {
          msg = "名称不能为空";
        } else if (this.data.sortNum == null || this.data.sortNum == '') {
          msg = "排序号不能为空";
        } else {
          params.name = this.data.name;
          params.sortNum = this.data.sortNum;
          params.restaurantId = this.data.restaurantId;
          params.createBy = this.data.managerId;
        }
      } else {    //编辑菜品
        var id = this.data.goodsCateList[this.data.enableEditorIndex].id;
        var name = this.data.name;
        var sortNum = this.data.sortNum;
        if (name == null || name == '') {
          msg = "名称不能为空";
        } else if (sortNum == null || sortNum == '') {
          msg = "排序号不能为空";
        } else {
          params.name = name;
          params.sortNum = sortNum;
          params.id = id;
          params.modifyBy = this.data.managerId;
        }
      }
      if (msg != ""){
        showModel('系统提示', msg);
      } else {
        addGoodsCate(this, params);
      }
    }
    
  },

  /**
   * 取消添加
   */
  onCancelClick: function(e){
    console.log("onCancelClick");
    this.setData({
      isAddLine: false,
    });
  },
})