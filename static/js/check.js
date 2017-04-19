$(document).ready(function () {

    var buttonCheck = document.getElementById("check");

    buttonCheck.onclick = function(){
        $.ajax({
            method:"POST",
            url:"http://localhost:8080/check?username=Kermit",
            contentType:"application/json",
            dataType:"application/json",
            success: function(data){
                var jsonTask = JSON.parse(data);
                alert(jsonTask.length);
                for(var i = 0; i< data.length; i++){
                    $("#task-title").append(data[i].name);
                }


            },
            error: function(request, options, error) {
                alert("Error");
            }
        });
    }
});


