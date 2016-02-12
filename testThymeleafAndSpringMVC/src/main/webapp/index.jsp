<%@ page contentType="text/html;charset=EUC-JP" pageEncoding="EUC-JP"%> 
<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=EUC-JP"> -->
<meta http-equiv="Content-Type" content="text/html; charset=EUC-JP">
<title>test List</title>
</head>
<body>
	<h1>Test Character Encoding</h1>
	<form action="./sample/hello" method="post">
		日本人<input type="text" name="jName" value="" placeholder="input your Japanese name" />
		外国人<input type="text" name="eName" value="" placeholder="input your English name" /> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>