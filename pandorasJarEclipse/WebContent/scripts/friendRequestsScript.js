$(document).ready(()=> {
    $('#search-field-sent').on('change', function(){searchRequests(0)});
    $('#search-field-received').on('change', function(){searchRequests(1)});

});

function searchRequests(received)
{
    var url = "/SearchRequests?received=1&id="+$("#search-field-received").val();
    if(received === 0)
    {
        url = "/SearchRequests?received=0&id="+$("#search-field-sent").val();
    }
    $.get(url,
        function (data)
        {
            var requests = JSON.parse(data);
            if(received === 1)
                $('.tr-color-received').remove();
            else
                $('.tr-color-sent').remove();
            if(requests.length > 0)
            {
                requests.forEach(function (request)
                {
                    if(received === 1)
                        addReceivedRequest(request);
                    else
                        addSentRequest(request);
                });
            }
            else
            {
                if(received === 1)
                    addReceivedRequest(requests);
                else
                    addSentRequest(requests);
            }
        });
}

function addReceivedRequest(request)
{
    $('#tbody-received').append("<tr class=\"tr-color-received\">\n" +
        "                                    <td>"+request.id+"</td>\n" +
        "                                    <td>"+request.username+"</td>\n" +
        "                                    <td>"+request.email+"</td>\n" +
        "                                    <td>\n" +
        "                                        <button type=\"button\" id=\"addFriend\" onclick=\"addFriend("+request.id+")\" class=\"btn btn-success\"><i class=\"fas fa-check d-xl-flex justify-content-xl-center align-items-xl-center\"></i></button>\n" +
        "                                        <a type=\"button\" href=\"/profile?id="+request.id+"\" class=\"btn btn-primary\"><i class=\"fas fa-address-card d-xl-flex justify-content-xl-center align-items-xl-center\"></i></a>\n" +
        "                                    </td>\n" +
        "                                </tr>");
}

function addSentRequest(request)
{
    $('#tbody-sent').append("<tr class=\"tr-color-sent\">\n" +
        "                                    <td>"+request.id+"</td>\n" +
        "                                    <td>"+request.username+"</td>\n" +
        "                                    <td>"+request.email+"</td>\n" +
        "                                    <td>\n" +
        "                                        <button type=\"button\" id=\"deleteFriend\" onclick=\"deleteFriend("+request.id+")\" class=\"btn btn-danger\"><i class=\"far fa-times-circle d-xl-flex justify-content-xl-center align-items-xl-center\"></i></button>\n" +
        "                                        <a type=\"button\" href=\"/profile?id="+request.id+"\" class=\"btn btn-primary\"><i class=\"fas fa-address-card d-xl-flex justify-content-xl-center align-items-xl-center\"></i></a>\n" +
        "                                    </td>\n" +
        "                                </tr>");
}

function deleteRequestFriend(id)
{
    $.get("/DeleteRequestFriend?id="+id).always(
        function (data)
        {
            var requests = JSON.parse(data);
            $('.tr-color-sent').remove();
            requests.forEach(function (request)
            {
                addSentRequest(request);
            });
        });
}

function addFriend(id)
{
    deleteRequestFriend(id);
    $.get("/addFriend?id="+id,
        function (data)
        {
            var requests = JSON.parse(data);
            $('.tr-color-received').remove();
            requests.forEach(function (request)
            {
                addReceivedRequest(request);
            });
        });
}
