$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide');
});

$('.qq').popup();

$('.wechat-a').popup({
    popup: $('.wechat-qr'),
    position: 'bottom center'
});
