<%@ page import = "java.util.* " %>

<% if( ${compress} ){ %>
  <script type="text/javascript" src="js/common-all.js"></script>
<%} else { %>
  <script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="js/jquery/jquery-ui-1.8.21.custom.min.js"></script>
  <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/class.js"></script>
  <script type="text/javascript" src="js/constants.js"></script>
  <script type="text/javascript" src="js/header.js"></script>
<% } %>