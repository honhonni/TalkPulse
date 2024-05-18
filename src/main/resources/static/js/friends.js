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





    var res = {
        status: 200,
        message: 'ok',
        data: [
            [
                {aid: 1, user_photo: "/images/avatar/defualt.png", user_name: "李四", user_id: 1234},
                {aid: 2, user_photo: "/images/avatar/defualt.png", user_name: "张三", user_id: 1235},
            ],
            [
                {aid: 3, user_photo: "/images/avatar/defualt.png", user_name: "王五", user_id: 1236},
                {aid: 4, user_photo: "/images/avatar/defualt.png", user_name: "赵六", user_id: 1237},
            ]
        ]
    }
    // 好友验证列表
    var htmlStr = template( 'tpl-friends-verify-list',res)
    $('.friends-verify-list').html(htmlStr)
    $('.friends-validation-group').click(function (){
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

    // 好友申请列表
    var htmlStr1 = template( 'tpl-friends-apply-list',res)
    $('.friends-apply-list').html(htmlStr1)
    $('.friends-apply-group').click(function (){
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
})
