$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide');
});

$('#payButton').popup({
    popup: $('.payQR.popup'),
    on: 'click',
    position: 'bottom center'
});

tocbot.init({
    // Where to render the table of contents.
    tocSelector: '.js-toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '.js-toc-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2, h3',
});

$('.toc.button').popup({
    popup: $('.toc-container.popup'),
    on: 'click',
    position: 'left center'
});

$('.wechat-b').popup({
    popup: $('.wechat-qr'),
    position: 'left center'
});

let qrcode = new QRCode("qrcode", {
    text: location.href,
    width: 110,
    height: 110,
    colorDark: "#000000",
    colorLight: "#ffffff",
    correctLevel: QRCode.CorrectLevel.H
});

$('#to-top-button').click(function () {
    $(window).scrollTo(0, 500)
});

let waypoint = new Waypoint({
    element: document.getElementById('waypoint'),
    handler: function (direction) {
        if (direction === 'down') {
            $('#toolbar').show(500);
        } else {
            $('#toolbar').hide(500);
        }
    }
});

// 表单非空校验
$('.ui.form').form({
    fields: {
        content: {
            identifier: 'content',
            rules: [{
                type: 'empty',
                prompt: '请输入评论内容'
            }]
        },
        nickname: {
            identifier: 'nickname',
            rules: [{
                type: 'empty',
                prompt: '请输入你的大名'
            }]
        },
        email: {
            identifier: 'email',
            rules: [{
                type: 'email',
                prompt: '请输入正确的邮箱地址'
            }]
        }
    }
});

$('#comment-btn').click(function () {
    let boo = $('.ui.form').form('validate form');

    if (boo) {
        console.log("校验成功");
        postComment();
    } else {
        console.log("校验失败");
    }
});

/**
 * 提交评论
 */
function postComment() {
    $("#comment-container").load("/comments", {
        "parentComment.id": $("[name='parentComment.id']").val(),
        "blog.id": $("[name='blog.id']").val(),
        "nickname": $("[name='nickname']").val(),
        "email": $("[name='email']").val(),
        "content": $("[name='content']").val()
    }, function () {
        clearContent();
    });
}

/**
 * 清除评论内容
 */
function clearContent() {
    let content = $("[name='content']");
    content.val('');
    $("[name='parentComment.id']").val(-1);
    content.attr("placeholder", "请输入评论信息......").focus();
}

/**
 * 回复评论
 */
function reply(obj) {
    let commentId = $(obj).data("comment-id");
    let commentNickname = $(obj).data("comment-nickname");
    $("[name='content']").attr("placeholder", '@' + commentNickname).focus();
    $("[name='parentComment.id']").val(commentId);
    $(window).scrollTo($("#comment-form"), 500);
}
