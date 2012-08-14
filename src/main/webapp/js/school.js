var School = function( id ) {
  this.id = id;
  this.$el = $("<div/>");
  this.school = null;
  this.data = null;
  this.newSchool = false;
  this.editMode = false;
};

School.prototype = {
  init: function() {
    this.$el = $("#school");
    this.school = new SchoolInformation();
    this.school.init(  this.$el.find(".information") );
    this.initEventListeners();
    this.fetchData();
  },
  initEventListeners : function() {
    var _this = this;
    this.$el.find("#edit").on( "click", function() {
      _this.showForm();
    });
    this.$el.find("#done").on( "click", function() {
      _this.hideForm();
    });
  },
  showForm : function() {
    this.editMode = true;
    this.school.setEditable( true );
    this.$el.find("#edit").hide();
    this.$el.find("#done").show();
  },
  hideForm: function() {
    this.editMode = false;
    this.school.setEditable( false );
    this.$el.find("#done").hide();
    this.$el.find("#edit").show();
  },
  hideButtons: function() {
    this.$el.find("#done").hide();
    this.$el.find("#edit").hide();
  },
  fetchData : function() {
    if( this.id == null || this.id == -1 ) {
      this.newSchool = true;
      this.school.setNew()
      this.showForm();
      this.hideButtons();
      return;
    }
    var _this = this;
    $.getJSON( "rest/school/" + this.id, {}, function( response, textStatus, jqXHR ) {
        _this.data = response;
        _this.school.setData( response )
    } );
  }
}

SchoolInformation = function( id ) {
  this.$el = $("<div/>");
  this.data = {};
  this.template = $("#schoolTemplate");
  this.informationTemplate = $("#schoolInformationTemplate");
  this.formTemplate = $("#schoolFormTemplate");
  this.departments = [];
  this.editable = false;
  this.newSchool = false;
}

