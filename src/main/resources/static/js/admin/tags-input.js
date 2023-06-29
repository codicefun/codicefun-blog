$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide')
})

$('.ui.dropdown').dropdown({
    on: 'hover'
})

// 前端空置校验
$('.ui.form').form({
    fields: {
        name: {
            identifier: 'name',
            rules: [{
                type: 'empty',
                prompt: '请输入标签名称'
            }]
        }
    }
})

// 消息提示关闭初始化
$('.message .close')
    .on('click', function () {
        $(this)
            .closest('.message')
            .transition('fade')
    })
