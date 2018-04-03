// pages/error/error.js

//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

var getUserMarkLikeInfoUrl = config.newsGetUserMarkNewsInfo; //查询用户是否对新闻收藏和点赞过
var getNewsSubCommListUrl = config.newsGetNewsSubCommentList;  //获取子评论列表
var publishCommentUrl = config.newsPublishComment; //发表评论
var likeCommentUrl = config.newsLikeComment; //点赞评论

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

var wxToast = function (text) {
  wx.showToast({
    title: text,
    icon: 'success',
    duration: 2000
  });
};

// arrA + arrB 去重
var duplicateRemoval = function (arrA, arrB) {
  var newArray = []
  if (arrB == null || arrB.length == 0) {
    return arrA;
  }
  if (arrA && arrA.length == 0) {
    return arrB;
  }
  arrB.forEach(function (itemB, indexB) {
    var isHave = false;
    arrA.forEach(function (itemA, indexA) {
      if (itemB.communityId == itemA.communityId) { //已经存在
        isHave = true;
      }
    });
    if (!isHave) {
      newArray.push(itemB);
    }
  });
  arrA = arrA.concat(newArray);

  return arrA
}

/**
 * 加载评论的回复评论列表
 */
var loadNewsSubCommentList = function (that, isFresh) {
  wx.showLoading({
    title: "正在加载",
    mask: true
  });
  if (isFresh) {
    that.setData({
      commentList:[]
    });
  }
  var commentList = that.data.commentList;
  var len = commentList.length;
  var pagenum = parseInt(len / 10) + 1;

  wx.request({
    url: getNewsSubCommListUrl + that.data.commentId,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    data: {
      // navigationCode: navCode
    },
    method: "GET",
    success: function (res) {
      var data = res.data;
      console.log(data.resultData);
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        var oldLen = commentList.length;
        commentList = duplicateRemoval(commentList, data.resultData)
        var newLen = commentList.length;
        that.setData({
          commentList: data.resultData,
          commentTotal: newLen,
          isLoading: false,
          commentStatus: oldLen < newLen ? 1 : 2,
        });
      } else {
        that.setData({
          isLoading: false,
          commentStatus: 2,
        });
      }
      wx.hideLoading();
    },
    fail: function (error) {
      that.setData({
        isLoading: false,
        commentStatus: 2,
      });
      wx.hideLoading();
      showModel('操作失败', error);
    },
  });
};


/**
 * 回复新闻评论
 */
var publishComment = function (that, content) {
  wx.request({
    url: publishCommentUrl,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    data: {
      newsId: that.data.newsId,
      userKey: that.data.sess_id,
      content: content,
      parentId: that.data.commentId
    },
    method: "POST",
    success: function (res) {
      var data = res.data;
      if (data.resultCode == "999999") {
        showSuccess('评论成功');
        //更新父评论的回复数
        var commentItem = that.data.commentItem;
        commentItem.replyCount = commentItem.replyCount + 1;
        that.setData({
          commentItem: commentItem
        });
        wx.hideLoading();
        //加载评论的回复评论列表
        loadNewsSubCommentList(that, true);
      } else {
        showFail('评论失败');
        wx.hideLoading();
      }
      
    },
    fail: function (error) {
      wx.hideLoading();
      showModel('操作失败', error);
    },
  });
};

/**
 * 点赞子评论
 */
var likedSubComment = function (that, commentId, userKey) {
  wx.showLoading({
    title: "正在处理",
    mask: true
  });
  //点赞新闻
  var url = likeCommentUrl + userKey + "/" + commentId;
  wx.request({
    url: url,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    method: "POST",
    success: function (res) {
      var data = res.data;
      if (data.resultCode == "999999") {
        showSuccess('操作成功');
        var commentList = that.data.commentList;
        commentList.forEach(function(item, index){
          if (item.id == commentId) {
            item.likeCount = item.likeCount + 1;
          }
        });
        that.setData({
          commentList: commentList
        });
      } else {
        showFail('点赞失败');
      }
      wx.hideLoading();
    },
    fail: function (error) {
      wx.hideLoading();
      showModel('操作失败', error);
    },
  });
};


Page({

  /**
   * 页面的初始数据
   */
  data: {
    commentList:[],   //子评论列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    if (options.newsId){
      this.setData({
        newsId: options.newsId,
        commentId: app.globalData.commentItem.id,
        commentItem: app.globalData.commentItem,
      });

      //加载评论的回复评论列表
      loadNewsSubCommentList(this, false);
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

  hidethis: function () {
    var hidefan = !this.data.newbottom;
    var faiao = !this.data.fabiao_focus;
    var that = this;
    that.setData({
      fabiao_focus: faiao
    })
    that.setData({
      newbottom: hidefan
    })
  },

  hidfabu: function (event) {
    var that = this;
    var thisval = event.detail.value;
    if (thisval == "" || thisval == null) {
      that.setData({
        newbottom: true
      });
    }
  },

  /**
   * 发表评论，可对评论进行回复
   * newsId	新闻id	long		否
userKey	用户标识	String		否
content	评论内容	String	500	否
parentId	父评论id	long		是	无则传0

   */
  onPublishComment: function (e) {
    var that = this;
    console.log("onPublishComment");
    var formInfo = e.detail.value;
    wx.showLoading({
      title: "正在加载",
      mask: true
    });

    if (this.data.sess_id) {
      publishComment(this, formInfo.content);
    } else {
      qcloud.login({
        success(result) {
          that.setData({
            sess_id: result.sess_id
          });
          publishComment(that, formInfo.content);
        },
        fail(error) {
          wx.hideLoading();
          showModel('登录失败', error);
        }
      });
    }
    that.setData({
      newbottom: true
    });
  },

  /**
   * 点赞子评论
   */
  onLikedClick: function (e) {
    var commentId = e.currentTarget.dataset.commentId;
    var that = this;
    qcloud.login({
      success(result) {
        that.setData({
          sess_id: result.sess_id
        });
        likedSubComment(that, commentId, result.sess_id);
      },
      fail(error) {
        wx.hideLoading();
        showModel('登录失败', error);
      }
    });
  },
})