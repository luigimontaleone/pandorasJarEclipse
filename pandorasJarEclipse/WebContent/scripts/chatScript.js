$(document).ready(()=>{
    //$('.first').trigger('click');
    setTimeout(function(){
        $('.first').trigger('click');
    }, 1000);

    $('#divChat a').click(function () {
        $('.active').removeClass("active text-white");
        $(this).addClass("active text-white");
        var id = $(this).attr('id');
        $('#messages').empty();
        $.post("/loadChat?id=" + id, function (data)
        {
            var messages = JSON.parse(data);
            messages.forEach(function (rev)
            {
                if(rev.logged == false) {
                    $('#messages').append("<div class=\"media w-50 mb-3\">\n" +
                        "                        <img src=\"/PrintImage?id=" + rev.userId + "\" alt=\"user\" width=\"50\" class=\"rounded-circle\">\n" +
                        "                        <div class=\"media-body ml-3\">\n" +
                        "                            <div class=\"bg-light rounded py-2 px-3 mb-2\">\n" +
                        "                                <p class=\"text-small mb-0 text-muted\">" + rev.messaggio + "</p>\n" +
                        "                            </div>\n" +
                        "                            <p class=\"small text-muted\">" + rev.date + "</p>\n" +
                        "                        </div>\n" +
                        "                    </div>");
                }
                if(rev.logged == true)
                {
                    $('#messages').append("<div class=\"media w-50 ml-auto mb-3\">\n" +
                        "                        <div style=\"margin-right: 2%; margin-top: 2%;\"class=\"media-body\">\n" +
                        "                            <div class=\"bg-primary rounded py-2 px-3 mb-2\">\n" +
                        "                                <p class=\"text-small mb-0 text-white\">" + rev.messaggio + "</p>\n" +
                        "                            </div>\n" +
                        "                            <p class=\"small text-muted\">" + rev.date + "</p>\n" +
                        "                        </div>\n" +
                        "                        <img src=\"/PrintImage?id=" + rev.userId + "\" alt=\"user\" width=\"50\" class=\"rounded-circle\">\n" +
                        "                    </div>");
                }
            });
        });
    })
});