$(function (){
    $('ul.navbar-left').on("click","li",function(){	// �� on ���ù��Ⱥ�
        $(this).addClass('active').siblings().removeClass('active')
    })
})