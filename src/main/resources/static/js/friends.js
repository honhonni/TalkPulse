$(function (){
    // 存储好友验证列表
    let validation = []
    // 获取好友验证列表
    getValidaionList()


    let res = [
        {
            friendship_name: '我的好友',
            friends: []
        }
    ]
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
                        if(validation.applylist[i].validation_senderid == res.data.data.user_id
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
            }
        })
    })



    // 获取好友验证列表
    function getValidaionList(){
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
                var htmlStr = template( 'tpl-friends-validation-list',res)
                $('.friends-validation-list').html(htmlStr)
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
    window.getValidaionList = getValidaionList

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
