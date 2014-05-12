// BISMILLAHIR RAHAMANIR RAHIM
window.onload= init;



function init(){ 
	
	var q = window.location.search.substring(3);
	$('#menutype').html("<h2>"+q+"</h2>");

	if(q=="") { showpage('home'); } else { showpage(q); }
	
	if (window.sessionStorage.getItem("usertype") != null) { var usertype = window.sessionStorage.getItem("usertype"); } else { var usertype="guest"; }
	
	if(usertype=="guest") { $('#user-block').hide();	 $('#admin-block').hide(); $('#guest-block').show(); }
	else if(usertype=="admin") { $('#guest-block').hide();	$('#user-block').hide(); $('#admin-block').show(); }
	else if (usertype=="user") { $('#admin-block').hide();	$('#guest-block').hide();	$('#user-block').show(); }
	
	$('#userid').html("<h2>"+usertype+"</h2>");

	$("#loginbtn").click(function() { var uname1 = $("input#userlogin").val(); var pword1 = $("input#passlogin").val();
		window.sessionStorage.setItem("usertype", uname1);
		 });
	 
		$('#signupbtn').click(function(event) { alert(event.currentTarget.id);	});
		$("#logoutbtn1").click(function() { window.sessionStorage.removeItem("usertype");  });	 
		$("#logoutbtn2").click(function() { window.sessionStorage.removeItem("usertype");   });
		
	$.getJSON("json/banks.json", { format: "json" }).done(function( data ) { for (var i=0;i<data.length;i++) { $("#bankname").append('<option value="'+data[i]+'">'+data[i]+'</option>'); } });
	$.getJSON("json/paymenttype.json", { format: "json" }).done(function( data ) { for (var i=0;i<data.length;i++) { $("#paymenttype").append('<option value="'+data[i]+'">'+data[i]+'</option>'); } });
	$.getJSON("json/states.json", { format: "json" }).done(function( data ) { for (var i=0;i<data.length;i++) { $("#state").append('<option value="'+data[i]+'">'+data[i]+'</option>'); } });
	$.getJSON("json/country.json", { format: "json" }).done(function( data ) { for (var i=0;i<data.length;i++) { $("#country").append('<option value="'+data[i]+'">'+data[i]+'</option>'); } });
	$.getJSON("json/lga.json", { format: "json" }).done(function( data ) { for (var i=0;i<data.length;i++) { $("#lga").append('<option value="'+data[i]+'">'+data[i]+'</option>'); } });
	$.getJSON("json/states.json", { format: "json" }).done(function( data ) { for (var i=0;i<data.length;i++) { $("#vstate").append('<option value="'+data[i]+'">'+data[i]+'</option>'); } });
	$.getJSON("json/vtype.json", { format: "json" }).done(function( data ) { for (var i=0;i<data.length;i++) { $("#vehicletype").append('<option value="'+data[i]+'">'+data[i]+'</option>'); } });
		
	$.ajax({
        type: "GET",
	url: "cars.xml",
	dataType: "xml",
	success: function(xml) {
		$(xml).find('car').each(function(){
			var id = $(this).attr('car');
			var carname = $(this).find('carname').text();
			var url = $(this).find('carmodel').text();
			$("#vehiclemake").append('<option value="'+carname+'">'+carname+'</option>');
		});
 
	}
});
	
	
}



var showpage = function(entity) {
	$('.tab').removeClass("active"); $('#'+entity).addClass("active");
	//hiding all the tabs and show selected
	$('.node').hide();	$('#' + entity + '-page').show();
	//hiding the create block
	///showHideCreate(entity, false);
	///if(entity!=HOME)$('#'+entity+'-search-reset').click();
}

var showblock = function(entity) { $('.block1').hide();	$('#' + entity + '-block1').show(); }





function getjson() {
	
	$.getJSON("/db?userlogin="+uname1+"&passlogin="+pword1, function(data) {
		alert("fromservlet: "+data);
		var jsondata = JSON.stringify(data);
		window.sessionStorage.setItem("userdata", JSON.stringify(data));

		
		///showmain();

	});
}

