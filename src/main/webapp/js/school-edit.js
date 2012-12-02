var SchoolEdit = function( id ) {
  this.id = id;
  this.$el = $("<div/>");
  this.school = null;
  this.data = null;
  this.newSchool = false;
  this.editMode = false;
};

SchoolEdit.prototype = {
  init: function() {
    this.header = new Header();
    this.header.init();
    this.$el = $("#school");
    this.school = new SchoolForm();
    this.school.init(  this.$el.find(".information") );
    this.getProgramList(
      this.fetchData
    );
  },
  getProgramList: function( onDone ) {
    var _this = this;
    $.getJSON( "rest/program/all", function( instruments ) {
      Constants.InstrumentsMap = {};
      for( var i in instruments ) {
        instruments[i].name = instruments[i].name;
        instruments[i].label = instruments[i].name;
        instruments[i].id = instruments[i].name;
        instruments[i].programId = instruments[i].programId;
        Constants.InstrumentsMap[ instruments[i].name.toLowerCase() ] = instruments[i].programId;
      }
      Constants.Instruments = instruments;
      onDone.apply( _this );
    });
  },
  fetchData : function() {
    if( this.id == null || this.id == -1 ) {
      $("#school h3").html( "Add New School" );
      this.newSchool = true;
      this.school.setNew();
      this.school.updateTemplate();
      return;
    }
    var _this = this;
    $.getJSON( "rest/school/" + this.id, {}, function( response, textStatus, jqXHR ) {
        if( response == null ) {
          $("#school h3").html( "Add New School" );
          _this.newSchool = true;
          _this.school.setNew()
          _this.school.updateTemplate();
          return;
        }
        _this.data = response;
        _this.school.setData( response )
    } );
  }
}

SchoolForm = function( id ) {
  this.$el = $("<div/>");
  this.data = {};
  this.template = $("#schoolTemplate");
  this.formTemplate = $("#schoolFormTemplate");
  this.departments = [];
  this.editable = false;
  this.newSchool = false;
}

