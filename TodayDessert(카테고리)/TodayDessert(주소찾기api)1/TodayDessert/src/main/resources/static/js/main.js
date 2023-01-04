var slideIndex = 1;
var timer;
window.onload = function() {
     showSlides();
}


function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides fade");
    var dots = document.getElementsByClassName("dot");



    if (slideIndex > slides.length) { slideIndex = 1 }
    if (slideIndex < 1) { slideIndex = slides.length }



    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";



    slideIndex++;//다음 레이어를 보여주기 위해 1 추가
    timer = setTimeout(showSlides, 2000);
}



function plusSlides(n) {
    clearTimeout(timer);
    slideIndex = slideIndex + (n-1);//showSlides() 함수에서 1을 추가 함으로 여기선 이런 식으로 처리해야 맞음
    showSlides();//인수 없이 호출
}

function currentSlide(n) {
    clearTimeout(timer);
    slideIndex = n;//함수 호출 전 선언
    showSlides();//인수 없이 호출
}
