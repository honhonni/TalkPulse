
$(function (){
    // 初始化消息列表
    init(-1)


    let isRecording = false;
    let recordedAudio = null;
    let mediaRecorder = null;
    let chunks = [];
    function clearVoice(){
        isRecording = false
        recordedAudio = null
        mediaRecorder = null
        chunks = []
    }


    // 初始化消息列表
    function init(id){
        $.ajax({
            method: 'get',
            url: '/chat',
            success: function (res){
                console.log(res.data)
                if(res.status != 200){
                    console.log('获取消息列表失败')
                }
                var htmlStr = template('tpl-messages-sender-list', res)
                $('.messages-sender-list').html(htmlStr)

                if(id == -1 ){
                    $('.messages-sender-list').find('button').eq(0).click()
                }
                // 跳转到指定 id 的聊天界面
                else{
                    $('.messages-sender-list').find('button').each(function (){
                        console.log($(this).attr('mid'),id,$(this).attr('mid') == id)
                        if($(this).attr('mid') == id){

                            console.log('click',id)
                            $(this).click()
                        }
                    })
                }
            }
        })
    }
    window.initMessages = init

    // 发送消息
    $('.input-group').on('click','#send',function (){
        let uid = localStorage.getItem("current_id")
        let type = $(this).attr('msgtype')
        if(uid == ''){
            return
        }
        if(type == 0) {
            // 文本消息
            let text = $('.input-group #text').val()
            if(text == ''){
                return
            }
            sendText({receiverid: uid, content: text, type: type}, '/friends/sendTextMessage')
        }else if(type == 1 ){
            // 图片消息
            var formData = new FormData();
            formData.append("receiverid", uid);
            formData.append("content", $("#file")[0].files[0]);
            formData.append("type", type);
            sendImg(formData, '/friends/sendFileMessage')
        }else if(type == 2){
            // 语音消息
            const blob = new Blob(chunks,{ type:'audio/webm' });
            const formData = new FormData();
            formData.append("receiverid", uid);
            formData.append('content',blob,'recording.webm');
            formData.append("type", type);
            sendVoice(formData,'/friends/sendFileMessage')
        }
    })
    // 发送消息
    function sendText(data, url){
        $.ajax({
            method: 'post',
            url: url ,
            data: data,
            success: function (res) {
                if(res.status != 200){
                    console.log('发送消息失败')
                }
                // console.log(res)
                let msg = `<li class="right list-group-item" >
                    <img src="${localStorage.getItem('user_photo')}"  class="head"/ >
                    <span> ${data.content} </span>
                </li>`
                $('.messages-list').append(msg)
                resetui()
                moveToButtom()
                $('.input-group #text').val("")
            }
        })
    }
    // 发送图片
    function sendImg(formData,url){
        $.ajax({
            method: "post",
            url: url,
            data: formData,
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success: function(res) {
                if(res.status != 200){
                    return console.log("发送失败")
                }
                console.log(res)
                var reads = new FileReader();
                var f = formData.content
                reads.readAsDataURL(f);
                reads.onload = function (e) {
                    $('#send-text').click()

                    let msg = `<li class="right list-group-item" >
                       <img src="${localStorage.getItem('user_photo')}"  class="head"/ >
                       <span>
                           <img src="${this.result}"/ >
                       </span>
                    </li>`
                    $('.messages-list').append(msg)
                    resetui()
                    moveToButtom()
                };
            }
        });
    }

    // 发送语音
    function sendVoice(formData,url){
        $.ajax({
            method: "post",
            url: url,
            data: formData,
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success: function(res) {
                if(res.status != 200){
                    return console.log("发送失败")
                }
                recordedAudio = URL.createObjectURL(formData.get('content'));
                $('#send-text').click()
                let msg = `<li class="right list-group-item" >
                   <img src="${localStorage.getItem('user_photo')}"  class="head"/ >
                   <span>
                       <audio src="${recordedAudio}" controls/ >
                   </span>
                </li>`
                $('.messages-list').append(msg)
                resetui()
                moveToButtom()
            }
        });
    }



    const audioPlayer = document.getElementById('audioPlayer');
    // 开始录音
    $(".voice").on('click',function() {
        if(isRecording == true) return
        clearVoice()
        navigator.mediaDevices.getUserMedia({ audio:true })
            .then(function(stream) {
                isRecording = true;
                mediaRecorder = new MediaRecorder(stream);
                mediaRecorder.addEventListener('dataavailable',handleDataAvailable);
                mediaRecorder.start();
                $('.voice span').css('color','red')
                $('.voice span').eq(1).text('结束录音')
                audioPlayer.style.display = 'none';
            })
            .catch(function(error) {
                console.error('无法访问麦克风：',error);
            });
    })
    $(".voice").on('click',function() {
        if(isRecording == false) return
        isRecording = false;
        mediaRecorder.stop();

        $('.voice span').css('color','green')
        $('.voice span').eq(1).text('重新录音')


    })

    function handleDataAvailable(event) {
        chunks.push(event.data);
        if (mediaRecorder.state === 'inactive') {
            processRecordedAudio();
        }
    }

    function processRecordedAudio() {
        const blob = new Blob(chunks,{ type:'audio/webm' });
        recordedAudio = URL.createObjectURL(blob);
        audioPlayer.src = recordedAudio;
        audioPlayer.style.display = 'inline-block';
    }
    $('.voice').bind("selectstart",function(){return false;});

    // 绑定选中文本消息事件
    $('#send-text').on('click',function (){
        $(".input-group img").hide()
        $(".voice ").hide()
        $('#send').attr("msgtype", 0)
        // 同时禁用textarea的输入功能
        $('#text').attr({
            readonly: false,
            placeholder: '请输入内容'
        })
    })
    // 绑定选中图片按钮事件
    $('#chose-msg').on('click',function (){
        $('#file').click()
    })
    // 切换图片
    $('#file').on('change',function(e){
        var fileList = e.target.files

        if(fileList.length === 0){
            return console.log("请选择图片!")
        }
        // 如果选中了图片
        var reads = new FileReader();
        var f = fileList[0]
        reads.readAsDataURL(f);
        reads.onload = function (e) {

            $(".voice ").hide()
            $('.input-group img').attr("src", this.result)
            $(".input-group img").show()
            $('#send').attr("msgtype", 1)
            // 同时禁用textarea的输入功能
            $('#text').attr({
                readonly: true,
                placeholder: ''
            })
        };

    })


    // 绑定选中录音按钮事件
    $('#send-voice').on('click',function (){
        $(".voice ").show()
        $(".input-group img").hide()
        $('#send').attr("msgtype", 2)
        // 同时禁用textarea的输入功能
        $('#text').attr({
            readonly: true,
            placeholder: ''
        })
    })



    var msg = {
        data: [{
            sendByme: true,
            img: '/images/avatar/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: false,
            img: '/images/avatar/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: true,
            img: '/images/avatar/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: false,
            img: '/images/avatar/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: true,
            img: '/images/avatar/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: true,
            img: '/images/avatar/defualt.png',
            message: '我是一只羊'
        }]
    }



    var msgStr = template('tpl-messages-list', msg)
    $('.messages-list').html(msgStr)
    resetui()
    moveToButtom()

    // 绑定消息列表点击事件
    $('.messages-sender-list').on('click','button',function (){
        let current_id = $(this).attr('mid')
        $.ajax({
            method: 'get',
            url: '/friends/search',
            data: {user_id: current_id},
            success: function (res) {
                if (res.status !== 200) {
                    return console.log('获取好友信息失败')
                }
                console.log(res.data)
                let info = template('tpl-info',res.data)
                $('.info-box').html(info)
                localStorage.setItem("current_id",current_id)
                // 获取成功后，刷新数量，

            }
        })
    })






    template.defaults.imports.cropMsg = function(msg){
        if(msg.length > 5){
            return msg.slice(0,6) + '...'
        }else{
            return msg
        }
    }
    template.defaults.imports.cropTime = function(date){
        return date.slice(11)
    }
    template.defaults.imports.setAge = function(age){
        if( age < 0){
            return "未设置年龄"
        }else{
            return age+"岁"
        }
    }


})