SchoolInformation.prototype = {
  init: function( $el ) {
    this.$el = $el;
    this.$el.find(".data").html( this.template.tmpl( {} ) );
  },
  setData : function( data ) {
    this.data = data;
    this.$el.find(".data").html( this.template.tmpl( this.data ) );
    this.setEditable( this.editable );
    for( var i in data.department ) {
      var department = data.department[i];
      this.addDepartment( department )
    }
  },
  addDepartment: function( data, editable ) {
    var department = new DepartmentInformation( this );
    if( data == null ) {
      data = {};
      department.setNew();
    }
    else {
      this.departments.push( department );
    }
    department.setData( data );
    this.$el.find(".data .department_list" ).append( department.$el );
    if( editable )
      department.setEditable( true );
    this.$el.find(".department_list").parent().show();
  },
  setEditable: function( value ) {
    this.editable = value;
    if( this.editable ) {
      this.showForm();
    }
    else {
      this.hideForm();
    }
  },
  initEventListeners: function() {
    var _this = this;
    this.$el.find(".school_information .btn.save-school").click( function() {
      _this.save()
    });
    this.$el.find(".school_information .btn.cancel-school").click( function() {
      _this.hideForm()
    });
  },
  getData : function() {
    data = {};
    data.name = $.trim( this.$el.find("#schoolName").val() );
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
    return validate;
  },
  save: function() {
    this.$el.find("input").removeClass("error");
    if( ! this.validate() ) {
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
        _this.setData( response );
        _this.hideForm();
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
  addEditButtons: function() {
    for( var i in this.departments ) {
      this.departments[i].addEditButton();
    }
  },
  showForm: function() {
    this.$el.find( ".data .school_information").html( this.formTemplate.tmpl( this.data ) );
    this.$el.find(".department_list").parent().show();
    if( ! this.newSchool ) {
      this.addEditButtons();
      this.addDepartmentButton();
    }
    else {
      this.$el.find(".department_list").parent().hide();
    }
    this.initEventListeners();
  },
  hideForm: function() {
    this.$el.find( ".data .school_information").html( this.informationTemplate.tmpl( this.data ) );
    this.$el.find(".department_list").parent().show();

    if( this.editable ) {
      this.addEditButtons();
      this.addDepartmentButton();
    }
    if( this.departments.length == 0 ) {
      this.$el.find(".department_list").parent().hide();
    }
  },
  getId: function() {
    return ( this.newSchool ) ? -1 : this.data.musicSchoolId;
  }
}

DepartmentInformation = function( school ) {
  this.school = school;
  this.$el = $("<div/>");
  this.template = $("#departmentTemplate");
  this.informationTemplate = $("#departmentInformationTemplate");
  this.formTemplate = $("#departmentFormTemplate");
  this.faculty = [];
  this.data = null;
  this.editable = false;
  this.newDepartment = false;
}

DepartmentInformation.prototype = {
  setData : function( data ) {
    this.data = data;
    this.$el.html( this.template.tmpl( data ) );
    this.setEditable( this.editable );
    for( var i in data.faculty ) {
      var faculty = data.faculty[i];
      this.addFaculty( faculty );
    }
  },
  addFaculty: function( data, editable ) {
    var faculty = new FacultyInformation(  this );
    if( data == null ) {
      data = {};
      faculty.setNew();
    }
    else {
      this.faculty.push( faculty );
    }
    faculty.setData( data )
    this.$el.find(".faculty_list").append( faculty.$el );
    if( editable )
      faculty.setEditable( true );
    this.$el.find(".faculty_list").parent().show();
   
  },
  setEditable: function( value ) {
    this.editable = value;
    if( this.editable ) {
      this.showForm();
    }
    else {
      this.hideForm();
    }
  },
  initEventListeners: function() {
    var _this = this;
    this.$el.find(".edit-department").click( function() {
      _this.showForm();
    } );
    this.$el.find(".btn.save-department").click( function() {
      _this.save();
    });
    this.$el.find(".btn.cancel-department").click( function() {
      _this.hideForm();
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

    var keyword = $.trim( this.$el.find(".keyword").val() );
    if( keyword.length == 0 ) {
      this.$el.find(".keyword").addClass("error");
      validate = false;     
    } 
    return validate;
  },
  getData: function() {
    var data = {};
    data.departmentName = $.trim ( this.$el.find(".departmentName").val() );
    data.departmentUrl = $.trim( this.$el.find(".departmentUrl").val() );
    data.address = $.trim( this.$el.find(".address").val() );
    data.city = $.trim( this.$el.find(".city").val() );
    data.state = $.trim( this.$el.find(".state").val() );  
    data.zip = $.trim( this.$el.find(".zip").val() );
    data.country = $.trim( this.$el.find(".country").val() );

    data.satMin = $.trim( this.$el.find(".satMin").val() );
    data.greMin = $.trim( this.$el.find(".greMin").val() );
    data.actMin = $.trim( this.$el.find(".actMin").val() );

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
    if( musicMinorAvailable.length == 0 ) {
      data.musicMinorAvailable = ( musicMinorAvailable.text() == "Yes" ) ? true: false;
    }   

    data.keyword = $.trim( this.$el.find(".keyword").val() );
    if( ! this.newDepartment ) {
      data.departmentId = this.data.departmentId;
    }
    data.musicSchoolId = this.school.getId();
    return data;
  },
  save: function() {
    var _this = this;
    this.$el.find(".error").removeClass(".error");
    if( ! this.validate() ) {
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
        _this.setData( response );
        _this.hideForm();
        if( _this.newDepartment ) {
          _this.editable = true;
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
  addEditButton: function() {
    if( this.$el.find(".department_information .name .edit-department").length == 0 ) {
      this.$el.find(".department_information .name").append("<div class='btn edit edit-department'>Edit</div>" );
    }
    this.editable = true;
    this.initEventListeners();
  },
  addEditButtons: function() {
    for( var i in this.faculty ) {
      this.faculty[i].addEditButton();
    }
  },
  showForm: function() {
    this.$el.find( ".department_information").html( this.formTemplate.tmpl( this.data ) );
    this.$el.find(".faculty_list").parent().show();
    if( ! this.newDepartment ) {
      this.addEditButtons();
      this.addFacultyButton();
    }
    else {
      this.$el.find(".faculty_list").parent().hide();
    }
    this.initEventListeners();
  },
  hideForm: function() {
    this.$el.find( ".department_information").html( this.informationTemplate.tmpl( this.data ) );
    this.$el.find(".faculty_list").parent().show();

    if( this.editable ) {
      this.addEditButton();
      this.addEditButtons();
      this.addFacultyButton();
    }
    if( this.faculty.length == 0 ) {
      this.$el.find(".faculty_list").parent().hide();
    }
  },
  getId: function() {
    return ( this.newDepartment ) ? -1 : this.data.departmentId;
  }

}

FacultyInformation = function( department ) {
  this.department = department;
  this.$el = $("<div/>");
  this.template = $("#facultyTemplate");
  this.informationTemplate = $("#facultyInformationTemplate")
  this.formTemplate = $("#facultyFormTemplate");
  this.data = null;
  this.editable = false;
  this.newFaculty = false;
}

FacultyInformation.prototype = {
  setData : function( data ) {
    this.data = data;
    this.$el.html( this.template.tmpl( data ) );
    this.setEditable( this.editable );
  },
  setEditable: function( value ) {
    this.editable = value;
    if( this.editable ) {
      this.showForm();
    }
    else {
      this.hideForm();
    }
  },
  setNew: function() {
    this.newFaculty = true;
  },
  initEventListeners: function() {
    var _this = this;
    this.$el.find(".edit-faculty").click( function() {
      _this.showForm();
    } );
    this.$el.find(".btn.save-faculty").click( function() {
      _this.save();
    });
    this.$el.find(".btn.cancel-faculty").click( function() {
      _this.hideForm();
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
    if( middleName.length === 0 ) {
      this.$el.find(".middleName").addClass("error");
      validate = false;
    }
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
    var url = $.trim ( this.$el.find(".url").val() );
    if( title.length === 0 ) {
      this.$el.find(".url").addClass("error");
      validate = false;
    }
    var keyword = $.trim ( this.$el.find(".keyword").val() );
    if( keyword.length === 0 ) {
      this.$el.find(".keyword").addClass("error");
      validate = false;
    }    

    return validate;
  },
  getData: function() {
    var data = {};
    data.firstName = $.trim ( this.$el.find(".firstName").val() );
    data.middleName = $.trim ( this.$el.find(".middleName").val() );
    data.title = $.trim ( this.$el.find(".title").val() );
    data.facultyUrl = $.trim ( this.$el.find(".url").val() );
    data.keyword = $.trim ( this.$el.find(".keyword").val() );
    
    if( ! this.newFaculty ) {
      data.departmentId = this.data.facultyId;
    }
    data.departmentId = this.department.getId();

    return data;
  },
  save: function() {
    var _this = this;
    this.$el.find(".error").removeClass(".error");
    if( ! this.validate() ) {
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
        if( _this.newFaculty )
          _this.editable = true;
        _this.setData( response );
        _this.hideForm();
        _this.newFaculty = false;

      }, 
      error: function( response, jqXHR, textStatus ) {
        console.log("error");
      }
    });    
  },
  showForm: function() {
    this.$el.find( ".faculty_information").html( this.formTemplate.tmpl( this.data ) );
    this.initEventListeners();  
  },
  hideForm: function() {
    this.$el.find( ".faculty_information").html( this.informationTemplate.tmpl( this.data ) );
    this.initEventListeners();
    if( this.editable ) {
      this.addEditButton();
    }
  },
  addEditButton: function() {
    if( this.$el.find(".faculty_information .name .edit-faculty").length == 0 ) {
      this.$el.find(".faculty_information .name").append("<div class='btn edit edit-faculty'>Edit</div>" );
    }
    this.editable = true;
    this.initEventListeners();
  },
}


