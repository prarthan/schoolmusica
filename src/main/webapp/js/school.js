var School = function( id ) {
  this.id = id;
  this.$el = $("<div/>");
  this.school = null;
  this.data = null;
};

School.prototype = {
  init: function() {
    this.header = new Header();
    this.header.init();
    this.$el = $("#school");
    this.school = new SchoolInformation();
    this.school.init(  this.$el.find(".information") );
    this.fetchData();
  },
  fetchData : function() {
    if( this.id == null || this.id == -1 ) {
      window.location.href = "/musicschool/index.jsp"
      return;
    }
    var _this = this;
    $.getJSON( "rest/school/" + this.id, {}, function( response, textStatus, jqXHR ) {
      if( response == null ) {
        window.location.href = "/musicschool/index.jsp"
        return;
      }
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
  this.departments = [];
}

SchoolInformation.prototype = {
  init: function( $el ) {
    this.$el = $el;
    this.$el.find(".data").html( this.template.tmpl( {} ) );
  },
  setData : function( data ) {
    this.data = data;
    this.$el.find(".data").html( this.template.tmpl( this.data ) );
    for( var i in data.department ) {
      var department = data.department[i];
      if( i == 0 )
         department.inClass = "in"
      this.addDepartment( department )
    }
    this.updateTemplate();
  },
  updateTemplate: function() {
    this.$el.find( ".data .school_information").html( this.informationTemplate.tmpl( this.data ) );
    this.$el.find(".department_list").parent().show();
    if( this.departments.length == 0 ) {
      this.$el.find(".department_list").parent().hide();
    }
  },  
  addDepartment: function( data ) {
    if( data == null ) {
      return;
    }
    var department = new DepartmentInformation( this );
    this.departments.push( department );
    department.setData( data );
    this.$el.find(".data .department_list" ).append( department.$el );
    this.$el.find(".department_list").parent().show();
  },
  getId: function() {
    return ( this.data && this.data.musicSchoolId ) ? this.data.musicSchoolId : -1;
  }
}

DepartmentInformation = function( school ) {
  this.school = school;
  this.$el = $("<div/>");
  this.template = $("#departmentInformationTemplate");
  this.faculty = [];
  this.data = null;
}

DepartmentInformation.prototype = {
  setData : function( data ) {
    this.data = data;
    this.$el.html( this.template.tmpl( data ) );
    for( var i in data.faculty ) {
      var faculty = data.faculty[i];
      if( i == 0 )
         faculty.inClass = "in"
      this.addFaculty( faculty );
    }
    this.updateTemplate();
  },  
  addFaculty: function( data, editable ) {
    if( data == null ) {
      return
    }
    var faculty = new FacultyInformation(  this );
    this.faculty.push( faculty );
    faculty.setData( data )
    this.$el.find(".faculty_list").append( faculty.$el );
    this.$el.find(".faculty_list").parent().show();
   
  },
  updateTemplate: function() {
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
  this.template = $("#facultyInformationTemplate")
  this.data = null;
}

FacultyInformation.prototype = {
  setData : function( data ) {
    this.data = data;
    this.$el.html( this.template.tmpl( data ) );
  },
  getId: function() {
    return ( this.newFaculty ) ? -1 : this.data.facultyId;
  }
}


