//获取应用实例
const app = getApp();
const config = require('../../config');
const util = require('../../utils/util.js');

const queryBaseCategoryListUrl = config.queryBaseCategoryListUrl;//获取一级导航
const querySubCategoryListUrl = config.querySubCategoryListUrl;//获取子分类
const readRestInfoUrl = config.readRestInfoUrl; //读取店铺信息
const updateRestInfoUrl = config.updateRestInfoUrl; //修改店铺信息
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

//加载店铺信息
var loadRestInfo = function (that) {
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  wx.request({
    url: readRestInfoUrl + that.data.id,
    method: "GET",
    success: function (res) {
      if (res.data.resultCode == "999999") {
        console.log(res.data.resultData);
        var tempFilePaths = [];
        if (res.data.resultData.coverPicture) {
          tempFilePaths.push(res.data.resultData.coverPicture);
        }

        that.setData({
          restInfo: res.data.resultData,
          openFlag: res.data.resultData.openFlag,
          pauseFlag: res.data.resultData.pauseFlag,
          tempFilePaths: tempFilePaths
        });
        console.log(res.data.resultData.latitude);
        if (!res.data.resultData.latitude || res.data.resultData.latitude == 0) {
          console.log(res.data.resultData.latitude);
          //showModel('系统提示', "请完善地址信息");
        } else {
          var locationInfo = {
            latitude: res.data.resultData.latitude,
           // longitude: res.data.resultData.longitude,
          }
          that.setData({
            locationInfo: locationInfo
          });
        }
        var restaurantCateList = that.data.restaurantCateList;
        console.log(restaurantCateList);
        console.log(res.data.resultData.parentCategoryId);
        if (res.data.resultData.parentCategoryId && res.data.resultData.parentCategoryId > 0){
          restaurantCateList.forEach(function (item, idx) {
            if (item.id == res.data.resultData.parentCategoryId) {
              that.setData({
                currRestCateIndex: idx,
              });
            }
          });
        } else {
          that.setData({
            currRestCateIndex: 0, //首次设置默认值
          });
        }

        //获取一级导航子分类
        querySubCategoryList(that);

        var serviceTimeCode = res.data.resultData.serviceTimeCode;
        if (serviceTimeCode) {
          var stSplits = serviceTimeCode.split(")-(");
          console.log(stSplits);
          var cw = stSplits[0].substring(1, stSplits[0].length);
          var ct = stSplits[1].substring(0, stSplits[1].length - 1);
          var cwo = cw.split(",");
          var openTime = ct.split("-")[0];
          var closeTime = ct.split("-")[1];
          var weeks = that.data.weeks;
          cwo.forEach(function(item, i){
            weeks.forEach(function(w,j){
              if (w.id == item) {
                w.checked = true;
              }
            });
          });
          that.setData({
            weeks: weeks,
            openTime: openTime,
            closeTime: closeTime
          })
        }
        
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

//获取一级导航
var queryBaseCategoryList = function (that) {
  wx.request({
    url: queryBaseCategoryListUrl,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log('一级导航')
      console.log(data);
      if (data.resultCode == "999999" && data.resultData) {
        that.setData({
          restaurantCateList: data.resultData
        });

        //加载店铺信息
        loadRestInfo(that);
      } else {
        showModel("系统提示", data.resultDesc);
      }
    },
    fail: function (error) {

    }
  })
};

//获取一级导航子分类
var querySubCategoryList = function (that) {
  wx.request({
    url: querySubCategoryListUrl + that.data.restaurantCateList[that.data.currRestCateIndex].id,
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
          foodsCateList: data.resultData
        });
        var foodsCateList = that.data.foodsCateList;
        foodsCateList.forEach(function (item, idx) {
          if (item.id == that.data.restInfo.categoryId) {
            that.setData({
              currFoodsCateIndex: idx,
            });
          }
        });
      }
    },
    fail: function (error) {

    }
  })
};

