$(document).ready(()=> {
    $('#search-field').on('change', searchFriend);

});

function searchFriend()
{
    $.get("/SearchFriend?id="+$('#search-field').val(),
        function (data)
        {
            var friends = JSON.parse(data);
            $('.tr-color').remove();
            if(friends.length > 0)
            {
                friends.forEach(function (friend)
                {
                    addFriend(friend);
                });
            }
            else
            {
                addFriend(friends);
            }
        });
}

function addFriend(friend)
{
    $('tbody').append("<tr class=\"tr-color\">\n" +
        "                                    <td>"+friend.id+"</td>\n" +
        "                                    <td>"+friend.username+"</td>\n" +
        "                                    <td>"+friend.email+"</td>\n" +
        "                                    <td>\n" +
        "                                        <button type=\"button\" id=\"deleteFriend\" onclick=\"deleteFriend("+friend.id+")\" class=\"btn btn-danger\"><i class=\"far fa-trash-alt d-xl-flex justify-content-xl-center align-items-xl-center\"></i></button>\n" +
        "                                        <a type=\"button\" href=\"/profile?id="+friend.id+"\" class=\"btn btn-primary\"><i class=\"fas fa-address-card d-xl-flex justify-content-xl-center align-items-xl-center\"></i></a>\n" +
        "                                    </td>\n" +
        "                                </tr>");
}

function deleteFriend(id)
{
    $.get("/DeleteFriend?id="+id,
        function (data)
        {
            var friends = JSON.parse(data);
            $('.tr-color').remove();
            friends.forEach(function (friend)
            {
                $('tbody').append("<tr class=\"tr-color\">\n" +
                    "                                    <td>"+friend.id+"</td>\n" +
                    "                                    <td>"+friend.username+"</td>\n" +
                    "                                    <td>"+friend.email+"</td>\n" +
                    "                                    <td>\n" +
                    "                                        <button type=\"button\" id=\"deleteFriend\" onclick=\"deleteFriend("+friend.id+")\" class=\"btn btn-danger\"><i class=\"far fa-trash-alt d-xl-flex justify-content-xl-center align-items-xl-center\"></i></button>\n" +
                    "                                        <a type=\"button\" href=\"/profile?id="+friend.id+"\" class=\"btn btn-primary\"><i class=\"fas fa-address-card d-xl-flex justify-content-xl-center align-items-xl-center\"></i></a>\n" +
                    "                                    </td>\n" +
                    "                                </tr>");
            });
        });
}