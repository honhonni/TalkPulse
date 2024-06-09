
$(function (){
    let msg_count = 0

    let messages = []
    let currentname = ''
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
                console.log('消息列表')
                console.log(res)
                if(res.status != 200){
                    console.log('获取消息列表失败')
                }
                console.log('消息列表')
                console.log(res.data)
                var htmlStr = template('tpl-messages-sender-list', res)
                $('.messages-sender-list').html(htmlStr)

                // 设置导航栏消息计数
                msg_count = 0
                $.each(res.data, function (index, value){
                    msg_count += value.no_read
                    console.log(value.no_read)
                })
                console.log(msg_count)
                window.parent.setMessagesCount(msg_count)


                // 初始打开一个窗口
                if(id == -1 ){
                    $('.messages-sender-list').find('button').eq(0).click()
                }
                // 跳转到指定 id 的聊天界面
                else{
                    $('.messages-sender-list').find('button').each(function (){
                        if($(this).attr('mid') == id){
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
        let id = localStorage.getItem("current_id")
        let type = $(this).attr('msgtype')
        if(id == ''){
            return
        }
        if(type == 0) {
            // 文本消息
            let text = $('.input-group #text').val()
            if(text == ''){
                return
            }
            if(id.toString().length <=6){
                sendText({receiverid: id, content: text, type: type}, '/friends/sendTextMessage')
            }else{
                sendText({gid: id, content: text, type: type}, '/group/sendTextMessage')
            }
        }else if(type == 1 ){
            // 图片消息
            const formData = new FormData();

            if(id.toString().length <=6){
                formData.append("receiverid", id);
                formData.append("content", $("#file")[0].files[0]);
                formData.append("type", type);
                sendImg(formData, '/friends/sendFileMessage')
            }else{
                formData.append("gid", id);
                formData.append("content", $("#file")[0].files[0]);
                formData.append("type", type);
                sendImg(formData, '/group/sendFileMessage')
            }
        }else if(type == 2){
            // 语音消息
            const blob = new Blob(chunks,{ type:'audio/webm' });
            const formData = new FormData();


            if(id.toString().length <=6){
                formData.append("receiverid", id);
                formData.append('content',blob,'recording.webm');
                formData.append("type", type);
                sendVoice(formData, '/friends/sendFileMessage')
            }else{
                formData.append("gid", id);
                formData.append('content',blob,'recording.webm');
                formData.append("type", type);
                sendVoice(formData,'/group/sendFileMessage')
            }
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
                let msg = `<li class="right list-group-item clearfix" >
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
                var reads = new FileReader();
                var f = formData.get('content')
                reads.readAsDataURL(f);
                reads.onload = function (e) {
                    $('#send-text').click()

                    let msg = `<li class="right list-group-item clearfix" >
                       <img src="${localStorage.getItem('user_photo')}"  class="head"/ >
                       <span>
                           <img src="${this.result}"/ >
                       </span>
                    </li>`
                    $('.messages-list').append(msg)
                    resetui()
                    moveToButtom()
                };
            },
            error: function (XMLHttpRequest, textStatus, errorThrown){
                if( XMLHttpRequest.status === 413){
                    $('#send-text').click()
                    $('#text').attr('placeholder','图片大小超过限制（1M）')
                }
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

                $('#send-text').click()

                recordedAudio = URL.createObjectURL(formData.get('content'));
                let msg = `<li class="right list-group-item clearfix" >
                   <img src="${localStorage.getItem('user_photo')}"  class="head"/ >
                   <span>
                       <audio src="${recordedAudio}" controls/ ></audio>
                   </span>
                </li>`
                $('.messages-list').append(msg)
                $('.voice').html(`
                    <div class="voice-btn">
                        <span class="glyphicon glyphicon-play-circle"></span>
                        <span>开始录音</span>
                    </div>
                `)
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





    // 绑定消息列表点击事件
    $('.messages-sender-list').on('click','button',function (){
        let current_id = $(this).attr('mid')

        let that = this
        // 用户
        if(current_id.toString().length <=6){
            $.ajax({
                method: 'get',
                url: '/friends/search',
                data: {user_id: current_id},
                success: function (res) {
                    if (res.status !== 200) {
                        return console.log('获取好友信息失败')
                    }

                    currentname = res.data.data.user_name

                    let info = template('tpl-friend-info',res.data)
                    $('.info-box').html(info)
                    localStorage.setItem("current_id",current_id)
                    // 获取成功后，刷新数量，
                    $.ajax({
                        method: 'post',
                        url: '/record/read',
                        data: {uid: current_id},
                        success: function (res){
                            if(res.status != 200){
                                return console.log('设置已读失败')
                            }
                            // 设置已读成功

                            msg_count -= Number($(that).find('.box .row1 span').eq(1).attr('count'))
                            $(that).find('.box .row1 span').eq(1).attr('count','0').html(0).hide()
                            window.parent.setMessagesCount(msg_count)
                        }
                    })


                    // 获取聊天记录
                    $.ajax({
                        methods: 'get',
                        url: '/friends/getPrivateMessages',
                        data: {user_id: current_id},
                        success: function (msg){
                            if(res.status != 200){
                                return console.log('获取聊天记录失败')
                            }
                            msg.friend_photo = res.data.data.user_photo
                            msg.my_id = localStorage.getItem("user_id")

                            console.log(res.data.data.user_photo)
                            msg.my_photo = localStorage.getItem('user_photo')

                            var msgStr = template('tpl-messages-list', msg)
                            $('.messages-list').html(msgStr)

                            // 监听图片加载完成事件

                            var images = $('.messages-list .messages-photo');
                            var loadedImages = 0;

                            images.on('load',function() {
                                loadedImages++;
                                if (loadedImages === images.length) {
                                    // 所有图片都加载完成了
                                    resetui()
                                    moveToButtom()
                                }
                            });
                            if(images.length === 0){
                                resetui()
                                moveToButtom()
                            }
                            messages = msg.data
                        }
                    })
                }
            })

        }
        // 群聊
        else{
            $.ajax({
                method: 'get',
                url: '/group/getGroupInfo',
                data: {group_Id: current_id},
                success: function (res) {
                    if (res.status !== 200) {
                        return console.log('获取群聊信息失败')
                    }

                    currentname = res.data.group.group_name

                    let info = template('tpl-group-info',res.data)
                    $('.info-box').html(info)
                    localStorage.setItem("current_id",current_id)
                    // 获取成功后，刷新数量，
                    $.ajax({
                        method: 'post',
                        url: '/grouprecord/read',
                        data: {gid: current_id},
                        success: function (res){
                            if(res.status != 200){
                                return console.log('设置已读失败')
                            }
                            // 设置已读成功

                            msg_count -= Number($(that).find('.box .row1 span').eq(1).attr('count'))
                            $(that).find('.box .row1 span').eq(1).attr('count','0').html(0).hide()
                            window.parent.setMessagesCount(msg_count)
                        }
                    })


                    // 获取聊天记录
                    $.ajax({
                        methods: 'get',
                        url: '/friends/getGroupMessages',
                        data: {group_id: current_id},
                        success: function (msg){
                            if(res.status != 200){
                                return console.log('获取聊天记录失败')
                            }
                            console.log('群聊消息')
                            console.log(msg)
                            msg.my_id = localStorage.getItem("user_id")
                            msg.my_photo = localStorage.getItem('user_photo')

                            console.log(msg.my_id == msg.data[0].grouprec)
                            var msgStr = template('tpl-messages-list', msg)
                            $('.messages-list').html(msgStr)

                            // 监听图片加载完成事件

                            var images = $('.messages-list .messages-photo');
                            var loadedImages = 0;

                            images.on('load',function() {
                                loadedImages++;
                                if (loadedImages === images.length) {
                                    // 所有图片都加载完成了
                                    resetui()
                                    moveToButtom()
                                }
                            });
                            if(images.length === 0){
                                resetui()
                                moveToButtom()
                            }
                            messages = msg.data
                        }
                    })
                }
            })

        }
    })


    // 下载聊天记录
    $('.info-box').on('click', '#download-messages-group,#download-messages-private', function (){
        console.log(messages)
        let data = {}
        data.current_name = currentname
        data.current_id = localStorage.getItem('current_id')
        if( $(this).attr('id') === 'download-messages-group')
            data.type = 'group'
        else
            data.type = 'private'
        data.messages = []
        for(let i =0 ;i<messages.length;i++){
            data.messages.push({
                sender_name: messages[i].user_name ? messages[i].user_name : ( messages[i].record_senderid === localStorage.getItem('current_id')) ? currentname : localStorage.getItem('user_name'),
                sender_id: messages[i].grouprecord_senderid ? messages[i].grouprecord_senderid : messages[i].record_senderid,
                content: messages[i].grouprecord_content ? messages[i].grouprecord_content: messages[i].record_content,
                time: messages[i].grouprecord_time ? messages[i].grouprecord_time : messages[i].record_time

            })
        }
        // 要保存的字符串, 需要先将数据转成字符串
        const stringData = JSON.stringify(data)
        // dada 表示要转换的字符串数据，type 表示要转换的数据格式
        const blob = new Blob([stringData], {
            type: 'application/json'
        })
        // 根据 blob生成 url链接
        const objectURL = URL.createObjectURL(blob)

        // 创建一个 a 标签Tag
        const aTag = document.createElement('a')
        // 设置文件的下载地址
        aTag.href = objectURL
        // 设置保存后的文件名称
        aTag.download = currentname + ".json"
        // 给 a 标签添加点击事件
        aTag.click()
        // 释放一个之前已经存在的、通过调用 URL.createObjectURL() 创建的 URL 对象。
        // 当你结束使用某个 URL 对象之后，应该通过调用这个方法来让浏览器知道不用在内存中继续保留对这个文件的引用了。
        URL.revokeObjectURL(objectURL)

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