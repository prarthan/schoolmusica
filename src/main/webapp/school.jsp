<%@ page isELIgnored="true" %>
<%@ page import = " java.util.* " %>
<%
    String id= "-1";
	  String email = "";
    String searchQuery = "";
    if (request.getParameter("id") != null) {
      id = request.getParameter("id");
    } 
    Boolean edit = (Boolean)session.getAttribute("canedit");
    if( edit == null ) {
      edit = new Boolean(false);
    }
    
    if (request.getParameter("search") != null ) {
      searchQuery = request.getParameter("search");
    }
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="includes/common_html_head.jsp" />  
    <link rel="stylesheet" href="css/school.css" type='text/css'>   
  </head>
  <body>
    <jsp:include page="includes/header.jsp">
      <jsp:param name="edit" value="<%=edit%>" />
      <jsp:param name="search" value="false" />
    </jsp:include>
    <div class="bd container-fluid gray-gradient">
      <div class="row-fluid ">
        <div id='school'>
          <h3></h3>
          <div class='information'>
            <div class="data">
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="ft"></div>
    <jsp:include page="includes/common_js.jsp" />
    <% if( edit == true ) { %>
      <script type="text/javascript" src="js/school-edit.js"></script>
    <% } else { %>
      <script type="text/javascript" src="js/school.js"></script>
    <% } %>
    <script id="schoolTemplate" type="text/x-jquery-tmpl"> 
      <div class='school' id="school_${musicSchoolId}">
        <div class='school_information'>
        </div>
        <div class='sublist'>
          <div class='sublist_title'>
            Departments
          </div>
          <div class='department_list accordian'>
          </div>
        </div>
      </div>
    </script>
    <script id="schoolInformationTemplate" type="text/x-jquery-tmpl">
      <div class='name'>
        <h2>${schoolName}</h2>
      </div>
      <div class='address'>
        ${address} ${city} ${state}-${zip} ${country}
      </div>
      <div class="resultitem scores">
         <b>Minimum Scores Required</b>
         <div class='resultiteminfo'>
           <div class="score">
             <div class="exam">SAT</div>
             <div class="min">${satMin}</div>
           </div>
           <div class="score">
             <div class="exam">GRE</div>
             <div class="min">${greMin}</div>
           </div>
           <div class="score">
             <div class="exam">ACT</div>
             <div class="min">${actMin}</div>
           </div>
         </div>
      </div>
    </script>
    <script id="departmentInformationTemplate" type="text/x-jquery-tmpl">
      <div class='accordion-group department'>
        <a class="accordion-toggle" data-toggle="collapse" data-parent=".department_list" href="#collapse_dept_${departmentId}"><div class='name'>${departmentName}</div></a>
        <div id="collapse_dept_${departmentId}" class="accordion-body collapse ${inClass}">
          <div class='accordian-inner'>
            <div class='keywords'>
              <b>Instruments: </b>
              <span>${keyword}</span>
            </div>
            <div class='departmentUrl'>
              <a target="_blank" href="rest/track/department?schoolId=${musicSchoolId}&departmentUrl=${departmentUrl}">${departmentUrl}</a>
            </div>
            <div class='email'>
              ${email}
            </div>
            <div class="resultitem">
              <b>Other Information</b>
              <div class='resultiteminfo'>
                <div><u>Music Minor Available:</u> {{if musicMinorAvailable}}Yes{{else}}No{{/if}}</div>
                <div><u>Graduate Program Available:</u> {{if graduateProgramAvailable}}Yes{{else}}No{{/if}}</div>
                <div><u>Scholarship Available:</u> {{if scholarshipsAvailable}}Yes{{else}}No{{/if}}</div>
              </div>
            </div>
            <div class="sublist">
              <div class="sublist_title">Faculty</div>
              <div class="faculty_list accordian"></div>    
            </div> 
          </div>
        </div>
      </div>
    </script>
    <script id="facultyInformationTemplate" type="text/x-jquery-tmpl">
      <div class='accordion-group faculty'>
        <a class="accordion-toggle" data-toggle="collapse" data-parent=".department_list" href="#collapse_faculty_${facultyId}" ><div class='name'>${firstName} ${middleName} ${lastName},&nbsp;<i>${title}</i></div></a>
        <div id="collapse_faculty_${facultyId}" class="accordion-body collapse ${inClass}">
          <div class='accordian-inner'>      
            <div class='faculty'>
              <div class='keywords'>
                <b>Instruments: </b>
                <span>${keyword}</span>
              </div>
              <div class='keywords'>
                <b>Styles: </b>
                <span>${styles}</span>
              </div>
              <div class='url'>
                <a href="${facultyUrl}" target="_blank">${facultyUrl}</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </script>
    <% if( edit == true ) { %>
      <script id="schoolFormTemplate" type="text/x-jquery-tmpl"> 
        <div class='school'>
          <div class="form">
          <div class="alert alert-error" style="display:none">Please fix the fields shown in red below</div>
          <div class='name'>
            <label>School Name</label>
            <input required type="text" id="schoolName" value="${schoolName}" required ></input>
          </div>
          <div class='address_information'>
            <label>Street Address</label>
            <input type="text" class="address" value="${address}" required></input>
            <label>City</label>
            <input type="text" class="city" value="${city}" required></input>
            <label>State</label>
            <input type="text" class="state" value="${state}" required></input>
            <label>Zip</label>
            <input type="text" class="zip" value="${zip}" required></input>
            <label>Country</label>
            <input type="text" class="country" value="${country}" required></input>
          </div>
          <div class="input_score">
             <b>Minimum Scores Required</b>
             <div class='resultiteminfo'>
               <div>
                 <label class="">SAT</label>
                 <input type="number" class="satMin" value="${satMin}" required></input>
               </div>
               <div>
                 <label class="">GRE</label>
                 <input type="number" class="greMin" value="${greMin}" required></input>
               </div>
               <div >
                 <label class="">ACT</label>
                 <input type="number" class="actMin" value="${actMin}" required></input>
               </div>
             </div>
          </div>
          <div class='button-group'>
            <div class='btn save save-school'>Save Changes</div>
            <div class='btn cancel cancel-school'>Cancel</div>
          </div>
          </div>
        </div>
      </script>
      <script id="departmentFormTemplate" type="text/x-jquery-tmpl">
      <div class='department accordion-group'>
        <div class='indicator'>Loading...</div>
        <a class="accordion-toggle" data-toggle="collapse" data-parent=".department_list" href="#collapse_dept_${departmentId}" ><div class='name'>${departmentName}</div></a>
        <div id="collapse_dept_${departmentId}" class="accordion-body collapse ${inClass}">
          <div class="form accordian-inner">
            <div class='form_title'>Edit Department Information</div>
            <div class="alert alert-error" style="display:none">Please fix the fields shown in red below</div>
            <div class='name'>
              <label>Name</label>
              <input type="text" class="departmentName" value="${departmentName}" required></input>
            </div>
            <div class="keywords">
              <label>Instruments</label>
              <input type="text" name='keyword[]' class="keyword" value="${keyword}" required></input>
            </div>
            <div class='url'>
              <label>Website</label>
              <input type="url" class="departmentUrl" value="${departmentUrl}" required></input>
            </div>
            <div class='department_email'>
              <label>Email</label>
              <input type="email" class="email" value="${email}" required></input>
            </div>
            <div class="other">
              <b>Other Information</b>
              <div class=''>
                <div class="item">
                  <label>Music Minor Available</label> 
                  <div class="btn-group musicMinorAvailable" data-toggle="buttons-radio">
                      <button class="btn btn-mini yes">Yes</button>
                      <button class="btn btn-mini no">No</button>
                    </div>
                  </div>
                <div class="item">
                  <label>Graduate Program Available</label>
                  <div class="btn-group graduateProgramAvailable" data-toggle="buttons-radio">
                      <button class="btn btn-mini yes">Yes</button>
                      <button class="btn btn-mini no">No</button>
                    </div>              
                </div>
                <div class="item">
                  <label>Scholarships Available</label>
                  <div class="btn-group scholarshipsAvailable" data-toggle="buttons-radio">
                      <button class="btn btn-mini yes">Yes</button>
                      <button class="btn btn-mini no">No</button>
                    </div>
                </div>
              </div>
            </div>
            <div class="button-group">
              <div class='btn save save-department'>Save Changes</div>
              <div class='btn cancel cancel-department'>Cancel</div>
			        <div class='btn delete delete-department'>Delete</div> 	
            </div>
            <div class="sublist">
              <div class="sublist_title">Faculty</div>
              <div class="faculty_list accordian"></div>    
            </div> 
          </div>
        </div>
      </div>
      </script>
      <script id="facultyFormTemplate" type="text/x-jquery-tmpl"> 
      <div class='faculty accordion-group'>
        <div class='indicator'>Loading...</div>
        <a class="accordion-toggle" data-toggle="collapse" data-parent=".department_list" href="#collapse_faculty_${facultyId}" ><div class='name'>${firstName} ${middleName} ${lastName},&nbsp;<i>${title}</i></div></a>
        <div id="collapse_faculty_${facultyId}" class="accordion-body collapse ${inClass}">
          <div class="faculty_form form accordian-inner">
            <div class='form_title'>Edit Faculty Information</div>
            <div class="alert alert-error" style="display:none">Please fix the fields shown in red below</div>
            <div class='name'>
              <label>Name</label>
              <input type="text" class="firstName" value="${firstName}" required></input>
              <input type="text" class="lastName" value="${lastName}" required></input>
            </div>
            <div class='title-field'>
              <label>Title</label>
              <input type="text" class="title" value="${title}" required></input>
            </div>
            <div class="keywords">
              <label>Instruments</label>
              <input type="text" name='keyword[]' class="keyword" value="${keyword}" required></input>
            </div>
            <div class="styles">
              <label>Styles</label>
              <input type="text" name='style[]' class="style" value="${styles}" required></input>
            </div>
            <div class='url-field'>
              <label>Website</label>
              <input type="url" class="facultyUrl" value="${facultyUrl}" required></input>
            </div>
            <div class='button-group'>
              <div class='btn save-faculty'>Save Changes</div>
              <div class='btn cancel-faculty'>Cancel</div>
			        <div class='btn delete-faculty'>Delete</div>
            </div>
          </div>
        </div>
      </div>
      </script>
    <% } %>
    <script type="text/javascript">
       $(document).ready( function() {
        $(".row-fluid").height( $(window).height() - 109 );
        $(window).on( "resize", function() {
          $(".row-fluid").height( $(window).height() - 109 );
        });
        <% if( edit == true ) { %>
          var school = new SchoolEdit(<%= id %> );
        <% } else { %>
          var school = new School(<%= id %>, "<%= searchQuery %>" );
        <% } %>
        school.init();
       });
    </script>
    <jsp:include page="includes/analytics.jsp" />
  </body>
</html>
