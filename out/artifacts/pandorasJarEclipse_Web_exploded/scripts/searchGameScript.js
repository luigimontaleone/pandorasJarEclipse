$(document).ready(()=> {

    $('#categoria').change(sendAjax);
    $('#valutazione').change(sendAjax);
    $('#prezzo').change(sendAjax);
});

function sendAjax()
{
    $.get("/SearchFilter",
        {
            data:JSON.stringify({categoria: $("#categoria :selected").val(), prezzo: $("#prezzo :selected").val(), valutazione: $("#valutazione :selected").val()})
        },
        function (data)
        {
            var games = JSON.parse(data);
            $('.row-games').remove();
            if(games.length > 0)
            {
                var index = 0;
                var indexRow = 0;
                games.forEach(function (game)
                {
                    if(index % 4 == 0)
                    {
                        indexRow = indexRow + 1;
                        $('#mainDiv').append("</div>\n" +
                            "<div class=\"row row-games\" id='"+indexRow+"'>");

                    }
                    $("#"+indexRow).append("<div class=\"col-xl-3\" style=\"height: 100%;\"><a href=\"/GameDataSheet?gameId="+game.id+"\"><img style=\"width: 100%;height: 100%;\" src="+game.frontImage+"></a></div>\n");
                    index = index + 1;
                });
                $('#mainDiv').append("</div>");
            }
            else
            {
                $('#h1GiochiRicercati').remove();
                var filter = $('#filter');
                $('#filter').remove();
                $('#mainDiv').append("<h1 class=\"text-left d-block\" id=\"h1GiochiRicercati\">La ricerca non ha prodotto risultati</h1>");
                $('#mainDiv').append(filter);
            }
        });
}