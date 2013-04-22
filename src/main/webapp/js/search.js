
var Search =  Header.extend({
  initUI: function() {
    this._super();
    this.initResultsPane();
    this.initSearchButton();
    this.initSearchAutoComplete();
  },
  initResultsPane : function() {
    this.searchPane = new SearchPane();
    this.searchPane.initialize();
  },
  validate : function( name ) {
    if( name.length == 0 ) {
      return false;
    }
    return true;
  },
  search : function() {
    var name = $.trim( $( ".searchfield input" ).val() );
    if( ! this.validate( name ) ) {
      return;
    }
    this.searchPane.search( name ); 
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
        instruments[i].name = instruments[i].name;
        instruments[i].label = instruments[i].name;
      }
      $( ".searchfield input" ).autocomplete({
        source: instruments,
        minLength: 1,
        select: function( event, ui ) {
          $( ".searchfield input" ).val( ui.item.name );
          _this.searchPane.query = ui.item.name;
          _this.searchPane.search( _this.searchPane.query );
          /*$( ".searchfield input" ).css( "background-image",  "url( " + ui.item.image + " )" ); */
          return false;
        }
      })
      .data( "autocomplete" )._renderItem = function( ul, item ) {
        return $( "<li></li>" )
          .data( "item.autocomplete", item )
          .append( "<a><div class='item-name'>" + item.name + "</div></a>" )
          .appendTo( ul );
      };
    });
  }
});

var FilterPane = function( searchPane ) {
  this.state = null; 
  this.searchPane = searchPane;
}

FilterPane.prototype = {
  initialize: function() {
    var _this = this;
    this.$el = $( "#filters" );
    this.$el.find('#stateAC').autocomplete({
      minLength: 1,
      source: Constants.States,
      select: function( event, ui ) {
        $( "#stateAC" ).val( ui.item.value );
        $( "#state" ).val( ui.item.name );
        _this.refreshSearch();
        return false;
      }
    });
    this.$el.find('#stateAC').on("change", function() {
      $( "#state" ).val('');
      if( $(this).val().length == 0 ) {
        _this.refreshSearch();
      }
    });
    this.$el.find( "input.filterInput" ).on( "change", function() {
      _this.refreshSearch() 
    } ); 

    this.$el.find( ".btn" ).click( function( event ) {
      console.log("clicked");
      if( $(this).hasClass('active') ) {
        $(this).removeClass('active');
      }
      else {
        $(this).parent().children().removeClass("active");
        $(this).addClass('active');
      }
      _this.refreshSearch();
      return false;
    });
  },
  refreshSearch : function() {
    this.searchPane.search( this.searchPane.query, null, 10 );
  },
  getValue: function() {
    var data =  this.$el.find( "form input.filterInput" ).serializeArray();
    var result = {};
    for( var i in data ) {
      var item = data[i];
      if( item.value.length != 0 ) {
        result[ item.name ] = item.value;
      }
      if( item.value == "on" ) {
        result[ item.name ] = true;
      }
    }
    var buttons = this.$el.find(".btn-group .active" );
    for( var i = 0; i < buttons.length; i++ ) {
      var item = $(buttons[i]).parent().parent().attr('id') + "Available";
      if( $(buttons[i]).html().toLowerCase() == "yes" ) {
        result[item ] = true;
      }
      else {
        result[ item ] = false;
      }
    }
    return result  
  }
};

var SearchPane = function() {
  this.filterPane = null;
  this.query = "";
  this.resultCount = 0;
  this.startCount = 0;
};

SearchPane.prototype = {
  initialize : function() {
    this.initFilters();
    this.initInfiniteScroller();
  },
  initInfiniteScroller: function() {
    var _this = this;
    $(window).scroll( function() {
      if( ( $(window).scrollTop() >= $(document).height() - $(window).height() ) && (_this.searched) && ! _this.fetching ) {
        _this.addMoreResults();
      }
    });
  },
  initFilters : function() {
    this.filterPane = new FilterPane( this );
    this.filterPane.initialize();
  },
  addMoreResults: function() {
    if( this.startCount <= this.resultCount-1 )
      this.search( this.query, this.startCount, 10 )
  },
  search: function( query, startCount, maxResults ) {
    var _this = this;
    if( ! startCount ) {
      _this.startCount = 0;
      _this.searched = false;
      _this.resultCount = 0;
      $('#searchinfo').addClass("hidden");
      $('#searchresults').addClass("hidden").empty();
      $('#searchlist').removeClass("hidden").addClass('loading');
    }
    
    query = $.trim( query );
    if( query.length == 0 ) {
      return;
    }
    _this.fetching = true;
    this.query = query;
    var queryData = this.filterPane.getValue();
    queryData.instrument = query;
    queryData.firstResult = _this.startCount;
    queryData.maxResult = 10;
    var c = new Date();
    $.ajax( {
      url: 'rest/school/search',
      async: true,
      contentType: 'application/json',
      type: "POST",
      dataType: "json",
      context: _this,
      data: JSON.stringify( queryData ),
      success: function( response, textStatus, jqXHR ) {
        if( ! _this.searched ) {
          _this.searched = true;
          _this.resultCount = response.resultCount;
          var d = new Date()
          var timeTaken = d.getTime() - c.getTime();
          var timeInfo = timeTaken + " milliseconds";
          if( timeTaken > 1000 ) {
            timeInfo = ( timeTaken/1000 ) + " seconds";
          }
          $('#searchlist').removeClass('loading');
          $('#searchinfo').removeClass('hidden').css('display', 'table-cell').html( "<span class='resultcount'>" + _this.resultCount + "</span> schools found in " + timeInfo + "." );
          $('#searchresults').removeClass("hidden");        
        }
        for( var i in response.schools ) {
          var result = new SearchResult( response.schools[i], query );
          result.initialize();
          _this.startCount++;
        }
        _this.fetching = false;
      },
      error : function( jqXHR, textStatus, errorThrown ) {
        console.log( errorThrown );
        $('#searchlist').removeClass('loading').addClass("hidden");
        _this.fetching = false;
      }
    } );
  }
};

var SearchResult = function( resultData, query ) {
  this.resultData = resultData;
  this.query = query;
}

SearchResult.prototype = {
  initialize : function() {
    var school = this.resultData;
    school.domId = "school_" + school.musicSchoolId;
    school.schoolName = school.name;
    school.searchQuery = this.query.replace(" ", "_-_");
    $("#searchResultSchoolTemplate").tmpl( school ).appendTo( "#searchresults")
    for( var j in school.department ) {
      $( "#searchResultDepartmentTemplate" ).tmpl( school.department[j] ).appendTo( "#searchresults #" + school.domId + " .department_list" );
    }
  }
};


