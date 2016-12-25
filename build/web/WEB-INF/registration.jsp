<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
        <link rel="icon" href="pictures/icon.png">
        <link rel="stylesheet" href="styles/mainStyle.css"/>
        <link rel="stylesheet" href="styles/registration.css"/>
        <script src="bower_components/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="bower_components/dist/sweetalert.css">
        <script type="text/javascript">
            function checkPassMatch() {
                if (document.getElementById("password").value != document.getElementById("passwordRep").value) {
                    swal("Oops!", "The passwords do not match!", "error");
                    return false;
                }
                if (!/[a-z]/i.test(document.getElementById("login").value)){
                    swal("Oops!", "The login should contain letters!", "error");
                    return false;
                }else{
                    return true;
                }
            }
            window.onload = function () {
                a = <%=request.getSession().getAttribute("userAuthorizationError")%>;
            <%request.getSession().removeAttribute("userAuthorizationError");%>
                if (a == 1) {
                    swal("Oops!", "You should log in!", "error");
                }
                if (a == 3) {
                    swal("Oops!", "This user already exists!", "error");
                }
                if (a == 5) {
                    swal("Oops!", "Some problem occurred", "error");
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
            <div id="main">
                <form action="registration" method="post" id="form" onsubmit="return checkPassMatch()" >
                    <div id="mainInfoArea">
                        <div class="top">
                            <p>Main information</p>
                        </div>
                        <div class="input">
                            <label>Login *</label>
                            <input type="text" name="login" id="login" required class="inputField" minlength="6" maxlength="20"
                                   onkeypress='return ((event.charCode >= 48 && event.charCode <= 57) || (event.charCode >= 65 && event.charCode <= 90) 
                                               || (event.charCode == 95) || (event.charCode >= 97 && event.charCode <= 122))'>
                            <label>E-mail *</label>
                            <input type="email" name="email" id="email" required class="inputField">
                            <label>Password *</label>
                            <input type="password" name="password" id="password" required class="inputField" minlength="6">
                            <label>Repeat password *</label>
                            <input type="password" name="passwordRep" id="passwordRep" required class="inputField">
                        </div>
                    </div>
                    <div id="extraInfoArea">
                        <div class="top">
                            <p>Additional information</p>
                        </div>
                        <div class="input">
                            <label>Phone</label>
                            <input type="tel" name="phone" id="phone" minlength="13" maxlength="13" value="+380"
                                   onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 43' class="inputField">
                            <label>Name *</label>
                            <input type="tel" name="name" id="name" minlength="3" maxlength="20"
                                   onkeypress='return ((event.charCode >= 48 && event.charCode <= 57) || (event.charCode >= 65 && event.charCode <= 90) 
                                               || (event.charCode == 95) || (event.charCode >= 97 && event.charCode <= 122))' class="inputField" required="true">
                            <label>City</label>
                            <select class="inputField" name="city">
                                <option value="Kiev">Kiev</option>
                                <option value="Odesa">Odesa</option>
                                <option value="Lviv">Lviv</option>
                                <option value="Dnipro">Dnipro</option>
                            </select>
                        </div>
                    </div>
                    <div class="bot">
                        <input type="submit" value="register" id="submitButton">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>