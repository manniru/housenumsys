<script src="JavaScript/ModalPopupWindow.js" type="text/javascript"></script>
 <style>
     body
        {
            font-family: Verdana;
            font-size: 10px;
         
        }
     
    </style>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%! House hs=null; %>
<% if(request.getParameter("submit") != null) {
	int houseno = Integer.parseInt(request.getParameter("houseno"));
	String lganame = request.getParameter("lganame");
	String district = request.getParameter("district");
	String ward = request.getParameter("ward");
	String street = request.getParameter("street");
	String holdername = request.getParameter("holdername");
	String holderaddress = request.getParameter("holderaddress");
	String mobileno = request.getParameter("mobileno");
	String nationality = request.getParameter("nationality");
	String landuse = request.getParameter("landuse");
	String date = request.getParameter("date");
	
	House house = new House();
	house.setHouseno(houseno);
	house.setLganame(lganame);
	house.setDistrict(district);
	house.setWard(ward);
	house.setHoldername(holdername);
	house.setHolderaddress(holderaddress);
	house.setMobileno(mobileno);
	house.setNationality(nationality);
	house.setLanduse(landuse);
	house.setDate(date);
	db.addHouse(house);
	System.out.println("Record Added!");
}

%>
<% if(request.getParameter("action") != null && request.getParameter("action").equals("view")) {
	int id = Integer.parseInt(request.getParameter("id"));
	System.out.println("view:"+id);
}
%>
<% if(request.getParameter("action") != null && request.getParameter("action").equals("edit")) {
	int id = Integer.parseInt(request.getParameter("id"));
	hs = db.getHouse(id);
	
	System.out.println("edit:"+id);
}
%>
<% if(request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
	int id = Integer.parseInt(request.getParameter("id"));
	House house = new House();
	db.delHouse(id);
	System.out.println("House deleted: "+id);
}
%>
<td valign="top">
<div id="main"><h1 class="title"></h1><h2 class="title">Houses Records</h2>

<div class="node">
<link rel="stylesheet" href="css/forms.css" />
<link rel="stylesheet" href="css/chosen.css" />
<form method="post">

<fieldset class=" collapsible"><legend>Add House</legend>
<input name="regform" type="hidden" value="regform" />
<div class="fieldset-wrapper"></div>
<div style="float:left;margin-right:2px;"><label>HouseNo</label><input type="text" name="houseno" value="" size="5" class="form-text"></div>
<div style="float:left;margin-right:2px;"><label>LGA</label><select name="lganame" class="form-select">
<option value='AJINGI'>AJINGI</option>
<option value='ALBASU'>ALBASU</option>
<option value='BAGWAI'>BAGWAI</option>
<option value='BEBEJI'>BEBEJI</option>
<option value='BICHI'>BICHI</option>
<option value='BUNKURE'>BUNKURE</option>
<option value='DALA'>DALA</option>
<option value='DAMBATTA'>DAMBATTA</option>
<option value='DAWAKIN KUDU'>DAWAKIN KUDU</option>
<option value='DAWAKIN TOFA'>DAWAKIN TOFA</option>
<option value='DOGUWA'>DOGUWA</option>
<option value='FAGGE'>FAGGE</option>
<option value='GABASAWA'>GABASAWA</option>
<option value='GARKO'>GARKO</option>
<option value='GARUN MALLAM'>GARUN MALLAM</option>
<option value='GAYA'>GAYA</option>
<option value='GEZAWA'>GEZAWA</option>
<option value='GWALE'>GWALE</option>
<option value='GWARZO'>GWARZO</option>
<option value='KABO'>KABO</option>
<option value='KANO MUNICIPAL'>KANO MUNICIPAL</option>
<option value='KARAYE'>KARAYE</option>
<option value='KIBIYA'>KIBIYA</option>
<option value='KIRU'>KIRU</option>
<option value='KUMBOTSO'>KUMBOTSO</option>
<option value='KUNCHI'>KUNCHI</option>
<option value='KURA'>KURA</option>
<option value='MADOBI'>MADOBI</option>
<option value='MAKODA'>MAKODA</option>
<option value='MINJIBIR'>MINJIBIR</option>
<option value='NASARAWA'>NASARAWA</option>
<option value='RANO'>RANO</option>
<option value='RIMIN GADO'>RIMIN GADO</option>
<option value='ROGO'>ROGO</option>
<option value='SHANONO'>SHANONO</option>
<option value='SUMAILA'>SUMAILA</option>
<option value='TAKAI'>TAKAI</option>
<option value='TARAUNI'>TARAUNI</option>
<option value='TOFA'>TOFA</option>
<option value='TSANYAWA'>TSANYAWA</option>
<option value='TUDUN WADA'>TUDUN WADA</option>
<option value='UNGOGO'>UNGOGO</option>
<option value='WARAWA'>WARAWA</option>
<option value='WUDIL'>WUDIL</option>

