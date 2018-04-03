// pages/search/search.js

//获取应用实例
const app = getApp();
const config = require('../../config');


var hotCitysUrl = config.idxHotCitysUrl;//搜索城市热词列表
var hotSchoolUrl = config.idxHotSchoolsUrl;//搜索学校热词列表

var communitysUrl= config.idxCommunitysUrl;//根据关键词查询房源列表
var comDistrictListUrl = config.comDistrictListByCityIdUrl;  //根据城市id查询区列表
var comCountryListUrl = config.comCountryListUrl;//获取国家列表
var comCitysByCountryIdUrl = config.comCitysByCountryIdUrl;//根据国家id获取城市列表
var comCityDetailUrl = config.comCityDetailUrl;//根据城市id读取房源列表

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

// arrA + arrB 去重
var duplicateRemoval = function (arrA, arrB) {
  var newArray = []
  if (arrB == null || arrB.length == 0) {
    return arrA;
  }
  if (arrA == null || arrA.length == 0) {
    return arrB;
  }

  arrB.forEach(function (itemB, indexB) {
    console.log("----> itemB.id:" + itemB.id);
    var isHave = false;
    arrA.forEach(function (itemA, indexA) {
      if (itemB.id == itemA.id) { //已经存在
        console.log("     ----> itemA.id:" + itemA.id);
        isHave = true;
      }
    });
    if (!isHave) {
      newArray.push(itemB);
    }
  });

  arrA = arrA.concat(newArray);

  return arrA
};

//调用API向本地缓存存入数据,如果keyword为空，则情况列表
var saveStorageData = function(that, key, value){
    var storageData = wx.getStorageSync(key) || []
    if (value){
      if (value != '' && storageData.indexOf(value) < 0){
        storageData.push(value);
        wx.setStorageSync(key, storageData);
      }
    } else {
      wx.setStorageSync(key, []);
    }
    //调用API向本地缓存存入数据显示历史搜索记录
    var historyList = loadStorageData(key);
    that.setData({
      historyList: historyList
    });
};

//加载指定key的缓存数据
var loadStorageData = function(key) {
  var storageData = wx.getStorageSync(key) || []
  return storageData;
}

var initFilterColumns = function(that) {
  that.setData({
    keyword: null,  //输入的关键字
    choiceCountry: null, //被选择的国家信息 item,
    choiceCity: null, //被选择的城市信息 item
    districtPickerIndex: 0, //城区选择器默认值
    sourcePickerIndex: 0, //房源选择器默认值

    keywordColumn: {
      type: null,
      leaseModel: null,
      leaseType: null,
      bathroomType: null,
      districtCode: null,
      sourceType: null,
      sortColumn: null,
      sortType:null,
    },
    filterColumn: {
      type: null,
      leaseModel: null,
      leaseType: null,
      bathroomType: null,
      districtCode: null,
      sourceType: null,
      sortColumn: null,
      sortType: null,
    }
  });
}

//加载搜索城市热词列表
var loadHotCityList = function(that){
  wx.request({
    url: hotCitysUrl,
    dota: {},
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    success: function (res) {
      var data = res.data;
      that.setData({
        hotCityList: data.resultData
      })
    }
  })
};

//加载搜索学校热词列表
var loadHotSchoolList = function (that) {
  wx.request({
    url: hotSchoolUrl,
    header: {
      'content-type': 'application/json;charset=UTF-8'
    },
    success: function (res) {
      var data = res.data;
      that.setData({
        hotSchoolList: data.resultData
      })
    }
  });
};
 
var loadDistrictList = function(that, withLoading){
  console.log(that.data.cityId);
//  if (that.data.searchType && that.data.searchType == 'city' && that.data.districtList == null || that.data.districtList.length == 0){
    wx.request({
      url: comDistrictListUrl + that.data.cityId,
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        var data = res.data;
        if (data.resultCode == "999999" && data.resultData.length > 0) {
          var districtNameList = [];
          var districtList = [];
          districtList.push({
            id: -1,
            name: "all",
            chineseName: "全部城区"
          });
          districtList = districtList.concat(data.resultData);
          console.log(districtList);
          districtList.forEach(function(item, idx){
            districtNameList.push(item.chineseName);
          });
          that.setData({
            districtList: districtList,
            districtNameList: districtNameList
          });
        }else{
          that.setData({
            districtList:'',
            districtNameList:''
          });
        }
        if (withLoading) {
          wx.hideLoading();
        }
      },
      fail: function (error) {
        if (withLoading) {
          wx.hideLoading();
        }
      }
    });
