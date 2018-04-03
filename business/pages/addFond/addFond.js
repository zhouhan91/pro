//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

const queryGoodsCateListUrl = config.queryGoodsCateListUrl; //根据店铺id获取商品分类信息
const readGoodsInfoUrl = config.readGoodsInfoUrl; //读取商品详情
const insertGoodsUrl = config.insertGoodsUrl;     //新增商品
const updateGoodsInfoUrl = config.updateGoodsInfoUrl;  //修改商品
const comUploadFileUrl = config.comUploadFileUrl; //上传图片


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

const AV = require('../common/lib/av-weapp-min.js');

//加载菜品分类列表
var loadGoodsCateList = function (that) {
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  if (that.data.restaurantId) {
    wx.request({
      url: queryGoodsCateListUrl + that.data.restaurantId,
      method: "GET",
      success: function (res) {
        console.log(res.data);
        if (res.data.resultCode == "999999") {
          if (res.data.resultData && res.data.resultData.length > 0) {
            that.setData({
              goodsCateList: res.data.resultData,
            });
            wx.hideLoading();
            if(!that.data.isAdd) {
              //获取菜品详情
              loadGoodsDetailInfo(that);
            }
          } else {
            wx.hideLoading();
            showModel('系统提示', "没有菜品信息");
          }
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
  } else {
    wx.hideLoading();
  }
};

//获取菜品详情
var loadGoodsDetailInfo = function(that){
  wx.request({
    url: readGoodsInfoUrl + that.data.id,
    method: "GET",
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        var cateId = res.data.resultData.categoryId;
        var cateList = that.data.goodsCateList;
        cateList.forEach(function(item, index){
          if(item.id == cateId){
            that.setData({
              currCate: item
            });
          }
        });
        var tempFilePaths = [];
        tempFilePaths.push(res.data.resultData.coverPicture);
        //加载成功,更新页面
        that.setData({
          goodsInfo: res.data.resultData,
          tempFilePaths: tempFilePaths
        });
      }
    },
    fail: function(err){

    }
  })
};

/**
 * 提交菜品订单
 */
var submitForm = function(that, formInfo){
  var url = "";
  if (that.data.isAdd) {
    url = insertGoodsUrl;
  } else {
    url = updateGoodsInfoUrl;
  }
  wx.request({
    url: url,
    data: formInfo,
    method: "POST",
    success: function(res){
      console.log(res.data);
      if(res.data.resultCode == "999999"){
        //添加成功
        showSuccess("菜品添加成功");
      }
    },
    fail: function(err){

    }
  })
}

Page({
  data: {
    tempFilePaths: [],//图片
    isblock: 'false',//是否显示图片
    items: [
      { name: 'Y', value: '是', checked: 'true' },
      { name: 'N', value: '否'},
    ]
  },
  radioChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value)
  },
  onLoad: function (options) {
    if (options.isAdd == "1") {   //添加菜品
      this.setData({
        restaurantId: options.restaurantId,
        managerId: options.managerId,
        isAdd: true
      });
    } else {
      this.setData({
        id: options.id,
        restaurantId: options.restaurantId,
        managerId: options.managerId,
        isAdd: false
      });
      
    }
    //加载分类列表
    loadGoodsCateList(this);
  },

  /**
   * 点击 菜品类型 ,弹出菜单选择
   */
  onGoodsCateClick: function(e){
    this.setData({
      isShowCate: true,
    });
  },

  /**
   * 隐藏菜品类型弹窗
   */
  onHideCateClick: function(e){
    this.setData({
      isShowCate: false,
    });
  },

  /**
   * 选择菜品分类
   */
  onCateChangeClick: function(e){
    console.log("onCateChangeClick");
    var cateIndex = e.detail.value;
    this.setData({
      currCate: this.data.goodsCateList[cateIndex],
      isShowCate: false
    });
  },

  /**
   * 关闭菜品分类选择弹窗
   */
  onHideCateClick: function(e){
    this.setData({
      isShowCate: false
    });
  },

  /**
   * 预览图片
   */
  previewImage: function(e) {
    //预览图片
    wx.previewImage({
      current: this.data.tempFilePaths[0], // 当前显示图片的http链接
      urls: this.data.tempFilePaths // 需要预览的图片http链接列表
    });
  },

  /**
   * 上传封面图片
   */
  chooseImage: function () {
    var _this = this;
    wx.chooseImage({
      count: 9, // 默认9  
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有  
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有  
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片  
        
        var tempFilePaths = [];
        tempFilePaths.push(res.tempFilePaths[0]);
        _this.setData({
          tempFilePaths: tempFilePaths,
        });

        //上传图片  busiCode=goodsCoverPicture
        const uploadTask = wx.uploadFile({
          url: comUploadFileUrl, //仅为示例，非真实的接口地址
          filePath: res.tempFilePaths[0],
          name: 'file',
          formData: {
            'busiCode': 'goodsCoverPicture'
          },
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (res) {
            console.log("success");
            console.log(res.data);
            var jsonStr = res.data;
            jsonStr = jsonStr.replace(" ", "");
            if (typeof jsonStr != 'object') {
              jsonStr = jsonStr.replace(/\ufeff/g, "");//重点
              var jj = JSON.parse(jsonStr);
              res.data = jj;
            }
            if (res.data.resultCode == "999999"){
              _this.setData({
                tempFilePath: res.data.resultData,
                // tempFilePath: "http://admin.wemecity.net/upload/goods/92a61193bbd54f7a997b659d8a37d062.png",
              });
            } else {
              showModel(_this, res.data.resultDesc);
            }
          },
          fail: function(err){
            showModel(_this, "上传失败，请稍后重试");
          },
          complete: function(){
            console.log("complete");
          }

        });
        uploadTask.onProgressUpdate((res) => {
          console.log('上传进度', res.progress);
          console.log('已经上传的数据长度', res.totalBytesSent);
          console.log('预期需要上传的数据总长度', res.totalBytesExpectedToSend);
        });
      }
    });
  },

  /**
   * 提交订单
   */
  onFormSubmit: function(e){
    var formInfo = e.detail.value;
    console.log(formInfo);
    var msg = "";
    if (formInfo.name == "") {
      msg = "商品名称不能为空";
    } else if (formInfo.price == "" || parseFloat(formInfo.price) == NaN) {
      msg = "价格不合法";
    } else if (formInfo.discountPrice == "" || parseFloat(formInfo.discountPrice) == NaN) {
      msg = "折扣价不合法";
    } else if (formInfo.stock == "" || parseInt(formInfo.discountPrice) == NaN) {
      msg = "库存不合法";
    } else if (formInfo.categoryId == ""){
      msg = "菜品分类不能为空";
    } else if ((this.data.isAdd && this.data.tempFilePath == null) && (!this.data.isAdd &&(this.data.goodsInfo == null || this.data.goodsInfo.coverPicture == null))) {
      msg = "菜品封面图片不能为空";
    }

    if (msg) {
      showModel("系统提示", msg);
    } else {
      if (this.data.isAdd){
        formInfo.restaurantId = this.data.restaurantId;
        formInfo.categoryId = this.data.currCate.id;
        formInfo.categoryCode = null;
        formInfo.coverPicture = this.data.tempFilePath;
        formInfo.sortNum = 0;
        formInfo.recommendSortNum = 0;
        formInfo.createBy = this.data.managerId;
      } else {
        formInfo.id = this.data.id;
        formInfo.managerId = this.data.managerId;
        formInfo.coverPicture = this.data.tempFilePath ? this.data.tempFilePath : this.data.goodsInfo.coverPicture;
        formInfo.modifyBy = this.data.managerId;
      }
      //提交订单
      submitForm(this, formInfo);
    }
  }
})
