$(document).ready(()=>{
    $("#btnLogin2").attr("href", "#Login");
    $("#btnLogin2").attr("data-toggle", "modal");

    $('#loadMoreRevs').click(function () {
        $.post("/loadMoreRevs?gameId=".concat(getUrlVars()["gameId"]),
            function (data)
            {
                var reviews = JSON.parse(data);
                $('#loadMoreRevs').remove();
                if(reviews.length > 0)
                {
                    reviews.forEach(function (rev)
                    {
                        $('#reviewsDiv').append("<div class=\"border rounded\" id=\"divReviews\">\n" +
                            "                    <label class=\"d-block color-orange\" style=\"font-size: 20px;\"><a href=\"/profile?id="+ rev.author +"\">" + rev.username + "</a></label>\n" +
                            "                    <label class=\"d-block color-orange\" style=\"font-size: 20px;\">" + rev.stars + "</label>\n" +
                            "                    <p class=\"p-review\">" + rev.comment + "</p>\n" +
                            "                </div>")
                    });
                }
                else
                {
                    $('#reviewsDiv').append("<blockquote class=\"blockquote\">\n" +
                        "  <p style=\"text-align: center\"class=\"mb-0\">Non ci sono ancora commenti per questo gioco!</p>\n" +
                        "</blockquote>")
                }
            });
    })
});

function getUrlVars()
{
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}