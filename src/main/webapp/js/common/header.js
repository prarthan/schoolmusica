
var Header = function() {

};

Header.prototype = {
  init : function() {
    this.initUI();
  },
  initUI : function() {
    this.initLoginButton();
  },
  initLoginButton: function() {
    $(".login .title").popover({
       "animate" : true,
       "trigger" : "hover",
       "html" : "true",
       "content": "We use <img src='img/google-logo.png'></img> for login.",
       "delay" : 100,
       "placement": "bottom"
    });
  }
};