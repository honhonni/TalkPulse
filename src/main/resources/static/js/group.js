$(function (){
    var validation = {}
    getValidaionList()

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



    // 搜索群聊
    $('#search-groups').on('keydown', function(event) {
        if (event.which === 13) { // 13代表回车键的键码
            $.ajax({
                method: 'get',
                url: '/friends/search',
                data: {user_id: $(this).val()},
                success: function (res){
                    if( res.status !== 200){
                        $('.search-groups-info-box').html("<br><h4>未查询到相关群聊</h4><br>")
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
                    var htmlStr = template( 'tpl-search-groups-info', res.data)
                    $('.search-groups-info-box').html(htmlStr)
                }
            })
        }
    })
    // 发送入群申请请求
    $('.search-groups-info-box').on('click','#add',function (){
        $.ajax({
            method: 'post',
            url: '/friends/addFriend',
            data: {
                friend_id: $('.search-groups-info-box .row1 span').eq(0).html()
            },
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success: function (res){
                if(res.status !== 200){
                    return $('.search-groups-info-box .row2 span').eq(1).html('发送好友申请失败')
                }
                $('.search-groups-info-box .row1 div').remove()
                $('.search-groups-info-box .row2 span').eq(1).html('发送好友申请成功')
            }
        })
    })

    // 获取群聊验证列表
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

                // 群聊验证列表及绑定事件
                var htmlStr = template( 'tpl-groups-validation-list',res)
                $('.groups-validation-list').html(htmlStr)
                    // 验证列表事件绑定
                    .on('click','.groups-validation-group',function (){
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
                    .on('click','.groups-apply-group',function (){
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
                var verify = $('.groups-validation-list').find('#verify-count')
                verify.attr('verify_count',verify_count)
                if(verify_count > 99){
                    verify.html('99+').show()
                }else if( verify_count > 0){
                    verify.html(verify_count).show()
                }else{
                    verify.html(0).hide()
                }
                window.parent.setGroupsCount(verify_count)
                // 第一次查看显示特殊颜色
                $('.groups-validation-list>button[readstatus=\'0\']').css('background-color', '#fcf8e3ff')
            }
        })
    }

})
