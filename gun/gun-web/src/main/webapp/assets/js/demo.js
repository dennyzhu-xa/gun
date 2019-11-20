var Demo = function() {

	// Calendar Demo
	var handleCalendar = function() {

		if (!jQuery().fullCalendar) {
			return;
		}

		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		var h = {};

		if ($(window).width() <= 320) {
			h = {
				left: 'title, prev,next',
				center: '',
				right: 'today,month,agendaWeek,agendaDay'
			};
		} else {
			h = {
				left: 'title',
				center: '',
				right: 'prev,next,today,month,agendaWeek,agendaDay'
			};
		}

		var initDrag = function(el) {
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim(el.text()) // use the element's text as the event title
			};
			// store the Event Object in the DOM element so we can get to it later
			el.data('eventObject', eventObject);
			// make the event draggable using jQuery UI
			el.draggable({
				zIndex: 999,
				revert: true, // will cause the event to go back to its
				revertDuration: 0 //  original position after the drag
			});
		}

		var addEvent = function(title, priority) {
			title = title.length == 0 ? "Untitled Event" : title;
			priority = priority.length == 0 ? "default" : priority;

			var html = $('<div data-class="label label-' + priority + '" class="external-event label label-' + priority + '">' + title + '</div>');
			jQuery('#event_box').append(html);
			initDrag(html);
		}

		$('#external-events div.external-event').each(function() {
			initDrag($(this))
		});

		$('#event_add').click(function() {
			var title = $('#event_title').val();
			var priority = $('#event_priority').val();
			addEvent(title, priority);
		});


		//predefined events
		addEvent("休息日", "success");
		addEvent("上班日", "important");

		$('#calendar').fullCalendar({
			header: h,
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped

				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);

				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				copiedEventObject.className = $(this).attr("data-class");

				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
			},
			titleFormat: {
				month: 'yyyy年 MMMM',
				week: "yyyy年 MMM d日{ '~'[ MMM] d日 [yyyy]}",
				day: 'dddd, MMM d, yyyy'
			},
			monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
			monthNamesShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
			dayNames: ['週日', '週一', '週二', '週三', '週四', '週五', '週六'],
			dayNamesShort: ['週日', '週一', '週二', '週三', '週四', '週五', '週六'],
			buttonText: {
				today: '今日',
				month: '月',
				week: '週',
				day: '日'
			},
			events: [{
					title: '全天活動',
					start: new Date(y, m, 1),
					className: 'label label-default',
				}, {
					title: '跨日活動',
					start: new Date(y, m, d - 5),
					end: new Date(y, m, d - 2),
					className: 'label label-success',
				}, {
					id: 999,
					title: '重複活動',
					start: new Date(y, m, d - 3, 16, 0),
					allDay: false,
					className: 'label label-default',
				}, {
					id: 999,
					title: '重複活動',
					start: new Date(y, m, d + 4, 16, 0),
					allDay: false,
					className: 'label label-important',
				}, {
					title: '會議',
					start: new Date(y, m, d, 10, 30),
					allDay: false,
					className: 'label label-info',
				}, {
					title: '午餐會議',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false,
					className: 'label label-warning',
				}, {
					title: '網站連結',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: 'http://google.com/',
					className: 'label label-warning',
				}
			]
		});
	}

	// Tables Demo
	var handleTables = function() {
		if (!jQuery().dataTable) {
			return;
		}

		// begin payment table
		jQuery('#data_payment_table .group-checkable').change(function() {
			var set = jQuery(this).attr("data-set");
			var checked = jQuery(this).is(":checked");
			jQuery(set).each(function() {
				if (checked) {
					$(this).attr("checked", true);
				} else {
					$(this).attr("checked", false);
				}
			});
			jQuery.uniform.update(set);
		});

		// begin first table
		$('#data_table_1').dataTable({
			"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
			"sPaginationType": "bootstrap",
			"oLanguage": {
				"sLengthMenu": "每页顯示 _MENU_ 筆資料",
				"oPaginate": {
					"sFirst": "第一页",
					"sPrevious": "上一页",
					"sNext": "下一页",
					"sLast": "最后一页"
				}
			},
			"aoColumnDefs": [{
					'bSortable': false,
					'aTargets': [0]
				}
			]
		});

		jQuery('#data_table_1 .group-checkable').change(function() {
			var set = jQuery(this).attr("data-set");
			var checked = jQuery(this).is(":checked");
			jQuery(set).each(function() {
				if (checked) {
					$(this).attr("checked", true);
				} else {
					$(this).attr("checked", false);
				}
			});
			jQuery.uniform.update(set);
		});

		jQuery('#data_table_1_wrapper .dataTables_filter input').addClass("input-medium");
		jQuery('#data_table_1_wrapper .dataTables_length select').addClass("input-mini");
	}

	// DateTimePickers
	var handleDateTimePickers = function() {

		if (!jQuery().daterangepicker) {
			return;
		}		

		$('#form-date-range').daterangepicker({
			ranges: {
				'今日': ['today', 'today'],
				'昨日': ['yesterday', 'yesterday'],
				'最近7日': [Date.today().add({
						days: -6
					}), 'today'],
				'最近30日': [Date.today().add({
						days: -29
					}), 'today'],
				'本月': [Date.today().moveToFirstDayOfMonth(), Date.today().moveToLastDayOfMonth()],
				'上月': [Date.today().moveToFirstDayOfMonth().add({
						months: -1
					}), Date.today().moveToFirstDayOfMonth().add({
						days: -1
					})]
			},
			opens: 'right',
			format: 'MM/dd/yyyy',
			separator: ' to ',
			startDate: Date.today().add({
				days: -29
			}),
			endDate: Date.today(),
			minDate: '01/01/2012',
			maxDate: '12/31/2014',
			locale: {
				applyLabel: '送出',
				fromLabel: '自',
				toLabel: '至',
				customRangeLabel: 'Custom Range',
				daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
				monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
				firstDay: 1
			},
			showWeekNumbers: true,
			buttonClasses: ['btn-danger']
		},

		function(start, end) {
			$('#form-date-range span').html(start.toString('MMMM d, yyyy') + ' - ' + end.toString('MMMM d, yyyy'));
		});

		$('#form-date-range span').html(Date.today().add({
			days: -29
		}).toString('MMMM d, yyyy') + ' - ' + Date.today().toString('MMMM d, yyyy'));

	}

	// HeaderToggler
	var handleHeaderToggler = function() {
		$("#span-block").hide();
		$("li.fat .dropdown-toggle").toggle(function() {
			$('li.fat').addClass("active");
		}, function() {
			$('li.fat').removeClass("active");
		});
		$("li.fat .dropdown-toggle").click(function() {
			$('#span-block').slideToggle('fast');
		});
	}

	// MoreToggler
	var handleMoreToggler = function() {
		$("#more-items").hide();
		$(".more-items").toggle(function() {
			$('.more-items').addClass("active");
		}, function() {
			$('.more-items').removeClass("active");
		});
		$(".more-items").click(function() {
			$('#more-items').slideToggle('fast');
		});
	}

	// ListMoreToggler
	var handleListMoreToggler = function () {
        $("#list-more-items").hide(); 
        $(".list-more-toggler").toggle(function(){ $('.list-more-toggler').addClass("active"); }, function () { $('.list-more-toggler').removeClass("active");});
        $(".list-more-toggler").click(function(){ $('#list-more-items').slideToggle('fast');});
    }

	return {
		init: function() {
			handleCalendar(); // handles full calendars
			handleTables(); // handles data tables
			handleDateTimePickers(); //handles form timepickers
			handleHeaderToggler(); // handles header toggle contents
			handleMoreToggler(); // handles more toggle contents
			handleListMoreToggler(); // handles more toggle contents
		}
	}

}();