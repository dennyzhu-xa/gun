var Index = function () {

    return {

        initIntro: function () {
            if ($.cookie('intro_show')) {
                return;
            }

            $.cookie('intro_show', 1);

            setTimeout(function () {
                var unique_id = $.gritter.add({
                    // (string | mandatory) the heading of the notification
                    title: '開放實體表單页面用戶使用',
                    // (string | mandatory) the text inside the notification
                    text: '自102/04月份起，開放實體表單页面用戶使用線上「表單页面瀏覽」功能，不論是查询或下載明細表單页面，皆不需額外申請。',
                    // (string | optional) the image to display on the left
                    image: './assets/img/avatar.jpg',
                    // (bool | optional) if you want it to fade out on its own or just sit there
                    sticky: true,
                    // (int | optional) the time you want it to be alive for before fading out
                    time: '',
                    // (string | optional) the class name you want to apply to that specific message
                    class_name: 'my-sticky-class'
                });

                // You can have it return a unique id, this can be used to manually remove it later using
                setTimeout(function () {
                    $.gritter.remove(unique_id, {
                        fade: true,
                        speed: 'slow'
                    });
                }, 12000);
            }, 2000);

            setTimeout(function () {
                var unique_id = $.gritter.add({
                    // (string | mandatory) the heading of the notification
                    title: '開放實體表單页面用戶使用',
                    // (string | mandatory) the text inside the notification
                    text: '自102/04月份起，開放實體表單页面用戶使用線上「表單页面瀏覽」功能，不論是查询或下載明細表單页面，皆不需額外申請。',
                    // (string | optional) the image to display on the left
                    image: './assets/img/avatar.jpg',
                    // (bool | optional) if you want it to fade out on its own or just sit there
                    sticky: true,
                    // (int | optional) the time you want it to be alive for before fading out
                    time: '',
                    // (string | optional) the class name you want to apply to that specific message
                    class_name: 'my-sticky-class'
                });

                // You can have it return a unique id, this can be used to manually remove it later using
                setTimeout(function () {
                    $.gritter.remove(unique_id, {
                        fade: true,
                        speed: 'slow'
                    });
                }, 13000);
            }, 8000);

            setTimeout(function () {

                var unique_id = $.gritter.add({
                    // (string | mandatory) the heading of the notification
                    title: '訊息通知',
                    // (string | mandatory) the text inside the notification
                    text: '您有三則新訊息',
                    // (string | optional) the image to display on the left
                    sticky: true,
                    // (int | optional) the time you want it to be alive for before fading out
                    time: '',
                    // (string | optional) the class name you want to apply to that specific message
                    class_name: 'my-sticky-class'
                });

                setTimeout(function () {
                    $.gritter.remove(unique_id, {
                        fade: true,
                        speed: 'slow'
                    });
                }, 6000);

            }, 30000);

        }

    };

}();