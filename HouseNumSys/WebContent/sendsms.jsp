<% if(request.getParameter("submit") != null) {
	System.out.println("Hello");

}

%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Untitled Page</title>
    <style>
        body
        {
            font-family: Verdana;
            font-size: 12px;
        }
    </style>
</head>
<body>
<form method="post">
    <table cellpadding="2" cellspacing="10">
        <tr>
            <td>
                Mobile No:
            </td>
            <td>
                <input type="text" name="mobileno" id="mobileno"  style="width: 100px">
            </td>
        </tr>
        <tr>
            <td>SMS Messages:
            </td>
            <td>
                <input type="text" name="message" id="message" style="width: 300px">
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td align="center">
            <input type="submit" name="submit" id="submit" value="Submit" onclick="EnrollNowClick()" />
                <input type="submit" onclick="EnrollNowClick()" value="Enroll Now" style="height: 30px;
                    width: 100px;">
                <input type="button" onclick="EnrollLaterClick()" value="Enroll Later" style="height: 30px;
                    width: 100px;">
            </td>
        </tr>
    </table>
    </form>
    <script>
        function EnrollNowClick() {
            PassValueToParentWindow();
            window.parent.modalWin.CallCallingWindowFunction(0, 'User Enrolled Scucessfully');
        }
        function EnrollLaterClick() {
            window.parent.modalWin.CallCallingWindowFunction(0, 'Information Saved Scucessfully');
        }

        function PassValueToParentWindow() {
            var mobileno = document.getElementById("mobileno").value;
            var message = document.getElementById("message").value;
            //window.parent.ShowChildWindowValues(mobileno, message);
            $.get( "http://mannirserver.appspot.com/sms.jsp", { no: "601123064474", msg: "Hello from House Num Sys", key: "13132" } );
			window.parent.modalWin.CallCallingWindowFunction(0, 'Message Sent!');
        }
    </script>
</body>
</html>
