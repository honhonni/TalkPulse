$(function (){
    $('.groups-group').click(function (){
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

    var res = {
        data: [
            // 我创建的群聊
            [
                { group_name: '超级无敌霸气的名字', img: '/images/avatar/defualt.png', GID: '6666666'},
                { group_name: '我创建的群1', img: '/images/avatar/defualt.png', GID: '6666666'},
                { group_name: '我创建的群2', img: '/images/avatar/defualt.png', GID: '6666666'},
                { group_name: '我创建的群3', img: '/images/avatar/defualt.png', GID: '6666666'},
            ],
            // 我加入的群聊
            [
                { group_name: '我加入的群1', img: '/images/avatar/defualt.png', GID: '6666666'},
                { group_name: '我加入的群2', img: '/images/avatar/defualt.png', GID: '6666666'},
                { group_name: '我加入的群3', img: '/images/avatar/defualt.png', GID: '6666666'},
            ]
        ]
    }

    var myGroupStr = template('tpl-my-groups', res)
    $('.my-groups').html(myGroupStr)
    var joinGroupStr = template('tpl-join-groups', res)
    $('.join-groups').html(joinGroupStr)
    var infoStr = template('tpl-info', res.data[0][0])
    $('.info-box').html(infoStr)
    // 绑定事件
    $('.groups-list').on('click', 'button', function (){
        console.log($(this).attr("gid"))
    })
})

