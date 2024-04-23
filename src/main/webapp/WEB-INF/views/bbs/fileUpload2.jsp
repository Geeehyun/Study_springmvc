<%--
  Created by IntelliJ IDEA.
  User: ryuri
  Date: 2024-04-23
  Time: PM 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<form action="/bbs/fileUpload2" method="post" enctype="multipart/form-data">
    <div>
        <span>파일업로드</span>
        <input type="file" name="files" id="file" multiple>
    </div>
    <div>
        <input type="submit" value="제출">
    </div>
</form>
</body>
</html>