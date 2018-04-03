const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

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

//调用API向本地缓存存入数据,如果keyword为空，则情况列表
var saveStorageData = function (key, value) {
  var storageData = wx.getStorageSync(key) || []
  if (value) {
    if (value != '' && storageData.indexOf(value) < 0) {
      storageData.push(value);
      wx.setStorageSync(key, storageData);
    }
  } else {
    wx.setStorageSync(key, []);
  }
};

//加载指定key的缓存数据
var loadStorageData = function (key) {
  var storageData = wx.getStorageSync(key) || []
  return storageData;
}

module.exports = {
  formatTime: formatTime,
  showSuccess: showSuccess,
  showModel: showModel,
  saveStorageData: saveStorageData,
  loadStorageData: loadStorageData
}
