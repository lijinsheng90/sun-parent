/**
 * 
 */
(function($) {
	$.scmtips = {
			verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
			horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
			repositionOnResize: true,           // re-centers the dialog on window resize
			defaultWidth:"300px",
			timeOutObj:null,
			random_id_:null,
			warn: function(message,width,timeOut ,hOffset) {
				$.scmtips.random_id_=Math.round(Math.random()*1000);
				$.scmtips._show(message,width, 'warn',timeOut,hOffset);
			},
			success: function(message,width,timeOut,hOffset) {
				$.scmtips.random_id_=Math.round(Math.random()*1000);
				$.scmtips._show(message,width, 'success',timeOut,hOffset);
			},
			error: function(message,width,timeOut,hOffset) {
				$.scmtips.random_id_=Math.round(Math.random()*1000);
				$.scmtips._show( message,width,'error',timeOut,hOffset);
			},
			_show: function(msg,width,type,timeOut,hOffset) {
				if(hOffset != null && hOffset != ''){
					$.scmtips.horizontalOffset = hOffset;
				}
				//clear last timeout object
				/*if($.scmtips.timeOutObj){
					clearTimeout($.scmtips.timeOutObj);
				}*/
				if(!width){
					width = $.scmtips.defaultWidth;
				}else{
					width = width + "px";
				}
				if(!timeOut){
					timeOut = 3000;
				}
				//$.scmtips._hide();
				$("BODY").append('<div id="scmtip_container_'+$.scmtips.random_id_+'"><div id="scmtip_icon_'+$.scmtips.random_id_+'"><ul><li id="scmtip_content_'+$.scmtips.random_id_+'"></li></ul></div></div>');
				$("BODY").append('<div id="scmtip_overlay_'+$.scmtips.random_id_+'" style="display:none"><iframe id="scmtip_overlay_frame_'+$.scmtips.random_id_+'" frameborder="0" hspace="0" src="" style="width:100%;height:100%;"/></div>');
				// IE6 Fix
				var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'absolute'; 
				$("#scmtip_container_"+$.scmtips.random_id_).addClass("scmtips_window");
				$("#scmtip_container_"+$.scmtips.random_id_).css({
					position: pos,
					zIndex: 999339999,
					padding: 0,
					width:width,
					margin: 0
				});
				
				$.scmtips._maintainPosition(true);
				
				switch( type ) {
					case 'warn':
						$("#scmtip_content_"+$.scmtips.random_id_).addClass("text_ts");
						$("#scmtip_icon_"+$.scmtips.random_id_).addClass("scmtip_icon_ts");
					break;
					case 'success':
						$("#scmtip_content_"+$.scmtips.random_id_).addClass("text_yes");
						$("#scmtip_icon_"+$.scmtips.random_id_).addClass("scmtip_icon_yes");
					break;
					case 'error':
						$("#scmtip_content_"+$.scmtips.random_id_).addClass("text_wrong");
						$("#scmtip_icon_"+$.scmtips.random_id_).addClass("scmtip_icon_wrong");
					break;
				}
				$("#scmtip_content_"+$.scmtips.random_id_).text(msg);
				$("#scmtip_content_"+$.scmtips.random_id_).html( $("#scmtip_content_"+$.scmtips.random_id_).text().replace(/\n/g, '<br />') );
				
				$("#scmtip_container_"+$.scmtips.random_id_).css({
					minWidth: $("#scmtip_container_"+$.scmtips.random_id_).outerWidth(),
					maxWidth: $("#scmtip_container_"+$.scmtips.random_id_).outerWidth()
				});
				var container = $("#scmtip_container_"+$.scmtips.random_id_);
				var width = container.width() + 2;
				var height = container.height() + 2;
				$("#scmtip_overlay_"+$.scmtips.random_id_).css({
					position: pos,
					zIndex: 9999998,
					minWidth: $("#scmtip_container_"+$.scmtips.random_id_).outerWidth(),
					maxWidth: $("#scmtip_container_"+$.scmtips.random_id_).outerWidth(),
					width: width + 'px',
					height: height + 'px'
				});
				$.scmtips._reposition();
				//hide after 3s
				$.scmtips._hide($.scmtips.random_id_,timeOut);
				//hide when click
				/*$("#scmtip_container_"+$.scmtips.random_id_).bind("click",function(){
					$.scmtips._hide();
				});*/
			},
			_hide: function(random_id_,timeOut_) {
				setTimeout(function(){
					$("#scmtip_container_"+random_id_).slideUp("slow", function() {//slide up
					      $(this).remove();//then remove from the DOM
					});
					$("#scmtip_overlay_"+random_id_).slideUp("slow", function() {//slide up
					      $(this).remove();//then remove from the DOM
					 });
					$.scmtips._maintainPosition(false);
				},timeOut_);
			},
			_reposition: function() {
				var top = (($(window).height() / 2) - ($("#scmtip_container_"+$.scmtips.random_id_).outerHeight() / 2)) + $.scmtips.verticalOffset;
				var left = (($(window).width() / 2) - ($("#scmtip_container_"+$.scmtips.random_id_).outerWidth() / 2)) + $.scmtips.horizontalOffset;
				if( top < 0 ) top = 0;
				if( left < 0 ) left = 0;
				// IE6 fix
				/*if($.browser.mozilla){
					top = top + $(window).scrollTop();
				}
				if( $.browser.msie && parseInt($.browser.version) <= 6 )*/ top = top + $(window).scrollTop();
				$("#scmtip_container_"+$.scmtips.random_id_).css({
					top: top + 'px',
					left: left + 'px'
				});
				$("#scmtip_overlay_"+$.scmtips.random_id_).css({
					top: top + 'px',
					left: left + 'px'
				});
				
			},
			_maintainPosition: function(status) {
				if( $.scmtips.repositionOnResize ) {
					switch(status) {
						case true:
							$(window).bind('resize', $.scmtips._reposition);
							$(window).bind('scroll', $.scmtips._reposition);
						break;
						case false:
							$(window).unbind('resize', $.scmtips._reposition);
							$(window).unbind('scroll', $.scmtips._reposition);
						break;
					}
				}
			}
	};
	// Shortuct functions
	scmWarn = function(message,width,timeOut,hOffset) {
		$.scmtips.warn(message,width,timeOut,hOffset);
	};
	scmSuccess = function(message,width,timeOut,hOffset) {
		$.scmtips.success(message,width,timeOut,hOffset);
	};
	scmError = function(message,width,timeOut,hOffset) {
		$.scmtips.error(message,width,timeOut,hOffset);
	};
})(jQuery);