</select></div>
<div style="float:left;margin-right:2px;"><label>District</label><input type="text" name="district" value="" size="15" class="form-text"></div>
<div style="float:left;margin-right:2px;"><label>Ward</label><input type="text" name="ward" value="" size="10" class="form-text"></div>
<div style="float:left;margin-right:2px;"><label>Holder Name</label><input type="text" name="holdername" value="" size="15" class="form-text"></div>
<div style="float:left;margin-right:2px;"><label>Address</label><input type="text" name="address" value="" size="15" class="form-text"></div>
<div style="float:left;margin-right:2px;"><label>Mobile No</label><input type="text" name="mobileno" value="" size="11" class="form-text"></div>
<div style="float:left;margin-right:2px;"><label>Nationality</label><input type="text" name="nationality" value="Nigerian" size="10" class="form-text"></div>
<div style="float:left;margin-right:2px;"><label>Land Use</label><select name="landuse" class="form-select">
<option value='Residential'>Residential</option><option value='Commercial'>Commercial</option></select></div>
<div style="float:left;margin-right:2px;"><label>Date</label><input type="text" name="date" value="04-25-2014" size="8" class="form-text"></div>

<div style="float:left;margin-right:2px;"><div id="btn" align="center"><input type="submit" name="submit" id="submit" value="Submit" /></div></div>
<input type="button" onclick="ShowNewPage()" value="Send SMS" />
<div style="clear:both"></div>

</fieldset></form>

<%
out.println("<table class='sticky-enabled' style=''width:90%'>");
out.println("<thead><tr><th>ID</th><th>House No</th><th>LGA</th><th>Distirict</th><th>Ward</th><th>Holder Name</th><th>Holder Address</th><th>Mobile No</th><th>Nationality</th><th>Land Use</th><th>Date Added</th><th>Action</th></tr></thead>");
out.println("<tbody>");
int sn = 1;
for(House hs : db.listHouse()) {
	int m = sn % 2;
	if(m==1) { out.println("<tr class='odd'>"); }
	else { out.println("<tr class='even'>"); }			
	//out.println("<td style='border:1px solid black;'>"+sn+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getId()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getHouseno()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getLganame()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getDistrict()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getWard()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getHoldername()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getHolderaddress()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getMobileno()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getNationality()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getLanduse()+"</td>");
	out.println("<td style='border:1px solid black;'>"+hs.getDate()+"</td>");
	out.println("<td style='border:1px solid black;'>");
	//out.println("<a href='?action=view&id="+hs.getId()+"'>View</a> - ");
	out.println(" <a href='?action=edit&id="+hs.getId()+"'>Edit</a> - ");
	out.println(" <a href='?action=delete&id="+hs.getId()+"'>Delete</a></td>");
	out.println("</tr>");
	sn++;	
}	
out.println("</tbody></table>");
%>
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
<div id="shadow" class="clear-block"><div class="shadow-right">
<div class="shadow-left">
</div>	</div></div></div>






<script>

 var modalWin = new CreateModalPopUpObject();
 modalWin.SetLoadingImagePath("images/loading.gif");
 modalWin.SetCloseButtonImagePath("images/remove.gif");
 //Uncomment below line to make look buttons as link
 //modalWin.SetButtonStyle("background:none;border:none;textDecoration:underline;cursor:pointer");

function ShowNewPage(){
 var callbackFunctionArray = new Array(EnrollNow, EnrollLater);
 modalWin.ShowURL('sendsms.jsp',200,470,'Send SMS Message',null,callbackFunctionArray);
 }
 
 
function ShowMessage() {
 modalWin.ShowMessage('This Modal Popup Window using Javascript',200,400,'User Information');
 }
  
 function  ShowMessageWithAction(){
    //ShowConfirmationMessage(message, height, width, title,onCloseCallBack, firstButtonText, onFirstButtonClick, secondButtonText, onSecondButtonClick);
    modalWin.ShowConfirmationMessage('This is confirmation window using Javascript',200,400,'User Confirmation',null,'Agree',Action1,'Disagree',Action2);
 }

function ShowMessageNoDragging(){
 modalWin.Draggable=false;
 modalWin.ShowMessage('You can not drag this window',200,400,'User Information');

}

function Action1(){
alert('Action1 is excuted');
modalWin.HideModalPopUp();
}

function Action2(){
alert('Action2 is excuted');
modalWin.HideModalPopUp();
}

function EnrollNow(msg){
modalWin.HideModalPopUp();
modalWin.ShowMessage(msg,200,400,'User Information',null,null);
}

function EnrollLater(){
modalWin.HideModalPopUp();
modalWin.ShowMessage(msg,200,400,'User Information',null,null);
}

function HideModalWindow() {
    modalWin.HideModalPopUp();
}

function ShowChildWindowValues(name,email,address,phone,zip) {
    var displayString = "<b>Values Of Child Window</b> <br><br>Name : " + name;
    displayString += "<br><br>Email : " + email;
    displayString += "<br><br>Address : " + address;
    displayString += "<br><br>Phone : " + phone;
    displayString += "<br><br>Zip : " + zip;
    var div = document.getElementById("divShowChildWindowValues");
    div.style.display = "";
    div.innerHTML = displayString;
}

 </script>
 
 </body></html>