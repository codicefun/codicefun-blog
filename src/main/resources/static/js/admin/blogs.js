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

// 点击 Clear 按钮，清除分类
$('#clear-btn')
    .on('click', function () {
        $('.ui.type.dropdown')
            .dropdown('clear')
    })

// 翻页
function page(obj) {
    // 先翻页，改变页码，再调 loadData() 获取新的页码
    // 新页码为 data-page=${page.number - 1} 中指定的值
    $("[name='page']").val($(obj).data("page"))
    loadData()
}

// 搜索
$('#search-btn').click(function () {
    $("[name='page']").val(0) // 搜索从第一页开始
    loadData()
})

/**
 * 异步加载数据，实现页面局部的刷新
 */
function loadData() {
    $("#table-container").load(/*[[@{/admin/blogs/search}]]*/"/admin/blogs/search", {
        title: $("[name='title']").val(),
        typeId: $("[name='type-id']").val(),
        recommend: $("[name='recommend']").prop('checked'),
        page: $("[name='page']").val()
    })
}
