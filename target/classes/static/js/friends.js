$(function (){
    $('.friends-group').click(function (){
        var class_string = $(this).find('span').attr('class')
        if(class_string.indexOf('right') >= 0){
            // console.log(this)
            $(this).find('span').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-right')
            $(this).siblings().show()
        }else{
            $(this).find('span').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-right')
            $(this).siblings().hide()
        }
        resetui()
        moveToTop()
    })



    $('#search-friends').on('keydown blur', function(event) {
        if (event.type === "blur" || (event.type === "keydown" && event.which === 13)) { // 13代表回车键的键码
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
})
