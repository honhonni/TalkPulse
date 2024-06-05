$(function (){
    // 获取个人信息
    getUserInfo()

    // 初始化websocket
    initWebsocket()

    // 初始化数字
    initCount()

    // 导航栏切换
    $('ul.navbar-left').on("click","li",function(){	// 切换选项
        $(this).addClass('active').siblings().removeClass('active')
    })
    // 点击编辑个人信息后，导航栏取消选择
    $('.dropdown').on('click', '#edit-info', function (){
        $('ul.navbar-left li').removeClass('active')
    })
    // 打开全局搜索框
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

    // websocket连接，用于接收有关自己的消息
    function initWebsocket(){
        const  url = 'wss://'+window.location.host+'/ws/'+localStorage.getItem('user_id');
        console.log(url)
        const ws = new WebSocket(url)
        // 连接成功后的回调函数
        ws.onopen = function (params) {
            console.log('客户端连接成功')
        };
        // 从服务器接受到信息时的回调函数
        ws.onmessage = function (e) {
            console.log(e.data)
            if(e.data === '1'){
                // 有消息
                var ele = $('.count').eq(0)
                var count = Number(ele.attr('count')) + 1
                ele.attr('count',count)
                if(count > 99){
                    ele.html('99+').show()
                }else if( count > 0){
                    ele.html(count).show()
                }
                var iframe = $('#inlineFrame').contents();
                if(iframe[0].title === '消息'){
                    // console.log('当前在friends页面下')
                    $('#inlineFrame')[0].contentWindow.initMessages(localStorage.getItem("current_id"))
                }
            }else if( e.data === '2'){
                // 好友申请有消息
                // 获取第二个元素
                var ele = $('.count').eq(1)
                var count = Number(ele.attr('count')) + 1
                ele.attr('count',count)
                if(count > 99){
                    ele.html('99+').show()
                }else if( count > 0){
                    ele.html(count).show()
                }
                var iframe = $('#inlineFrame').contents();
                if(iframe[0].title === '好友'){
                    // console.log('当前在friends页面下')
                    $('#inlineFrame')[0].contentWindow.initFriends()
                }

            }else if( e.data === '3'){
                // 群申请有消息
                // 获取第三个元素
                var ele = $('.count').eq(2)
                var count = Number(ele.attr('count')) + 1
                ele.attr('count',count)
                if(count > 99){
                    ele.html('99+').show()
                }else if( count > 0){
                    ele.html(count).show()
                }
                var iframe = $('#inlineFrame').contents();
                if(iframe[0].title === '群聊'){
                    // console.log('当前在groupss页面下')
                    $('#inlineFrame')[0].contentWindow.initGroups()
                }
            }
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
    }


    // 初始化数字
    function initCount(){
        // 获取好友验证列表
        $.ajax({
            method: 'get',
            url: '/friends/getValidation',
            success: function (res){
                // 获取验证列表
                if(res.status !== 200){
                    return console.log('获取验证列表失败！')
                }
                let validation = res.data
                // 绑定数字
                let verify_count = 0
                // 记录有多少条未处理事件
                for(var i in validation.validationlist){
                    if(validation.validationlist[i].validation_status === 0){
                        verify_count++
                    }
                }
                setFriendsCount(verify_count)
            }
        })
        // 获取群聊验证列表
        $.ajax({
            method: 'get',
            url: '/group/getGroupapply',
            success: function (res){
                // 获取验证列表
                if(res.status !== 200){
                    return console.log('获取验证列表失败！')
                }
                let validation = res.data

                // 绑定数字
                let verify_count = 0
                // 记录有多少条未处理事件
                for(var i in validation.applylist){
                    if(validation.applylist[i].groupapply_status === 0){
                        verify_count++
                    }
                }
                setGroupsCount(verify_count)
            }
        })
    }

    // 获取个人信息
    function getUserInfo(){
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
                    location.replace('/html/login.html')
                    return
                }
                // 加上时间戳，防止浏览器缓存，图片不刷新
                var time = new Date().getTime();
                res.data.user_photo = res.data.user_photo + "?res=" + time

                var htmlStr = template('tpl-info-dropdown', res.data)
                $('.dropdown').html(htmlStr)
                // for(let k in localStorage){
                //     localStorage[k] = ''
                // }
                for(let k in res.data){
                    localStorage.setItem( k , res.data[k])
                }
            }
        })
    }
    window.getUserInfo = getUserInfo


    // 设置消息图标
    window.setMessagesCount = function (count){
        var ele = $('.count').eq(0)
        ele.attr('count',count)
        if(count > 99){
            ele.html('99+').show()
        }else if( count > 0){
            ele.html(count).show()
        }else{
            ele.html(0).hide()
        }
    }
    // 设置好友图标
    window.setFriendsCount = function (count){
        var ele = $('.count').eq(1)
        ele.attr('count',count)
        if(count > 99){
            ele.html('99+').show()
        }else if( count > 0){
            ele.html(count).show()
        }else{
            ele.html(0).hide()
        }

    }
    // 设置群聊图标
    window.setGroupsCount = function (count){
        var ele = $('.count').eq(2)
        ele.attr('count',count)
        if(count > 99){
            ele.html('99+').show()
        }else if( count > 0){
            ele.html(count).show()
        }else{
            ele.html(0).hide()
        }
    }

    // 跳转到消息页面指定好友或群聊
    window.jump = function(id){
        $('#messages-button').click()
        var iframe = $('#inlineFrame')
        // 修改iframe的src
        iframe.attr('src','/html/messages.html')

        // 监听 iframe 加载完成的事件
        iframe.on('load',function() {
            iframe[0].contentWindow.initMessages(id)
        });

    }
})