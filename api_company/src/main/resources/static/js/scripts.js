function setAdmin() {
  var checkBox = document.getElementById("admin");
  var username = document.getElementById("username");

  // If the checkbox is checked, display the output text
  if (checkBox.checked == true){
    username.value += "_admin";
  } else {
   username.value = username.value.split("_admin").join('');
  }
}