//  } else {
//    if (withLoading) {
//      wx.hideLoading();
//    }
//  }
  
};

//根据关键字搜索房源列表
var loadCommunityList = (that, keyword, withExt, isMore) => {
  //输入值为空返回第一个内容块（即搜索页显示默认信息模块）
  if(!isMore) {
    //更改筛选条件时，清空房源列表
    that.setData({
      communityList:[]
    });
  }

  if (keyword == null || keyword == '') {
    that.setData({
      isCancel: true,
      keyword: null,
      searchStatus: 0,
    });
  } else {      //输入值不为空且返回到第二个内容块
    that.setData({
      isCancel: false,
      keyword: keyword
    });

    wx.showLoading({
      title: "正在加载",
      mask: true
    });

    var params = {};
    if(withExt) {
      params = that.data.keywordColumn;
    }
    params.keyWords = that.data.keyword;
    if (that.data.cityCode){
      params.cityCode = that.data.cityCode;
    }
    

    var len = that.data.communityList.length;
    var pagenum = parseInt(len / 10) + 1;
    console.log(params);
    console.log(pagenum);
    //使用关键词搜索商品
    wx.request({
      url: communitysUrl + pagenum,
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      method: "POST",
      data: params,
      success: function (res) {
        var data = res.data;
        console.log(data);
        if (data.resultCode == "999999" && data.resultData.length > 0) {
          that.setData({
            communityList: data.resultData,
            searchStatus: 1,
          });

          //调用API向本地缓存存入数据  
          saveStorageData(that,"SearchKeywordHistory", keyword);
        } else {
          that.setData({
            communityList: [],
            searchStatus: 2,
          });
        }

        //第一页时加载城市区域信息
        loadDistrictList(that, true);

      },
      fail: function (res) {
        wx.hideLoading();
      },
    });
  }
};

//根据国家id获取城市列表
var loadCityListByCountryId = function (that, countryId){
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
        that.setData({
          cityList: data.resultData,
        });
      } else {
        showModel('加载失败', "没有数据");
      }
    },
    fail: function(msg){
      showModel('加载失败', "请求没有包含会话响应，请确保服务器处理");
    }
  })
};

//加载国家列表
var loadCountryList = function (that){
  var countryList = that.data.countryList || [];
  if (countryList.length > 0) {
    //onLoad中加载完成，不需要重新加载
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
          that.setData({
            countryList: data.resultData,
            choiceCountry: data.resultData[0],
          });
          console.log(that.data.countryList);

          //默认加载第一个国家的城市信息
          loadCityListByCountryId(that, that.data.countryList[0].id);
        } else {
          showModel('加载失败', "没有数据");
        }       
      },
      fail: function(msg) {
        showModel('加载失败', "请求没有包含会话响应，请确保服务器处理");
      }
    });
  }
};

