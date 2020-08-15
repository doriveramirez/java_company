function setAdmin() {
  var checkBox = document.getElementById("admin");
  var username = document.getElementById("username");

  if (checkBox.checked == true){
    username.value += "_admin";
  } else {
   username.value = username.value.split("_admin").join('');
  }
}