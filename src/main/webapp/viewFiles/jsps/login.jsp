<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Screen</title>
    <style type="text/css">@import url(/viewFiles/css/login.css);</style>
</head>

<body>

<div class="container">
    <div class="row" id="pwd-container">

        <div class="col-md-4"></div>

        <div class="col-md-4">
            <section class="login-form">

                <form method="post" action="login" role="login">

                    <h4>LOGIN</h4>

                    <h6>
                        ${empty requestScope.error? "" : "<p style='color:red'>"}
                        ${requestScope.error}
                        ${empty requestScope.error? "" :"</p>"}
                    </h6>

                    <input type="text" name="user_name" placeholder="User Name" required class="form-control input-lg"
                           value=""/>

                    <input type="password" class="form-control input-lg" name="password" placeholder="Password"
                           required=""/>


                    <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Sign in</button>


                </form>

            </section>
        </div>

        <div class="col-md-4"></div>


    </div>
</div>
</body>