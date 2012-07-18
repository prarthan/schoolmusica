
var Index = function() {

};

Index.prototype = {
  init : function() {
    this.initUI();
    this.initResultsPane();
  },
  initUI : function() {
    this.initSearchButton();
    //this.initSearchAutoComplete();
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
    var instruments = [
       {
          label: "Accordion",
          image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_accordion_480.png"
       },
       {
          label: "Balalaika",
          image: "http://aux3.iconpedia.net/uploads/5911775481413106767.png"
       },
       {
          label: "Bagpipe",
          image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_bagpipe_480.png"
       },
       {
         label: "Banjo",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_banjo_480.png"
       },
       {
         label: "Bassoon",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_bassoon_480.png"
       },
       {
         label: "Bugle",
         image: "http://preview.turbosquid.com/Preview/Content_2009_07_15__11_04_51/Trumpet04.jpg6dc9938a-1225-4eb0-8296-792b51581627Small.jpg"
       },
       {
         label: "Castanets",
         image: "http://www.fallingpixel.com/products/36510/thumbs/SA_Castanet_3.jpg"
       },
       {
         label: "Cello",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_cello_480.png"
       },
       {
         label: "Clarinet",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_clarinet_480.png"
       },
       {
         label: "Concertina",
         image: "http://www.lajajakids.com/media/catalog/product/cache/1/thumbnail/64x64/5e06319eda06f020e43594a9c230972d/6/4/6401_1.jpg"
       },
       {
         label: "Double Bass",
         image: "http://static.abcteach.com/content_preview/d/doublebasscolor_t0.png"
       },
       {
         label: "Drum",
         image: "http://icons.mysitemyway.com/wp-content/gallery/black-ink-grunge-stamps-textures-icons-media/thumbs/thumbs_000579-black-ink-grunge-stamp-texture-icon-media-music-drum.png"
       },
       {
         label: "Flute",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_flute_480.png"
       },
       {
         label: "French Horn",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_frenchhorn_480.png"
       },
       {
         label: "Guitar",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_eguitaroverdrive_480.png"
       },
       {
         label: "Harmonica",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_harmonica_480.png"
       },
       {
         label: "Harp",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_harp_480.png"
       },
       {
         label: "Keyboard",
         image: "http://files.softicons.com/download/object-icons/musical-instruments-icons-by-sirea/png/64x64/Keyboard.png"
       },
       {
         label: "Koto",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_koto_480.png"
       },
       {
         label: "Mandolin",
         image: "http://icons.iconarchive.com/icons/artua/ukrainian-motifs/64/Mandolin-icon.png"
       },
       {
         label: "Maracas",
         image: "http://images2.wikia.nocookie.net/__cb20110507205003/cityville/images/d/d6/Maracas-icon.png"
       },
       {
         label: "Marimba",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_marimba_480.png"
       },
       {
         label: "Oboe",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_oboe_480.png"
       },
       {
         label: "Mouth Organ",
         image: "http://preview.turbosquid.com/Preview/Content_2009_07_13__11_35_12/harmonica_01.jpg09cb8875-bd01-47a3-b36b-cf151f2a15b6Small.jpg"
       },
       {
         label: "Piano",
         image: "http://www.piano-liquidators.com/wp-content/uploads/2010/08/ico-piano-64x64.png"
       },
       {
         label: "Saxophone",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_tenorsax_480.png"
       },
       {
         label: "Tambourine",
         image: "http://static.abcteach.com/content_preview/t/tambourine_grayscale_t0.png"
       },
       {
         label: "Trombone",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_trombone_480.png"
       },
       {
         label: "Trumpet",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_trumpet_480.png"
       },
       {
         label: "Tuba",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_tuba_480.png"
       },
       {
         label: "Ukulele",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_ukulele_480.png"
       },
       {
         label: "Vibraphone",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_vibraphone_480.png"
       },
       {
         label: "Viola",
         image: "http://www.xewton.com/musicstudio/features/icons/instr_icon_violin_480.png"
       },
       {
         label: "Xylophone",
         image: "http://static.abcteach.com/content_preview/x/xylophone_bw_t0.png"
       }                                                               
    ];
 
    $( ".searchfield input" ).keypress( function( e ) {
      $( ".searchfield input" ).css( "background-image", "none" );
      if( e.keyCode == 13 ) {
        $( this ).autocomplete( "close" );
        _this.search(); 
      }
    });
 
    $( ".searchfield input" ).autocomplete({
      minLength: 0,
      source: instruments,
      select: function( event, ui ) {
        $( ".searchfield input" ).val( ui.item.label );

        $( ".searchfield input" ).css( "background-image",  "url( " + ui.item.image + " )" );
        return false;
      }
    })
    .data( "autocomplete" )._renderItem = function( ul, item ) {
      return $( "<li></li>" )
        .data( "item.autocomplete", item )
        .append( "<a><div class='item-image'><img src='" + item.image + "'/></div><div class='item-name'>" + item.label + "</div></a>" )
        .appendTo( ul );
    };
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
    $.ajax( {
      url: 'http://schoolmusica.elasticbeanstalk.com/rest/department/search',
      async: true,
      type: "POST",
      context: _this,
      data: { query: query },
      success: function( response, textStatus, jqXHR ){
        // log a message to the console
        console.log( response );
      },
      error : function( jqXHR, textStatus, errorThrown ) {
        console.log( errorThrown )
      }
    } );
  }
};

var SearchResult = function() {
  
}

