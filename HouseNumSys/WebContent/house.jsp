<%@include file="header.jsp" %>
<%@
page import="javax.servlet.http.*,
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
<% if(request.getParameter("submit") != null) {
	String vehicleno = request.getParameter("vehicleno");
	String drivername = request.getParameter("drivername");
	String watercapacity = request.getParameter("watercapacity");
	String status = request.getParameter("status");
	
	Db db = new Db("sqlite","localhost","admin","13131","housenumsys");
	

}

%>

<td valign="top"><div id="main"><h1 class="title"></h1><h2 class="title">Records</h2>

<div class="node">
</div>


				</div>
		</td>


		
		

		
			</tr>
</table>
</div>


<div id="footer-wrapper" class="clear-block">
	<div id="bar2"></div>
	<div id="footer">
	<div style="float:left"> <img src="files/appengine.gif" width="120" height="30" alt="App Engine" /></div>
		
<div class="legal">Cloud Systems Design & Developed and Maintained by <a href="http://mannir.net/">MUHAMMAD MANNIR AHMAD</a>.<div id="brand"></div></div>
	</div>
	<div class="footer-right">
		<div class="footer-left">
		</div> <!-- /footer-left -->
	</div> <!-- /footer-right -->
</div> <!-- /footer-wrapper -->

<div id="shadow" class="clear-block">
	<div class="shadow-right">
		<div class="shadow-left">

		</div>
	</div>
</div>

</div>
</body>
</html>