SchoolForm.prototype = {
  init: function( $el ) {
    this.$el = $el;
    this.$el.find(".data").html( this.template.tmpl( {} ) );
    this.$el.find(".department_list").on( "shown", function(e) {
      $(e.target).parent().addClass("expanded");
    });
    this.$el.find(".department_list").on( "hidden", function(e) {
      $(e.target).parent().removeClass("expanded");
    });
  },
  setData : function( data, skipDepartment ) {
    this.data = data;
    this.$el.find(".data").html( this.template.tmpl( this.data ) );
    if( ! skipDepartment ) {
      for( var i in data.department ) {
        var department = data.department[i];
        this.addDepartment( department );
      }
    }
    this.updateTemplate();
  },
  initTooltips: function() {
    var options = {
      animation: true,
      placement: "right",
      title: "Please enter a school name",
      trigger: "focus"
    };
    this.$el.find('#schoolName').tooltip( options );
    
    options.title = "Please provide an address for the school"
    this.$el.find(".address_information input").tooltip( options );
   
    options.title = "What is the minimum SAT score required?"
    this.$el.find(".satMin").tooltip( options );
  
    options.title = "What is the minimum GRE score required?"
    this.$el.find(".greMin").tooltip( options );
      
    options.title = "What is the minimum ACT score required?"
    this.$el.find(".actMin").tooltip( options );
  },
  addDepartment: function( data ) {
    var department = new DepartmentForm( this );
    if( data == null ) {
      data = {};
      department.setNew();
    }
    else {
      this.departments.push( department );
    }
    if( this.departments.length == 1 )
      data.inClass = "in"
    department.setData( data );
    this.$el.find(".data .department_list" ).append( department.$el );
    this.$el.find(".department_list").parent().show();

  },
  initEventListeners: function() {
    var _this = this;
    this.$el.find(".school_information .btn.save-school").click( function() {
      _this.save()
    });
    this.$el.find(".school_information .btn.cancel-school").click( function() {
      if( _this.newSchool ) {
        _this.$el.find(".form input").removeClass("error").val("");
        _this.$el.find(".form .alert").hide();
        return
      }
      _this.resetData( _this.data )

    });

  },
  resetData: function() {
    this.$el.find("#schoolName").val( this.data.name );
    this.$el.find(".address").val( this.data.address );
    this.$el.find(".city").val( this.data.city );
    this.$el.find(".state").val( this.data.state ); 
    this.$el.find(".zip").val( this.data.zip );
    this.$el.find(".country").val( this.data.country );
    this.$el.find(".satMin").val( this.data.satMin );
    this.$el.find(".greMin").val( this.data.greMin );
    this.$el.find(".actMin").val( this.data.actMin );       
  },
  getData : function() {
    data = {};
    data.name = $.trim( this.$el.find("#schoolName").val() );
    data.address = $.trim( this.$el.find(".address").val() );
    data.city = $.trim( this.$el.find(".city").val() );
    data.state = $.trim( this.$el.find(".state").val() );  
    data.zip = $.trim( this.$el.find(".zip").val() );
    data.country = $.trim( this.$el.find(".country").val() );

    data.satMin = $.trim( this.$el.find(".satMin").val() );
    data.greMin = $.trim( this.$el.find(".greMin").val() );
    data.actMin = $.trim( this.$el.find(".actMin").val() );

    data.sponsorWeight = 1;
    if( ! this.newSchool ) {
      data.musicSchoolId = this.data.musicSchoolId;
      data.sponsorWeight = this.data.sponsorWeight;
    }
    return data;
  },
  validate: function() {
    var validate = true;
    var name = $.trim( this.$el.find("#schoolName").val() );
    if( name.length === 0 ) {
      validate = false;
      this.$el.find("#schoolName").addClass("error");
    }

   var address = $.trim( this.$el.find(".address").val() );
    if( address.length == 0 ) {
      this.$el.find(".address").addClass("error");
      validate = false;
    }

    var city = $.trim( this.$el.find(".city").val() );
    if( city.length == 0 ) {
      this.$el.find(".city").addClass("error");
      validate = false;
    }

    var state = $.trim( this.$el.find(".state").val() );
    if( state.length == 0 ) {
      this.$el.find(".state").addClass("error");
      validate = false;
    }  

    var country = $.trim( this.$el.find(".country").val() );
    if( country.length == 0 ) {
      this.$el.find(".country").addClass("error");
      validate = false;
    }  

    var zip = $.trim( this.$el.find(".zip").val() );
    if( zip.length == 0 && ( zip.match(/^\d{5}(-\d{4})?$/g) ) == null  ) {
      this.$el.find(".zip").addClass("error");
      validate = false;
    }  

    var satMin = $.trim( this.$el.find(".satMin").val() );
    if( satMin.length == 0 && ( satMin.match(/^\d+$/g) ) == null  ) {
      this.$el.find(".satMin").addClass("error");
      validate = false;
    }  

    var greMin = $.trim( this.$el.find(".greMin").val() );
    if( satMin.length == 0 && ( satMin.match(/^\d+$/g) ) == null  ) {
      this.$el.find(".greMin").addClass("error");
      validate = false;
    }      

    var actMin = $.trim( this.$el.find(".actMin").val() );
    if( actMin.length == 0 && ( actMin.match(/^\d+$/g) ) == null  ) {
      this.$el.find(".actMin").addClass("error");
      validate = false;
    }     

    return validate;
  },
  save: function() {
    this.$el.find("input").removeClass("error");
    this.$el.find(".form .alert").hide();
    if( ! this.validate() ) {
      this.$el.find(".form .alert").fadeIn();
      return;
    }
    var data = this.getData();
    var _this = this;
    
    $.ajax({
      url: "rest/school",
      type: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify( data ),
      success: function( response, jqXHR, textStatus) {
        _this.data = response;
        if( _this.newSchool ) {
          _this.newSchool = false;
          _this.$el.find(".department_list").parent().show();
          _this.addDepartmentButton();
        }
      }, 
      error: function( response, jqXHR, textStatus ) {
        console.log("error");
      }
    });

  },
  setNew: function() {
    this.newSchool = true;
  },
  addDepartmentButton: function() {
    if( this.$el.find(".addDepartment").length == 0 && ! this.newSchool ) {
      var _this = this;
      this.$el.find(".department_list").parent().append("<div class='btn addDepartment'>Add Department</div>" );
      this.$el.find(".btn.addDepartment").click( function() {
        _this.addDepartment( null, true );
      });    
    }
  },
  removeDepartmentButton: function() {
    this.$el.find(".addDepartment").remove();
  },
  updateTemplate: function() {
    this.$el.find( ".data .school_information").html( this.formTemplate.tmpl( this.data ) );
    this.$el.find( ".form .address_information .state").autocomplete( {
        minLength: 1,
        source: Constants.States,
        select: function( event, ui ) {
          $(this).val( ui.item.name );
          return false;
        }
      } 
    );
    this.$el.find(".department_list").parent().show();
    if( ! this.newSchool ) {
      this.addDepartmentButton();
    }
    else {
      this.$el.find(".department_list").parent().hide();
    }
    this.initEventListeners();
    this.initTooltips();
  },
  getId: function() {
    return ( this.newSchool ) ? -1 : this.data.musicSchoolId;
  }
}