var submitForm = function(that, params){
  wx.request({
    url: updateRestInfoUrl,
    data: params,
    method: "POST",
    success: function(res){
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        showSuccess("操作成功");
      } else {
        showModel("系统提示", res.data.resultDesc);
      }
    },
    fail: function(err){
      showModel("系统提示", "操作失败，请稍后重试");
    }
  })
}

var getLacationInfo = function(that) {
  console.log("getLacationInfo");
  //获取当前位置信息
  wx.getLocation({
    type: "gcj02",
    success: function (res) {
      console.log(res);
      var markers = [{
        iconPath: "",
        id: 0,
        latitude: res.latitude,
        //longitude: res.longitude,
        width: 50,
        height: 50
      }];
      that.setData({
        locationInfo: res,
        markers: markers
      })
    },
    fail: function (err) {
      showModel("系统提示", err);
    }
  });
}

var getLacationInfoWithGoogle = function (that, address, okFunction) {
  //获取当前位置信息
  wx.request({
    url: 'https://hkservice.wemecity.net/components/googleMap/geoCode/address/' + address,
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
        console.log(results[0]);
        var locationInfo = {};
        //locationInfo.longitude = results[0].geometry.location.lng;
        locationInfo.latitude = results[0].geometry.location.lat;
        that.setData({
          locationInfo: locationInfo,
        });
        okFunction();
      } else {
        //showModel("系统提示", "定位失败，请稍后重试")
      }
    },
    fail: function(err){
      //showModel("系统提示", "定位失败，请稍后重试")
    }
  })
}

