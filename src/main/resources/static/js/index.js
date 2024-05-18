$(function (){
    $.ajax({
        method: 'get',
        url: '/account/get',
        success: function (res){
            if(res.status !== 200){
                var htmlStr = template('tpl-info-dropdown', {
                        user_name:"请重新登录",
                        user_photo: "/images/avatar/defualt.png"
                    })
                $('.dropdown').html(htmlStr)
                return
            }
            var htmlStr = template('tpl-info-dropdown', res.data)
            $('.dropdown').html(htmlStr)
        }
    })

    $('ul.navbar-left').on("click","li",function(){	// 切换选项
        $(this).addClass('active').siblings().removeClass('active')
    })
    $('#edit-info').on('click', function (){
        $('ul.navbar-left li').removeClass('active')
    })
    $('#searchbutton').on('click',function (){
        $('#searchModal').modal('show')
    })
    $('#searchModal').on('shown.bs.modal', function (event) {
        $('#search').focus()
        var listHeight = $('.search-list').outerHeight(false)
        var modal_body = $('.modal-body')
        if(listHeight > 300){
            modal_body.css('height', 300 + 'px')
        }else {
            modal_body.css('height', listHeight + 'px')
        }
    })

    const ws = new WebSocket('wss://localhost/ws/123')
    // 连接成功后的回调函数
    ws.onopen = function (params) {
        console.log('客户端连接成功')
        // 向服务器发送消息
        ws.send('hello')
    };
    // 从服务器接受到信息时的回调函数
    ws.onmessage = function (e) {
        console.log('收到服务器响应', e.data)
    };

    // 连接关闭后的回调函数
    ws.onclose = function(evt) {
        console.log("关闭客户端连接");
    };

    // 连接失败后的回调函数
    ws.onerror = function (evt) {
        console.log("连接失败了");
    };


    // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，这样服务端会抛异常。
    window.onbeforeunload = function() {
        ws.close();
    }

})