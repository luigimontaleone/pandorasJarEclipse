$(document).ready(()=>{
    $("#btnLogin2").attr("href", "#Login");
    $("#btnLogin2").attr("data-toggle", "modal");

    $('#loadMoreRevs').click(function () {
        $.post("/loadMoreRevs?gameId=".concat(getUrlVars()["gameId"]),
            function (data)
            {
                var reviews = JSON.parse(data);
                $('#loadMoreRevs').remove();
                reviews.forEach(function (rev)
                {
                    $('#reviewsDiv').append("<div class=\"border rounded\" id=\"divReviews\">\n" +
                        "                    <label class=\"d-block color-orange\" style=\"font-size: 20px;\"><a href=\"/profile?id="+ rev.author +"\">" + rev.username + "</a></label>\n" +
                        "                    <label class=\"d-block color-orange\" style=\"font-size: 20px;\">" + rev.stars + "</label>\n" +
                        "                    <p class=\"p-review\">" + rev.comment + "</p>\n" +
                        "                </div>")
                })
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