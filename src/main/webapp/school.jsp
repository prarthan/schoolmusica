<%@ page isELIgnored="true" %>
<%@ page import = " java.util.* " %>
<%
    String id= "-1";
    if (request.getParameter("id") != null) {
      id = request.getParameter("id");
    } 
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset=utf-8>
    <title>School Musica</title>
    <link rel="stylesheet" href="css/reset.min.css" type='text/css'>    
    <link rel="stylesheet" href="css/jquery-ui-1.8.21.custom.css" type='text/css'>
    <link rel="stylesheet" href="css/bootstrap.min.css" type='text/css'>
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" type='text/css'>
    <link rel="stylesheet" href="css/gradients.css" type='text/css'>
    <link rel="stylesheet" href="css/common.css" type='text/css'>   
    <link rel="stylesheet" href="css/school.css" type='text/css'>   
  </head>
  <body>
    <div class="hd blue-gradient navbar navbar-fixed-top">
      <div class="logo">
        <a href="index.html">
          <img src="img/logo2.png" alt="Logo"></img>
        </a>
      </div>
    </div>
    <div class="bd container-fluid gray-gradient">
      <div class="row-fluid ">
        <div id='school'>
          <h1>School Information</h1>
          <div class='information'>
            <div class="data">
            </div>
            <button id="edit">Edit</button>
            <button id="done" style="display:none;">Done</button>
          </div>
        </div>
      </div>
    </div>
    <div class="ft"></div>
    <script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui-1.8.21.custom.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.tmpl.min.js"></script>
    <script type="text/javascript" src="js/constants.js"></script>
    <script type="text/javascript" src="js/school.js"></script>
    <script id="schoolTemplate" type="text/x-jquery-tmpl"> 
      <div class='school' id="school_${musicSchoolId}">
        <div class='school_information'>
        </div>
        <div class='sublist'>
          <div class='sublist_title'>
            Departments
          </div>
          <div class='department_list'>
          </div>
        </div>
      </div>
    </script>
    <script id="schoolInformationTemplate" type="text/x-jquery-tmpl">
      <div class='name'>
        <b>${name}</b>
      </div>
    </script>
    <script id="schoolFormTemplate" type="text/x-jquery-tmpl"> 
      <div class='name'>
        <label>School Name</label>
        <input type="text" id="schoolName" value=${name} required></input>
      </div>
      <div class='btn save save-school'>Save</div>
      <div class='btn cancel cancel-school'>Cancel</div>
    </script>
    <script id="departmentTemplate" type="text/x-jquery-tmpl">
      <div class='department' id="department_${departmentId}">
        <div class='department_information'>
        </div>
        <div class="sublist">
          <div class="sublist_title">Faculty</div>
          <div class="faculty_list"></div>    
        </div>    
      </div>
      <hr></hr>
    </script>
    <script id="departmentInformationTemplate" type="text/x-jquery-tmpl">
      <div class='name'>
        <b>${departmentName}</b>
      </div>
      <div class='departmentUrl'>
        ${departmentUrl}
      </div>
      <div class='email'>
        ${email}
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
      <div class="resultitem">
        <b>Other Information</b>
        <div class='resultiteminfo'>
          <div><u>Music Minor Available:</u> {{if musicMinorAvailable}}Yes{{else}}No{{/if}}</div>
          <div><u>Graduate Program Available:</u> {{if graduateProgramAvailable}}Yes{{else}}No{{/if}}</div>
          <div><u>Scholarship Available:</u> {{if scholarshipsAvailable}}Yes{{else}}No{{/if}}</div>
        </div>
      </div>
      <div class='keywords'>
        <b>Keywords: </b>
        <span>${keyword}</span>
      </div>
    </script>
    <script id="departmentFormTemplate" type="text/x-jquery-tmpl">
        <div class='name'>
          <label>Name</label>
          <input type="text" class="departmentName" value="${departmentName}" required></input>
        </div>
        <div class='url'>
          <label>Website</label>
          <input type="url" class="departmentUrl" value="${departmentUrl}" required></input>
        </div>
        <div class='department_email'>
          <label>Email</label>
          <input type="email" class="email" value="${email}" required></input>
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
        <div class="resultitem scores">
           <b>Minimum Scores Required</b>
           <div class='resultiteminfo'>
             <div class="score">
               <label class="exam">SAT</label>
               <input type="number" class="satMin" value="${satMin}" required></input>
             </div>
             <div class="score">
               <label class="exam">GRE</label>
               <input type="number" class="greMin" value="${greMin}" required></input>
             </div>
             <div class="score">
               <label class="exam">ACT</label>
               <input type="number" class="actMin" value="${actMin}" required></input>
             </div>
           </div>
        </div>
        <div class="resultitem">
          <b>Other Information</b>
          <div class='resultiteminfo'>
            <div class="musicMinorAvailable">
              <u>Music Minor Available:</u> 
              <div class="btn-group" data-toggle="buttons-radio">
                  <button class="btn btn-mini">Yes</button>
                  <button class="btn btn-mini">No</button>
                </div>
              </div>
            <div class="graduateProgramAvailable">
              <u>Graduate Program Available:</u>
              <div class="btn-group" data-toggle="buttons-radio">
                  <button class="btn btn-mini">Yes</button>
                  <button class="btn btn-mini">No</button>
                </div>              
            </div>
            <div class="scholarshipsAvailable">
              <u>Scholarships Available:</u>
              <div class="btn-group" data-toggle="buttons-radio">
                  <button class="btn btn-mini">Yes</button>
                  <button class="btn btn-mini">No</button>
                </div>
            </div>
          </div>
        </div>
        <div class="keywords">
          <label>Keywords</label>
          <input type="text" class="keyword" value="${keyword}" required></input>
        </div>
        <div class='btn save save-department'>Save</div>
        <div class='btn cancel cancel-department'>Cancel</div>
    </script>
    <script id="facultyTemplate" type="text/x-jquery-tmpl"> 
      <div class='faculty' id="faculty_${facultyId}">
        <div class="faculty_information"></div>
      </div>
    </script>
    <script id="facultyInformationTemplate" type="text/x-jquery-tmpl">
        <div class='name'>
          Name: ${firstName} ${middleName} ${lastName}
        </div>
        <div class='title'>
          Title: ${title}
        </div>
        <div class='url'>
          Website: ${facultyUrl}
        </div>
        <div class='keywords'>
          <b>Keywords: </b>
          <span>${keyword}</span>
        </div>
    </script>
    <script id="facultyFormTemplate" type="text/x-jquery-tmpl"> 
      <div class='name'>
        <label>First Name</label>
        <input type="text" class="firstName" value="${firstName}" required></input>
        <label>Middle Name</label>
        <input type="text" class="middleName" value="${middleName}" required></input>
        <label>Last Name</label>
        <input type="text" class="lastName" value="${lastName}" required></input>
      </div>
      <div class='title-field'>
        <label>Title</label>
        <input type="text" class="title" value="${title}" required></input>
      </div>
      <div class='url-field'>
        <label>Website</label>
        <input type="url" class="facultyUrl" value="${facultyUrl}" required></input>
      </div>
      <div class="keywords">
        <label>Keywords</label>
        <input type="text" class="keyword" value="${keyword}" required></input>
      </div>
      <div class='btn save-faculty'>Save</div>
      <div class='btn cancel-faculty'>Cancel</div>
    </script>
    <script type="text/javascript">
       $(document).ready( function() {
         var school = new School(<%= id %>);
         school.init();
       });
    </script>
  </body>
</html>