DepartmentForm = function( school ) {
  this.school = school;
  this.$el = $("<div/>");
  this.template = $("#departmentFormTemplate");
  this.faculty = [];
  this.data = null;
  this.newDepartment = false;
  this.addedEvent = false;
}

DepartmentForm.prototype = {
  setData : function( data, skipFaculty ) {
    this.data = data;
    this.$el.html( this.template.tmpl( data ) );
    if( ! skipFaculty ) {
      for( var i in data.faculty ) {
        var faculty = data.faculty[i];
        this.addFaculty( faculty );
      }
    }
    this.updateTemplate();
    if( ! this.addedEvent ) {
      this.$el.find(".faculty_list").on( "shown", function(e) {
        $(e.target).parent().addClass("expanded");
      });
      this.$el.find(".faculty_list").on( "hidden", function(e) {
        $(e.target).parent().removeClass("expanded");
      });
      this.addedEvent = true;
    }
  },
  addFaculty: function( data ) {
    var faculty = new FacultyForm(  this );
    if( data == null ) {
      data = {};
      faculty.setNew();
    }
    else {
      this.faculty.push( faculty );
    }
    if( this.faculty.length == 1 )
      data.inClass = "in"
    faculty.setData( data )
    this.$el.find(".faculty_list").append( faculty.$el );
    this.$el.find(".faculty_list").parent().show();
   
  },
  initEventListeners: function() {
    var _this = this;
    this.$el.find(".edit-department").click( function() {
      _this.showForm();
    } );
    this.$el.find(".delete-department").click( function() {
      _this.deleteDepartment();
    } );
    this.$el.find(".btn.save-department").click( function() {
      _this.save();
    });
    this.$el.find(".btn.cancel-department").click( function() {
      if( _this.newDepartment ) {
        _this.$el.remove();
        return;
      }
      _this.resetData();    
    });
  },
  validate: function() {
    var validate = true;
    var name = $.trim ( this.$el.find(".departmentName").val() );
    if( name.length === 0 ) {
      this.$el.find(".departmentName").addClass("error");
      validate = false;
    }

    var url = $.trim( this.$el.find(".departmentUrl").val() );
    if( url.length === 0 ) {
      this.$el.find(".departmentUrl").addClass("error");
      validate = false;
    }

    var email = $.trim( this.$el.find(".email").val() );
    if( email.length == 0 ) {
      this.$el.find(".email").addClass("error");
      validate = false;
    } 

    var graduateProgramAvailable = this.$el.find(".graduateProgramAvailable .btn.active");
    if( graduateProgramAvailable.length == 0 ) {
      this.$el.find(".graduateProgramAvailable").addClass("error");
      validate = false;     
    } 

    var scholarshipsAvailable = this.$el.find(".scholarshipsAvailable .btn.active");
    if( scholarshipsAvailable.length == 0 ) {
      this.$el.find(".scholarshipsAvailable").addClass("error");
      validate = false;     
    }   

    var musicMinorAvailable = this.$el.find(".musicMinorAvailable .btn.active");
    if( musicMinorAvailable.length == 0 ) {
      this.$el.find(".musicMinorAvailable").addClass("error");
      validate = false;     
    }   

    var keyword = $.trim ( this.$el.find(".keywords .tagedit-list input:hidden").map(function(){ val = $(this).val(); if( val.length > 0 ) return val; }).get().join(",") );
    if( keyword.length == 0 ) {
      this.$el.find(".keywords .tagedit-list").addClass("error");
      validate = false;     
    } 
    return validate;
  },
  getData: function() {
    var data = {};
    data.departmentName = $.trim ( this.$el.find(".departmentName").val() );
    data.departmentUrl = $.trim( this.$el.find(".departmentUrl").val() );

    data.email = $.trim( this.$el.find(".email").val() );
    
    var graduateProgramAvailable = this.$el.find(".graduateProgramAvailable .btn.active");
    if( graduateProgramAvailable.length > 0 ) {
      data.graduateProgramAvailable = ( graduateProgramAvailable.text() == "Yes" ) ? true: false;
    }   

    var scholarshipsAvailable = this.$el.find(".scholarshipsAvailable .btn.active");
    if( scholarshipsAvailable.length > 0 ) {
      data.scholarshipsAvailable = ( scholarshipsAvailable.text() == "Yes" ) ? true: false;
    }   

    var musicMinorAvailable = this.$el.find(".musicMinorAvailable .btn.active");
    if( musicMinorAvailable.length > 0 ) {
      data.musicMinorAvailable = ( musicMinorAvailable.text() == "Yes" ) ? true: false;
    }   

    data.keyword = $.trim ( this.$el.find(".keywords .tagedit-list input:hidden").map(function(){ val = $(this).val(); if( val.length > 0 ) return val; }).get().join(",") );
    if( ! this.newDepartment ) {
      data.departmentId = this.data.departmentId;
    }
    data.musicSchoolId = this.school.getId();
    return data;
  },
  resetData: function( data ) {
    this.$el.find(".departmentName").val( this.data.departmentName );
    this.$el.find(".departmentUrl").val( this.data.departmentUrl );
    this.$el.find(".email").val( this.data.email );
    keywords = this.$el.find(".keywords .tagedit-list li")
    keywords.each( function(index) {
     var target = $(this)
      if( ! target.hasClass("originalValue") ) {
        target.remove();
      }
    });
    this.updateOtherInfo();
  },
  save: function() {
    var _this = this;
    this.$el.find(".error").removeClass("error");
    this.$el.find(".form .alert").hide();
    if( ! this.validate() ) {
      this.$el.find(".form .alert").fadeIn();
      return;
    }
    var data = this.getData();
    $.ajax({
      url: "rest/department",
      type: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify( data ),
      success: function( response, jqXHR, textStatus) {
        _this.data = response;
        _this.$el.find(".keywords li" ).addClass("originalValue");
        if( _this.newDepartment ) {
          _this.newDepartment = false;
          _this.$el.find(".faculty_list").parent().show();
          _this.addFacultyButton();
        }
      }, 
      error: function( response, jqXHR, textStatus ) {
        console.log("error");
      }
    });    
  },
  setNew: function() {
    this.newDepartment = true;
    this.editable = true;
  },
  addFacultyButton: function() {
    if( this.$el.find(".addFaculty").length == 0 && ! this.newDepartment) {
      var _this = this;
      this.$el.find(".faculty_list").parent().append("<div class='btn addFaculty'>Add Faculty</div>" );
      this.$el.find(".btn.addFaculty").click( function() {
        _this.addFaculty( null, true );
      });
    }
  },
  updateOtherInfo: function() {
   if( this.data.musicMinorAvailable ) {
      this.$el.find(".musicMinorAvailable .yes").addClass("active")
      this.$el.find(".musicMinorAvailable .no").removeClass("active")
    }
    else {
      this.$el.find(".musicMinorAvailable .no").addClass("active")
      this.$el.find(".musicMinorAvailable .yes").removeClass("active")
    }
    if( this.data.graduateProgramAvailable ) {
      this.$el.find(".graduateProgramAvailable .yes").addClass("active")
      this.$el.find(".graduateProgramAvailable .no").removeClass("active")
    }
    else {
      this.$el.find(".graduateProgramAvailable .no").addClass("active")
      this.$el.find(".graduateProgramAvailable .yes").removeClass("active")
    }
    if( this.data.scholarshipsAvailable ) {
      this.$el.find(".scholarshipsAvailable .yes").addClass("active")
      this.$el.find(".scholarshipsAvailable .no").removeClass("active")
    }
    else {
      this.$el.find(".scholarshipsAvailable .no").addClass("active")
      this.$el.find(".scholarshipsAvailable .yes").removeClass("active")
    }
  },
  initTooltips: function() {
    var options = {
      animation: true,
      placement: "right",
      title: "Please enter the name of your department offering courses for music or music related topics",
      trigger: "focus"
    };
    this.$el.find(".departmentName").tooltip(options);
    
    options.title = "Please enter the URL for the department which will help students know more about the department";
    this.$el.find(".departmentUrl").tooltip( options );
   
    options.title = "Please provide an email so that students can get in touch with your department";
    this.$el.find(".email").tooltip( options );
   
    options.title = "Which instruments can a student learn in this department?";
    this.$el.find(".keywords .tagedit-list input").tooltip( options );

    options.trigger = "hover";
    options.title = "Are graduate programs offered by this department?";
    this.$el.find(".graduateProgramAvailable").tooltip(options);
  
    options.title = "Are scholarships available?";
    this.$el.find(".scholarshipsAvailable").tooltip( options );

    options.title = "Are music or music related minor programs offered by this department?";
    this.$el.find(".musicMinorAvailable").tooltip( options );
  },
  updateTemplate: function() {
    this.$el.find(".faculty_list").parent().show();
    if( ! this.newDepartment ) {
      this.addFacultyButton();
    }
    else {
      this.$el.find(".faculty_list").parent().hide();
    }
    this.$el.find('input.keyword').tagedit({
      allowEdit: false,
      autocompleteOptions: {
        source: Constants.Instruments,
        minLength: 1,
        select: function( event, ui ) {
          $(this).val(ui.item.value).trigger('transformToTag', [ui.item.id]);
          return false;
        }
      }
    });
    if( this.newDepartment ) {
      this.$el.find(".form_title").html("Add Department");
    }   
    this.updateOtherInfo();
    this.initTooltips();
    this.initEventListeners();
  },
  deleteDepartment: function() {
    var _this = this;
    if( confirm("Are you sure you want to delete this department?") ) {
      $.ajax( {
        url: "rest/department/" + this.getId(),
        method: "POST",
        success: function( response, jqXHR, textStatus) {
        }, 
        error: function( response, jqXHR, textStatus ) {
          console.log("error");
        }
      });
    }
  },
  getId: function() {
    return ( this.newDepartment ) ? -1 : this.data.departmentId;
  }

}

