$(function (){
    $('ul.navbar-left').on("click","li",function(){	// 用 on 不用管先后
        $(this).addClass('active').siblings().removeClass('active')
    })
})