var loadMoreCommunity = (that) => {
  var len = that.data.communityList.length;
  console.log(len);
  var pagenum = parseInt(len / 10) + 1;
  var start = len % 10;
  var params = {};
  params = that.data.keywordColumn;
  params.keyWords = that.data.keyword;
  if (that.data.cityCode) {
    params.cityCode = that.data.cityCode;
  }
  params.pageNum = pagenum;

  //使用关键词搜索商品
  wx.request({
    url: communitysUrl + pagenum,
    header: {
      'content-type': 'application/json;charset=UTF-8', // 默认值
    },
    method: "POST",
    data: params,
    success: function (res) {
      var data = res.data;
      console.log(data);
      if (data.resultCode == "999999" && data.resultData.length > 0) {
        var oldLen = that.data.communityList.length;
        var communityList = duplicateRemoval(that.data.communityList, data.resultData);
        var newLen = communityList.length;
        that.setData({
          // communityList: that.data.communityList.concat(data.resultData),
          communityList: communityList,
          isLoading: false,
          searchStatus: oldLen < newLen ? 1 : 2,
        });

        //调用API向本地缓存存入数据  
        saveStorageData(that, "SearchKeywordHistory", that.data.keyword);
      } else {
        that.setData({
          isLoading: false,
          searchStatus: 2,
        });
      }
    },
    fail: function (res) {
      that.setData({
        isLoading: false,
        searchStatus: 2,
      });
    },
  });
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    historyList: [],    //历史搜索列表
    hotCityList: [],   //城市热词列表
    hotSchoolList: [],  //学校热词列表
  
    isCancel: true, //是否显示取消按钮，true显示取消按钮，false显示切换城市按钮
    switchCity: false, //是否显示切换城市模块，true显示；false隐藏
    searchStatus: 0,    //搜索状态值，0,默认暂未搜索；1,搜索完成且有数据；2,搜索完成且没有数据
    isFilter: false,  //是否显示筛选条件
    isPrice: false,   //是否按价格排序

    communityList: [],  //根据关键字搜索到的房源信息
    districtList: [],  //城市对应城区
    countryList: [],//国家名字列表
    cityList: [], //城市列表

    keyword: null,  //输入的关键字
    searchType: null, //搜索类型，city
    pageNum: 1,
    choiceCountry: null, //被选择的国家信息 item,
    choiceCity: null, //被选择的城市信息 item
    districtPickerIndex: 0, //城区选择器默认值
    sourcePickerIndex: 0, //房源选择器默认值

    districtList: [], //城区列表
    sourceTypeList: ['全部房源','中介','个人'], //房源
    filterColumns: {
      houseCategory: ['公寓', '民居'],
      leaseTerm: ['短租','月租'],
      houseType: ['单人卧室','多人卧室','整房整套'],
      bathroomType: ['独立卫浴','公共卫浴']
    },
    keywordColumn:{
      type: null,
      leaseModel: null,
      leaseType: null,
      bathroomType: null,
      districtCode: null,
      sourceType: null,
      sortColumn: null,
      sortType:null,
    },
    filterColumn:null
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      filterColumn: this.data.keywordColumn,
    });
    var that = this;
    console.log(options);
    console.log(options.id);
    if (options.id) {
      that.setData({
        keyword: options.keyword,
        cityId: options.id,
        cityCode: options.cityCode,
        searchType: options.searchType
      });
    }
    
    //根据关键字搜索租房信息
    loadCommunityList(that, options.keyword, false, false);

    //加载国家列表
    loadCountryList(that);

    //调用API向本地缓存存入数据显示历史搜索记录
    var historyList = loadStorageData('SearchKeywordHistory');
    that.setData({
      historyList: historyList
    });

    //加载热门城市热词
    loadHotCityList(that);

    //加载热门学校热词
    loadHotSchoolList(that);
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
    this.setData({
      isLoading: true
    });
    loadMoreCommunity(this);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  //搜索框获得焦点事件
  onSearchFocus: function(e){
    this.setData({
      isFilter: false,  //是否显示筛选条件
    });
  },

  //搜索框点击搜索或输入回车时
  onSearchClick: function (e) {
    var that = this
    var keyword = e.detail.value;
    this.setData({
      searchType: '',
      communityList: [],
      cityCode: null
    });
    //清空筛选条件
    initFilterColumns(that);
    loadCommunityList(that, keyword, false, false);
  },

  //返回按钮-点击事件
  onGoBackClick: function (e) {
    this.setData({
      isFilter: false,  //是否显示筛选条件
    });
    wx.navigateBack();
  },

  //切换城市按钮-点击事件
  onSwitchCityClick: function () {
    //加载国家列表
    loadCountryList(this);

    this.setData({
      isFilter: false,  //是否显示筛选条件
      switchCity: !this.data.switchCity,
    })
  },

  //删除历史记录 
  onDelHistoryClick: function () {
    wx.showModal({
      title: '提示',
      content: '是否删除历史搜索',
      success: (res) => {
        if (res.confirm) {
          saveStorageData(this, "SearchKeywordHistory", null);
        }
      }
    })
  },

  //点击历史搜索项搜索
  onHistoryItemClick: function(e){
    var keyword = e.currentTarget.dataset.historyName;
    this.setData({
      keyword: keyword
    });
    var that = this;
    loadCommunityList(that, keyword, false, false);
  },

  //点击热门城市项搜索
  onHotCityItemClick: function(e){
    var keyword = e.currentTarget.dataset.cityName;
    this.setData({
      keyword: keyword
    });
    var that = this;
    loadCommunityList(that, keyword, false, false);
  },

  //点击热门学校项搜索
  onHotSchoolItemClick:function(e){
    var keyword = e.currentTarget.dataset.schoolName;
    this.setData({
      keyword: keyword
    });
    var that = this;
    loadCommunityList(that, keyword, false, false);
  },

  //点击国家列表项，获取城市列表
  onCountryItemClick: function (e) {
    var that = this;
    var country = e.currentTarget.dataset.countryItem;
    that.setData({
      choiceCountry: country,
    });
    loadCityListByCountryId(that, country.id);
  },

  //点击城市列表项，搜索房源信息
  onCityItemClick: function(e){
    var city = e.currentTarget.dataset.cityItem;
    var that = this;
    console.log(city)
    that.setData({
      cityId: city.id,
      choiceCity: city,
      cityCode: city.code,
      communityList: [],
      switchCity: false,
      searchType: 'city'
    });
    console.log('城市id')
    console.log(that.data.cityId)
    loadCommunityList(that, city.chineseName, false, false);
    //loadDistrictList(that.data.cityId,false);
  },

  //点击城区选项时-监听事件
  bindDistrictPickerChange: function(e){
    var districtPickerIndex = parseInt(e.detail.value);
    if (this.data.districtList.length > 0) {
      this.data.filterColumn.districtCode = this.data.districtList[districtPickerIndex].code;
      this.setData({
        filterColumn: this.data.filterColumn,
        keywordColumn: this.data.filterColumn,
        districtPickerIndex: districtPickerIndex
      });
    }
    this.setData({
      isFilter: false,  //是否显示筛选条件
    });
    //搜索房源-附带筛选条件
    loadCommunityList(this, this.data.keyword, true, false);
  },

  //点击房源选项时-监听事件
  bindSourcePickerChange: function(e){
    var sourcePickerIndex = parseInt(e.detail.value);
    this.data.filterColumn.sourceType = this.data.sourceTypeList[sourcePickerIndex] == "全部房源" ? "" : this.data.sourceTypeList[sourcePickerIndex];
    this.setData({
      filterColumn: this.data.filterColumn,
      keywordColumn: this.data.filterColumn,
      isFilter: false,  //是否显示筛选条件
    });

    //搜索房源-附带筛选条件
    loadCommunityList(this, this.data.keyword, true, false);
  },

  onDistrictPickerClick: function(e){
    this.setData({
      isFilter: false
    });
    loadDistrictList(this, false);
  },

  onSourcePickerClick: function(e){
    this.setData({
      isFilter: false
    });
  },

  //点击价格按钮，进行排序
  onPriceClick: function(e){
    var sourcePickerIndex = parseInt(e.detail.value);
    
    var isprice = true;
    this.data.filterColumn.sortColumn = isprice ? "discount_price" : null;
    this.data.filterColumn.sortType = this.data.filterColumn.sortType == "desc" ? "asc" : "desc";
    this.setData({
      isPrice: isprice,
      keywordColumn: this.data.filterColumn,
      isFilter: false,  //是否显示筛选条件
    });

    //搜索房源-附带筛选条件
    loadCommunityList(this, this.data.keyword, true, false);
  },

  //点击筛选按钮
  onFilterClick: function(e) {
    var isfilter = !this.data.isFilter;
    if (isfilter) {
      this.setData({
        filterColumn: this.data.keywordColumn
      });
    }
    this.setData({
      isFilter: isfilter
    });

  },

  //筛选 类型 监听事件
  houseCategoryChange: function(e) {
    this.data.filterColumn.type = e.detail.value;
    this.setData({
      filterColumn: this.data.filterColumn,
    });
  },
  //筛选 租期 监听事件
  leaseTermChange: function(e){
    this.data.filterColumn.leaseModel = e.detail.value;
    this.setData({
      filterColumn: this.data.filterColumn,
    });
  },
  //筛选 房型 监听事件
  houseTypeChange: function(e){
    this.data.filterColumn.leaseType = e.detail.value;
    this.setData({
      filterColumn: this.data.filterColumn,
    });
  },
  //筛选 卫浴类型 监听事件
  bathroomTypeChange: function(e){
    this.data.filterColumn.bathroomType = e.detail.value;
    this.setData({
      filterColumn: this.data.filterColumn,
    });
  },

  //点击筛选确定按钮
  onFilterConfirmClick: function(e){
    this.setData({
      isFilter: false,
      keywordColumn: this.data.filterColumn,
    });

    //搜索房源-附带筛选条件
    loadCommunityList(this, this.data.keyword, true, false);
  },

  //点击房源列表项，调转至房源详情
  onNavToDetailClick: function(e){
    var item = e.currentTarget.dataset.communityItem;
    //将跳转的房源项保存到全局变量中
    app.globalData.communityItem = item

    //跳转至详情页
    wx.navigateTo({
      url: '../house_detail/house_detail?communityId=' + item.id,
    })
  }
})