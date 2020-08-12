var url = "http://localhost:8080/api/items";

var xmlHttp = new XMLHttpRequest();
xmlHttp.onreadystatechange = function() {
  if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
    document.getElementById('output').innerHTML = JSON.parse(xmlHttp.responseText).total;
}
xmlHttp.open("GET", url, true);
xmlHttp.send();