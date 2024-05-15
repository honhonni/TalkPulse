$(function(){

    var form = layui.form;
    var layer = layui.layer;

    $('#to-reg').on('click',function(){
        $('.login-container').hide()
        $('.reg-container').show()
    })
    $('#to-login').on('click',function(){
        $('.login-container').show()
        $('.reg-container').hide()
    })

    // 提交事件
    form.on('submit(login)', function(data){
        var field = data.field; // 获取表单字段值

        // 此处执行 Ajax 等操作
        var data = {
            user_id: field.user_id,
            user_pwd: field.user_pwd
        }
        $.ajax({
            method: 'post',
            url: '/account/login',
            data: data,
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success:function(res) {
                if (res.status !== 200) {
                    return layer.msg("登录失败")
                }
                layer.msg('登录成功！')
                // 跳转到后台主页
                location.replace('/index.html')
            }
        })



        return false; // 阻止默认 form 跳转
    });


    // 自定义验证规则
    form.verify({
        user_pwd: [
            /^[\S]{6,12}$/,
            "密码为6-12位"
        ],
        // 确认密码
        confirmPassword: function(value, item){
            var passwordValue = $('#reg-password').val();
            if(value !== passwordValue){
                return '两次密码输入不一致';
            }
        }
    });

    // 提交事件
    form.on('submit(reg)', function(data){
        var field = data.field; // 获取表单字段值

        // 发起Ajax的POST请求

        var data = {
            user_id: field.user_id,
            user_name: field.user_name,
            user_pwd: field.confirmPassword
        }
        $.ajax({
            method: 'post',
            url: '/account/register',
            data: data,
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            success:function(res) {
                if (res.status !== 200) {
                    return layer.msg("注册失败，请重新注册")
                }
                layer.msg('注册成功，请登录！')
                // 模拟人的点击行为
                $('#to-login').click()
            }
        })

        return false; // 阻止默认 form 跳转
    });

})