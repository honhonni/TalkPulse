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


})