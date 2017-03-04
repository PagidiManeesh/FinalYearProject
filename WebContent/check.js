/*
 * this page consists of javascript functions which are used for dynamically maintaining the page */
function startchating() {
    var uname = document.getElementById('uname');
    var pw = document.getElementById('pw');
    if (uname.value == '' || pw.value == '')//Basic validation is done
    {
        alert('Please login to continue....');
        return false;
    }


    var xmlhttp;
    if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();/* method initializes a request. This method is to be used from 
        JavaScript code;*/

    } else { // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.open("POST", "chat?uname=" + uname.value + "&pw=" + pw.value, true);/* method initializes a request. This method is to be used from 
    JavaScript code;*/

    document.getElementById("loading-icon").innerHTML = '<img src="loader.gif" border="0" alt="Loading, please wait..." />';
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) 
//status will be an unsigned short. Before the request is complete, the value of status will be 0.
        	{
            if (xmlhttp.responseText.indexOf('Incorrect') !== -1) {
                document.getElementById("loading-icon").innerHTML = "<h4 style='color:red'>" + xmlhttp.responseText + "<h4>";
            } else {
                document.getElementById("result-data").innerHTML = '<h1>' + xmlhttp.responseText + '</h1>';
            }


        }


    }
    xmlhttp.send(null);

}

function addText()//used to add the text into the text box as soon as user clicks send
{
    var username = document.getElementById('u').value;//get user name
    var msg = document.getElementById('h').value;// get message
    olist = document.getElementById('list'); 
    op = document.createElement('p'); 
    op.innerHTML = username + "- <g>" + msg + "</g>";//element is a container used to group other SVG elements
    ocontent = document.getElementById('content');//get element which has id as content    
    ocontent.appendChild(op);             //Add new line on click
    olist.scrollTop = olist.scrollHeight; //Adjust Height 
    document.getElementById('h').value = '';
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.open("POST", "chatstore?uname=" + username + "&msg=" + msg, true);/* method initializes a request. This method is to be used from 
    JavaScript code;*/

    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)/*status will be an unsigned short. Before the request is complete, the value of status will be 0.*/
        {
            document.getElementById("result").innerHTML = 'sent';
            document.getElementById("result").innerHTML = '';
        }
    }
    xmlhttp.send(null);
}

function reloaddata()// used to reload the data present in the database for every 3000ms with the help of ReloadData.java
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }


    xmlhttp.open("POST", "reloaddata", true);

    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {

            document.getElementById("content").innerHTML = xmlhttp.responseText;
            olist = document.getElementById('list'); //everything in id list
            olist.scrollTop = olist.scrollHeight;


        }


    }
    xmlhttp.send(null);




}

