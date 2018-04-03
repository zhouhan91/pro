// pages/comment_list/comment_list.js
//获取应用实例
const app = getApp();
const config = require('../../config');
var qcloud = require('../common/index');

var getUserMarkLikeInfoUrl = config.newsGetUserMarkNewsInfo; //查询用户是否对新闻收藏和点赞过
var getNewsCommListUrl = config.newsGetNewsCommentList;  //获取评论列表
var publishCommentUrl = config.newsPublishComment; //发表评论
var likeCommentUrl = config.newsLikeComment; //点赞评论

var markedUrl = config.newsMark; //收藏新闻
var cancelMarkedUrl = config.newsCancelMark;  //取消收藏新闻
var likedUrl = config.newsLike; //点赞新闻

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

var loadNewsCommentList = function (that) {
  wx.showLoading({
    title: "正在加载",
    mask: true
  });

  var commentList = that.data.commentList;
  var len = commentList.length;
  var pagenum = parseInt(len / 10) + 1;

  wx.request({
    url: getNewsCommListUrl + that.data.newsId + "/" + pagenum,
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
        if (pagenum == 1) {
          that.setData({
            commentList:[]
          });
        }
        var commentList = that.data.commentList;
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
 * 加载用户收藏和点赞信息
 */
var loadUserMarkLikeInfo = function (that) {
  qcloud.login({
    success(result) {
      that.setData({
        // userInfo: result.userInfo,
        sess_id: result.sess_id
      });
      wx.request({
        url: getUserMarkLikeInfoUrl + that.data.newsId + "/" + result.sess_id,
        header: {
          'content-type': 'application/json;charset=UTF-8'
        },
        method: "GET",
        success: function (res) {
          var data = res.data;
          if (data.resultCode == "999999" && data.resultData) {
            that.setData({
              newsMarkLikeInfo: data.resultData,
              isLoading: false,
              loadingStatus: 1
            });
          } else {
            that.setData({
              isLoading: false,
              loadingStatus: 2
            });
          }
          wx.hideLoading();
        },
        fail: function (error) {
          that.setData({
            isLoading: false,
            loadingStatus: 2
          });
          wx.hideLoading();
          showModel('操作失败', error);
        },
      });
    },
    fail(error) {
      that.setData({
        isLoading: false,
        loadingStatus: 2
      });
      wx.hideLoading();
      showModel('登录失败', error);
    }
  });
};

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
      parentId: 0
    },
    method: "POST",
    success: function (res) {
      var data = res.data;
      if (data.resultCode == "999999") {
        showSuccess('评论成功');

        //加载评论列表
        loadNewsCommentList(that);

      } else {
        showFail('评论失败');
      }
      wx.hideLoading();
    },
    fail: function (error) {
      wx.hideLoading();
      showModel('操作失败', error);
    },
  });
};

var markedNews = function (that, newsId, userKey) {
  wx.showLoading({
    title: "正在处理",
    mask: true
  });

  var newsMarkLikeInfo = that.data.newsMarkLikeInfo;
  var url = "";
  if (newsMarkLikeInfo.marked == 'Y') {
    //取消收藏新闻
    url = cancelMarkedUrl + newsId + "/" + userKey;
  } else {
    //收藏新闻
    url = markedUrl + newsId + "/" + userKey;
  }
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
        newsMarkLikeInfo.marked = newsMarkLikeInfo.marked == "N" ? "Y" : "N";
        that.setData({
          newsMarkLikeInfo: newsMarkLikeInfo
        });
      } else {
        showFail('收藏失败');
      }
      wx.hideLoading();
    },
    fail: function (error) {
      wx.hideLoading();
      showModel('操作失败', error);
    },
  });
};

var likedNews = function (that, newsId, userKey) {
  var newsMarkLikeInfo = that.data.newsMarkLikeInfo;
  var url = "";
  if (newsMarkLikeInfo.marked == "Y") {
    //不支持去掉新闻点赞
    wxToast("已点赞");
  } else {
    wx.showLoading({
      title: "正在处理",
      mask: true
    });
    //点赞新闻
    url = likedUrl + newsId + "/" + userKey;
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
          newsMarkLikeInfo.liked = newsMarkLikeInfo.liked == "N" ? "Y" : "N";
          that.setData({
            newsMarkLikeInfo: newsMarkLikeInfo
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
  }
};


Page({

  /**
   * 页面的初始数据
   */
  data: {
    commentList: [],
    commentTotal: 0,
    newsMarkLikeInfo: {},

    newbottom: true,
    fabiao_focus: false,

    commentStatus: 0, //用户评论加载状态

    commentMaxLen: 2000,  //评论最大长度
    commentShowLen: 140, //评论显示字数
    commentShowNum: 3,  //评论显示条数
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('onLoad');
    //设置房源参数
    if (app.globalData.newsInfo && app.globalData.newsInfo.newsId) {
      this.setData({
        newsId: app.globalData.newsInfo.newsId,
        newsInfo: app.globalData.newsInfo,
      });

      //加载评论列表
      loadNewsCommentList(this);

      //加载用户收藏点赞信息
      loadUserMarkLikeInfo(this);
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
   * 点击展开全文
   */
  onFulltextClick: function (e) {
    var commentIndex = e.currentTarget.dataset.commentIndex;
    var commentItem = e.currentTarget.dataset.commentItem;
    commentItem.isFullText = commentItem.isFullText ? false : true;
    var commentList = this.data.commentList;
    commentList[commentIndex] = commentItem;
    this.setData({
      commentList: commentList
    });
  },

  /**
   * 点击评论列表项，进入评论详情
   */
  onCommentDetailClick: function (e) {
    console.log("onCommentDetailClick");
    var commentItem = e.currentTarget.dataset.commentItem;
    app.globalData.commentItem = commentItem;
    wx.navigateTo({
      url: '../comment_details/comment_details?newsId=' + this.data.newsId,
    })
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
   * 点击收藏
   */
  onMarkedClick: function (e) {
    var that = this;
    qcloud.login({
      success(result) {
        that.setData({
          sess_id: result.sess_id
        });
        markedNews(that, that.data.newsId, result.sess_id);
      },
      fail(error) {
        wx.hideLoading();
        showModel('登录失败', error);
      }
    });
  },

  /**
   * 点赞
   */
  onLikedClick: function (e) {
    var that = this;
    qcloud.login({
      success(result) {
        that.setData({
          sess_id: result.sess_id
        });
        likedNews(that, that.data.newsId, result.sess_id);
      },
      fail(error) {
        wx.hideLoading();
        showModel('登录失败', error);
      }
    });
  },
})