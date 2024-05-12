$(function(){

    // var $ = layui.$;
    var form = layui.form;
    var layer = layui.layer;

    if(localStorage.getItem('token') === 'obsolete'){
        layer.msg('请重新登录')
        localStorage.removeItem('token')
    }

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
        var data = JSON.stringify({
            username: field.username,
            password: field.password
        })
        // $.ajax({
        //     method: 'post',
        //     url: '/api/login',
        //     data: data,
        //     headers: {"Content-Type": "application/json"},
        //     success:function(res) {
        //         if(res.code !== 0){
        //             return layer.msg('登录失败！')
        //         }
        //         layer.msg('登录成功！')
        //         // console.log(res.token)
        //         // Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTcyNjksInVzZXJuYW1lIjoiaG9uaG9ubmkiLCJwYXNzd29yZCI6IiIsIm5pY2tuYW1lIjoiIiwiZW1haWwiOiIiLCJ1c2VyX3BpYyI6IiIsImlhdCI6MTcxMjkxMTQ3NSwiZXhwIjoxNzEyOTQ3NDc1fQ.8lX3eRPMnUpVwLv5zdWifAyJ4VT5WLBhBK_EcpgA7XM
        //         // 将 token 存入localStorage
        //         localStorage.setItem('token',res.token)
        //         // 跳转到后台主页
        //         location.replace('/index.html')
        //     }
        // })



        return false; // 阻止默认 form 跳转
    });


    // 自定义验证规则
    form.verify({
        password: [
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

        var data = JSON.stringify({
            username: field.username,
            password: field.password,
            repassword: field.confirmPassword
        })
        // $.ajax({
        //     method: 'post',
        //     url: '/api/reg',
        //     data: data,
        //     headers: {"Content-Type": "application/json"},
        //     success:function(res) {
        //         if (res.code !== 0) {
        //             return layer.msg(res.message)
        //         }
        //         layer.msg('注册成功，请登录！')
        //         // 模拟人的点击行为
        //         $('#to-login').click()
        //     }
        // })

        return false; // 阻止默认 form 跳转
    });

})