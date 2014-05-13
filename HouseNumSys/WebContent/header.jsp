<%@page import="javax.servlet.http.*,
java.io.*,
java.util.*,
java.io.FileReader,
java.io.BufferedReader,
java.sql.*,
java.text.NumberFormat,
java.text.DecimalFormat,
java.util.Date,
java.text.DateFormat,
com.mannir.housenumsys.Db,
com.mannir.housenumsys.House"
%>
<%! Db db = new Db("sqlite","localhost","admin","13131","housenumsys"); %><%  db.connect(); %>


<% if(request.getParameter("login") != null) {
	String user1 = request.getParameter("user");
	String pass1 = request.getParameter("pass");
	session.setAttribute("username", user1);
	session.setAttribute("password", pass1);
	session.setAttribute("role", "admin");
}

if(request.getParameter("q") != null) {
	session.setAttribute("role", null);
	response.setStatus(response.SC_MOVED_TEMPORARILY); 
	response.setHeader("Location", "/");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<head>
<title>Nassarawa Local Government Area House Numbering Information System</title>

<link rel="shortcut icon" href="files/favicon0.png" type="image/x-icon">
<link type="text/css" rel="stylesheet" media="all" href="files/node0000.css">
<link type="text/css" rel="stylesheet" media="all" href="files/defaults.css">
<link type="text/css" rel="stylesheet" media="all" href="files/system00.css">
<link type="text/css" rel="stylesheet" media="all" href="files/system-m.css">
<link type="text/css" rel="stylesheet" media="all" href="files/user0000.css">
<link type="text/css" rel="stylesheet" media="all" href="files/style000.css">

<script type="text/javascript" src="files/jquery00.js"></script>
<script type="text/javascript" src="files/drupal00.js"></script>
<script type="text/javascript" src="files/jquery01.js"></script>
<script type="text/javascript" src="files/suckerfi.js"></script>
<script language="javascript" src="files/jquery-1.js"></script>
</head>
<body>
<div id="above" class="clear-block"></div>
<div id="page">
	<div id="masthead">

		<div id="header" class="clear-block">
			<div class="header-right">
				<div class="header-left">
					<div id="logo" style="text-align:left;"><a href="/" title="Home"><img width="80" height="80" src="images/logo.png" alt="Home"></a></div> 
					<div id="name-and-slogan">
                    <h1 id="site-name" align="center">
							<a href="/" title="Home">Nassarawa Local Government Area House Numbering Information System</a>
                          
						</h1>
						</div> <!-- /name-and-slogan -->
                                                            
								</div>  <!-- /header-left -->
  			</div> <!-- /header-right -->
		</div> <!-- /header -->

	</div>

	<div id="prenav"></div>
	<div id="navigation" class="menu">
			<div id="primary" class="clear-block">
			
			
<div class="navcont">
<ul>
<% if(session.getAttribute("role")==null) {
	out.println("<li class='menu-114 first'><a href='/' title='Home'>Home</a></li>");
}

else {
	if(session.getAttribute("role").equals("admin")) {
		out.println("<li class='menu-114 first'><a href='/' title='Home'>Home</a></li>");
		out.println("<li class='menu'><a href='houses.jsp'>List Houses</a></li>");
		out.println("<li class='menu'><a href='#'>Reports</a></li>");
		out.println("<li class='menu'><a href='#'>Exports</a></li>");
		out.println("<li class='leaf last'><a href='?q=logout'>Log out</a></li>");
}
	//
}

%>


</ul>
</div>



		</div>
			</div>	<div id="postnav"></div>

	


<div id="middlecontainer">
<table border="0" cellpadding="0" cellspacing="0" id="content"><tr>


<% // MENUS
int uid=1;

if(session.getAttribute("role")==null) {
	out.println("<td id='sidebar-left'><div class='block block-user' id='block-user-0'>");
out.println("<h2 class='title'>User login</h2><div class='content'><form action='' method='post' id='loginform'>"+
		"<div class='form-item'><label>Username</label><input type='text' name='user' id='user' size='15'/></div>"+
		"<div class='form-item'><label>Password</label><input type='password' name='pass' id='pass' size='15'/></div>"+
		"<input type='submit' name='login' id='login' value='Log in'  class='form-submit' />"+
		//"<div class='item-list'><ul><li class='first'><a href='appedit.jsp'>New Applicaton</a></li>"+
		//"<li><a href='regedit.jsp'>New Registration</a></li><li class='last'><a href='#'>Forgot Password</a></li>"+
		"</ul></div></form></div>");
}
out.println("</td>");
%>