function getip() {
	$.get("/get", function(data) {
		$('#serverip').html(data);
		});
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function sendlogin() {
	alert("send login here");

	
	var uname1 = $("input#userlogin").val();
	var pword1 = $("input#passlogin").val();

	// WORKING 03/31/13	$.getJSON("/login?userlogin="+uname1+"&passlogin="+pword1, function(data) {
	$.getJSON("/db?userlogin="+uname1+"&passlogin="+pword1, function(data) {
		alert("fromservlet: "+data);
		var jsondata = JSON.stringify(data);
		window.sessionStorage.setItem("userdata", JSON.stringify(data));
	///	alert(print_r(JSON.stringify(data)));
	//	$('#fullname').val("MANNIR AHMAD");
		
		showmain();
	///	  var items = [];
		 
	///	  $.each(data, function(key, val) { items.push('<li id="' + key + '">' + val + '</li>'); });
		 
	///	  $('<ul/>', { 'class': 'my-new-list', html: items.join('') }).appendTo('#m1'); 
		
	
	});
	

}

function sendsignup() {
	var formdata = $('#signupform').serialize();
	//var uname1 = $("input#userlogin").val();
	//var pword1 = $("input#passlogin").val();
	if(document.location.port=='8888' || document.location.port=='80' ) { var url = '/ds'; } if(document.location.port=='8080') { var url = '/db'; }
	
	$.ajax({
		url : url, 
		type : "POST", 
		data: formdata, 
		success : function(data) 
		{ 
		//var data1 = { First: "abc", Second: "def" };
			//var jsData = JSON.stringify(data);
			window.sessionStorage.setItem("userdata", JSON.stringify(data));
			//var userdt = window.sessionStorage.getItem("userdata");
			var u = JSON.parse(window.sessionStorage.getItem("userdata"));
			//alert(u.email);
			//$('#userid').text("User ID: "+ u.username);
			
			alert(data);	
			showmain();
		
		
		
		} ,
	///error: function (xhr, ajaxOptions, thrownError) { alert(xhr.status); alert(thrownError); }
		error: function (request, status, error) { alert(request.responseText); }
	
	}); 

}


function register() {
	var formdata = $('#regform').serialize();
	//var uname1 = $("input#userlogin").val();
	//var pword1 = $("input#passlogin").val();
	if(document.location.port=='8888' || document.location.port=='80' ) { var url = '/ds'; } if(document.location.port=='8080') { var url = '/db'; }
	
	$.ajax({
		url : url, 
		type : "POST", 
		data: formdata, 
		success : function(data) 
		{ 
		//var data1 = { First: "abc", Second: "def" };
			//var jsData = JSON.stringify(data);
			window.sessionStorage.setItem("userdata", JSON.stringify(data));
			//var userdt = window.sessionStorage.getItem("userdata");
			var u = JSON.parse(window.sessionStorage.getItem("userdata"));
			//alert(u.email);
			//$('#userid').text("User ID: "+ u.username);
			
			alert(data);	
			showmain();
		
		
		
		} ,
	///error: function (xhr, ajaxOptions, thrownError) { alert(xhr.status); alert(thrownError); }
		error: function (request, status, error) { alert(request.responseText); }
	
	}); 

}

function sendlogout() {
	sessionStorage.removeItem("user");
	sessionStorage.removeItem("userdata");
	alert("You have successfull log out!");
	location.reload(); 
	
}

function sendreg() {
	var formdata = $('#regform').serialize();
	$.ajax({
		url : "/regdata", 
		type : "POST", 
		data: formdata, 
		success : function(data) { 
		//var data1 = { First: "abc", Second: "def" };
			//var jsData = JSON.stringify(data);
			window.sessionStorage.setItem("userdata", JSON.stringify(data));
			//var userdt = window.sessionStorage.getItem("userdata");
			var u = JSON.parse(window.sessionStorage.getItem("userdata"));
			//alert(u.email);
		//	$('#userid').text("User ID: "+ u.username);
			
			///alert("Thank you for Register");	
			showmain();
		
		} ,
	///error: function (xhr, ajaxOptions, thrownError) { alert(xhr.status); alert(thrownError); }
		error: function (request, status, error) { alert(request.responseText); }
	
	}); 

}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




function showguest() {
	$("#guestblock").show();
	$("#userblock").hide();
	$("#adminblock").hide();
}

function showuser() {
   // $('#accinfo').html("<h2>Account Information</h2>");
    //$("#accinfo").append("<b>Amount Charges: "+ac+"</b><br>");
   // $("#accinfo").append("<b>Amount Paid: "+ap+"</b><br>");
   // $("#accinfo").append("<b>Current Balance: "+bl+"</b>");
    
	$("#guestblock").hide();
	$("#userblock").show();
	$("#adminblock").hide();
}

function showadmin() {
	$("#guestblock").hide();
	$("#userblock").hide();
	$("#adminblock").show();
}


