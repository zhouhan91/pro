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

  //获取首页餐饮一级分类
  queryBaseCategoryListUrl: `https://${host}/catering/category/queryBaseCategoryList`,

  //获取首页餐饮子分类
  querySubCategoryListUrl: `https://${host}/catering/category/querySubCategoryList/`,


  //上传图片
  comUploadFileUrl: `https://admin.wemecity.net/components/fileUpload`,

  // 注册
  iRegisterUrl: `https://${host}/catering/manager/register`,

  //登录
  iLoginUrl: `https://${host}/catering/manager/login`,

  //读取店铺信息
  readRestInfoUrl: `https://${host}/catering/restaurant/readCateringRestaurant/`,

  //修改店铺信息
  updateRestInfoUrl: `https://${host}/catering/restaurant/updateRestaurantInfo`,

  //根据店铺内商品分类获取商品列表
  queryGoodsListUrl: `https://${host}/catering/goods/queryGoodsList/`,

  //读取商品详情
  readGoodsInfoUrl: `https://${host}/catering/goods/readGoodsInfo/`,

  //新增商品
  insertGoodsUrl: `https://${host}/catering/goods/insertGoods`,

  //删除商品
  deleteGoodsUrl: `https://${host}/catering/goods/deleteGoods/`,

  //修改商品
  updateGoodsInfoUrl: `https://${host}/catering/goods/updateGoodsInfo`,

  //上架商品
  putOnLineUrl: `https://${host}/catering/goods/putOnLine/`,

  //下架商品
  putOffLineUrl: `https://${host}/catering/goods/putOffLine/`,

  //商品估清
  clearStockUrl: `https://${host}/catering/goods/clearStock/`,

  //根据店铺id获取商品分类信息
  queryGoodsCateListUrl: `https://${host}/catering/goodsCategory/queryGoodsCategoryList/`,

  //新增商品分类信息
  insertGoodsCateUrl: `https://${host}/catering/goodsCategory/insertGoodsCategory`,

  //删除商品分类信息
  deleteGoodsCateUrl: `https://${host}/catering/goodsCategory/deleteGoodsCategory/`,

  //修改商品分类信息
  updateGoodsCateUrl: `https://${host}/catering/goodsCategory/updateGoodsCategory`,

  //读取商品分类信息
  readGoodsCateInfoUrl: `https://${host}/catering/goodsCategory/readGoodsCategoryInfo/`,

  //根据店铺id读取活动信息列表
  queryDiscountListUrl: `https://${host}/catering/discount/queryDiscountList/`,

  //修改店铺活动信息
  updateDiscountListUrl: `https://${host}/catering/discount/updateDiscountInfo`,

  //获取店铺配送地址信息
  queryShopLocListUrl: `https://${host}/catering/location/queryRestaurantLocationList/`,

  //保存店铺配送信息
  saveShopLocListUrl: `https://${host}/catering/location/saveRestaurantLocationList`,

  //根据店铺id查询配送员列表
  queryCateringCourierListUrl: `https://${host}/catering/courier/queryCateringCourierList/`,

  //保存配送员列表
  saveCateringCourierListUrl: `https://${host}/catering/courier/saveCateringCourierList`,

  //删除配送员信息
  deleteCateringCourierUrl: `https://${host}/catering/courier/deleteCateringCourier/`,

  //获取商户订单统计信息
  queryOrderStatisticInfoUrl: `https://${host}/catering/order/queryStatisticsInfo/`,

  //获取商户订单列表
  queryOrderListUrl: `https://${host}/catering/order/queryOrderList`,

  //取消订单
  cancelOrderUrl: `https://${host}/catering/order/cancel`,

  //确认订单
  comfirmOrderUrl: `https://${host}/catering/order/confirm/`,

  //根据店铺id查询所有配送员列表【不分页】
  queryAllCourierListUrl: `https://${host}/catering/courier/queryAllCateringCourierList/`,

  //配送订单
  distributeOrderUrl: `https://${host}/catering/order/distribute`,

  //结算订单
  settingOrderUrl: `https://${host}/catering/order/settling`,

  //统计当天订单数据
  queryTodayOrderStatInfoUrl: `https://${host}/catering/order/queryTodayStatisticsInfo/`,

  //经营统计
  queryOperationAnalysisUrl: `https://${host}/catering/data/queryOperationAnalysis/`,

  //获取汇率
  comGetExchangeRateUrl: `https://${host}/components/exchangeRate/getExchangeRate`,

};

module.exports = config
