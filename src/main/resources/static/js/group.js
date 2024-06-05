$(function (){
    var validation = {}
    init()

    // 初始化
    function init(){
        // 获取群聊列表
        $.ajax({
            method: 'get',
            url: '/friends/getUserGroups',
            success: function (res){
                if(res.status != 200){
                    return console.log('获取群聊失败')
                }
                // console.log('获取群聊列表')
                // console.log(res)
                var htmlStr = template('tpl-my-groups', res)
                $('.my-groups').html(htmlStr)
                var htmlStr = template('tpl-join-groups', res)
                $('.join-groups').html(htmlStr)

                var infoStr = template('tpl-info-summary', res)
                $('.info-box').html(infoStr)
            }
        })
        // 获取群聊验证列表
        $.ajax({
            method: 'get',
            url: '/group/getGroupapply',
            success: function (res){
                // 获取验证列表
                if(res.status !== 200){
                    return console.log('获取验证列表失败！')
                }
                validation = res.data

                // 群聊验证列表及绑定事件
                var verifyStr = template( 'tpl-groups-verify-list',res.data)
                $('.groups-verify-list').html(verifyStr)
                var applyStr = template( 'tpl-groups-apply-list',res.data)
                $('.groups-apply-list').html(applyStr)

                // 绑定数字
                let verify_count = 0
                // 记录有多少条未处理事件
                // console.log(validation.applylist)
                for(var i in validation.applylist){
                    if(validation.applylist[i].groupapply_status === 0){
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
    window.initGroups = init

    // 绑定群聊分组展开事件
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


    // 绑定事件,点击群聊列表或创建群聊
    $('.groups-list').on('click', 'button', function (){
        let gid = $(this).attr('gid')
        if(gid == 'create-group'){
            // 显示创建群聊模板
            infoStr = template('tpl-create-group')
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
                            console.log('创建群聊成功')
                            // 添加按钮
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
        }else {
            // ajax获取群聊信息
            var friendship_name = $(this).parents('li').find('h2').text()
            $.ajax({
                method: 'get',
                url: '/group/getGroupInfo',
                data: {group_Id: gid},
                success: function (res) {
                    if (res.status != 200) {
                        return console.log('获取好友信息失败')
                    }
                    let data = res.data
                    // 获取群成员
                    $.ajax({
                        method: 'get',
                        url: '/group/getGroupMember',
                        data: { group_id: gid},
                        success: function (res) {
                            if(res.status != 200){
                                return console.log("获取群成员列表失败")
                            }
                            console.log(res)
                            data.members = res.data
                            var htmlStr = template('tpl-info', data)
                            $('.info-box').html(htmlStr)
                        }
                    })

                }
            })
        }
    })



    // 搜索群聊
    $('#search-groups').on('keydown', function(event) {
        if (event.which === 13) { // 13代表回车键的键码
            $.ajax({
                method: 'get',
                url: '/group/getGroupInfo',
                data: {group_Id: $(this).val()},
                success: function (res){
                    if( res.status !== 200){
                        $('.search-groups-info-box').html("<br><h4>未查询到相关群聊</h4><br>")
                        return
                    }
                    // console.log(validation)
                    for(var i in validation.apply){
                        if(validation.apply[i].groupapply_groupid == res.data.group.group_id
                            && validation.apply[i].groupapply_status == 0){
                            res.data.present = 'sended'
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
            url: '/group/addGroup',
            data: {
                groupapply_groupid: $('.search-groups-info-box .row2 span').eq(0).html(),
                groupapply_hostid: $('#add').attr('hostid'),
                groupapply_introduce: "申请"
            },
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success: function (res){
                if(res.status !== 200){
                    return $('.search-groups-info-box .row2 span').eq(1).html('发送群聊申请失败')
                }
                $('.search-groups-info-box .row1 div').remove()
                $('.search-groups-info-box .row2 span').eq(1).html('发送群聊申请成功')
            }
        })
    })


    // 同意与拒绝群聊申请
    $('.groups-verify-list').on('click','.checkbox span',function (){
        var class_string = $(this).attr('class')
        var data = {}
        data.groupapply_id = $(this).parent().attr('aid')
        console.log("groupapply_id",data.groupapply_id)
        let htmlStr
        if( class_string.indexOf('agree') >= 0){
            data.groupapply_status = 1
            htmlStr = '<span class="agreed">已同意</span>'
        }else{
            data.groupapply_status = -1
            htmlStr = '<span class="rejected">已拒绝</span>'
        }
        $.ajax({
            method: 'post',
            url: '/group/handleGroupapply',
            data,
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success: function (res){
                if( res.status != 200){
                    return console.log('入群申请处理失败')
                    htmlStr = '发送失败<span class="agree">同意</span><span class="reject">拒绝</span>'
                    $(`.groups-verify-list .checkbox[aid='${data.groupapply_id}']`).html(htmlStr)
                }
                console.log('入群申请处理成功')
                $(`.groups-verify-list .checkbox[aid='${data.groupapply_id}']`).html(htmlStr)

                // 绑定数字
                var verify = $('.groups-verify-list').find('#verify-count')
                let verify_count = Number(verify.attr('verify_count')) - 1
                verify.attr('verify_count', verify_count)
                if(verify_count > 99){
                    verify.html('99+').show()
                }else if( verify_count > 0){
                    verify.html(verify_count).show()
                }else{
                    verify.html(0).hide()
                }
                window.parent.setGroupsCount(verify_count)
                init()
            }
        })


    })

    // 退出群聊
    $('.info-box').on('click', '#quite',function (){
        let group_id = $('.info-box li').eq(0).find('span').eq(1).text()
        $.ajax({
            method: 'post',
            url: '/group/exit',
            data: {
                group_id
            },
            success: function (res){
                if(res.status !== 200){
                    console.log('退出群聊失败')
                }
                console.log('退出群聊成功')
                init()
            }
        })
    })

    $('.groups-validation-list')
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

    // 绑定发送消息跳转事件
    $('.info-box').on('click','#jump',function (){
        window.parent.jump($(this).attr("mid"))
    })
})
