/**
 * 小程序配置文件
 */

// 此处主机域名是腾讯云解决方案分配的域名
// 小程序后台服务解决方案：https://www.qcloud.com/solution/la

// var host = "i.wemecity.net"
var host = "i.wemecity.net"

var config = {

    // 下面的地址配合云端 Server 工作
    host,

    // 登录地址，用于建立会话
    iLoginUrl: `https://${host}/user/login`,
  
    //检查登录是否有效
    iCheckSessionUrl: `https://${host}/user/checkSession`,

    //根据session_key获取用户信息
    iUserInfoUrl: `https://${host}/user/readUserInfo/`,

    //修改用户信息
    iUpdateUserInfoUrl: `https://${host}/user/updateUserInfo`,

    //上报用户信息
    iReportUserInfoUrl: `https://${host}/user/reportUserInfo`,

    //查询用户可用优惠券列表
    iUsableCouponsUrl: `https://${host}/coupon/queryUsableCoupons`,

    //查询用户收藏的房源列表
    iMarkedListUrl: `https://${host}/community/queryMarkedList/`,

    //查询租房订单列表
    iCommunityOrdersUrl: `https://${host}/community/queryCommunityOrderList/`,

    //根据租房订单id读取租房订单详情
    iCommunityOrderByIdUrl: `https://${host}/readCommunityOrderById/`,

    //根据租房订单编码读取租房订单详情
    iCommunityOrderByCodeUrl: `https://${host}/readCommunityOrderByCode/`,

    //查询用户收藏新闻列表
    iMarkedNewsUrl: `https://${host}/news/queryMarkedNews/`,

    //查询用户好吃订单列表
    iQueryMyOrderListUrl: `https://${host}/catering/order/queryMyOrderList/`,

    //用户取消好吃订单
    iCancelMyOrderUrl: `https://${host}/catering/order/cancelMyOrder/`,

    //发表好吃评论
    iPublishCommentUrl: `https://${host}/catering/comment/publish`,

    //读取用户好吃评论信息
    iReadCommentUrl: `https://${host}/catering/comment/readComment/`,

    //查询首页推荐城市及房源数列表
    idxRecommendCitysUrl: `https://${host}/community/queryCityCommunityIndexList`,

    //查询租房首页搜索城市热词列表
    idxHotCitysUrl: `https://${host}/popularWords/queryPopularWords/communityIndexCity`,

    //查询租房首页搜索学校热词列表
    idxHotSchoolsUrl: `https://${host}/popularWords/queryPopularWords/communityIndexSchool`,

    //查询房源列表
    idxCommunitysUrl: `https://${host}/community/queryCommunityList/`,

    //读取房源详情
    idxCommunityDetailUrl: `https://${host}/community/readCommunityById/`,

    //收藏房源
    idxCommunityMarkUrl: `https://${host}/community/mark`,

    //取消收藏房源
    idxCommunityCancelMarkUrl: `https://${host}/community/cancelMark`,

    //检查是否已收藏了该房源
    idxCommunityCheckMarkUrl: `https://${host}/community/checkMarked`,

    //立即支付
    idxCommunityPayUrl: `https://${host}/community/pay`,

    //获取国家列表
    comCountryListUrl: `https://${host}/region/queryCountryList`,

    //根据国家id读取国家详情
    comCountryDetailUrl: `https://${host}/region/readCountry/`,

    //根据国家id获取城市列表
    comCitysByCountryIdUrl: `https://${host}/region/queryCityListByCountryId/`,

    //根据城市id读取城市详情
    comCityDetailUrl: `https://${host}/region/readCity/`,

    //根据城市id查询区列表
    comDistrictListByCityIdUrl: `https://${host}/region/queryDistrictListByCityId/`,

    //根据区id读取区详情
    comDistrictDetailUrl: `https://${host}/region/readDistrict/`,

    //获取汇率
    comGetExchangeRateUrl: `https://${host}/components/exchangeRate/getExchangeRate`,

    //上传图片
    comUploadFileUrl: `https://admin.wemecity.net/components/fileUpload`,

    //获取头条一级导航栏
    newsGetNavigations: `https://${host}/news/getNavigations`,

    //根据一级导航获取二级导航
    newsGetSubNavigationList: `https://${host}/news/querySubNavigationList/`,

    //获取首页新闻信息，返回3条
    newsGetIndexList: `https://${host}/news/queryIndexList/`,

    //获取新闻列表
    newsGetNewsList: `https://${host}/news/queryNewsList/`,

    //读取新闻详情
    newsReadNewsInfo: `https://${host}/news/readNewsInfo/`,

    //新闻详情读取评论概要信息
    newsReadNewsCommentOnDetail: `https://${host}/news/readNewsCommentOnDetail/`,

    //查询用户是否对新闻收藏和点赞过
    newsGetUserMarkNewsInfo: `https://${host}/news/getUserMarkNewsInfo/`,

    //收藏新闻
    newsMark: `https://${host}/news/mark/`,

    //取消收藏新闻
    newsCancelMark: `https://${host}/news/cancelMark/`,

    //点赞新闻
    newsLike: `https://${host}/news/like/`,

    //新闻详情获取延伸阅读
    newsGetExtendedNewsList: `https://${host}/news/queryExtendedNewsList`,

    //获取评论列表
    newsGetNewsCommentList: `https://${host}/news/queryNewsCommentList/`,

    //点赞评论
    newsLikeComment: `https://${host}/news/likeComment/`,

    //发表评论
    newsPublishComment: `https://${host}/news/publishComment`,

    //获取子评论列表
    newsGetNewsSubCommentList: `https://${host}/news/querySubNewsCommentList/`,

    //获取巴别塔列表
    newsQueryBabietaList: `https://${host}/news/queryBabietaList/`,

    //获取首页餐饮一级分类
    footindex: `https://${host}/catering/category/queryBaseCategoryList`,

    //获取首页餐饮子分类
    footindexzi: `https://${host}/catering/category/querySubCategoryList/`,

    //获取广告
    getfootbanner: `https://${host}/catering/recommend/queryCateringRecommendList`,

     //获取首页推荐商户列表
    getshoplist: `https://${host}/catering/restaurant/queryRecommendRestaurantList`,

    //获取商户主页列表
    getshoplistsj: `https://${host}/catering/restaurant/queryCateringRestaurantList/`,

    //获取商户主页信息
    getshopdetail: `https://${host}/catering/restaurant/readCateringRestaurantInfo/`,

    //获取商户信息
    getshopdetails: `https://${host}/catering/restaurant/readCateringRestaurant/`,

    //获取菜单列表
    getshopfoodmenu: `https://${host}/catering/goodsCategory/queryGoodsCategoryList/`,

    //根据菜单ID获取菜品列表
    getshopfood: `https://${host}/catering/goods/queryGoodsList/`,

    //根据商品id读取商品详情
    readGoodsInfoUrl: `https://${host}/catering/goods/readGoodsInfo/`,

    //获取店铺统一配送地址
    queryRestLocListUrl: `https://${host}/catering/location/queryRestaurantLocationList/`,

    //立即支付
    cateringOrderPayUrl: `https://${host}/catering/order/pay`,

    //查询用户收货人列表
    queryCateringContactsListUrl: `https://${host}/catering/contacts/queryCateringContactsList/`,

    //读取收货人信息
    readCateringContactsUrl: `https://${host}/catering/contacts/readCateringContacts/`,

    //新增收货人信息
    insertCateringContactsUrl: `https://${host}/catering/contacts/insertCateringContacts`,

    //修改收货人信息
    updateCateringContactsUrl: `https://${host}/catering/contacts/updateCateringContacts`,

    //删除收货人信息
    deleteCateringContactsUrl: `https://${host}/catering/contacts/deleteCateringContacts/`,

    //读取用户默认收货人信息
    queryDefCateringContactsUrl: `https://${host}/catering/contacts/queryDefaultCateringContacts/`,

    //读取店铺评论列表
    queryCommentListByRestIdUrl: `https://${host}/catering/comment//queryCommentListByRestaurantId/`,

    //检查是否新会员
    checkNewMemberUrl: `https://${host}/catering/restaurant/checkNewMember/`,
};

module.exports = config
