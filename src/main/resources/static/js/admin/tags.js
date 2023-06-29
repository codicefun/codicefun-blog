$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide')
})

$('.ui.dropdown').dropdown({
    on: 'hover'
})

// 消息提示关闭初始化
$('.message .close')
    .on('click', function () {
        $(this)
            .closest('.message')
            .transition('fade')
    })
