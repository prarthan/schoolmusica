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
            <button>Edit</button>
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
        <input type="text" id="schoolName" value=${name}></input>
      </div>
      <div class='btn'>Save</div>
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
      <div class='url'>
        ${departmentUrl}
      </div>
      <div class='address'>
        ${address} ${city} ${state}-${zip}
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
      <div class="keyword">
        ${keyword}
      </div>
    </script>
    <script id="departmentFormTemplate" type="text/x-jquery-tmpl">
      <div class='name'>
        <label>Name</label>
        <input name="departmentName" value="${departmentName}"></input>
      </div>
      <div class='url'>
        <label>Website</label>
        <input name="departmentUrl" value="${departmentUrl}"></input>
      </div>
      <div class='address'>
        <label>Street Address</label>
        <input name="address" value="${address}"></input>
        <label>City</label>
        <input name="city" value="${city}"></input>
        <label>State</label>
        <input name="state" value="${state}"></input>
        <label>Zip</label>
        <input name="zip" value="${zip}"></input>
      </div>
      <div class="resultitem scores">
         <b>Minimum Scores Required</b>
         <div class='resultiteminfo'>
           <div class="score">
             <label class="exam">SAT</label>
             <input type="number" name="satMin" value="${satMin}"></input>
           </div>
           <div class="score">
             <label class="exam">GRE</label>
             <div class="min">${greMin}</div>
             <input type="number" name="greMin" value="${greMin}"></input>
           </div>
           <div class="score">
             <label class="exam">ACT</label>
             <input type="number" name="actMin" value="${actMin}"></input>
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
      <div class="keyword">
        <input name="keyword" value="${keyword}"></input>
      </div>
    </script>
    <script id="facultyTemplate" type="text/x-jquery-tmpl"> 
      <div class='faculty' id="faculty_${facultyId}">
      </div>
    </script>
    <script id="facultyTemplate" type="text/x-jquery-tmpl">
      <div class='name'>
        ${firstName} ${middleName} ${lastName}
      </div>
      <div class='title'>
        ${title}
      </div>
      <div class='url'>
        ${facultyUrl}
      </div>
      <div class='keywords'>
         ${keyword}
      </div>
    </script>
    <script id="facultyFormTemplate" type="text/x-jquery-tmpl"> 
      <div class='name'>
        <input name="firstName" value="${firstName} "></input>
        <input name="middleName" value="{middleName} "></input>
        <input name="lastName" value="${lastName}"></input>
      </div>
      <div class='title'>
        <label>Title</label>
        <input name="title" value="${title}"></input>
      </div>
      <div class='url'>
        <label>Website</label>
        <input name="facultyUrl" value="${facultyUrl}"></input>
      </div>
      <div class='keywords'>
        <label>Keywords</label>
        <input name="keyword" value="${keyword}"></input>
      </div>
    </script>
    <script type="text/javascript">
       $(document).ready( function() {
         var school = new School(<%= id %>);
         school.init();
       });
    </script>
  </body>
</html>
