$(document).ready(function () {

    var buttonCheck = document.getElementById("check");

    buttonCheck.onclick = function(){
        $.ajax({
            method:"POST",
            url:"http://localhost:8080/check?username=Kermit",
            contentType:"application/json",
            success: function(data){

            },
            error: function(request, options, error) {
                alert("Error");
            }
        });
    }
});