Page({
  switch1Change: function (e) {
    console.log('switch1 发生 change 事件，携带值为', e.detail.value)
    var openFlag = "N";
    if(e.detail.value) {
      openFlag = "Y";
    }
    this.setData({
      openFlag: openFlag
    })
  },
  switch2Change: function (e) {
    console.log('switch2 发生 change 事件，携带值为', e.detail.value)
    var pauseFlag = "N";
    if (e.detail.value) {
      pauseFlag = "Y";
    }
    this.setData({
      pauseFlag: pauseFlag
    })
  },
  data: {
    tempFilePaths:[],//图片
    //商户类型
    currRestCateIndex: -1,
    restaurantCateList: [{ id: "1", name: "餐饮" }, { id: "2", name: "饮品果汁" }, { id: "3", name: "果蔬生鲜" }, { id: "4", name: "商超便利" }],
    //餐饮类型
    currFoodsCateIndex: -1,
    foodsCateList: [],
    weeks: [{ id: "0", name: "日" }, { id: "1", name: "一" }, { id: "2", name: "二" }, { id: "3", name: "三" }, { id: "4", name: "四" }, { id: "5", name: "五" }, { id: "6", name: "六" }],
  },

  onLoad: function (options) {
    if(options.id){
      this.setData({
        id: options.id,
        name: options.name,
        managerId: options.managerId
      });
    } else {
      //测试
      this.setData({
        id: 2,
        name: "你的菜",
        managerId: 4
      });
    }

    //加载商户类型
    queryBaseCategoryList(this);
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
            console.log(res.data.resultCode);
            console.log(res.data.resultData);
            if (res.data.resultCode == "999999") {
              _this.setData({
                tempFilePath: res.data.resultData,
                // tempFilePath: "https://admin.wemecity.net/upload/goods/92a61193bbd54f7a997b659d8a37d062.png",
              });
            } else {
              showModel(_this, res.data.resultDesc);
            }
          },
          fail: function (err) {
            showModel(_this, "上传失败，请稍后重试");
          },
          complete: function () {
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
   * 商户类型改变时触发
   */
  bindRestCateChange: function(e){

    this.setData({
      currRestCateIndex: e.detail.value,
    });
    querySubCategoryList(this);
  },

  /**
   * 餐饮类型改变时触发
   */
  bindFoodCateChange: function(e){
    this.setData({
      currFoodsCateIndex: e.detail.value,
    });
  },

  /**
   * 开业时间改变时触发
   */
  bindOpenTimeChange: function(e){
    this.setData({
      openTime: e.detail.value
    })
  },

  /**
   * 闭业时间改变时触发
   */
  bindCloseTimeChange:function(e){
    this.setData({
      closeTime: e.detail.value
    })
  },

  /**
   * 周几改变时触发
   */
  bindWeekDayChange: function(e){
    console.log(e.detail.value);
    this.setData({
      choiceWeeks: e.detail.value
    })
  },

  /**
   * 取消返回主页
   */
  onCancelClick: function(e){
    wx.navigateBack({
      
    });
  },

  /**
   * 提交表单
   */
  onFormSubmit: function(e){
    var formInfo = e.detail.value;
    console.log(formInfo);
    var msg = "";
    if (formInfo.name == ""){
      msg = "商家名称不能为空";
    } else if (this.data.tempFilePath == null && (this.data.restInfo == null || this.data.restInfo.coverPicture == null)) {
      msg = "LOGO不能为空";
    } else if (this.data.currRestCateIndex == -1) {
      msg = "商户类型不能为空";
    } else if (this.data.currFoodsCateIndex == -1 && this.data.foodsCateList.length != 0){
      msg = "餐饮类型不能为空";
    // } else if (!this.data.openTime || !this.data.closeTime || !choiceWeeks || choiceWeeks.length == 0 ) {
    //   msg = 
    // }
    } else if (formInfo.managerPhone == ""){
      msg = "店主电话不能为空";
    } else if (formInfo.phone == ""){
      msg = "客服电话不能为空";
    } else if (formInfo.email == "") {
      msg = "EMAIL不能为空";
    } else if (formInfo.address == "") {
      msg = "地址不能为空";
    }

    if (msg != ""){
      showModel("系统提示", msg);
    } else {
      var that = this;
      formInfo.id = that.data.id;
      formInfo.managerId = that.data.managerId;
      formInfo.parentCategoryId = that.data.restaurantCateList[that.data.currRestCateIndex].id;
      formInfo.parentCategoryCode = that.data.restaurantCateList[that.data.currRestCateIndex].code;
      if (that.data.foodsCateList.length!=0){
        formInfo.categoryId = that.data.foodsCateList[that.data.currFoodsCateIndex].id;
        formInfo.categoryCode = that.data.foodsCateList[that.data.currFoodsCateIndex].code;
      }
     
      formInfo.coverPicture = that.data.tempFilePath ? that.data.tempFilePath : that.data.restInfo.coverPicture;

      if (that.data.openTime && that.data.closeTime && that.data.choiceWeeks) {
        var serviceTimeCode = "(";
        var serviceTimeDesc = "星期";
        var weeks = this.data.weeks;
        this.data.choiceWeeks.forEach(function (item, idx) {
          if (idx == 0) {
            serviceTimeCode = serviceTimeCode + item;
          } else {
            serviceTimeCode = serviceTimeCode + "," + item;
          }
          weeks.forEach(function (w, idx) {
            if (w.id == item) {
              serviceTimeDesc = serviceTimeDesc + w.name;
            }
          });

        });
        serviceTimeCode = serviceTimeCode + ")-(" + that.data.openTime + "-" + that.data.closeTime + ")";
        serviceTimeDesc = serviceTimeDesc + that.data.openTime + "-" + that.data.closeTime;

        formInfo.serviceTimeCode = serviceTimeCode;
        formInfo.serviceTimeDesc = serviceTimeDesc;
      }

     // formInfo.  = that.data.locationInfo.longitude;
     // formInfo.latitude = that.data.locationInfo.latitude;
      formInfo.openFlag = that.data.openFlag;
      formInfo.pauseFlag = that.data.pauseFlag;
      console.log('测试这是')
      console.log(formInfo);
      submitForm(that, formInfo);
      //getLacationInfoWithGoogle(this, formInfo.address, function(){
        
      //});

      
    }
  }
})
