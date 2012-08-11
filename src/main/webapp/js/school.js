var School = function( id ) {
  this.id = id;
  this.$el = $("<div/>");
  this.school = null;
  this.data = null;
  this.newSchool = false;
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
    this.$el.find(".information button").on( "click", function() {
      _this.showForm();
    });
    this.$el.find(".edit button").on( "click", function() {
      _this.showInformation();
    });
  },
  showForm : function() {
    this.school.setEditable( true );
  },
  showInformation: function() {
    this.school.setEditable( false );
  },
  hideButtons: function() {
    this.$el.find(".information button").hide();
    this.$el.find(".edit button").hide();
  },
  fetchData : function() {
    if( this.id == null || this.id == -1 ) {
      this.newSchool = true;
      this.school.setNew()
      this.hideButtons();
      this.showForm();
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
      this.departments[i] = new DepartmentInformation( _this );
      this.department.setData( department )
      this.$el.find(".data .department_list" ).append( this.department[i].$el );
    }
  },
  setEditable: function( value ) {
    this.editable = value;
    if( this.editable ) {
      this.showForm();
    }
    else {
      this.hideForm();
    }
    if( this.departments.length == 0 ) {
      this.$el.find(".sublist").hide();
    }
  },
  initEventListeners: function() {
    var _this = this;
    this.$el.find(".btn").click( function() {
      _this.save()
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
        _this.setEditable( false );
        console.log( "seeing data")
        _this.setData( response );
      }, 
      error: function( response, jqXHR, textStatus ) {
        console.log("error");
      }
    });

  },
  setNew: function() {
    this.newSchool = true;
  },
  showForm: function() {
    this.$el.find( ".data .school_information").html( this.formTemplate.tmpl( this.data ) );
    this.initEventListeners();
  },
  hideForm: function() {
    this.$el.find( ".data .school_information").html( this.informationTemplate.tmpl( this.data ) );
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
}

DepartmentInformation.prototype = {
  setData : function( data ) {
    this.data = data;
    this.$el.html( this.template.tmpl( data ) );
    this.setEditable( this.editable );
    for( var i in data.faculty ) {
      var faculty = data.faculty[i];
      this.faculty[i] = new FacultyInformation(  this );
      this.faculty[i].setData( faculty )
      this.$el.find(".faculty_list").append( this.faculty[i].$el );
    }
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
  showForm: function() {
    this.$el.find( ".department_information").html( this.formTemplate.tmpl( this.data ) );
  },
  hideForm: function() {
    this.$el.find( ".department_information").html( this.informationTemplate.tmpl( this.data ) );
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
}

FacultyInformation.prototype = {
  setData : function( data ) {
    this.data = data;
    this.$el = this.template.tmpl( data );
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
  showForm: function() {
    this.$el.find( ".faculty_information").html( this.formTemplate.tmpl( this.data ) );
  },
  hideForm: function() {
    this.$el.find( ".faculty_information").html( this.informationTemplate.tmpl( this.data ) );
  }
}


