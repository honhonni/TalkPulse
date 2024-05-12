$(function(){
	
	// 设置滚动条数据
	var $main = $('.messages')
	var $list = $('.messages-list')
	var $drager = $('.drager')
	// $drager.hide()
	var $mainh = $main.outerHeight(false)
	var $listh = $list.outerHeight(false)
	var $rate = $mainh / $listh
	var $dragh = $mainh * $rate
	// $dragh 的顶部距离
	var $top = 0
	$drager.css('height',$dragh)
	
	$drager.draggable({
		containment: 'parent',
		drag:function(ev,ui){
			$top = ui.position.top
			$list.css('top',- $top / $rate)
		}
	})
	// 窗口变化，重新设置
	$(window).resize(function(){
		resetui()
	})
	var flag = false
	$main.on('mousewheel DOMMouseScroll',function(e){
		// console.log(e.originalEvent.wheelDelta)
		var delta = e.originalEvent.wheelDelta
		if(flag || $listh < $mainh){
			// 正在滚动ing  或者  list比main小
			return
		}
		flag = true;
		setTimeout(function(){
			flag = false
			$drager.hide()
		},300)
		$drager.show()
		if(delta > 0){
			// 向上滚动
			$top -= 60
			if($top < 0){
				$top = 0
			}
		}else{
			// 向下滚动
			$top += 60
			if($top > $mainh - $dragh){
				$top = $mainh - $dragh
			}
		}
		$drager.animate({'top': $top},200)
		$list.animate({'top': -$top / $rate},200)
	})
	
	function resetui(){
		$mainh = $main.outerHeight(false)
		$listh = $list.outerHeight(false)
		$rate = $mainh / $listh
		$dragh = $mainh * $rate
		// $drager高度设置
		$drager.css('height',$dragh)
		if($listh < $mainh){
			$top = 0
		}else{
			$top = $mainh - $dragh
		}
		$drager.css('top',$top)
		$list.css('top',- $top / $rate)
	}
	window.resetui = resetui
})