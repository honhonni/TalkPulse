$(function(){
    var layer = layui.layer

    // 1.1 ��ȡ�ü������ DOM Ԫ��
    var $image = $('#image')
    // 1.2 ����ѡ��
    const options = {
        // �ݺ��
        aspectRatio: 1,
        // ָ��Ԥ������
        preview: '.img-preview'
    }

    // 1.3 �����ü�����
    $image.cropper(options)

    $('#bnt-choose-img').on('click',function(){
        $('#file').click()

    })

    $('#file').on('change',function(e){
        // console.log(e)

        var fileList = e.target.files
        if(fileList.length === 0){
            return layer.msg("��ѡ���ļ�!")
        }

        var newImgURL = URL.createObjectURL(fileList[0])

        $image
            .cropper('destroy')      // ���پɵĲü�����
            .attr('src', newImgURL)  // ��������ͼƬ·��
            .cropper(options)        // ���³�ʼ���ü�����


    })
    $('#btn-upload').on('click',function(){
        // 1. �õ��û��ü���� ͼƬ
        var dataURL = $image
            .cropper('getCroppedCanvas', { // ����һ�� Canvas ����
                width: 100,
                height: 100
            })
            .toDataURL('image/png')       // �� Canvas �����ϵ����ݣ�ת��Ϊ base64 ��ʽ���ַ���

        var raw = JSON.stringify({
            "avatar": dataURL
        })
        // 2. �ϴ�ͼƬ

        // $.ajax({
        //     method: 'patch',
        //     url: '/my/update/avatar',
        //     data: raw,
        //     headers: {"Content-Type": "application/json"},
        //     success:function(res) {
        //         if(res.code !==0 ){
        //             return layer.msg('����ͷ��ʧ�ܣ�')
        //         }
        //         layer.msg('����ͷ��ɹ���')
        //         window.parent.getUserInfo()
        //     }
        // })
    })
})
