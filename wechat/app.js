//app.js
var qcloud = require('/pages/common/index');
const config = require('./config');

App({
  onLaunch: function (options) {
    console.log('onLaunch');
    console.log(config.iLoginUrl);
    console.log(config.iCheckSessionUrl);
    /**
     * 小程序初始化时执行，我们初始化客户端的登录地址，以支持所有的会话操作
     */
    qcloud.setLoginUrl(config.iLoginUrl);
    qcloud.setCheckSessionUrl(config.iCheckSessionUrl);
  },

  onError: function (msg) {
    //当小程序发生脚本错误，或者 api 调用失败时，会触发 onError 并带上错误信息
    console.log(msg)
  },
  globalData: {
    communityItem: null,
    orderInfo: null,
    newsSubNavList: null,
    owner:null,
    orderfood:[],
    dangqian: null,
  },
})