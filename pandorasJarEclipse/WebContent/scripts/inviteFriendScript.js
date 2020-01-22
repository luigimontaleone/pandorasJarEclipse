$(document).ready(()=> {
    $('#inviaRichiesta').on('click', sendRequest);
});

function sendRequest()
{
    alert("here");
    $.post("/inviteFriend?id="+$('#friend-input').val(),
        {},
        function(data){
            var compare = "ok";
            if(data.localeCompare(compare) == 0)
            {
                $('#friend-jumbotron').append("<h3 style='color:rgb(255,165,0);'>Richiesta inviata!</h3>");
            }
        });
}

