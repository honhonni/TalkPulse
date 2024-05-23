$(function(){
    $('#to-reg').on('click',function(){
        $('.login-container').hide()
        $('.reg-container').show()
    })
    $('#to-login').on('click',function(){
        $('.login-container').show()
        $('.reg-container').hide()
    })

    layui.use(['form', 'laydate', 'util'], function(){
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var util = layui.util;

        // 自定义验证规则
        form.verify({
            uid: [
                /^[1-9][0-9]{5}$/,
                "UID 为 6 位数字(开头不为0)"
            ],
            uname: [
                /^[\S]{0,6}$/,
                "用户名 为 0-6 位任意字符"
            ],
            password: function(value){
                if(value.length < 6 || value.length > 12){
                    return "密码为 6-12 位任意字符"
                }
            },
            confirmPassword: function(value){
                if(value !== $('[name="reg_pwd"]').val()){
                    return "两次输入的密码不一致"
                }
            }
        });
        // 登录事件
        form.on('submit(login)', function(data){
            var field = data.field; // 获取表单字段值
            console.log(field)
            // 此处执行 Ajax 等操作
            var data = {
                user_id: String(field.user_id),
                user_pwd: String(field.user_pwd)
            }
            $.ajax({
                method: 'post',
                url: '/account/login',
                data: data,
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                success:function(res) {
                    if (res.status !== 200) {
                        return layer.msg(res.message)
                    }
                    layer.msg('登录成功！')
                    localStorage.setItem('user_id',String(data.user_id));
                    // 跳转到主页
                    location.replace('/index.html')

                }
            })
            return false; // 阻止默认 form 跳转
        });

        // 提交事件
        form.on('submit(reg)', function(data){
            var field = data.field; // 获取表单字段值

            // 发起Ajax的POST请求

            var data = {
                user_id: String(field.user_id),
                user_name: String(field.user_name),
                user_pwd: String(field.confirmPassword)
            }
            $.ajax({
                method: 'post',
                url: '/account/register',
                data: data,
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                success:function(res) {
                    if (res.status !== 200) {
                        return layer.msg(res.message)
                    }
                    layer.msg('注册成功，请登录！')
                    // 模拟人的点击行为
                    $('#to-login').click()
                }
            })

            return false; // 阻止默认 form 跳转
        });

    });


})