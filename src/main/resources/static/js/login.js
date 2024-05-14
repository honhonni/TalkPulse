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

    // �ύ�¼�
    form.on('submit(login)', function(data){
        var field = data.field; // ��ȡ���ֶ�ֵ

        // �˴�ִ�� Ajax �Ȳ���
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
                    return layer.msg("��¼ʧ��")
                }
                layer.msg('��¼�ɹ���')
                // ��ת����̨��ҳ
                location.replace('/index.html')
            }
        })



        return false; // ��ֹĬ�� form ��ת
    });


    // �Զ�����֤����
    form.verify({
        user_pwd: [
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
                    return layer.msg("ע��ʧ�ܣ�������ע��")
                }
                layer.msg('ע��ɹ������¼��')
                // ģ���˵ĵ����Ϊ
                $('#to-login').click()
            }
        })

        return false; // ��ֹĬ�� form ��ת
    });

})