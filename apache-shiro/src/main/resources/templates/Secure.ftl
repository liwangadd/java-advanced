<html>
<head>
    <title>Secure</title>
</head>
<body style="margin-left: 30px;">
<h1>Welcome ${username}</h1>
<p><strong>Role</strong>: ${role}</p>
<p><strong>Permission</strong></p>
<p>${permission}</p>
<br>
<form role="form" action="/logout" method="post">
    <input type="submit" value="Logout"/>
</form>
</body>
</html>