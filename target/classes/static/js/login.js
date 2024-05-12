$(function(){

    // var $ = layui.$;
    var form = layui.form;
    var layer = layui.layer;

    if(localStorage.getItem('token') === 'obsolete'){
        layer.msg('�����µ�¼')
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

    // �ύ�¼�
    form.on('submit(login)', function(data){
        var field = data.field; // ��ȡ���ֶ�ֵ

        // �˴�ִ�� Ajax �Ȳ���
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
        //             return layer.msg('��¼ʧ�ܣ�')
        //         }
        //         layer.msg('��¼�ɹ���')
        //         // console.log(res.token)
        //         // Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTcyNjksInVzZXJuYW1lIjoiaG9uaG9ubmkiLCJwYXNzd29yZCI6IiIsIm5pY2tuYW1lIjoiIiwiZW1haWwiOiIiLCJ1c2VyX3BpYyI6IiIsImlhdCI6MTcxMjkxMTQ3NSwiZXhwIjoxNzEyOTQ3NDc1fQ.8lX3eRPMnUpVwLv5zdWifAyJ4VT5WLBhBK_EcpgA7XM
        //         // �� token ����localStorage
        //         localStorage.setItem('token',res.token)
        //         // ��ת����̨��ҳ
        //         location.replace('/index.html')
        //     }
        // })



        return false; // ��ֹĬ�� form ��ת
    });


    // �Զ�����֤����
    form.verify({
        password: [
            /^[\S]{6,12}$/,
            "����Ϊ6-12λ"
        ],
        // ȷ������
        confirmPassword: function(value, item){
            var passwordValue = $('#reg-password').val();
            if(value !== passwordValue){
                return '�����������벻һ��';
            }
        }
    });

    // �ύ�¼�
    form.on('submit(reg)', function(data){
        var field = data.field; // ��ȡ���ֶ�ֵ

        // ����Ajax��POST����

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
        //         layer.msg('ע��ɹ������¼��')
        //         // ģ���˵ĵ����Ϊ
        //         $('#to-login').click()
        //     }
        // })

        return false; // ��ֹĬ�� form ��ת
    });

})