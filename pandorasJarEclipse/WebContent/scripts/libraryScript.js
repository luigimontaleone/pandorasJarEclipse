$(document).ready(function () {
    $("#addComment").click(function () {
        if($("#commentContent").val() && $("#commentStars").val()) {
            $.ajax({
                type: "POST",
                url: "/addComment",
                data: {
                    content: $("#commentContent").val(),
                    stars: $("#commentStars").val(),
                    game: $(".formCommenti").attr('id')
                },
                success: function () {
                    window.location.reload();
                }
            })
        }
        else{
            alert("Il commento o le stelle non possono essere vuote!");
        }
    });

});

function downloadGame(game)
{
    window.location.replace("/downloadGame?id="+game);
}


function addMoreRev(game)
{
    $.post("/loadMoreRevs?gameId="+game,
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
}
