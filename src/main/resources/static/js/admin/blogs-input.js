// 初始化 markdown 编辑器
let contentEditor;

$(function () {
    contentEditor = editormd("md-content", {
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/editor.md/lib/"
    });
});

$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide')
});

$('.ui.dropdown').dropdown({
    on: 'hover'
});

// 保存文章
$('#save-btn').click(function () {
    $("[name='published']").val(false)
    $('#blog-form').submit()
})

// 发布文章
$('#publish-btn').click(function () {
    $("[name='published']").val(true)
    $('#blog-form').submit()
})

// 自动新增标签
$('.ui.tag.dropdown')
    .dropdown({
        allowAdditions: true
    })

// 非空校验
$('.ui.form').form({
    fields: {
        flag: {
            identifier: 'flag',
            rules: [{
                type: 'empty',
                prompt: '标题：请输入博客标记'
            }]
        },
        title: {
            identifier: 'title',
            rules: [{
                type: 'empty',
                prompt: '标题：请输入博客标题'
            }]
        },
        content: {
            identifier: 'content',
            rules: [{
                type: 'empty',
                prompt: '标题：请输入博客内容'
            }]
        },
        typeId: {
            identifier: 'type.id',
            rules: [{
                type: 'empty',
                prompt: '标题：请输入博客分类'
            }]
        },
        firstPicture: {
            identifier: 'firstPicture',
            rules: [{
                type: 'empty',
                prompt: '标题：请输入博客首图'
            }]
        },
        description: {
            identifier: 'description',
            rules: [{
                type: 'empty',
                prompt: '标题：请输入博客描述'
            }]
        }
    }
});
