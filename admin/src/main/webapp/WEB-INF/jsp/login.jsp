<%--
  Created by IntelliJ IDEA.
  User: YEJA
  Date: 8/4/2018
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Login | Propeller - Admin Dashboard">
    <meta content="width=device-width, initial-scale=1, user-scalable=no" name="viewport">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>Login</title>
    <link rel="shortcut icon" type="image/x-icon" href="../themes/images/favicon.ico">

    <!-- Google icon -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Bootstrap css -->
    <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">

    <!-- Propeller css -->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller.min.css">

    <!-- Propeller theme css-->
    <link rel="stylesheet" type="text/css" href="../themes/css/propeller-theme.css" />

    <!-- Propeller admin theme css-->
    <link rel="stylesheet" type="text/css" href="../themes/css/propeller-admin.css">

</head>

<body class="body-custom">
<div class="logincard">
    <div class="pmd-card card-default pmd-z-depth">
        <div class="login-card">
            <form action="/admin/login" method="post" id="loginForm">
                <div class="pmd-card-title card-header-border text-center">
                    <div class="loginlogo">
                        <a href="javascript:void(0);"><img src="../themes/images/logo-icon.png" alt="Logo"></a>
                    </div>
                    <h3><span><strong>Please Sign in First</strong></span></h3>
                </div>

                <div class="pmd-card-body">
                    <div class="alert alert-success" role="alert"> Oh snap! Change a few things up and try submitting again. </div>
                    <div class="form-group pmd-textfield pmd-textfield-floating-label">
                        <label for="inputError1" class="control-label pmd-input-group-label">LoginName</label>
                        <div class="input-group">
                            <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">perm_identity</i></div>
                            <input type="text" class="form-control" id="loginName" name="loginName">
                            <span class="pmd-checkbox" id="errMsg1"></span>
                        </div>
                    </div>

                    <div class="form-group pmd-textfield pmd-textfield-floating-label">
                        <label for="inputError1" class="control-label pmd-input-group-label">LoginPassword</label>
                        <div class="input-group">
                            <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">lock_outline</i></div>
                            <input type="password" class="form-control" id="loginPassword" name="password">
                            <span class="pmd-checkbox" id="errMsg2"></span>
                        </div>
                    </div>
                </div>

                <div class="pmd-card-footer card-footer-no-border card-footer-p16 text-center">
                    <!--
					<div class="form-group clearfix">
						<div class="checkbox pull-left">
							<label class="pmd-checkbox checkbox-pmd-ripple-effect">
								<input type="checkbox" checked="" value="">
								<span class="pmd-checkbox"> Remember me</span>
							</label>
						</div>
						<span class="pull-right forgot-password">
							<a href="javascript:void(0);">Forgot password?</a>
						</span>
					</div>
                    -->
                    <div class="form-group clearfix">

                        <span class="pmd-checkbox" id="errMsg3"> </span>


                    </div>
                    <a href="javascript:void(0);" onclick="check();" type="button" class="btn pmd-ripple-effect btn-primary btn-block">Login</a>
            </form>

</body>
<p class="redirection-link">Don't have an account? <a href="javascript:void(0);" class="login-register">Sign Up</a>. </p>

</div>

</form>
</div>

<div class="register-card">
    <div class="pmd-card-title card-header-border text-center">
        <div class="loginlogo">
            <a href="javascript:void(0);"><img src="../themes/images/logo-icon.png" alt="Logo"></a>
        </div>
        <h3>Sign Up <span>with <strong>PROPELLER</strong></span></h3>
    </div>
    <form>
        <div class="pmd-card-body">

            <div class="form-group pmd-textfield pmd-textfield-floating-label">
                <label for="inputError1" class="control-label pmd-input-group-label">Username</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">perm_identity</i></div>
                    <input type="text" class="form-control" id="exampleInputAmount1">
                </div>
            </div>

            <div class="form-group pmd-textfield pmd-textfield-floating-label">
                <label for="inputError1" class="control-label pmd-input-group-label">Email address</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">email</i></div>
                    <input type="text" class="form-control" id="exampleInputAmount2">
                </div>
            </div>

            <div class="form-group pmd-textfield pmd-textfield-floating-label">
                <label for="inputError1" class="control-label pmd-input-group-label">Password</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">lock_outline</i></div>
                    <input type="text" class="form-control" id="exampleInputAmount3">
                </div>
            </div>
        </div>

        <div class="pmd-card-footer card-footer-no-border card-footer-p16 text-center">
            <a href="" type="button" class="btn pmd-ripple-effect btn-primary btn-block">Sign Up</a>
            <p class="redirection-link">Already have an account? <a href="javascript:void(0);" class="register-login">Sign In</a>. </p>
        </div>
    </form>
</div>

<div class="forgot-password-card">
    <form>
        <div class="pmd-card-title card-header-border text-center">
            <div class="loginlogo">
                <a href="javascript:void(0);"><img src="../themes/images/logo-icon.png" alt="Logo"></a>
            </div>
            <h3>Forgot password?<br><span>Submit your email address and we'll send you a link to reset your password.</span></h3>
        </div>
        <div class="pmd-card-body">
            <div class="form-group pmd-textfield pmd-textfield-floating-label">
                <label for="inputError1" class="control-label pmd-input-group-label">Email address</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">email</i></div>
                    <input type="text" class="form-control" id="exampleInputAmount4">
                </div>
            </div>
        </div>
        <div class="pmd-card-footer card-footer-no-border card-footer-p16 text-center">
            <a href="" type="button" class="btn pmd-ripple-effect btn-primary btn-block">Submit</a>
            <p class="redirection-link">Already registered? <a href="javascript:void(0);" class="register-login">Sign In</a></p>
        </div>
    </form>
</div>
</div>
</div>

<!-- Scripts Starts -->
<script src="../assets/js/jquery-1.12.2.min.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/propeller.min.js"></script>
<!-- login -->
<script src="../js/login.js"></script>
<script>
    $(document).ready(function() {
        var sPath=window.location.pathname;
        var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
        $(".pmd-sidebar-nav").each(function(){
            $(this).find("a[href='"+sPage+"']").parents(".dropdown").addClass("open");
            $(this).find("a[href='"+sPage+"']").parents(".dropdown").find('.dropdown-menu').css("display", "block");
            $(this).find("a[href='"+sPage+"']").parents(".dropdown").find('a.dropdown-toggle').addClass("active");
            $(this).find("a[href='"+sPage+"']").addClass("active");
        });
    });
</script>
<!-- login page sections show hide -->
<script type="text/javascript">
    $(document).ready(function(){
        $('.app-list-icon li a').addClass("active");
        $(".login-for").click(function(){
            $('.login-card').hide()
            $('.forgot-password-card').show();
        });
        $(".signin").click(function(){
            $('.login-card').show()
            $('.forgot-password-card').hide();
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $(".login-register").click(function(){
            $('.login-card').hide()
            $('.forgot-password-card').hide();
            $('.register-card').show();
        });

        $(".register-login").click(function(){
            $('.register-card').hide()
            $('.forgot-password-card').hide();
            $('.login-card').show();
        });
        $(".forgot-password").click(function(){
            $('.login-card').hide()
            $('.register-card').hide()
            $('.forgot-password-card').show();
        });
    });
</script>

<!-- Scripts Ends -->

</body>
</html>