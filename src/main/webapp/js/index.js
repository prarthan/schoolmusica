
var Index = function() {

};

Index.prototype = {
  init : function() {
    this.initUI();
    this.initResultsPane();
  },
  initUI : function() {
    this.initSearchButton();
    this.initSearchAutoComplete();
  },
  initResultsPane : function() {
    this.searchPane = new SearchPane();
    this.searchPane.init();
  },
  validate : function( value ) {
    if( value.length == 0 ) {
      return false;
    }
    return true;
  },
  search : function() {
    var value = $.trim( $( ".searchfield input" ).val() );
    if( ! this.validate( value ) ) {
      return;
    }
    this.searchPane.search( value ); 
  },
  initSearchButton : function() {
    var _this = this;
    $("#searchbutton button").click( function() {
      _this.search();
    });
  },
  initSearchAutoComplete : function() {
    var _this = this;
 
    $( ".searchfield input" ).keypress( function( e ) {
      $( ".searchfield input" ).css( "background-image", "none" );
      if( e.keyCode == 13 ) {
        $( this ).autocomplete( "close" );
        _this.search(); 
      }
    });
 
    $.getJSON( "rest/program/all", function( instruments ) {
      for( var i in instruments ) {
        instruments[i].value = instruments[i].name;
        instruments[i].label = instruments[i].name;
      }
      $( ".searchfield input" ).autocomplete({
        source: instruments,
        minLength: 1,
        select: function( event, ui ) {
          $( ".searchfield input" ).val( ui.item.name );
          /*$( ".searchfield input" ).css( "background-image",  "url( " + ui.item.image + " )" ); */
          return false;
        }
      })
      .data( "autocomplete" )._renderItem = function( ul, item ) {
        return $( "<li></li>" )
          .data( "item.autocomplete", item )
          .append( "<a><div class='item-image'><img style='display:none;' src='" + item.image + "'/></div><div class='item-name'>" + item.name + "</div></a>" )
          .appendTo( ul );
      };
    });
  }
};

var SearchPane = function() {

};

SearchPane.prototype = {
  init : function() {
    this.initUI();
  },
  initUI : function() {
  },
  search: function( query ) {
    var _this = this;
    var c = new Date();
    $.ajax( {
      url: 'rest/department/search',
      async: true,
      contentType: 'application/json',
      type: "POST",
      dataType: "json",
      context: _this,
      data: JSON.stringify( { instrument: query } ),
      success: function( response, textStatus, jqXHR ){
        var d = new Date()
        var timeTaken = d.getTime() - c.getTime();
        var timeInfo = timeTaken + " milliseconds";
        if( timeTaken > 1000 ) {
          timeInfo = ( timeTaken/1000 ) + " seconds";
        }
        $('#searchinfo').css('display', 'table-cell').html( "<span class='resultcount'>" + response.department.length + "</span> departments found in " + timeInfo + "." );
        for( var i in response.department ) {
          $( "#searchResultTemplate" ).tmpl( response.department[i] ).appendTo( "#searchresults" );
        }
      },
      error : function( jqXHR, textStatus, errorThrown ) {
        console.log( errorThrown )
      }
    } );
  }
};

var SearchResult = function() {
  
}

