<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Welcome</title>
        <link rel="icon" href="pictures/icon.png">
        <link rel="stylesheet" href="styles/mainStyle.css"/>
        <link rel="stylesheet" href="styles/welcomePage.css"/>
        <link rel="stylesheet" href="bower_components/dist/sweetalert.css">
        <script src="bower_components/dist/sweetalert.min.js"></script>
        <script type="text/javascript">
            window.onload = function () {
                a = <%=request.getSession().getAttribute("userAuthorizationError")%>;
            <%request.getSession().removeAttribute("userAuthorizationError");%>
                if (a == 1) {
                    swal("Oops!", "You should log in!", "error");
                }
                if (a == 2) {
                    swal("Oops!", "Login or password is incorrect", "error");
                }
                if (a == 3) {
                    swal("Oops!", "This user is already logged in", "error");
                }
                if (a == 4) {
                    swal("You've beed registred")
                }
            };
        </script>
    </head>
    <body>
        <div class="header">
            <img class="logo" src="pictures/logo.jpg">
            <div class="navigation">
                <ul>
                    <li><a href="purchaseTicket">PURCHASE TICKETS</a></li>
                    <li><a href="#2" style="text-decoration:line-through">SUPPORT</a></li>
                    <li><a href="#3" style="text-decoration:line-through">ABOUT</a></li>
                </ul>
            </div>
            <div class="profileHeaderInfo">
                <a href="profile"><img class="profilePicture" src="pictures/anonymUser.jpg"></a>
                <p class="profileName"><a href="profile"><strong>Anonymous</strong></a></p>
            </div>
        </div>
        <div id="main">
            <div id="inputArea">
                <div id="loginArea">
                    <form action="login" method="post" id="loginForm">
                        <p class="loginItems" id="entry">Entry</p>
                        <input type="text" placeholder="Login" class="loginItems" id="login" name="loginField" required="true">
                        <input type="password" placeholder="Password" class="loginItems" id="password" name="passwordField" required="true">
                        <div id="keepLoggedArea" class="loginItems">
                            <input type="checkbox" id="check">
                            <label for="check">Keep me logged in</label>
                        </div>
                        <input type="submit" value="login" class="loginItems" id="loginButton"> 
                    </form>
                </div>
                <div id="registrationArea">
                    <div id="container">
                        <p class="regItems" id="noAcc">If you has no account</p>
                        <a href="registration"><div class="regItems blocks" id="emailRegastration"><p>registration via e-mail</p></div></a>
                        <p class="regItems" id="or">or</p>
                        <a href="#"><div class="regItems blocks" id="facebookRegastration"><p>registration via facebook</p><img src="pictures/facebooklogo.png"></div></a>
                        <a href="#"><div class="regItems blocks" id="googleRegastration"><p>registration via google plus</p><img src="pictures/googlepluslogo@3x.png"></div></a>
                    </div>
                </div>
            </div>
            <div id="forgotArea">
                <p>You can not login into your account? Maybe <a href="#">you forgot your password?</a></p>
            </div>
        </div>

    </body>
</html>