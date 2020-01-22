$(document).ready(()=> {
    $(".btn-open-comments").attr("href", "#commentsList");
    $(".btn-open-comments").attr("data-toggle", "modal");

    $("#btn-addPost").on('click', addNewPost);
});

function addLikeDislike(idPost, like)
{
    var url = "/addLikeDislike?like="+like+"&id="+idPost;
    $.get(url,
        function (data)
        {
            handlePosts(data);
        });
}

function handlePosts(data)
{
    var posts = JSON.parse(data);
    $('.filtr-container').remove();

    posts.forEach(function (post)
    {
        addPost(post);
    });
}

function handleComments(data, post)
{
    var comments = JSON.parse(data);
    $('.divComments').remove();

    addNewCommentForm(post);
    comments.forEach(function (comment)
    {
        addComment(comment);
    });

}

function addPost(post)
{
    $('#container').append("<div class=\"row filtr-container\">" +
        "<div class=\"col-md-11 col-lg-6 filtr-item\" style=\"margin: auto;\">\n" +
        "                <div class=\"card border-dark\">\n" +
        "                    <div class=\"card-header bg-dark text-light\">\n" +
        "                        <h5 class=\"m-0\">"+post.title+"</h5>\n" +
        "                    </div>\n" +
        "                    <img class=\"img-fluid card-img w-100 d-block rounded-0\" src="+post.image+">\n" +
        "                    <div class=\"card-body\">\n" +
        "                        <p class=\"card-text\">Autore: <a href=\"/profile?id="+post.authorId+"\">"+post.author+"</a></p>\n" +
        "                        <p class=\"card-text\">"+post.description+"</p>\n" +
        "                    </div>\n" +
        "                    <div class=\"d-flex card-footer\">\n" +
        "                        <button class=\"btn btn-success btn-sm\" type=\"button\" onclick=\"addLikeDislike("+post.id+",1)\"><i class=\"fas fa-thumbs-up\"></i> "+post.numLike+"</button>\n" +
        "                        <button class=\"btn btn-danger btn-sm ml-auto\" type=\"button\" onclick=\"addLikeDislike("+post.id+",0)\"><i class=\"fas fa-thumbs-down\"></i> "+post.numDislike+"</button>\n" +
        "                        <button class=\"btn btn-primary btn-sm ml-auto\" type=\"button\"><i class=\"fas fa-comments\"></i> "+post.comments.length+"</button>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>" +
        "</div>");

}

function openComments(post)
{
    var url = "/OpenComments?id="+post;
    $.get(url,
        function (data)
        {
            handleComments(data,post);
        });
}

function addNewCommentForm(post)
{
    if($('#userId').val() != undefined)
    {
        $('.add-comment').remove();
        $('#commentsList').append("" +
            "                    <div class=\"row add-comment\" style=\"margin-top: 2%;\">\n" +
            "                        <div class=\"col-md-6\" style=\"margin: auto;\">\n" +
            "                            <form class=\"border rounded form-addPost\">\n" +
            "                                <h1 class=\"text-center\" style=\"font-size: 40px;margin-bottom: 4%; color:white;\">Aggiungi un commento!</h1>\n" +
            "                                <input class=\"border rounded input-addPost\" type=\"text\" id=\"authorId\" readonly value=\""+$('#userId').val()+"\">\n" +
            "                                <input class=\"border rounded input-addPost\" type=\"text\" id=\"postIdComment\" readonly value=\""+post+"\">\n" +
            "                                <input class=\"border rounded input-addPost\" type=\"text\" id=\"commento\" style='display:block;' placeholder=\"Commento\">\n" +
            "                                <button class=\"btn btn-primary\" id=\"btn-add-comment\" onclick='addNewComment()' type=\"button\" style=\"margin-right: 0px;margin-left: 0px;background-color: #00baff;\">Salva</button>\n" +
            "                            </form>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "");
    }
}

function addComment(comment)
{

    $('.modal-content').append("<div class=\"border rounded divComments\">\n" +
        "                                <label class=\"d-block color-orange\" style=\"font-size: 20px;\"><a href=\"/profile?id="+comment.authorId+"\">"+comment.author+"</a></label>\n" +
        "                                <p class=\"p-comment\">"+comment.comment+"</p>\n" +
        "                            </div>");


}

function addNewPost()
{
    var userId = $('#userId').val();
    var titolo = $('#titolo').val();
    var descrizione = $('#descrizione').val();
    var immagine = $('#immagine').val();

    if(immagine == null || immagine == undefined || immagine == "")
    {
        alert("immagine non può essere vuota!");
        return;
    }

    var url = "/AddNewPost";
    $.get(url,
        {
            data:JSON.stringify({authorId: userId, numLike: 0, numDislike: 0, title: titolo, image: immagine, description: descrizione})
        },
        function (data)
        {
            handlePosts(data);
            $('#titolo').val("");
            $('#descrizione').val("");
            $('#immagine').val("");
        });

}

function addNewComment()
{
    var authorId = $('#authorId').val();
    var postId = $('#postIdComment').val();
    var commento = $('#commento').val();

    if(commento == null || commento == undefined || commento == "")
    {
        alert("commento non può essere vuoto!");
        return;
    }

    var url = "/AddNewComment?id="+postId;
    $.get(url,
        {
            data:JSON.stringify({authorId: authorId, comment: commento})
        },
        function (data)
        {
            handleComments(data, postId);
            $('#commento').val("");
        });

}