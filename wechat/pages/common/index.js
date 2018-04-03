var login = require('./lib/login');
var Session = require('./lib/session');

var exports = module.exports = {
  login: login.login,
  setLoginUrl: login.setLoginUrl,
  setCheckSessionUrl: login.setCheckSessionUrl,
  LoginError: login.LoginError,
  clearSession: Session.clear
};