FacultyForm = function( department ) {
  this.department = department;
  this.$el = $("<div/>");
  this.template = $("#facultyFormTemplate");
  this.data = null;
  this.editable = false;
  this.newFaculty = false;
  this.instruments = [];
}

FacultyForm.prototype = {
  setData : function( data ) {
    this.data = data;
    this.$el.html( this.template.tmpl( data ) );
    this.updateTemplate();
  },
  setNew: function() {
    this.newFaculty = true;
  },
  initEventListeners: function() {
    var _this = this;
    this.$el.find(".delete-faculty").click( function() {
      _this.deleteFaculty();
    } );
    this.$el.find(".btn.save-faculty").click( function() {
      _this.save();
    });
    this.$el.find(".btn.cancel-faculty").click( function() {
      if( _this.newFaculty ) {
        _this.$el.remove();
        return;
      }
      _this.setData( _this.data );
    });
  },
  validate: function() {
    var validate = true;
    var firstName = $.trim ( this.$el.find(".firstName").val() );
    if( firstName.length === 0 ) {
      this.$el.find(".firstName").addClass("error");
      validate = false;
    }
    var middleName = $.trim ( this.$el.find(".middleName").val() );

    var lastName = $.trim ( this.$el.find(".lastName").val() );
    if( lastName.length === 0 ) {
      this.$el.find(".lastName").addClass("error");
      validate = false;
    }
    var title = $.trim ( this.$el.find(".title").val() );
    if( title.length === 0 ) {
      this.$el.find(".title").addClass("error");
      validate = false;
    }
    var url = $.trim ( this.$el.find(".facultyUrl").val() );
    if( title.length === 0 ) {
      this.$el.find(".facultyUrl").addClass("error");
      validate = false;
    }
    var keyword = $.trim ( this.$el.find(".keywords .tagedit-list input:hidden").map(function(){ val = $(this).val(); if( val.length > 0 ) return val; }).get().join(",") );
    if( keyword.length === 0 ) {
      this.$el.find(".keywords .tagedit-list").addClass("error");
      validate = false;
    }    

    var styles = $.trim ( this.$el.find(".styles .tagedit-list input:hidden").map(function(){ val = $(this).val(); if( val.length > 0 ) return val; }).get().join(",") );
    if( styles.length === 0 ) {
      this.$el.find(".styles .tagedit-list").addClass("error");
      validate = false;
    }   

    return validate;
  },
  getData: function() {
    var data = {};
    data.firstName = $.trim ( this.$el.find(".firstName").val() );
    data.middleName = $.trim ( this.$el.find(".middleName").val() );
    data.lastName = $.trim ( this.$el.find(".lastName").val() );
    data.title = $.trim ( this.$el.find(".title").val() );
    data.facultyUrl = $.trim ( this.$el.find(".facultyUrl").val() );
    data.keyword = $.trim ( this.$el.find(".keywords .tagedit-list input:hidden").map(function(){ val = $(this).val(); if( val.length > 0 ) return val; }).get().join(",") );
    data.styles = $.trim ( this.$el.find(".styles .tagedit-list input:hidden").map(function(){ val = $(this).val(); if( val.length > 0 ) return val; }).get().join(",") ); 
    if( ! this.newFaculty ) {
      data.departmentId = this.data.facultyId;
    }
    data.departmentId = this.department.getId();

    return data;
  },
  save: function() {
    var _this = this;
    this.$el.find(".error").removeClass("error");
    this.$el.find(".form .alert").hide();
    if( ! this.validate() ) {
      this.$el.find(".form .alert").fadeIn();
      return;
    }
    var data = this.getData();
    $.ajax({
      url: "rest/faculty",
      type: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify( data ),
      success: function( response, jqXHR, textStatus) {
        _this.data = response;
        _this.$el.find(".keywords li" ).addClass("originalValue");
        _this.$el.find(".styles li" ).addClass("originalValue");
        _this.newFaculty = false;
      }, 
      error: function( response, jqXHR, textStatus ) {
        console.log("error");
      }
    });    
  },
  getStyleList: function() {
    var _this = this;
    if( _this.instruments.length == 0 ) {
      _this.updateInstrumentList();
    }
    $.getJSON( "rest/program/style/" + _this.instruments.join(","), function( styles ) {
      Constants.Styles = [];
      for( var i in styles ) {
        styles[i].name = styles[i].name;
        styles[i].label = styles[i].name;
        styles[i].id = styles[i].name;
        styles[i].styleId = styles[i].styleId;
        styles[i].programId = styles[i].programId;
      }
      Constants.Styles = styles;
      console.log(_this.$el.find('.styles #tagedit-input').length );
      _this.$el.find('.styles #tagedit-input').autocomplete( "option" , "source" ,  Constants.Styles );
    });
  },
  getStyles: function() {
    return Constants.Styles;
  },
  updateTemplate: function() {
    var _this = this;
    this.updateInstrumentList();
    this.getStyleList();
    Constants.Styles = [];
    this.$el.find('input.style').tagedit({
      allowEdit: false,
      autocompleteOptions: {
        source: _this.getStyles,
        minLength: 1,
        select: function( event, ui ) {
          $(this).val(ui.item.value).trigger('transformToTag', [ui.item.id]);
          return false;
        }
      }
    }); 

    this.$el.find('input.keyword').tagedit({
      allowEdit: false,
      autocompleteOptions: {
        source: Constants.Instruments,
        minLength: 1,
        select: function( event, ui ) {
          $(this).val(ui.item.value).trigger('transformToTag', [ui.item.id]);
          return false;
        }
      }
    }); 
    this.$el.find('.keywords .ui-autocomplete-input').bind( "itemAdded", function(e, data ) {
      _this.updateInstrumentList();
      _this.getStyleList();
    });

    if( this.newFaculty ) {
      this.$el.find(".form .form_title").html("Add Faculty");
    }   
    this.initTooltips();
    this.initEventListeners();  
  },
  updateInstrumentList: function() {
    var _this = this;
    var instruments;

    if ( _this.$el.find(".keywords .tagedit-list input:hidden").length == 0 ) {
      instruments = this.data.keyword.split(",");
    }
    else {
      instruments = _this.$el.find(".keywords .tagedit-list input:hidden").map(function(){ val = $(this).val(); if( val.length > 0 ) return val; }).get();
    }
    if( typeof instruments == "string" ) {
      instrument = instruments;
      instruments = [ instrument ];
    }
    _this.instruments = [];
    for( var i in instruments ) {
      _this.instruments.push( Constants.InstrumentsMap[ instruments[i].toLowerCase() ] ); 
    }
  },
  initTooltips: function() {
    var options = {
      animation: true,
      placement: "right",
      trigger: "focus",
      title: "Please enter the first name of the faculty",
    };    
    this.$el.find(".firstName").tooltip( options );
    
    options.title = "Please enter the middle name of the faculty";
    this.$el.find(".middleName").tooltip( options );
   
    options.title = "Please enter the last name of the faculty";
    this.$el.find(".lastName").tooltip( options );

    options.title = "Please enter the title of the faculty";
    this.$el.find(".title").tooltip( options );

    options.title = "Please provide us with the URL for the faculty";
    this.$el.find(".facultyUrl").tooltip( options );

    options.title = "What are instruments taught by this faculty?";
    this.$el.find(".keywords .tagedit-list input").tooltip( options );
  },
  getId: function() {
    return ( this.newFaculty ) ? -1 : this.data.facultyId;
  },
  deleteFaculty: function() {
    var _this = this;

    if( confirm("Are you sure you want to delete this faculty?") ) {
      $.ajax( {
        url: "rest/faculty/" + this.getId(),
        method: "POST",
        success: function( response, jqXHR, textStatus) {
          this.$el.remove();
        }, 
        error: function( response, jqXHR, textStatus ) {
          console.log("error");
        }
      });
    }  
  }
}


