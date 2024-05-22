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
            [1,2,3],
            // 我加入的群聊
            [1,2,3,4]
        ]
    }

    var myGroupStr = template('tpl-my-groups', res)
    $('.my-groups').html(myGroupStr)
    var joinGroupStr = template('tpl-join-groups', res)
    $('.join-groups').html(joinGroupStr)


    var infoStr = template('tpl-info-summary', res)
    $('.info-box').html(infoStr)

    // 绑定事件
    $('.groups-list').on('click', 'button', function (){
        if($(this).prop('id') == 'create-group'){
            // 显示创建群聊模板
            infoStr = template('tpl-create-group', res.data[0][3])
            $('.info-box').html(infoStr)

            layui.use(['form'], function(){
                var form = layui.form;
                var layer = layui.layer;

                // 自定义验证规则
                form.verify({
                    gid: [
                        /^[1-9][0-9]{7}$/,
                        "UID 为 8 位数字(开头不为0)"
                    ],
                    gname: [
                        /^[\S]{0,6}$/,
                        "群名称 为 0-6 位任意字符"
                    ],
                    gintroduce: [
                        /^[\S]{0,20}$/,
                        "群介绍 最多 20 位任意字符"
                    ],
                });


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

                // 创建群聊事件
                form.on('submit(create)',function(fdata){
                    var field = fdata.field; // 获取表单字段值
                    // 获取群头像
                    var dataURL = $image
                        .cropper('getCroppedCanvas', { // 创建一个 Canvas 画布
                            width: 100,
                            height: 100
                        })
                        .toDataURL('image/png')       // 将 Canvas 画布上的内容，转化为 base64 格式的字符串
                    var img = dataURL.replace("data:image/png;base64,","")

                    var data = {}
                    for(var k in field){
                        data[k] = field[k]
                    }
                    data.group_photo = img
                    $.ajax({
                        method: 'post',
                        url: '/group/create',
                        data: data,
                        headers: {"Content-Type": "application/x-www-form-urlencoded"},
                        success: function(res){
                            if(res.status !== 200){
                                return layer.msg(res.message)
                            }
                            layer.msg(res.message)
                            // 调用父页面的方法，重新渲染用户的头像和用户信息
                            var newGroup = "\n" +
                                "    <button type=\"button\" class=\"list-group-item\" gid=\""+data.group_id+"\">\n" +
                                "        <img src=\""+dataURL+"\" class=\"group-photo\">\n" +
                                "        "+data.group_name+"\n" +
                                "    </button>"
                            $('.my-groups button').last().before(newGroup)
                            $(".my-groups button").eq(-2).click()
                        }
                    })
                    return false
                })

            });
        }else{
            // ajax获取群聊信息
            infoStr = template('tpl-info', res.data[0][0])
            $('.info-box').html(infoStr)
        }
    })



})
