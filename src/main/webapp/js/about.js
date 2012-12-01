var About = function() {

} 

About.prototype = {
  init: function() {
    var header = new Header();
    header.init();
    this.initTabView();
    this.checkActive();
  },
  initTabView: function() {
    $("#navbar li a").click( function(e) {
      $(".bd > .container > div").css("display", "none");
      selector = $(e.currentTarget).attr('href');
      $(".bd > .container > " + selector ).fadeIn();
      $("#navbar li").removeClass("active");
      $(e.currentTarget).parent().addClass("active");
    })
  },
  checkActive: function() {
    selector = window.location.hash;
    if( selector && selector.length > 0 && $(selector).length > 0 ) {
      $("#navbar li").removeClass("active");   
      $(".bd > .container > div").css("display", "none");
      $(".bd > .container > " + selector ).fadeIn();
      $('#navbar li a[href="' + selector + '"]').parent().addClass("active")
    }
  }
}