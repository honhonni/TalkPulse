$(function(){
    // 初始化
    init()



    layui.use(['form', 'laydate', 'util'], function(){
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var util = layui.util;

        // 自定义验证规则
        form.verify({
            password: function(value) {
                if (!/(.+){6,12}$/.test(value)) {
                    return '密码必须 6 到 12 位';
                }
            }
        });

        // 提交事件
        form.on('submit(update)', function(data){
            var field = data.field; // 获取表单字段值
            console.log(field)
            $.ajax({
                method: 'post',
                url: '/account/updatetonew',
                data: field,
                success: function (res){
                    if( res.status != 200){
                        return layer.msg('修改信息失败！')
                    }
                    return layer.msg('修改信息成功！')
                }
            })
            return false; // 阻止默认 form 跳转
        });

    });



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

    })

    $('#file').on('change',function(e){
        // console.log(e)

        var fileList = e.target.files
        if(fileList.length === 0){
            return layer.msg("请选择图片!")
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
        dataURL = dataURL.replace("data:image/png;base64,","")
        $.ajax({
            method: 'post',
            url: '/account/updateAvatar',
            data: {
                uphoto: dataURL
            },
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success:function(res) {
                if(res.status !== 200 ){
                    return layer.msg('更换头像失败！')
                }
                layer.msg('更新头像成功！')
                window.parent.getUserInfo();
            }
        })
    })

    function init(){
        $('#image').attr('src', localStorage.getItem('user_photo'))
        $('[name=uid]').val( localStorage.getItem('user_id'))
        $('[name=uname]').val( localStorage.getItem('user_name'))

        $('[name=ugender]').each(function (i, item) {
            if ($(item).val() == localStorage.getItem('user_gender')) {
                //更改选中值
                $(item).prop('checked', true);
                //重新渲染
                layui.use('form', function () {
                    var form = layui.form;
                    form.render();
                });
            }
        })
        $('[name=uage]').val(localStorage.getItem('user_age'))
        $('[name=uintroduce]').val( localStorage.getItem('user_introduce'))
    }
})