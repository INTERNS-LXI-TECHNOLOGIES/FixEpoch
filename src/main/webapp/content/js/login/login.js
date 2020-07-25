function openForm() {
        document.getElementById("popupForm").style.display = "block";
        }
function closeForm() {
  document.getElementById("popupForm").style.display = "none";
}

var modal = document.getElementById("popupForm");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
  modal.style.display = "block";
}

span.onclick = function() {
  modal.style.display = "none";
}
