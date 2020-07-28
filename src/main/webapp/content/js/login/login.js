function openForm() {
        document.getElementById("popupForm").style.display = "block";
        }
function closeForm() {
  document.getElementById("popupForm").style.display = "none";
}

var modal = document.getElementById("popupForm");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];



span.onclick = function() {
  modal.style.display = "none";
}

var disableBodyScroll = function(){
    window.body_scroll_pos = $(window).scrollTop(); // write page scroll position in a global variable
    $('body').css('overflow-y','hidden');
}

// Run this function when you close your popup:
var enableBodyScroll = function(){
    $('body').css('overflow-y','scroll');
    $(window).scrollTop(window.body_scroll_pos); // restore page scroll position from the global variable
}
