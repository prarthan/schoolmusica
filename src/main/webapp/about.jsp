<%@ page isELIgnored="true" %>
<%@ page import = " java.util.* " %>
<%
    Boolean edit = (Boolean)session.getAttribute("canedit");
    if( edit == null ) {
      edit = new Boolean(false);
    }
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="includes/common_html_head.jsp" />  
    <link rel="stylesheet" href="css/about.css" type='text/css'>   
  </head>
  <body>
    <jsp:include page="includes/header.jsp">
      <jsp:param name="edit" value="<%=edit%>" />
      <jsp:param name="search" value="false" />
    </jsp:include>
    <div class='bd'>
      <ul class="nav nav-pills nav-stacked" id="navbar">
        <li class='active'><a href="#about">About</a></li>
        <li><a href="#product">Product</a></li>
        <li><a href="#questions">Questions</a></li>
        <li><a href="#contact">Contact</a></li>
      </ul>
      <div class="container">
        <div id="about">
          <a name="about">
            <h1>About</h1>
          </a>
          <div class="information">
            <h1>SchoolMusica provides way to connect musicians with music school.</h1>
            <p>
              SchoolMusica will enable musicians to search music schools and courses based on instruments, styles and methods. It will enable schools to manage profiles and reach out to students.  
            </p>
            <h2>Our Values</h2>
            <h3>Simple</h3>
            <p>
              SchoolMusica will provide simple way for musicians to search music schools. It will simplify a school's admission process by recommending matching students and allowing schools to search for the students.
            </p>
            <h3>Quality</h3>
            <p>
              SchoolMusica will provide quality results using inputs from our expert panel of music professionals and teachers. Music Professionals and teachers have approved and ranked music schools.
            </p>
            <h3>Specific</h3>
            <p>
              SchoolMusica is designed only for musicians and music schools.
            </p>
            <h3>Smart</h3>
            <p>
              SchoolMusica will provide recommendation to schools and students based on their preferences and will provide matrices to improve their visibility and reach.
            </p>
          </div>
        </div>
        <div id="product" style="display:none;">
          <a name="product">
            <h1>Product</h1>
          </a>
          <div class="information">
            <h2>Our Product</h2>
            <h3>Search School</h3>
            <p>
              Search music school by instrument, your score(s) and location. Filter them by scholarships,graduate program or music minor provided.
            </p>
            <h3>Manage School</h3>
            <p>
              Manage music school profiles. Manage departments<i>(Department is defined as the contact point for learning similar instruments.)</i>
            </p>
          </div>
        </div>
        <div id="questions" style="display:none;">
          <a name="questions">
            <h1>Questions</h1>
          </a>
          <div class="information"></div>
        </div>
        <div id="contact" style="display:none;">
          <a name="contact">
            <h1>Contact Us</h1>
          </a>
          <div class="information">
            <p>
              Email : <a href="mailto:contact@scoolmusica.com">contact@scoolmusica.com</a>
            </p>
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="includes/common_js.jsp" />
    <script type="text/javascript" src="js/about.js"></script>
    <script type="text/javascript">
      $(document).ready( function() {
        var about = new About();
        about.init();
      });
    </script>
    <jsp:include page="includes/analytics.jsp" />
  </body>
</html>