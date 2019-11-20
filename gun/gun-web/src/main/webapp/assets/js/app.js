var App = function () {
    
    // IE mode
    var isIE8 = false;
    var isIE9 = false;
    var isIE10 = false;
    var responsiveHandlers = [];
	
		//{"days": ["日", "一", "二", "三", "四", "五", "六","日"],"daysShort": ["日", "一", "二", "三", "四", "五", "六","日"],"daysMin": ["日", "一", "二", "三", "四", "五", "六","日"],"months": ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],"monthsShort": ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],"today": "今日"}
	var defaultDatePickerDateSetting = JSON.parse('{\"days\": [\"\u65E5\", \"\u4E00\", \"\u4E8C\", \"\u4E09\", \"\u56DB\", \"\u4E94\", \"\u516D\",\"\u65E5\"],\"daysShort\": [\"\u65E5\", \"\u4E00\", \"\u4E8C\", \"\u4E09\", \"\u56DB\", \"\u4E94\", \"\u516D\",\"\u65E5\"],\"daysMin\": [\"\u65E5\", \"\u4E00\", \"\u4E8C\", \"\u4E09\", \"\u56DB\", \"\u4E94\", \"\u516D\",\"\u65E5\"],\"months\": [\"1\u6708\",\"2\u6708\",\"3\u6708\",\"4\u6708\",\"5\u6708\",\"6\u6708\",\"7\u6708\",\"8\u6708\",\"9\u6708\",\"10\u6708\",\"11\u6708\",\"12\u6708\"],\"monthsShort\": [\"1\u6708\",\"2\u6708\",\"3\u6708\",\"4\u6708\",\"5\u6708\",\"6\u6708\",\"7\u6708\",\"8\u6708\",\"9\u6708\",\"10\u6708\",\"11\u6708\",\"12\u6708\"],\"today\": \"\u4ECA\u65E5\"}'),
		defaultToggleButtonWidth = 70,
		//{"enabled":"開","disabled":"關"}
		defaultToggleButtonWording = JSON.parse('{\"enabled\":\"\u958B\",\"disabled\":\"\u95DC\"}');

    //* BEGIN:CORE HANDLERS *//
    // this function handles responsive layout on screen size resize or mobile device rotate.
    var handleResponsive = function () {
        //if (jQuery.browser.msie && jQuery.browser.version.substr(0, 1) == 8) {
        if (!!navigator.userAgent.match(/MSIE 8/)){
            isIE8 = true; // detect IE8 version
        }
                
        //if (jQuery.browser.msie && jQuery.browser.version.substr(0, 1) == 9) {
        if (!!navigator.userAgent.match(/MSIE 9/)) {
            isIE9 = true; // detect IE9 version
        }

        var isIE10 = !! navigator.userAgent.match(/MSIE 10/);

        if (isIE10) {
            jQuery('html').addClass('ie10'); // detect IE10 version
        }

        // loops all page elements with "responsive" class and applies classes for tablet mode
        // For metornic  1280px or less set as tablet mode to display the content properly
        var _handleTabletElements = function () {
            if ($(window).width() <= 1280) {
                $(".responsive").each(function () {
                    var forTablet = $(this).attr('data-tablet');
                    var forDesktop = $(this).attr('data-desktop');
                    if (forTablet) {
                        $(this).removeClass(forDesktop);
                        $(this).addClass(forTablet);
                    }
                });
            }
        }

        // loops all page elements with "responsive" class and applied classes for desktop mode
        // For metornic  higher 1280px set as desktop mode to display the content properly
        var _handleDesktopElements = function () {
            if ($(window).width() > 1280) {
                $(".responsive").each(function () {
                    var forTablet = $(this).attr('data-tablet');
                    var forDesktop = $(this).attr('data-desktop');
                    if (forTablet) {
                        $(this).removeClass(forTablet);
                        $(this).addClass(forDesktop);
                    }
                });
            }
        }

        // handle all elements which require to re-initialize on screen width change(on resize or on rotate mobile device)
        var handleElements = function () {
            // reinitialize core elements
            handleTooltip();
            _handleSidebar();
            _handleTabletElements();
            _handleDesktopElements();
            handleSidebarAndContentHeight();

            // reinitialize other subscribed elements
            for (var i in responsiveHandlers) {
                var each = responsiveHandlers[i];
                each.call();
            }
        }

        // handles responsive breakpoints.
        $(window).setBreakpoints({
            breakpoints: [320, 480, 768, 900, 1024, 1280]
        });

        $(window).bind('exitBreakpoint320', function () {
            handleElements();
        });
        $(window).bind('enterBreakpoint320', function () {
            handleElements();
        });

        $(window).bind('exitBreakpoint480', function () {
            handleElements();
        });
        $(window).bind('enterBreakpoint480', function () {
            handleElements();
        });

        $(window).bind('exitBreakpoint768', function () {
            handleElements();
        });
        $(window).bind('enterBreakpoint768', function () {
            handleElements();
        });

        $(window).bind('exitBreakpoint900', function () {
            handleElements();
        });
        $(window).bind('enterBreakpoint900', function () {
            handleElements();
        });

        $(window).bind('exitBreakpoint1024', function () {
            handleElements();
        });
        $(window).bind('enterBreakpoint1024', function () {
            handleElements();
        });

        $(window).bind('exitBreakpoint1280', function () {
            handleElements();
        });
        $(window).bind('enterBreakpoint1280', function () {
            handleElements();
        });
    }

    var handleSidebarAndContentHeight = function () {
        var content = $('#body');
        var sidebar = $('#sidebar');

        if (!content.attr("data-height")) {
            content.attr("data-height", content.height());
        }

        if (sidebar.height() > content.height()) {
            content.css("min-height", sidebar.height() + 20);
        } else {
            content.css("min-height", content.attr("data-height"));
        }
    }
	

    var handlePortletSortable = function () {
        if (!jQuery().sortable) {
            return;
        }
        $(".sortable").sortable({
            connectWith: '.sortable',
            iframeFix: false,
            items: 'div.widget',
            opacity: 0.8,
            helper: 'original',
            revert: true,
            forceHelperSize: true,
            placeholder: 'sortable-box-placeholder round-all',
            forcePlaceholderSize: true,
            tolerance: 'pointer'
        });

    }

    var handleMainMenu = function () {
        jQuery('#sidebar .has-sub > a').click(function () {
            var last = jQuery('.has-sub.open', $('#sidebar'));
            last.removeClass("open");
            jQuery('.arrow', last).removeClass("open");
            jQuery('.sub', last).slideUp(200);
            var sub = jQuery(this).next();
            if (sub.is(":visible")) {
                jQuery('.arrow', jQuery(this)).removeClass("open");
                jQuery(this).parent().removeClass("open");
                sub.slideUp(200);
            } else {
                jQuery('.arrow', jQuery(this)).addClass("open");
                jQuery(this).parent().addClass("open");
                sub.slideDown(200);
            }
        });
    }

    var handleMobileMenu = function () {
        jQuery('#mobile-pane .has-sub > a').click(function () {
            var last = jQuery('.has-sub.mopen', $('#mobile-pane'));
            last.removeClass("mopen");
            jQuery('.arrow', last).removeClass("mopen");
            jQuery('.sub', last).slideUp(200);
            var sub = jQuery(this).next();
            if (sub.is(":visible")) {
                jQuery('.arrow', jQuery(this)).removeClass("mopen");
                jQuery(this).parent().removeClass("mopen");
                sub.slideUp(200);
            } else {
                jQuery('.arrow', jQuery(this)).addClass("mopen");
                jQuery(this).parent().addClass("mopen");
                sub.slideDown(200);
            }
        });
    }

    var handleWidgetTools = function () {
        jQuery('.widget .tools .icon-remove').click(function () {
            jQuery(this).parents(".widget").parent().remove();
        });

        jQuery('.widget .tools .icon-refresh').click(function () {
            var el = jQuery(this).parents(".widget");
            App.blockUI(el);
            window.setTimeout(function () {
                App.unblockUI(el);
            }, 1000);
        });

        jQuery('.widget .tools .icon-chevron-up, .widget .tools .icon-chevron-down').click(function () {
            var el = jQuery(this).parents(".widget").children(".widget-body");
            if (jQuery(this).hasClass("icon-chevron-up")) {
                jQuery(this).removeClass("icon-chevron-up").addClass("icon-chevron-down");
                el.slideUp(200);
            } else {
                jQuery(this).removeClass("icon-chevron-down").addClass("icon-chevron-up");
                el.slideDown(200);
            }
        });
    }

    var handleFixInputPlaceholderForIE = function () {
        //fix html5 placeholder attribute for ie7 & ie8
        if ($.support.boxModel && $.support.leadingWhitespace) { // ie7&ie8
            jQuery('input[placeholder], textarea[placeholder]').each(function () {

                var input = jQuery(this);

                jQuery(input).val(input.attr('placeholder'));

                jQuery(input).focus(function () {
                    if (input.val() == input.attr('placeholder')) {
                        input.val('');
                    }
                });

                jQuery(input).blur(function () {
                    if (input.val() == '' || input.val() == input.attr('placeholder')) {
                        input.val(input.attr('placeholder'));
                    }
                });
            });
        }
    }

    var handleTooltip = function () {
        jQuery('.tooltips').tooltip();
    }

    var handlePopover = function () {
        jQuery('.popovers').popover();
    }

    var handleToggleButtons = function () {
        if (!jQuery().toggleButtons) {
            return;
        }
        $('.basic-toggle-button').toggleButtons({
            width: defaultToggleButtonWidth,
            }
		);
        $('.text-toggle-button').toggleButtons({
            width: defaultToggleButtonWidth,
            label: defaultToggleButtonWording
        });
    }
	

    var handleUniform = function () {
        if (!jQuery().uniform) {
            return;
        }
        if (test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle)")) {
            test.uniform();
        }
    }

    var handleAccordions = function () {
        $(".accordion").collapse().height('auto');
    }

    var handleScrollers = function () {
        if (!jQuery().slimScroll) {
            return;
        }

        $('.scroller').each(function () {
            $(this).slimScroll({
                //start: $('.blah:eq(1)'),
                height: $(this).attr("data-height"),
                alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
                railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
                disableFadeOut: true,
				distance : '0px'
            });
        });
    }

    var handleFancyBox = function () {
        if (!jQuery().fancybox) {
            return;
        }

        if (jQuery(".fancybox-button").size() > 0) {
            jQuery(".fancybox-button").fancybox({
                groupAttr: 'data-rel',
                prevEffect: 'none',
                nextEffect: 'none',
                closeBtn: true,
                helpers: {
                    title: {
                        type: 'inside'
                    }
                }
            });
        }
    }

    var handleGoTop = function () {
			$(window).scroll(function() {
				if ($(this).scrollTop() > 10) {
					$('.go-top').fadeIn(200);
				} else {
					$('.go-top').fadeOut(200);
				}
			});
			// animate the scroll to top
			$('.go-top').click(function(event) {
				event.preventDefault();
				$('html, body').animate({scrollTop: 0}, 300);
			})
    }
	
    var handleSidebarToggler = function () {
    $("#sidebar-trigger").click(
			function(){
				if ($(this).hasClass("sidebar-toggler")){	
				$("#body").css("margin-left","0"); 
				$("#sidebar div.profile-box").hide(); 
				$("#sidebar div.profile-function").hide(); 
				$("#sidebar div.partner-identity").hide(); 
				$("#sidebar div.navbar").hide(); 
				$("#sidebar div.tabbable-sidebar").hide(); 
				$("#sidebar div.sidebar-section").hide(); 
				$(this).removeClass("sidebar-toggler").addClass("sidebar-toggler-closed");
				} else {
				$("#body").css("margin-left","234px");  
				$("#sidebar div.profile-box").show(); 
				$("#sidebar div.profile-function").show(); 
				$("#sidebar div.partner-identity").show(); 
				$("#sidebar div.navbar").show(); 
				$("#sidebar div.tabbable-sidebar").show(); 
				$("#sidebar div.sidebar-section").show(); 
				$(this).removeClass("sidebar-toggler-closed").addClass("sidebar-toggler");
				}
			});
    }
	
    var handleThirdColToggler = function () {
    $("#third-col-trigger").click(
			function(){
				if ($(this).hasClass("third-col-toggler")){	
				$("#body.narrow").css("margin-right","0"); 
				$("#third-col div.row-fluid").hide(); 
				$(this).removeClass("third-col-toggler").addClass("third-col-toggler-closed");
				} else {
				$("#body.narrow").css("margin-right","234px");  
				$("#third-col div.row-fluid").show(); 
				$(this).removeClass("third-col-toggler-closed").addClass("third-col-toggler");
				}
			});
    }
    
    var handleBrandToggler = function () {
        $(".brand-nav").hide(); 
        $(".brand-toggler").toggle(function(){ $('.brand-toggler').addClass("active"); }, function () { $('.brand-toggler').removeClass("active");});
        $(".brand-toggler").click(function(){ $('.brand-nav').slideToggle('fast');});
    }


   var handleSiteToggler = function () {
        $("#site_nav").hide(); 
        $(".site-toggler").toggle(function(){ $('.site-toggler').addClass("active"); }, function () { $('.site-toggler').removeClass("active");});
        $(".site-toggler").click(function(){ $('#site_nav').slideToggle('fast');});
    }


    var handleStyler = function () {

        jQuery('#styler .colors span').click(function () {
            var color = $(this).attr("data-style");
            setColor(color);
			$('#styler .colors span').removeClass("active");
			$(this).addClass("active");
        });

        var setColor = function (color) {
            $('#style_color').attr("href", "assets/css/themes/" + color + ".css");
        }
    }

    var handleLayout = function () {

        jQuery('#styler .layouts span').click(function () {
            var layout = $(this).attr("data-layout");
            setLayout(layout);
			$('#styler .layouts span').removeClass("active");
			$(this).addClass("active");
        });

        var setLayout = function (layout) {
            $('#layout_width').attr("href", "assets/css/themes/" + layout + ".css");
        }

    }

    // DateTimePickers
    var handleDateTimePickers = function() {

        if (!jQuery().daterangepicker) {
            return;
        }
        
        var dates = $('.date-range');
		dates.each(function(index,obj){
			var value = $(obj).val();
			if (value.length > 0){
				var arrays = value.split("~");
				if (arrays.length == 2) {
					var start = $.trim(arrays[0]);
					var end = $.trim(arrays[1]);
					$(obj).daterangepicker({format: 'yyyy/MM/dd',startDate:start, endDate:end });
				}
			}else {
				$(obj).daterangepicker({ format: 'yyyy/MM/dd' });
			}
		});
        



        if (!jQuery().datepicker || !jQuery().timepicker) {
            return;
        }
		;(function($){
		    $.fn.datepicker.dates['zh-CN'] = defaultDatePickerDateSetting;
		}(jQuery));
		$('.date-picker').datepicker({ language: 'zh-CN' });
        $('.timepicker-default').timepicker({ minuteStep: 30 });

        $('.timepicker-24').timepicker({
            minuteStep: 1,
            showSeconds: true,
            showMeridian: false
        });
    }

    return {

        //main function to initiate template pages
        init: function () {
                        
            //if (jQuery.browser.msie && jQuery.browser.version.substr(0, 1) == 8) {
            if (!!navigator.userAgent.match(/MSIE 8/)){
                isIE8 = true; // checkes for IE8 browser version
                $('.visible-ie8').show();
            }
			
            
            handlePortletSortable(); // handles portlet draggable sorting
            handleScrollers(); // handles slim scrolling contents
            handleUniform(); // handles uniform elements            
            handleWidgetTools(); // handles portlet action bar functionality(refresh, toggle, remove)
            handleTooltip(); // handles bootstrap tooltips
            handlePopover(); // handles bootstrap popovers
            handleToggleButtons(); // handles form toogle buttons
            handleMainMenu(); // handles main menu
            handleMobileMenu(); // handles mobile menu
            handleFixInputPlaceholderForIE(); // fixes/enables html5 placeholder attribute for IE9, IE8
            handleFancyBox(); // handles fancy box image previews
		    handleGoTop(); //handles scroll to top functionality in the footer
            handleAccordions(); // handles bootstrap accordions
            handleSidebarToggler(); // handles sidebar toggle contents
            handleThirdColToggler(); // handles third col toggle contents
            handleBrandToggler(); // handles common toggle contents
            handleSiteToggler(); // handles site toggle navigation
            handleStyler(); // handles custom style 
            handleLayout(); // handles layout width
            handleDateTimePickers(); //handles form timepickers
        },


        // wrapper function to scroll to an element
        scrollTo: function (el) {
            pos = el ? el.offset().top : 0;
            jQuery('html,body').animate({
                scrollTop: pos
            }, 'slow');
        },

        // wrapper function to  block element(indicate loading)
        blockUI: function (el, loaderOnTop) {
            lastBlockedUI = el;
            jQuery(el).block({
                message: '<img src="./assets/img/loading.gif" align="absmiddle">',
                css: {
                    border: 'none',
                    padding: '2px',
                    backgroundColor: 'none'
                },
                overlayCSS: {
                    backgroundColor: '#000',
                    opacity: 0.05,
                    cursor: 'wait'
                }
            });
        },

        // wrapper function to  un-block element(finish loading)
        unblockUI: function (el) {
            jQuery(el).unblock({
                onUnblock: function () {
                    jQuery(el).removeAttr("style");
                }
            });
        },

        // set main page
        setMainPage: function (flag) {
            isMainPage = flag;
        },


    };

}();