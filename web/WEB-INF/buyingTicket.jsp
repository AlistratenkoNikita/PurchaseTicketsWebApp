<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Purchase ticket</title>
        <link rel="icon" href="pictures/icon.png">
        <link rel="stylesheet" href="styles/mainStyle.css"/>
        <link rel="stylesheet" href="styles/buyingTicket.css"/>
        <link rel="stylesheet" href="bower_components/dist/sweetalert.css">
        <script src="bower_components/dist/sweetalert.min.js"></script>
        <script type="text/javascript">
            function funcIncr(field, area) {
                str1 = field.value;
                if (parseInt(str1) === 99) {
                    return;
                }
                field.value = parseInt(str1) + 1;
                document.getElementById("totalAmountText").value = parseInt(document.getElementById("totalAmountText").value) + parseInt("4") + " grn.";
                funcCheckout(field, area);
            }
            function funcDecr(field, area) {
                str1 = field.value;
                if (parseInt(str1) === 0) {
                    return;
                }
                field.value = parseInt(str1) - 1;
                document.getElementById("totalAmountText").value = parseInt(document.getElementById("totalAmountText").value) - parseInt("4") + " grn.";
                funcCheckout(field, area);
            }
            function funcCheckout(field, area) {
                str = field.value;
                if (str == 0) {
                    area.style.backgroundImage = "url(pictures/" + area.getAttribute("id") + "-disabled-buyingPage.png)";
                    area.style.boxShadow = "none";
                    document.getElementById("buyButton").style.backgroundColor = "#e2e2e2";
                    document.getElementById("buyButton").setAttribute("disabled", "true");
                } else {
                    area.style.backgroundImage = "url(pictures/" + area.getAttribute("id") + "-enable-buyingPage.png)";
                    area.style.boxShadow = "0px 10px 35px -9px rgba(0,0,0,0.75)";
                    document.getElementById("buyButton").style.backgroundColor = "black";
                    document.getElementById("buyButton").removeAttribute("disabled");
                }
            }
            ;
            function pointerChange() {
                document.getElementById("buyButton").style.cursor = "pointer";
            };
            
            function checkCC(){
                if ('<%=request.getSession().getAttribute("number").toString()%>'.localeCompare("") === 0){
                    swal("Oops!", "You have no credit card", "error");
                    return false;
                }
                return true;
            };
            function setParam(){
                document.getElementById("busAmoutHide").value = document.getElementById("busAmount").value;
                document.getElementById("trolleyAmoutHide").value = document.getElementById("trolleyAmount").value;
                document.getElementById("metroAmoutHide").value = document.getElementById("metroAmount").value;
                document.getElementById("tramAmoutHide").value = document.getElementById("tramAmount").value;
            }
        </script>
    </head>
    <body>
        <div class="header">
            <img class="logo" src="pictures/logo.jpg">
            <div class="navigation">
                <ul>
                    <li><a href="purchaseTicket" style="color:black">PURCHASE TICKETS</a></li>
                    <li><a href="#2" style="text-decoration:line-through">SUPPORT</a></li>
                    <li><a href="#3" style="text-decoration:line-through">ABOUT</a></li>
                </ul>
            </div>
            <div class="profileHeaderInfo">
                <a href="profile"><img class="profilePicture" src="pictures/profilePictureMock.jpg"></a>
                <p class="profileName"><a href="profile"><strong><%=request.getSession().getAttribute("name")%></strong></a></p>
            </div>
            <div id="main">
                <form action="purchaseTicket" method="post" onsubmit="setParam()">
                    <input type="hidden" name="busAmoutHide" id="busAmoutHide" value="0"/> 
                    <input type="hidden" name="trolleyAmoutHide" id="trolleyAmoutHide" value="0"/> 
                    <input type="hidden" name="metroAmoutHide" id="metroAmoutHide" value="0"/> 
                    <input type="hidden" name="tramAmoutHide" id="tramAmoutHide" value="0"/> 
                    <div id="ticketsArea">
                        <div class="ticket" id="bus">
                            <div class="inputAmount">
                                <input type="button" value="-" class="But" onclick="funcDecr(busAmount, bus)">
                                <input type="text" class="amount" disabled="true" value="0" id="busAmount">
                                <input type="button" value="+" class="But" onclick="funcIncr(busAmount, bus)">
                            </div>
                        </div>
                        <div class="ticket" id="trolley">
                            <div class="inputAmount">
                                <input type="button" value="-" class="But" onclick="funcDecr(trolleyAmount, trolley)">
                                <input type="text" class="amount" disabled="true" value="0" id="trolleyAmount">
                                <input type="button" value="+" class="But" onclick="funcIncr(trolleyAmount, trolley)"> 
                            </div>
                        </div>
                        <div class="ticket" id="tram">
                            <div class="inputAmount">
                                <input type="button" value="-" class="But" onclick="funcDecr(tramAmount, tram)">
                                <input type="text" class="amount" disabled="true" value="0" id="tramAmount">
                                <input type="button" value="+" class="But" onclick="funcIncr(tramAmount, tram)"> 
                            </div>
                        </div>
                        <div class="ticket" id="metro">
                            <div class="inputAmount">
                                <input type="button" value="-" class="But" onclick="funcDecr(metroAmount, metro)">
                                <input type="text" class="amount" disabled="true" value="0" id="metroAmount">
                                <input type="button" value="+" class="But" onclick="funcIncr(metroAmount, metro)"> 
                            </div>
                        </div>
                    </div>
                    <div id="buttonArea">
                        <div id="totalArea">
                            <p>Total: </p>
                            <input type="text" value="0 grn." disabled="true" id="totalAmountText">
                        </div>
                        <div id="buttonBuyArea">
                            <input type="submit" value="BUY" name="buyButton" id="buyButton" onmouseenter="pointerChange()" onmousedown="checkCC()" disabled="true">
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </body>
</html>