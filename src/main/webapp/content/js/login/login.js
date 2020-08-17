function openForm() {
        document.getElementById("popupForm").style.display = "block";
        document.getElementById("transparent-div").style.display = "block";
        disableBodyScroll();
        }
function closeForm() {
        document.getElementById("popupForm").style.display = "none";
        document.getElementById("transparent-div").style.display = "none";
        enableBodyScroll();
}

let modal = document.getElementById("popupForm");

let btn = document.getElementById("myBtn");

let span = document.getElementsByClassName("close")[0];



span.onclick = function() {
  modal.style.display = "none";
}

let disableBodyScroll = function(){
    window.body_scroll_pos = $(window).scrollTop(); // write page scroll position in a global variable
    $('body').css('overflow-y','hidden');
}

// Run this function when you close your popup:
let enableBodyScroll = function(){
    $('body').css('overflow-y','scroll');
    $(window).scrollTop(window.body_scroll_pos); // restore page scroll position from the global variable
}

