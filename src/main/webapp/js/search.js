var Constants = {
  States: [
    {
      value: "Alabama",
      name: "AL"
    },
    {
      value: "Alaska",
      name: "AK"
    },
    {
      value: "Arizona",
      name: "AZ"
    },
    {
      value: "Arkansas",
      name: "AR"
    },
    {
      value: "California",
      name: "CA"
    },
    {
      value: "Colorado",
      name: "CO"
    },
    {
      value: "Connecticut",
      name: "CT"
    },
    {
      value: "Delaware",
      name: "DE"
    },
    {
      value: "District of Columbia",
      name: "DC"
    },
    {
      value: "Florida",
      name: "FL"
    },
    {
      value: "Georgia",
      name: "GA"
    },
    {
      value: "Hawaii",
      name: "HI"
    },
    {
      value: "Idaho",
      name: "ID"
    },
    {
      value: "Illinois",
      name: "IL"
    },
    {
      value: "Indiana",
      name: "IN"
    },
    {
      value: "Iowa",
      name: "IA"
    },
    {
      value: "Kansas",
      name: "KS"
    },
    {
      value: "Kentucky",
      name: "KY"
    },
    {
      value: "Louisiana",
      name: "LA"
    },
    {
      value: "Maine",
      name: "ME"
    },
    {
      value: "Maryland",
      name: "MD"
    },
    {
      value: "Massachusetts",
      name: "MA"
    },
    {
      value: "Michigan",
      name: "MI"
    },
    {
      value: "Minnesota",
      name: "MN"
    },
    {
      value: "Mississippi",
      name: "MS"
    },
    {
      value: "Missouri",
      name: "MO"
    },
    {
      value: "Montana",
      name: "MT"
    },
    {
      value: "Nebraska",
      name: "NE"
    },
    {
      value: "Nevada",
      name: "NV"
    },
    {
      value: "New Hampshire",
      name: "NH"
    },
    {
      value: "New Jersey",
      name: "NJ"
    },
    {
      value: "New Mexicon",
      name: "NM"
    },
    {
      value: "New York",
      name: "NY"
    },
    {
      value: "North Carolina",
      name: "NC"
    },
    {
      value: "North Dakota",
      name: "ND"
    },
    {
      value: "Ohio",
      name: "OH"
    },
    {
      value: "Oklahoma",
      name: "OK"
    },
    {
      value: "Oregon",
      name: "OR"
    },
    {
      value: "Pennsylvania",
      name: "PA"
    },
    {
      value: "Rhode Island",
      name: "RI"
    },
    {
      value: "South Carolina",
      name: "SC"
    },
    {
      value: "South Dakota",
      name: "SD"
    },
    {
      value: "Tennessee",
      name: "TN"
    },
    {
      value: "Texas",
      name: "TX"
    },
    {
      value: "Utah",
      name: "UT"
    },
    {
      value: "Vermont",
      name: "VT"
    },     
    {
      value: "Virginia",
      name: "VA"
    },    
    {
      value: "Washington",
      name: "WA"
    }, 
    {
      value: "West Virginia",
      name: "WV"
    }, 
    {
      value: "Wisconsin",
      name: "WI"
    }, 
    {
      value: "Wyoming",
      name: "WY"
    }
  ]

}

var Search = function() {

};

Search.prototype = {
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
          .append( "<a><div class='item-image'><img style='display:none;' src='" + item.image + "'/></div><div class='item-name'>" + item.name + "</div></a>" )
          .appendTo( ul );
      };
    });
  }
};

var FilterPane = function( searchPane ) {
  this.state = null; 
  this.searchPane = searchPane;
}

FilterPane.prototype = {
  init : function() {
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
    this.$el.find( "input.filterInput" ).on( "change", function() {
     if( this.type == "checkbox" ) 
      if( this.checked )
        $(this).parent().removeClass("unchecked")
      else 
        $(this).parent().addClass("unchecked")
      _this.refreshSearch() 
    } ); 
  },
  refreshSearch : function() {
    this.searchPane.search( this.searchPane.query );
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
    return result  
  }
};

var SearchPane = function() {
  this.filterPane = null;
  this.query = "";
};

SearchPane.prototype = {
  init : function() {
    this.initFilters();
  },
  initFilters : function() {
    this.filterPane = new FilterPane( this );
    this.filterPane.init();
  },
  search: function( query ) {
    var _this = this;
    $('#searchinfo').css('display', 'none' );
    $('#searchresults').empty();
    
    query = $.trim( query );
    if( query.length == 0 ) {
      return;
    }
    this.query = query;
    var queryData = this.filterPane.getValue();
    queryData.instrument = query
    
    var c = new Date();
    $.ajax( {
      url: 'rest/school/search',
      async: true,
      contentType: 'application/json',
      type: "POST",
      dataType: "json",
      context: _this,
      data: JSON.stringify( queryData ),
      success: function( response, textStatus, jqXHR ){
        var d = new Date()
        var timeTaken = d.getTime() - c.getTime();
        var timeInfo = timeTaken + " milliseconds";
        if( timeTaken > 1000 ) {
          timeInfo = ( timeTaken/1000 ) + " seconds";
        }
        $('#searchinfo').css('display', 'table-cell').html( "<span class='resultcount'>" + response.schools.length + "</span> schools found in " + timeInfo + "." );
        for( var i in response.schools ) {
          var result = new SearchResult( response.schools[i]);
          result.init();
        }
      },
      error : function( jqXHR, textStatus, errorThrown ) {
        console.log( errorThrown )
      }
    } );
  }
};

var SearchResult = function( resultData ) {
  this.resultData = resultData;
}

SearchResult.prototype = {
  init : function() {
    var school = this.resultData;
    school.domId = "school_" + school.musicSchoolId;
    $("#searchResultSchoolTemplate").tmpl( school ).appendTo( "#searchresults")
    for( var j in school.department ) {
      $( "#searchResultDepartmentTemplate" ).tmpl( school.department[j] ).appendTo( "#searchresults #" + school.domId + " .department_list" );
    }    
  }
};


