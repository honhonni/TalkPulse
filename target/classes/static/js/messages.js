
$(function (){
    var res={
        data: [{
            img: '/images/defualt.png',
            user_name: '洪邦伟'
        },{
            img: '/images/defualt.png',
            user_name: '俞杨'
        },{
            img: '/images/defualt.png',
            user_name: '黄康'
        },{
            img: '/images/defualt.png',
            user_name: '黄弘阳'
        },{
            img: '/images/defualt.png',
            user_name: '肖星明'
        },]
    }
    var msg = {
        data: [{
            sendByme: true,
            img: '/images/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: false,
            img: '/images/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: true,
            img: '/images/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: false,
            img: '/images/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: true,
            img: '/images/defualt.png',
            message: '我是一只羊'
        },{
            sendByme: true,
            img: '/images/defualt.png',
            message: '我是一只羊'
        }]
    }
    console.log(res)
    var htmlStr = template('tpl-messages-sender-list', res)
    $('.messages-sender-list').html(htmlStr)


    var msgStr = template('tpl-messages-list', msg)
    $('.messages-list').html(msgStr)
    resetui()
    moveToButtom()
})