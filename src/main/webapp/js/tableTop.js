function dataTable(ele,tr,h){
			var leftWidth=0;
			var rightWidth=0;
			var height=h;
			var fixedColumns=tr;
			var fixedRows=1;
			var headRow = $(ele).find("tr:lt("+fixedRows+")").clone();
			var headRowLeft = $(headRow).clone();
			var headRowRight = $(headRow).clone();
			var bodyRow = $(ele).find("tr:gt("+(fixedRows-1)+")").clone();
			var bodyRowLeft = $(bodyRow).clone();
			var bodyRowRight = $(bodyRow).clone();
			var leftTd = $(ele).find("tr:eq(1)").find("td:lt("+fixedColumns+")");
			var rightTd = $(ele).find("tr:eq(1)").find("td:gt("+(fixedColumns-1)+")");
			leftTd.each(function(){
				leftWidth += $(this).outerWidth();
			});
			rightTd.each(function(){
				rightWidth += $(this).outerWidth();
			});
			$(headRowLeft).each(function(index){
				$(this).find("th:gt("+(fixedColumns-1)+")").remove();
			});
			$(headRowRight).each(function(index){
				$(this).find("th:lt("+(fixedColumns)+")").remove();
			});

			$(bodyRowLeft).each(function(index){
				$(this).find("td:gt("+(fixedColumns-1)+")").remove();
			});
			$(bodyRowRight).each(function(index){
				$(this).find("td:lt("+(fixedColumns)+")").remove();
			});
            var boxW = $("body").width()-40,
				minW= boxW-leftWidth,
				appNum = 16;
			navigator.userAgent.indexOf("Mac OS X")>0 && (appNum = 0);

			var appendFixed = '<div   class="freeze" style="position: relative; overflow: hidden; width:'+(boxW+appNum)+'px; margin-top: 30px" >';
			appendFixed+='	<div class="headDiv" style="white-space: normal;position: relative; overflow: hidden; height: 33px;width:'+boxW+'px;">';
			appendFixed+='		<div style="position: absolute; width:'+leftWidth+'px; "><table style="width:'+leftWidth+'px"><thead class="headRowLeft"></thead></table></div>';
			appendFixed+='		<div id="wrapper" class="headDivRight" style="overflow: hidden; height: 33px;position: relative;left:'+leftWidth+'px;width:'+minW+'px; "><div class="scrollBox"><table style="width: '+rightWidth+'px"><thead  class="headRowRight"></thead></table></div></div>';
			appendFixed+='	</div>';
			appendFixed+='	<div class="bodyDiv" style="position: relative; white-space: normal;height: '+height+'px; ">';
			appendFixed+='		<div class="bodyDivLeft"  style="position: absolute;overflow:auto;overflow-y:hidden; height: '+height+'px"><table style="position:relative;width:'+leftWidth+'px"><tbody class="bodyRowLeft"></tbody></table></div>';
			appendFixed+='		<div class="bodyDivRight" style="position: relative; overflow:hidden; overflow-y:scroll;width:'+minW+'px; height: '+height+'px;left:'+leftWidth+'px; "><div class="scrollBox"><table style="width: '+rightWidth+'px"><tbody class="bodyRowRight"></tbody></table></div></div>';
			appendFixed+='	</div>';
			appendFixed+='</div>';
			
			var $appendFixedObj = $(appendFixed);

			$appendFixedObj.find(".headRowLeft").append(headRowLeft);
			$appendFixedObj.find(".headRowRight").append(headRowRight);
			$appendFixedObj.find(".bodyRowLeft").append(bodyRowLeft);
			$appendFixedObj.find(".bodyRowRight").append(bodyRowRight);


			$appendFixedObj.insertAfter($(ele));
			$(ele).remove();
			enclose($appendFixedObj.find(".bodyDivLeft")[0],$appendFixedObj.find(".bodyDivLeft").find("table")[0],$appendFixedObj);
			myScroll("wrapper");
		}

		function myScroll(obj){
			var wrapper = document.getElementById(obj),
					scrollBox = wrapper.getElementsByTagName("div")[0],
					scrollber = document.createElement("div"),
					div = document.createElement("div"),
					moveX= 0,
					oWidth = 0,
					boxWidth = 0,
					scrollBoxWidth = scrollBox.offsetWidth,
					pageX = wrapper.offsetLeft,
					width = wrapper.offsetWidth,
					event = function(e){
						var eve = e || window.event,
								layerX = eve.offsetX ? eve.offsetX : eve.layerX;
						document.onmousemove = function(event){
							var e = event || window.event,
									x = e.clientX ? e.clientX : e.pageX;
							moveX = x -pageX -layerX;
							oWidth = width -berWidth;
							boxWidth = scrollBox.offsetWidth - width;

								moveX <= 0 && (moveX =0);
								moveX > oWidth && (moveX = oWidth);
								div.style.left = moveX+"px";
								scrollBox.style.left = -(moveX/ oWidth* boxWidth) +"px";
								$(".bodyDivRight div")[0].style.left = -(moveX/ oWidth* boxWidth) +"px";

						};
						document.onmouseup=function(){
							document.onmousemove=null;
							document.onmouseup=null;

							document.getElementsByTagName('body')[0].onselectstart=function(){
								return true;
							};
						};
						document.getElementsByTagName('body')[0].onselectstart=function(){
							return false;
						}

					};
			if(scrollBoxWidth >= width){
				scrollber.className = "scrollBer";
				scrollber.style.width = wrapper.offsetWidth+"px";
				div.className = "scrolline";
				scrollber.appendChild(div);
				wrapper.appendChild(scrollber);
				var berWidth = div.offsetWidth;
				div.onmousedown = event;
			}
		}


		function enclose(frame,box,$appendFixedObj){

			var isMacWebkit=(navigator.userAgent.indexOf('Macintosh')!==-1&& navigator.userAgent.indexOf('WebKit')!==-1);
			var isFirefox=(navigator.userAgent.indexOf('Gecko')!==-1);
			var contentY=frame.offsetTop,
				i =0;

				frame.onwheel = wheelHandler;   //未来浏览器
				frame.onmousewheel = wheelHandler;	//大多数当前浏览器

				if (isFirefox) {   //仅firefox
					frame.addEventListener('DOMMouseScroll', wheelHandler, false);
				}
			$appendFixedObj.find(".bodyDivRight").scroll(function(){
				var top = $(this).scrollTop();
				i=top;
				$appendFixedObj.find(".bodyDivLeft").scrollTop(top);
			});
			function wheelHandler(event){
				var e=event||window.event;
				var deltaY=e.deltaY*-30|| e.wheelDeltaY/4|| (e.wheelDeltaY===undefined&& e.wheelDelta/4)|| e.detail*-10|| 0;
				isMacWebkit && (deltaY/=30);

				if(isFirefox&&e.type!=='DOMMouseScroll'){
					frame.removeEventListener('DOMMouseScroll',wheelHandler,false);
				}

					contentY=box.offsetTop;
					deltaY < 0 ? i+=8 : i-=8;
					contentY+=i;
					contentY >= box.offsetHeight && (i = box.offsetHeight);
					contentY < 0 && (i = 0);
					$appendFixedObj.find(".bodyRowLeft").scrollTop(contentY);
					$appendFixedObj.find(".bodyDivRight").scrollTop(contentY);
				e.preventDefault &&	e.preventDefault();
				e.stopPropagation && e.stopPropagation();
				e.cancelBubble=true;
				e.returnValue=false;
				return false;
				}
		}
