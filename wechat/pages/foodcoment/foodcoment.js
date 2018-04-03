//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

const publishCommentUrl = config.iPublishCommentUrl; //发表好吃评论
const readCommentUrl = config.iReadCommentUrl; //读取用户好吃评论信息
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

//加载用户评论信息
var loadCommentInfo = function(that){
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  wx.request({
    url: readCommentUrl + that.data.orderId,
    method: "GET",
    success: function (res) {
      console.log(res.data);
      if (res.data.resultCode == "999999") {
        var tempFilePaths = [];
        if (res.data.resultData.lstCommentImg && res.data.resultData.lstCommentImg.length > 0) {
          res.data.resultData.lstCommentImg.forEach(function (item, idx) {
            tempFilePaths.push(item.path);
          });
        }
        that.setData({
          flag: res.data.resultData.score,
          kouwei: res.data.resultData.tasteLevel,
          huanj: res.data.resultData.environmentLevel,
          serves: res.data.resultData.serviceLevel,
          isRecomment: res.data.resultData.recommendFlag == "Y" ? true : false,
          tempFilePaths: tempFilePaths,
          comment: res.data.resultData.content,
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

//发表用户评论信息
var publishComment = function(that, formInfo){
  wx.showLoading({
    title: "加载中",
    mask: true
  });
  qcloud.login({
    success(result) {
      console.log(result);
      that.setData({
        sess_id: result.sess_id
      });
      formInfo.userKey = result.sess_id;
      wx.request({
        url: publishCommentUrl,
        method: "POST",
        data: formInfo,
        success: function (res) {
          console.log(res.data);
          if (res.data.resultCode == "999999") {
            showModel("评价成功");
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
    },
    fail(error) {
      wx.hideLoading();
      showModel('登录失败', error);
    }
  });
}

Page({
  data: {
    flag: 0,//总评分
    huanj:0,//环境
    kouwei:0,//口味
    serves:0,//服务
    tempFilePaths: [],//选中的图片
    lstCommentImg: [],//上传成功的图片
    comment: "",//评语
  },

  onLoad: function(options){
    //订单页，点击发表评论
    if (options.orderId) {
      this.setData({
        orderId: options.orderId,
        isPublish: options.commentFlag == "Y" ? false : true
      });
    }
    
    //店铺首页，点击查看评论
    // if (options.restaurantId) {
    //   this.setData({
    //     commentId: options.restaurantId,
    //     isPublish: false
    //   });
    // }
    //获取用户评论信息
    if(!this.data.isPublish) {
      loadCommentInfo(this);
    }
  },

  changeColor1: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        flag: 1
      });
      console.log('1分');
    } else {
      showModel("系统提示","当前状态不可编辑")
    }
   
  },
  changeColor2: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        flag: 2
      });
      console.log('2分');
    }
  },
  changeColor3: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        flag: 3
      });
      console.log('3分');
    }
    
  },
  changeColor4: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        flag: 4
      });
      console.log('4分');
    }

  },
  changeColor5: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        flag: 5
      });
      console.log('5分');
    }
    
  },
  changehuanjing1: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        huanj: 1
      });
      console.log('2分');
    }
  },
  changehuanjing2: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        huanj: 2
      });
      console.log('4分');
    }
  },
  changehuanjing3: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        huanj: 3
      });
      console.log('6分');
    }
  },
  changehuanjing4: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        huanj: 4
      });
      console.log('8分');
    }
  },
  changehuanjing5: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        huanj: 5
      });
      console.log('10分');
    }
  },

  kouwei1: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        kouwei: 1
      });
      console.log('2分');
    }
  },
  kouwei2: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        kouwei: 2
      });
      console.log('4分');
    }
  },
  kouwei3: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        kouwei: 3
      });
      console.log('6分');
    }
  },
  kouwei4: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        kouwei: 4
      });
      console.log('8分');
    }
  },
  kouwei5: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        kouwei: 5
      });
      console.log('10分');
    }
  },
  serves1: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        serves: 1
      });
      console.log('2分');
    }
  },
  serves2: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        serves: 2
      });
      console.log('4分');
    }
  },
  serves3: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        serves: 3
      });
      console.log('6分');
    }
  },
  serves4: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        serves: 4
      });
      console.log('8分');
    }
  },
  serves5: function () {
    if (this.data.isPublish) {
      var that = this;
      that.setData({
        serves: 5
      });
      console.log('10分');
    }
  },

  /**
   * 切换 是否推荐
   */
  switch1Change: function(e) {
    this.setData({
      isRecomment: e.detail.value,
    })
  },

  /**
   * 上传封面图片
   */
  chooseImage: function (e) {
    if (this.data.isPublish) {
      var _this = this;
      var index = e.currentTarget.dataset.idx;
      var tempFilePaths = this.data.tempFilePaths;

      wx.chooseImage({
        count: 1, // 默认9  
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有  
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有  
        success: function (res) {
          // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片  
          console.log(res);
          var newFilePath = null;
          if (!index){  //未选图片
            if (tempFilePaths.indexOf(res.tempFilePaths[0]) == -1) {  //选择图片不在已选列表中
              newFilePath = res.tempFilePaths[0];
            } else {
              showModel("系统提示", "图片不能重复添加");
            }
          } else {  //已选
            if (tempFilePaths[index] == res.tempFilePaths[0]) {
              showModel("系统提示", "图片不能重复添加");
            } else {
              if (tempFilePaths.indexOf(res.tempFilePaths[0]) == -1) {
                newFilePath = res.tempFilePaths[0];
              } else {
                showModel("系统提示", "图片不能重复添加");
              }
            }
          }


          //上传图片  busiCode=goodsCoverPicture
          if (newFilePath) {
            const uploadTask = wx.uploadFile({
              url: comUploadFileUrl, //仅为示例，非真实的接口地址
              filePath: newFilePath,
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
                if (res.data.resultCode == "999999") {
                  
                  var lstCommentImg = _this.data.lstCommentImg;
                  if (index) {
                    lstCommentImg[index] = {
                      id: "0", path: res.data.resultData
                    };
                    tempFilePaths[index] = newFilePath;
                  } else {
                    lstCommentImg.push({
                      id: "0", path: res.data.resultData
                    });
                    tempFilePaths.push(newFilePath);
                  }
                  
                  _this.setData({
                    lstCommentImg: lstCommentImg,
                    tempFilePaths: tempFilePaths,
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
        }
      });
    }
  },

  /**
   * 评语 光标离开时触发
   */
  onCommentBlur: function(e){
    console.log(e.detail.value);
    this.setData({
      comment: e.detail.value
    })
  },

  /**
   * 点击发表评论
   */
  onPublishCommentClick: function(e){
    var msg = "";
    if (this.data.flag == 0) {
      msg = "总评分没有打分";
    } else if (this.data.huanj == 0) {
      msg = "环境没有打分";
    } else if (this.data.kouwei == 0) {
      msg = "口味没有打分";
    } else if (this.data.serves == 0) {
      msg = "服务没有打分";
    } else if (this.data.comment == ""){
      msg = "评语没有写";
    }

    if (msg == ""){
      //发表评论
      var formInfo = e.detail.value;
      
      formInfo.orderId = this.data.orderId;
      formInfo.score = this.data.flag;
      formInfo.tasteLevel = this.data.kouwei;
      formInfo.environmentLevel = this.data.huanj;
      formInfo.serviceLevel = this.data.serves;
      formInfo.lstCommentImg = this.data.lstCommentImg;

      if (this.data.isRecomment) {
        formInfo.recommendFlag = "Y";
      } else {
        formInfo.recommendFlag = "N";
      }

      publishComment(this, formInfo);
    } else {
      showModel("系统提示", msg);
    }
  }
})