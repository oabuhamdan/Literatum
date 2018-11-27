<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <meta http-equiv="Content-Type">
    <style type="text/css">@import url(/viewFiles/css/upload.css);</style>
    <title>File Upload</title>
</head>

<body>

<%--<div class="theWholePage">--%>

    <%--<h2>File Upload</h2>--%>

    <%--<br/>--%>

    <%--<!-- Upload  -->--%>
    <%--<form action="<c:url value="/uploadFile"/>" method="post" class="uploader" enctype="multipart/form-data">--%>

        <%--<input type="file" name="file"/>--%>

        <%--<label>--%>

            <%--<div id="start">--%>

                <%--<br/>--%>

                <%--&lt;%&ndash;<div>Select a file please</div>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<span id="file-upload-btn" class="btn btn-primary">Upload</span>&ndash;%&gt;--%>

                <%--Press to Select File to Upload<input type="file" name="fileName" class="btn btn-primary"/>--%>

                <%--<br/>--%>
                <%--<br/>--%>

                <%--<input type="submit" value="Upload" class="btn btn-primary"/>--%>

            <%--</div>--%>

        <%--</label>--%>
    <%--</form>--%>

<%--</div>--%>

<div>
    <h3> Choose File to Upload in Server </h3>
    <form action="uploadFile" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="upload" />
    </form>
</div>

</body>


</html>


