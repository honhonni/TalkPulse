$(function (){


    var res = {
        status: 200,
        message: "ok",
        data: [
            {
                friendship_name: '我的好友',
                friends: [
                    {user_id: '1', user_name: '张三', user_photo: '/images/avatar/defualt.png'},
                    {user_id: '2', user_name: '李四', user_photo: '/images/avatar/defualt.png'},
                    {user_id: '3', user_name: '王五', user_photo: '/images/avatar/defualt.png'},
                ]
            },
            {
                friendship_name: '同学',
                friends: [
                    {user_id: '4', user_name: '赵六', user_photo: '/images/avatar/defualt.png'},
                ]
            },
            {
                friendship_name: '死党',
                friends: [
                    {user_id: '5', user_name: '孙七', user_photo: '/images/avatar/defualt.png'},
                    {user_id: '6', user_name: '周八', user_photo: '/images/avatar/defualt.png'},
                ]
            }
        ]
    }


    // 好友列表
    var htmlStr = template('tpl-friends-list', res)
    $('.friends-list').html(htmlStr).on('click','.friends-group', function (){
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

    // 搜索好友
    $('#search-friends').on('keydown', function(event) {
        if (event.which === 13) { // 13代表回车键的键码
            console.log($(this).val())
            $.ajax({
                method: 'get',
                url: '/friends/search',
                data: {user_id: $(this).val()},
                success: function (res){
                    if( res.status !== 200){
                        $('.search-friends-info-box').html("<br>未查询到相关用户<br>")
                        return
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
                $('.search-friends-info-box .row1 button').remove()
                $('.search-friends-info-box .row2 span').eq(1).html('发送好友申请成功')
            }
        })
    })





    // 好友验证列表
    $('.friends-validation-group').click(function (){
        var class_string = $(this).find('span').eq(0).attr('class')
        if(class_string.indexOf('right') >= 0){
            // console.log(this)
            $(this).find('span').eq(0).addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-right')
            $(this).siblings().show()
        }else{
            $(this).find('span').eq(0).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-right')
            $(this).siblings().hide()
        }
        // 获取好友验证列表
        $.ajax({
            method: 'get',
            url: '/friends/getValidation',
            success: function (res){
                // 获取验证列表
                if(res.status !== 200){
                    return console.log('获取验证列表失败！')
                }
                console.log(res)
                for(let i = 0; i < res.data.length; i++){
                    var validation = res.data[i]
                    // 根据发起申请者id获取用户详细信息
                    $.ajax({
                        method: 'get',
                        url: '/friends/search',
                        data: {user_id: validation.validation_senderid},
                        success: function (user){
                            console.log(user)
                            if(user.status != 200){
                                return console.log('获取验证列表详细信息失败！')
                            }
                            res.data[i].data = user.data.data
                            var htmlStr = template( 'tpl-friends-verify-list',res)
                            $('.friends-verify-list').html(htmlStr)


                            $('.friends-verify-list>button[readstatus=\'0\']').css('background-color', '#fcf8e3ff')

                        }
                    })
                }
            }
        })
    })
    // 同意与拒绝好友申请
    $('.friends-verify-list').on('click','.checkbox span',function (){
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

            }
        })


    })
    // 好友申请列表
    var htmlStr1 = template( 'tpl-friends-apply-list',res)
    $('.friends-apply-list').html(htmlStr1)
    $('.friends-apply-group').click(function (){
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
})
