var swiper1 = new Swiper(".swiper-sync", {
	slidesPerView: 1,
	spaceBetween: 70,
	freeMode: false,
	loop: true,
	loopAdditionalSlides: 1,
	pagination: {
		el: ".swiper-pagination",
		clickable: true,
	},
	autoplay: {
		delay: 4000,
		disableOnInteraction: false
	},
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev"
	}
});


var swiper2 = new Swiper(".mySwiper0", {
	slidesPerView: 5,
	spaceBetween: 30,
	slidesPerGroup: 5,
	loop: false,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});

var swiper3 = new Swiper(".mySwiper", {
	slidesPerView: 5,
	spaceBetween: 30,
	slidesPerGroup: 5,
	loop: false,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});

var swiper4 = new Swiper(".mySwiper1", {
	slidesPerView: 5,
	spaceBetween: 30,
	slidesPerGroup: 5,
	loop: false,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});

var swiper5 = new Swiper(".mySwiper2", {
	slidesPerView: 5,
	spaceBetween: 30,
	slidesPerGroup: 5,
	loop: false,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});

var swiper6 = new Swiper(".mySwiper3", {
	slidesPerView: 5,
	spaceBetween: 30,
	slidesPerGroup: 5,
	loop: false,
	loopFillGroupWithBlank: true,
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
});




$(function() {
	$(window).scroll(function() {
		if ($(this).scrollTop() > 200) {
			$('#topBtn').fadeIn();
		}
		else { $('#topBtn').fadeOut(); }
	});
	$("#topBtn").click(function() {
		$('html, body').animate({ scrollTop: 0 }, 'fast');
		return false;
	});
});

$(".btn-search").click(function(){
	$(".modal-mask").css("display","table");
});

$(".popup-close").click(function(){
	$(".modal-mask").css("display","none");
});


$(".scrollto").click(function(event){
    $('html,body').animate({scrollTop:$(this.hash).offset().top -200}, 300);
    event.preventDefault();
});

function del_func() {
   var result = confirm("정말 삭제하시겠습니까?");

   if (result == true) {
      // 자바스크립트에서 파라미터 포함해서 넘겨줄때 ``사용

      location.href = `delete?userNum=[[${userInfo.userNum}]]`;
   }
   return false;

}





  