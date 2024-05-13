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
	var hide = undefined
	var linear = undefined
	var cnt = 0
	$main.on('mousewheel DOMMouseScroll',function(e) {
		// console.log(e.originalEvent.wheelDelta)
		var delta = e.originalEvent.wheelDelta
		cnt++
		if (hide != undefined) {
			clearTimeout(hide)
		}
		if (linear != undefined) {
			clearTimeout(linear)
		}
		if ($listh < $mainh) {
			// 正在滚动ing  或者  list比main小
			return
		}
		hide = setTimeout(function () {
			$drager.hide()
		}, 1500)

		$drager.show()
		if (delta > 0) {
			// 向上滚动
			$top -= 6*cnt
			if ($top < 0) {
				$top = 0
			}
		} else {
			// 向下滚动
			$top += 6*cnt
			if ($top > $mainh - $dragh) {
				$top = $mainh - $dragh
			}
		}
		// $drager.stop().animate({'top': $top}, 100, linear)
		// $list.stop().animate({'top': -$top / $rate}, 100, linear)

		$drager.stop().animate({'top': $top}, 500)
		$list.stop().animate({'top': -$top / $rate}, 500)
		linear = setTimeout(function () {
			cnt = 0
			// $drager.stop().animate({'top': $top}, 500)
			// $list.stop().animate({'top': -$top / $rate}, 500)
		}, 60)
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