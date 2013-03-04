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
    <link rel="stylesheet" href="css/search.css" type='text/css'>   
  </head>
  <body>
    <jsp:include page="includes/header.jsp">
      <jsp:param name="edit" value="<%=edit%>" />
      <jsp:param name="search" value="true" />
    </jsp:include>
    <div class="bd container-fluid gray-gradient">
      <div class="row-fluid ">
        <div id="filters" class="span2">
          <form>
            <div id="states" class='filter'>
              <div class='hd blue-gradient'>
                <span>State</span>
              </div>
              <div class='bd'>
                <input id="stateAC" name="stateAC" type="text" ></input>
                <input type="hidden" class="filterInput" id="state" name="state"></input>
              </div>
            </div>
            <div id="scores" class='filter'>
              <div class='hd blue-gradient'>
                <span>Minimum Scores</span>
              </div>
              <div class='bd'>
                <div id="sat_score" class='label unchecked'>
                  <span>SAT</span>
                  <input id="satMin" class="filterInput" type="number" name="satMin" value="0" min="0" max="800"></input>
                </div>
                <div id="gre_score" class='label unchecked'>
                  <span>GRE</span>
                  <input id="greMin" class="filterInput" type="number" name="greMin" value="0"  min="0" max="1600"></input>
                </div>
                <div id="act_score" class='label unchecked'>
                  <span>ACT</span>
                  <input id="actMin" class="filterInput" type="number" name="actMin" value="0"  min="0" max="2000"></input>
                </div>  
              </div>                
            </div>
          </form>
          <div id="others" class='filter'>
            <div class='hd blue-gradient'>
              <span>Other</span>
            </div>
            <div class='bd'>
              <div id="musicMinor" class='label unchecked'>
                <div>Music Minor</div>
                <div class="btn-group" data-toggle="buttons-radio">
                  <button class="btn btn-mini">Yes</button>
                  <button class="btn btn-mini">No</button>
                </div>
              </div>
              <div id="graduateProgram" class='label unchecked'>
                <div>Graduate Program</div>
                <div class="btn-group" data-toggle="buttons-radio">
                  <button class="btn btn-mini">Yes</button>
                  <button class="btn btn-mini">No</button>
                </div>
              </div>
              <div id="scholarships" class='label unchecked'>
                <div>Scholarships</div>
                <div class="btn-group" data-toggle="buttons-radio">
                  <button class="btn btn-mini">Yes</button>
                  <button class="btn btn-mini">No</button>
                </div>
              </div>
            </div>
              </div>
        </div>
        <div id="searchlist" class="span10">
           <div id="searchinfo"></div>
           <div id="searchresults"></div>
           <div> 
           		<font id="footer" size="-2">&copy;2012 - 2016 schoolmusica, All rights reserved</font>
           </div>
        </div>
      </div>
    </div>
    <div class="ft"></div>
    <jsp:include page="includes/common_js.jsp" />
    <script type="text/javascript" src="js/search.js"></script>
    <script id="searchResultSchoolTemplate" type="text/x-jquery-tmpl"> 
      <div class='searchresult' id=${domId} >
        <h2><a href="school.jsp?id=${musicSchoolId}&search=${searchQuery}" target="_blank">${schoolName}</a></h2>
        <div class='address'>
          ${address} ${city} ${state}-${zip}
        </div>
        <div class="resultitem scores">
           <h4>Minimum Scores Required</h4>
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
        <div class='department_list'></div>
      </div>
    </script>
    <script id="searchResultDepartmentTemplate" type="text/x-jquery-tmpl"> 
      <div class='department'>
        <h3><a href="rest/track/department?schoolId=${musicSchoolId}&departmentUrl=${departmentUrl}" target="_blank">${departmentName}</a></h3>
        <div class="resultitem">
          <h4>Other Information</h4>
          <div class='resultiteminfo'>
            <div><u>Music Minor Available:</u> {{if musicMinorAvailable}}Yes{{else}}No{{/if}}</div>
            <div><u>Graduate Program Available:</u> {{if graduateProgramAvailable}}Yes{{else}}No{{/if}}</div>
            <div><u>Scholarship Available:</u> {{if scholarshipsAvailable}}Yes{{else}}No{{/if}}</div>
          </div>
        </div>        
      </div>
      <hr></h3>
    </script>
    <script type="text/javascript">
      $(document).ready( function() {
        var search = new Search();
        search.init();
      });
    </script>
    <jsp:include page="includes/analytics.jsp" />
  </body>
</html>

