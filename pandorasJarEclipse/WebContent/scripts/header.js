$(document).ready(function(){
    if($('.hidable').attr('id') === "loginRef"){
        $("#addGameLink").attr("href", "#Login");
        $("#addGameLink").attr("data-toggle", "modal");
        $("#communityLink").attr("href", "#Login");
        $("#communityLink").attr("data-toggle", "modal");
        $("#libraryLink").attr("href", "#Login");
        $("#libraryLink").attr("data-toggle", "modal");
        $("#profileLink").attr("href", "#Login");
        $("#profileLink").attr("data-toggle", "modal");
    }
    //if(Cookies.get("logged") === "false" || Cookies.get("logged") === undefined)
    $("#loginBtn").click(function () {
        $.ajax({
            type: "POST",
            url: "/login",
            data: {
                email: $("#logEmail").val(),
                password: $("#logPassword").val()
            },
            success: function() {
                    $("#errorLabel").hide();
                    window.location.replace("/");
            },
            error: function () {
                $("#errorLabel").show();
            }
        })
    })

    function f()
    {
        let urlString = window.location;
        let url = new URL(urlString);
        let c = url.searchParams.get("registered");
        if(c === "true"){
            alert("REGISTRAZIONE EFFETTUATA");
        }
    }

})

/*$(document).ready(function () {
    let urlString = window.location;
    let url = new URL(urlString);
    let c = url.searchParams.get("registered");
    if(c === "true"){
        alert("REGISTRAZIONE EFFETTUATA");
    }
    //TODO : testami
});*/