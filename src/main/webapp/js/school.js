var School = function( id, searchQuery ) {
  this.id = id;
  this.$el = $("<div/>");
  this.school = null;
  this.data = null;
  this.searchQuery = ( searchQuery && searchQuery.length > 0 ) ? searchQuery : null;
  if( this.searchQuery != null ) {
    this.searchQuery = this.searchQuery.replace("_-_", " ")
  }
};

School.prototype = {
  init: function() {
    this.header = new Header();
    this.header.init();
    this.$el = $("#school");
    this.school = new SchoolInformation();
    this.school.init(  this.$el.find(".information"), this.searchQuery );
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

SchoolInformation = function( id, searchQuery ) {
  this.$el = $("<div/>");
  this.data = {};
  this.template = $("#schoolTemplate");
  this.informationTemplate = $("#schoolInformationTemplate");
  this.departments = [];
}

SchoolInformation.prototype = {
  init: function( $el, searchQuery ) {
    this.$el = $el;
    this.searchQuery = ( searchQuery && searchQuery.length > 0 ) ? searchQuery : null;
    this.$el.find(".data").html( this.template.tmpl( {} ) );
    this.$el.find(".department_list").on( "shown", function(e) {
      $(e.target).parent().addClass("expanded");
    });
    this.$el.find(".department_list").on( "hidden", function(e) {
      $(e.target).parent().removeClass("expanded");
    });
  },
  setData : function( data ) {
    this.data = data;
    this.$el.find(".data").html( this.template.tmpl( this.data ) );
    for( var i in data.department ) {
      var department = data.department[i];
      if( !this.searchQuery ) {
        if( i == 0 ) {
          department.inClass = "in"
        }
      }
      else {
        if( data.department[i].keyword.match( this.searchQuery ) ) {
          department.inClass  = "in"
        }
      }

      this.addDepartment( department )
    }
    this.updateTemplate();
    this.$el.find(".department_list").on( "shown", function(e) {
      $(e.target).parent().addClass("expanded");
    });
    this.$el.find(".department_list").on( "hidden", function(e) {
      $(e.target).parent().removeClass("expanded");
    });
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
    if( this.departments.length == 1 ) {
      this.$el.find(".department_list .department").addClass("expanded");
    }
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
  this.addedEvent = false;
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
    if( this.faculty.length == 1 ) 
      this.$el.find(".faculty_list .faculty").addClass("expanded");   
  },
  updateTemplate: function() {
    if( this.faculty.length == 0 ) {
      this.$el.find(".faculty_list").parent().hide();
    }
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


