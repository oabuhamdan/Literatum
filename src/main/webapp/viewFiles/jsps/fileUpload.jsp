<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>


<head>
    <meta http-equiv="Content-Type">
    <style type="text/css">@import url(/viewFiles/css/upload.css);</style>
    <title>File Upload</title>
</head>

<body>

<div class="theWholePage">

    <h2>File Upload</h2>

    <br/>

    <!-- Upload  -->
    <form action="uploadFile" method="post" class="uploader" enctype="multipart/form-data">

        <label>

            <div id="start">

                <div>Select a file please</div>
                <br>

                ${empty requestScope.error? "" : "<p style='color:red'>"}
                ${requestScope.error}
                ${empty requestScope.error? "" :"</p>"}

                <input type="file" value="Browse" class="btn btn-primary" name="fileName" />
                <br/>

                <input type="submit" value="Upload" class="btn btn-primary"/>
            </div>
        </label>
    </form>

    <form method="get" action="<c:url value="/mainPage"/>">
        <button class="btn btn-primary">Return to main page</button>
    </form>
</div>
</body>


</html>


