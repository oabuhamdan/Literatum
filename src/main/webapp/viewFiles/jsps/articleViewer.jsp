<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Main Screen</title>

    <style type="text/css">@import url(/viewFiles/css/mainpage.css);</style>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

</head>
<body>


<div class="row admin-sub-header">
    <div class="col-md-8 col-sm-8 col-8 site-breadcrumb">
        <div id="wrap">
            <span id="main-module-name">File Manager</span>
            <span id="breadcrumb-nav-links">
          </span>
        </div>
    </div>
    <div class="col-md-4 col-sm-4 col-4 text-right">
        <form method="get" action="<c:url value="/uploadFile"/>">
            <button class="btn btn-primary btn-sm pl-3 pr-3">Upload Files</button>
        </form>
    </div>
</div>

<div class="row edrive-wrapper">
    <div class="col-lg-2 col-md-3 col-sm-4 col-12 edrive-sidebar">

    </div>
    <div class="col-lg-10 col-md-9 col-sm-8 col-12 edrive-space">
        <div class="row edrive-table-head">
            <div class="col-lg-3 col-md-3 col-sm-3 col-12">Name</div>
            <div class="col-lg-2 col-md-3 col-sm-3 col-12">Date Modified</div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-12">Type</div>
            <div class="col-lg-1 col-md-1 col-sm-1 col-12">Size</div>
            <div class="col-lg-4 col-md-3 col-sm-3 col-12"></div>
        </div>
        <c:forEach items="${requestScope.issues}" var="issue">
            <c:if test="${issue.id == param.journalID}">
                <div class="row edrive-table-data-row">
                    <div class="col-lg-3 col-md-3 col-sm-3 col-12 data-name"><i class="fas fa-folder edrive-file-icon"></i>
                        <a href="" title="${issue.pubMonth}/${issue.pubYear}">${issue.pubMonth}/${issue.pubYear}</a></div>
                    <div class="col-lg-2 col-md-3 col-sm-3 col-12 data-info">-</div>
                    <div class="col-lg-2 col-md-2 col-sm-2 col-12 data-info">Folder</div>
                    <div class="col-lg-1 col-md-1 col-sm-1 col-12 data-info">-</div>
                    <div class="col-lg-4 col-md-3 col-sm-3 col-12 data-info"></div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>


</body>
</html>