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
        if($(this).prop('id') == 'create-group'){
            // console.log($(this).attr("gid"))
            infoStr = template('tpl-create-group', res.data[0][3])
            $('.info-box').html(infoStr)

            var layer = layui.layer

            // 1.1 获取裁剪区域的 DOM 元素
            var $image = $('#image')
            // 1.2 配置选项
            const options = {
                // 纵横比
                aspectRatio: 1,
                // 指定预览区域
                preview: '.img-preview'
            }

            // 1.3 创建裁剪区域
            $image.cropper(options)

            $('#bnt-choose-img').on('click',function(){
                $('#file').click()
                console.log($(this))
            })

            $('#file').on('change',function(e){
                // console.log(e)

                var fileList = e.target.files
                if(fileList.length === 0){
                    return layer.msg("请选择文件!")
                }

                var newImgURL = URL.createObjectURL(fileList[0])

                $image
                    .cropper('destroy')      // 销毁旧的裁剪区域
                    .attr('src', newImgURL)  // 重新设置图片路径
                    .cropper(options)        // 重新初始化裁剪区域


            })
            $('#btn-upload').on('click',function(){
                // 1. 拿到用户裁剪后的 图片
                var dataURL = $image
                    .cropper('getCroppedCanvas', { // 创建一个 Canvas 画布
                        width: 100,
                        height: 100
                    })
                    .toDataURL('image/png')       // 将 Canvas 画布上的内容，转化为 base64 格式的字符串

                var raw = JSON.stringify({
                    "avatar": dataURL
                })
                // 2. 上传图片

                // $.ajax({
                //     method: 'patch',
                //     url: '/my/update/avatar',
                //     data: raw,
                //     headers: {"Content-Type": "application/json"},
                //     success:function(res) {
                //         if(res.code !==0 ){
                //             return layer.msg('更换头像失败！')
                //         }
                //         layer.msg('更新头像成功！')
                //         window.parent.getUserInfo()
                //     }
                // })
            })
            return
        }
        // ajax获取群聊信息
        infoStr = template('tpl-info', res.data[0][0])
        $('.info-box').html(infoStr)
    })


    $('.info-box').on('submit','.layui-form',function(e){
        e.preventDefault()
        var formData = $(this).serializeArray()
        var data = {}
        $.each(formData,function(index,field){
            data[field.name] = field.value
        })

        $.ajax({
            method: 'post',
            url: '/group/create',
            data: data,
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success: function(res){
                layer.msg(res.message)
                // 调用父页面的方法，重新渲染用户的头像和用户信息
                var newGroup = "\n" +
                    "    <button type=\"button\" class=\"list-group-item\" gid=\"{{$value.GID}}\">\n" +
                    "        <img src=\""+"/images/avatar/defualt.png"+"\" class=\"group-photo\">\n" +
                    "        "+data.group_name+"\n" +
                    "    </button>"
                $('.my-groups button').last().before(newGroup)
                $(".my-groups button").eq(-2).click()
            }
        })
    })
})

$(function(){
})