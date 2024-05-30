$(function (){
    // 存储分组信息
    let friendship = {}
    // 存储好友验证列表
    let validation = []
    // 初始化页面
    init()


    // 初始化页面
    function init(){
        // 获取好友分组
        $.ajax({
            method: 'get',
            url: '/friends/getFriendship',
            success: function (res){
                if(res.status != 200){
                    return console.log('获取好友分组信息失败')
                }
                friendship = res.data.data
            }
        })
        // 获取好友列表
        $.ajax({
            method: 'get',
            url: '/friends/getFriendList',
            success: function (res){
                if(res.status != 200){
                    return console.log('获取好友列表失败')
                }
                // console.log(res)
                var htmlStr = template('tpl-friends-list', res)
                $('.friends-list').html(htmlStr)
            }
        })
        // 获取好友验证列表
        $.ajax({
            method: 'get',
            url: '/friends/getValidation',
            success: function (res){
                // 获取验证列表
                if(res.status !== 200){
                    return console.log('获取验证列表失败！')
                }
                validation = res.data

                // 好友验证列表及绑定事件
                var verifyStr = template( 'tpl-friends-verify-list',res.data)
                $('.friends-verify-list').html(verifyStr)
                var applyStr = template( 'tpl-friends-apply-list',res.data)
                $('.friends-apply-list').html(applyStr)

                // 绑定数字
                let verify_count = 0
                // 记录有多少条未处理事件
                for(var i in validation.validationlist){
                    if(validation.validationlist[i].validation_status === 0){
                        verify_count++
                    }
                }
                var verify = $('.friends-validation-list').find('#verify-count')
                verify.attr('verify_count',verify_count)
                if(verify_count > 99){
                    verify.html('99+').show()
                }else if( verify_count > 0){
                    verify.html(verify_count).show()
                }else{
                    verify.html(0).hide()
                }
                window.parent.setFriendsCount(verify_count)
                // 第一次查看显示特殊颜色
                $('.friends-validation-list>button[readstatus=\'0\']').css('background-color', '#fcf8e3ff')
            }
        })
    }

    window.initFriends = init

   // 绑定好友列表分组展开事件
    $('.friends-list').on('click','.friends-group', function (){
        var class_string = $(this).find('span').attr('class')
        if(class_string.indexOf('right') >= 0){
            // console.log(this)
            $(this).find('span').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-right')
            $(this).siblings().show()
        }else{
            $(this).find('span').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-right')
            $(this).siblings().hide()
        }
    })
    // 绑定点击列表获取好友信息事件
    $('.friends-list').on('click','button', function (){
        var uid = $(this).attr('uid')
        var friendship_name = $(this).parents('li').find('h2').text()
        $.ajax({
            method: 'get',
            url: '/friends/search',
            data: {user_id: uid},
            success: function (res){
                if(res.status != 200){
                    return console.log('获取好友信息失败')
                }
                res.data.data.friendship = friendship
                res.data.data.friendship_name = friendship_name
                console.log(res.data.data)
                var htmlStr = template('tpl-friends-info',res.data.data)
                $('.info-box').html(htmlStr)

                // 分组移动绑定
                $(".select-selected").click(function(e) {
                    e.stopPropagation();// 阻止事件冒泡
                    $(this).next(".select-items").toggle();
                });

                $(".select-items").on('click','div',function() {
                    if ($(this).hasClass("add-option")) {
                        var inputField = '<input type="text" class="add-option-input" placeholder="创建分组">';
                        $(this).html(inputField);
                        $(".add-option-input").focus();
                    }else{
                        var value = $(this).text();
                        $(".select-selected").text(value);
                        $(".select-items").hide();
                        var friendship_id
                        for(let i in friendship){
                            if(friendship[i].friendship_name === value){
                                friendship_id = friendship[i].friendship_id
                            }
                        }
                        $.ajax({
                            method: 'post',
                            url: '/friends/removeFriend',
                            data: {
                                friend_id: uid,
                                friendship_id: friendship_id
                            },
                            headers: {"Content-Type": "application/x-www-form-urlencoded"},
                            success: function (res){
                                if(res.status != 200){
                                    return console.log('移动分组失败')
                                }
                                init()
                            }

                        })
                    }
                });

                // 创建分组事件绑定
                $(document).on("blur",".add-option-input",function() {
                    var value = $(this).val();
                    if (value !== '') {
                        $(this).parent().before('<div>' + value + '</div>');
                        $(this).parent().html("创建分组");
                        // 创建分组成功
                        // 发起ajax请求
                        $.ajax({
                            method: 'post',
                            url: '/friends/createFriendship',
                            data: {friendship_name: value},
                            headers: {"Content-Type": "application/x-www-form-urlencoded"},
                            success: function (res){
                                if(res.status != 200) {
                                    $(this).parent().html("创建分组失败");
                                    return console.log('创建分组失败')
                                }
                                init()
                            }
                        })
                    } else {
                        $(this).parent().html("创建分组");
                    }
                });

                $(document).on("keypress",".add-option-input",function(e) {
                    if (e.which === 13) {
                        $(this).blur();
                    }
                });
                $(document).on("click",".add-option-input",function(e) {
                    e.stopPropagation();// 阻止事件冒泡
                });


                $(document).click(function(e) {
                    var container = $(".custom-select");
                    if (!container.is(e.target) && container.has(e.target).length === 0) {
                        $(".select-items").hide();
                    }
                });
            }
        })

    })

    // 绑定搜索好友事件
    $('#search-friends').on('keydown', function(event) {
        if (event.which === 13) { // 13代表回车键的键码
            $.ajax({
                method: 'get',
                url: '/friends/search',
                data: {user_id: $(this).val()},
                success: function (res){
                    if( res.status !== 200){
                        $('.search-friends-info-box').html("<br><h4>未查询到相关用户</h4><br>")
                        return
                    }
                    for(var i in validation.validationlist){
                        if(validation.validationlist[i].validation_senderid == res.data.data.user_id
                            && validation.validationlist[i].validation_status == 0){
                            res.data.isfriend = 'received'
                            break
                        }
                    }
                    for(var i in validation.applylist){
                        if(validation.applylist[i].validation_receiverid == res.data.data.user_id
                            && validation.applylist[i].validation_status == 0){
                            res.data.isfriend = 'sended'
                            break
                        }
                    }
                    var htmlStr = template( 'tpl-search-friends-info', res.data)
                    $('.search-friends-info-box').html(htmlStr)
                }
            })
        }
    })

    // 发送添加好友请求
    $('.search-friends-info-box').on('click','#add',function (){
        $.ajax({
            method: 'post',
            url: '/friends/addFriend',
            data: {
                friend_id: $('.search-friends-info-box .row1 span').eq(0).html()
            },
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success: function (res){
                if(res.status !== 200){
                    return $('.search-friends-info-box .row2 span').eq(1).html('发送好友申请失败')
                }
                $('.search-friends-info-box .row1 div').remove()
                $('.search-friends-info-box .row2 span').eq(1).html('发送好友申请成功')
                init()
            }
        })
    })



    // 绑定好友验证展开事件
    $('.friends-validation-list')
        // 验证列表事件绑定
        .on('click','.friends-validation-group',function (){
            var class_string = $(this).find('span').eq(0).attr('class')
            if(class_string.indexOf('right') >= 0){
                // console.log(this)
                $(this).find('span').eq(0).addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-right')
                $(this).siblings().show()
            }else{
                $(this).find('span').eq(0).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-right')
                $(this).siblings().hide()
            }
        })
        // 申请列表事件绑定
        .on('click','.friends-apply-group',function (){
            var class_string = $(this).find('span').eq(0).attr('class')
            if(class_string.indexOf('right') >= 0){
                // console.log(this)
                $(this).find('span').eq(0).addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-right')
                $(this).siblings().show()
            }else{
                $(this).find('span').eq(0).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-right')
                $(this).siblings().hide()
            }
        })


    // 同意与拒绝好友申请
    $('.friends-validation-list').on('click','.checkbox span',function (){
        var class_string = $(this).attr('class')
        var data = {}
        data.validation_id = $(this).parent().attr('aid')


        let htmlStr
        if( class_string.indexOf('agree') >= 0){
            data.agree = true
            htmlStr = '<span class="agreed">已同意</span>'
        }else{
            data.agree = false
            htmlStr = '<span class="rejected">已拒绝</span>'
        }
        $.ajax({
            method: 'post',
            url: '/friends/handleValidation',
            data,
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success: function (res){
                if( res.status != 200){
                    return console.log('好友申请处理失败')
                    htmlStr = '发送失败<span class="agree">同意</span><span class="reject">拒绝</span>'
                    $(`.friends-verify-list .checkbox[aid='${data.validation_id}']`).html(htmlStr)
                }
                console.log('好友申请处理成功')
                $(`.friends-verify-list .checkbox[aid='${data.validation_id}']`).html(htmlStr)

                // 绑定数字
                var verify = $('.friends-validation-list').find('#verify-count')
                let verify_count = Number(verify.attr('verify_count')) - 1
                verify.attr('verify_count', verify_count)
                if(verify_count > 99){
                    verify.html('99+').show()
                }else if( verify_count > 0){
                    verify.html(verify_count).show()
                }else{
                    verify.html(0).hide()
                }
                window.parent.setFriendsCount(verify_count)
                init()
            }
        })


    })

    // 绑定发送消息跳转事件
    $('.info-box').on('click','#jump',function (){
        window.parent.jump($(this).attr("mid"))
    })

})
