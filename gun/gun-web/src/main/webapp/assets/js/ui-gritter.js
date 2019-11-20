var UIGritter = function () {

    var handleGritterNotifications = function () {
        if (!jQuery.gritter) {
            return;
        }

        $('#gritter-sticky').click(function () {
            var unique_id = $.gritter.add({
                // (string | mandatory) the heading of the notification
                title: '常駐訊息 (不會自動消失)',
                // (string | mandatory) the text inside the notification
                text: '本訊息不會自動消失。登入店組當日已啟用門號(當天)，恭嘉開通0936-***123/0938-***111 共計兩門。',
                // (string | optional) the image to display on the left
                sticky: true,
                // (int | optional) the time you want it to be alive for before fading out
                time: '',
                // (string | optional) the class name you want to apply to that specific message
                class_name: 'my-sticky-class'
            });
            return false;
        });

        $('#gritter-regular').click(function () {

            $.gritter.add({
                // (string | mandatory) the heading of the notification
                title: '一般圖片訊息',
                // (string | mandatory) the text inside the notification
                text: '本訊息會自動消失。登入店組當日已啟用門號(當天)，恭嘉開通0936-***123/0938-***111 共計兩門。',
                // (string | optional) the image to display on the left
                image: './assets/img/avatar.jpg',
                // (bool | optional) if you want it to fade out on its own or just sit there
                sticky: false,
                // (int | optional) the time you want it to be alive for before fading out
                time: ''
            });

            return false;

        });

        $('#gritter-without-image').click(function () {
            $.gritter.add({
                // (string | mandatory) the heading of the notification
                title: '一般訊息',
                // (string | mandatory) the text inside the notification
                text: '本訊息會自動消失。登入店組當日已啟用門號(當天)，恭嘉開通0936-***123/0938-***111 共計兩門。'
            });

            return false;
        });

        $('#gritter-light').click(function () {

            $.gritter.add({
                // (string | mandatory) the heading of the notification
                title: '反白版一般訊息',
                // (string | mandatory) the text inside the notification
                text: '本訊息會自動消失。登入店組當日已啟用門號(當天)，恭嘉開通0936-***123/0938-***111 共計兩門。',
                class_name: 'gritter-light'
            });

            return false;
        });

        $("#gritter-remove-all").click(function () {

            $.gritter.removeAll();
            return false;

        });
    }

    return {
        //main function to initiate the module
        init: function () {
            handleGritterNotifications();
        }

    };

}();