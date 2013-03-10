<%@ page isELIgnored="true" %>
<%@ page import = " java.util.* " %>
<%
    Boolean edit = Boolean.parseBoolean( request.getParameter("edit") );
    if( edit == null ) {
      edit = new Boolean(false);
    }
    Boolean search = Boolean.parseBoolean( request.getParameter("search") ); 
    if( search == null ) {
      search = new Boolean(false);
    }    
%> 

    <div class="hd blue-gradient navbar navbar-fixed-top">
      <div class="logo">
        <a href="index.jsp">
          <img src="img/logo2.png" alt="Logo"></img>
        </a>
      </div>
      <% if( search == true ) { %>
        <div class="searchfield">
          <input type="text" id="searchinput" placeholder="Search by instrument"></input>
          <div id="searchbutton"><button></button></div>
        </div>
      <% } %>
      <div class="options">
        <% if( edit != true ) { %>
          <div class='login'>
            <div class='title'> 
              <a href="rest/openid?op=google">Manage Your School</a>
            </div>
          </div>
        <% }else{ %>
          <div class='signout'>
            <div class='title'><a href='http://www.schoolmusica.com/rest/openid/logout'>Sign Out</a></div>
          </div>
        <%}%>     
        <div class='spacer'>|</div>
        <div class='about-us'>
          <div class='title'><a href='about.jsp'>About Us</a></div>
        </div>
      </div>
    </div>