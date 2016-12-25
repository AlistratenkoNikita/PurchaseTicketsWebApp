<%@page import="ua.com.alistratenko.entities.Ticket"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="styles/mainStyle.css"/>
        <link rel="stylesheet" href="styles/profile.css"/>
        <link rel="icon" href="pictures/icon.png">
        <link rel="stylesheet" href="bower_components/dist/sweetalert.css">
        <script src="bower_components/dist/sweetalert.min.js"></script>
        <script type="text/javascript">
            function edit() {
                if (!document.getElementById("submitChangesButton1").style.display) {
                    document.getElementById("profileName").removeAttribute("disabled");
                    document.getElementById("email").removeAttribute("disabled");
                    document.getElementById("phone").removeAttribute("disabled");
                    document.getElementById("City").removeAttribute("disabled");
                    document.querySelector("#profileName").style.border = "solid #8A98FF 1px";
                    document.querySelector("#profileName").style.borderRadius = "4px";
                    document.querySelector("#email").style.border = "solid #8A98FF 1px";
                    document.querySelector("#email").style.borderRadius = "4px";
                    document.querySelector("#phone").style.border = "solid #8A98FF 1px";
                    document.querySelector("#phone").style.borderRadius = "4px";
                    document.querySelector("#City").style.border = "solid #8A98FF 1px";
                    document.querySelector("#City").style.borderRadius = "4px";
                    document.querySelector("#submitChangesButton").style.display = "inline";
                }
            }
            ;
            function edit1() {
                if (!document.getElementById("submitChangesButton").style.display) {
                    document.getElementById("creditCardOwner").removeAttribute("disabled");
                    document.getElementById("creditCardNumber").removeAttribute("disabled");
                    document.getElementById("year").removeAttribute("disabled");
                    document.getElementById("month").removeAttribute("disabled");
                    document.getElementById("CVV").removeAttribute("disabled");
                    document.querySelector("#creditCardOwner").style.border = "solid #8A98FF 1px";
                    document.querySelector("#creditCardOwner").style.borderRadius = "4px";
                    document.querySelector("#year").style.border = "solid #8A98FF 1px";
                    document.querySelector("#year").style.borderRadius = "4px";
                    document.querySelector("#creditCardNumber").style.border = "solid #8A98FF 1px";
                    document.querySelector("#creditCardNumber").style.borderRadius = "4px";
                    document.querySelector("#month").style.border = "solid #8A98FF 1px";
                    document.querySelector("#month").style.borderRadius = "4px";
                    document.querySelector("#CVV").style.border = "solid #8A98FF 1px";
                    document.querySelector("#CVV").style.borderRadius = "4px";
                    document.querySelector("#submitChangesButton1").style.display = "inline";
                }
            }
            ;
            function pointerChange(button) {
                button.style.cursor = "pointer";
            }
            window.onload = function () {
                a = <%=request.getSession().getAttribute("buyingTicket")%>;
            <%request.getSession().removeAttribute("buyingTicket");%>
                b = <%=request.getSession().getAttribute("ccEdit")%>;
            <%request.getSession().removeAttribute("ccEdit");%>
                c = <%=request.getSession().getAttribute("userEdit")%>;
            <%request.getSession().removeAttribute("userEdit");%>
                if (a === 1) {
                    swal("You have bought some tickets");
                }
                if (b === 1) {
                    swal("Oops!", "The credit with such number belongs to other user", "error");
                }
                if (c === 1) {
                    swal("Oops!", "This phone belongs to other user", "error");
                }
                if (c === 2) {
                    swal("Oops!", "This email belongs to other user", "error");
                }
                document.getElementById("City").value = '<%=request.getSession().getAttribute("city").toString()%>';
                document.getElementById("month").value = '<%=request.getSession().getAttribute("month").toString()%>';
                document.getElementById("year").value = '<%=request.getSession().getAttribute("year").toString()%>';
                document.getElementById("profileName").setAttribute("disabled", "true");
                document.getElementById("email").setAttribute("disabled", "true");
                document.getElementById("phone").setAttribute("disabled", "true");
                document.getElementById("City").setAttribute("disabled", "true");
                document.getElementById("creditCardOwner").setAttribute("disabled", "true");
                document.getElementById("creditCardNumber").setAttribute("disabled", "true");
                document.getElementById("year").setAttribute("disabled", "true");
                document.getElementById("month").setAttribute("disabled", "true");
                document.getElementById("CVV").setAttribute("disabled", "true");
            }
            function func1() {
                document.getElementById("ticketListBought").style.display = "block";
                document.getElementById("ticketListUsed").style.display = "none";
                document.getElementById("useButton").style.display = "block";
            }
            ;
            function func2() {
                document.getElementById("ticketListBought").style.display = "none";
                document.getElementById("ticketListUsed").style.display = "block";
                document.getElementById("useButton").style.display = "none";
            }
            ;
            function changeButtonColor() {
                if (document.getElementById("busBox").value.localeCompare("on") === 0 ||
                        document.getElementById("metroBox").value.localeCompare("on") === 0 ||
                        document.getElementById("tramBox").value.localeCompare("on") === 0 ||
                        document.getElementById("trolleyBox").value.localeCompare("on") === 0) {
                    document.getElementById("useButton").style.backgroundColor = "#fecc03";
                }
                return;
            }
            ;
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
                <a href="profile"><img class="profilePicture" src="pictures/profilePictureMock.jpg"></a>
                <p class="profileName"><a href="profile"><strong><%=request.getSession().getAttribute("name")%></strong></a></p>
            </div>
            <div id="main">
                <form id="profileInfoForm" action="editUserInfo" method="post">
                    <div id="profileArea">
                        <p id="profileText">PROFILE</p>
                        <p id="change" onmouseenter="return pointerChange(change)" onmousedown="return edit()">Edit</p>
                        <img id="profileImage" src="pictures/profilePictureMock.jpg">
                        <input id="profileName" name="name" type="text" value="<%=request.getSession().getAttribute("name")%>" minlength="4" maxlength="25" required>
                        <p id="emailText">E-mail</p>
                        <input id="email" name="email" type="email" required value="<%=request.getSession().getAttribute("email")%>">
                        <p id="phoneText">Phone</p>
                        <input id="phone" name="phone" type="text" value="<%=request.getSession().getAttribute("phone")%>"
                               maxlength="13" minlength="13" pattern="^\+380[0-9]{9}$"
                               title="+380991234567 for example"
                               onkeypress='return ((event.charCode >= 48 && event.charCode <= 57) || event.charCode == 43)'
                               required>
                        <p id="gorodText">City</p>
                        <select id="City" name="city">
                            <option disabled selected value>City</option>
                            <option value="Kiev">Kiev</option>
                            <option value="Odesa">Odesa</option>
                            <option value="Lviv">Lviv</option>
                            <option value="Dnipro">Dnipro</option>
                        </select>
                        <input id="submitChangesButton" type="submit" value="save">
                    </div>
                </form>
                <form id="creditCardForm" action="editCCInfo" method="post">
                    <div id="creditCardArea">
                        <p id="bankCartaText">CREDIT CARD</p>
                        <input type="button" id="changeForCreditCard" onmouseenter="return pointerChange(changeForCreditCard)"
                               onmousedown="edit1()" value="Edit">
                        <p id="ImyaText">Credit card owner name</p>
                        <span id="lalala1"><input id="creditCardOwner" name="creditCardOwner" type="text"
                                                  minlength="4" maxlength="25" required="true"
                                                  value="<%=request.getSession().getAttribute("owner")%>"></span>
                        <p id="nomerBankCart">Credit card number</p>
                        <span id="lalala"><input id="creditCardNumber" minlength="16" maxlength="16" name="creditCardNumber"
                                                 required="true" value="<%=request.getSession().getAttribute("number")%>"
                                                 onkeypress='return event.charCode >= 48 && event.charCode <= 57'></span>
                        <p id="deictv">Valid till</p>
                        <p id="cvvText">CVV</p>
                        <select id="month" required="true" name="month">
                            <option disabled selected value>Month</option>
                            <option value="January">January</option>
                            <option value="February">February</option>
                            <option value="March">March</option>
                            <option value="April">April</option>
                            <option value="May">May</option>
                            <option value="June">June</option>
                            <option value="July">July</option>
                            <option value="August">August</option>
                            <option value="September">September</option>
                            <option value="October">October</option>
                            <option value="November">November</option>
                            <option value="December">December</option>
                        </select>
                        <select id="year" required="true" name="year">
                            <option disabled selected value>Year</option>
                            <option value="2016">2016</option>
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                            <option value="2025">2025</option>
                        </select>
                        <input id="CVV" minlength="3" maxlength="3" required="true" name="cvv"
                               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                               pattern="[0-9]{3}" title="Three letters CVV code" value="<%=request.getSession().getAttribute("cvv")%>">
                        <input type="submit" id="submitChangesButton1" value="save">
                    </div>
                </form>
                <form action="useTickets" method="post">
                    <div id="ticketArea">
                        <div id="fuckoffdiv">
                            <input type="button" id="boughtText" value="Bought" onclick="func1()">
                            <input type="button" id="usedText" value="Used" onclick="func2()">
                        </div>
                        <ul id="ticketListBought">
                            <li class="ticket">
                                <div class="galleryArea">
                                    <input type="checkbox" class="ticketCheckbox" name="metroBox" id="metroBox" onmousedown="return changeButtonColor()" onclick="return changeButtonColor()">
                                    <img src="pictures/metro-disabled-account.png" class="ticketPhoto">
                                </div>
                                <div class="ticketInfoArea">
                                    <p class="ticketName">Metro ticket</p><br>
                                    <p class="ticketDiscription">In stock: <%=request.getSession().getAttribute("metroAmount")%></p>
                                </div>
                            </li>
                            <li class="ticket">
                                <div class="galleryArea">
                                    <input type="checkbox" class="ticketCheckbox" name="busBox" id="busBox" onclick="return changeButtonColor()">
                                    <img src="pictures/bus-disabled-account.png" class="ticketPhoto">
                                </div>
                                <div class="ticketInfoArea">
                                    <p class="ticketName">Bus ticket</p><br>
                                    <p class="ticketDiscription">In stock: <%=request.getSession().getAttribute("busAmount")%></p>
                                </div>
                            </li>
                            <li class="ticket">
                                <div class="galleryArea">
                                    <input type="checkbox" class="ticketCheckbox" name="tramBox" id="tramBox" onclick="return changeButtonColor()">
                                    <img src="pictures/tram-disabled-account.png" class="ticketPhoto">
                                </div>
                                <div class="ticketInfoArea">
                                    <p class="ticketName">Tram ticket</p><br>
                                    <p class="ticketDiscription">In stock: <%=request.getSession().getAttribute("tramAmount")%></p>
                                </div>
                            </li>
                            <li class="ticket">
                                <div class="galleryArea">
                                    <input type="checkbox" class="ticketCheckbox" name="trolleyBox" id="trolleyBox" onclick="return changeButtonColor()">
                                    <img src="pictures/trolley-disabled-account.png" class="ticketPhoto">
                                </div>
                                <div class="ticketInfoArea">
                                    <p class="ticketName">Trolley ticket</p><br>
                                    <p class="ticketDiscription">In stock: <%=request.getSession().getAttribute("trolleyAmount")%></p>
                                </div>
                            </li>
                        </ul>
                        <ul id="ticketListUsed">
                            <%ArrayList<Ticket> list = (ArrayList<Ticket>) request.getSession().getAttribute("list");%>
                            <%for (Ticket t : list) {%>
                            <li class="ticket">
                                <div class="galleryArea">
                                    <input type="checkbox" class="ticketCheckbox">
                                    <img src=<%="pictures/" + t.getTransport().toLowerCase().trim()+"-enable-account.png"%> class="ticketPhoto">
                                </div>
                                <div class="ticketInfoArea">
                                    <p class="ticketNameUse"><%=t.getTransport()%> ticket</p><br>
                                    <p class="ticketDiscriptionUse">ID: <%=t.getId()%></p><br>
                                    <p class="ticketDiscriptionUse">Date of use: <%=t.getUsed_time()%></p>
                                </div>
                            </li>
                            <%}%>
                        </ul>
                </form>
                <input type="submit" id="useButton" value="USE" onmouseenter="return pointerChange(useButton)">
            </div>
        </div>
    </div>
</body>
</html>