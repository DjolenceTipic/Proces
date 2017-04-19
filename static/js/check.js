$(document).ready(function () {

    var buttonCheck = document.getElementById("check");

    buttonCheck.onclick = function(){
        $.ajax({
            method:"POST",
            url:"http://localhost:8080/check?username=Kermit",
            success: function(data){
                alert(JSON.parse(data));
                var jsonData = JSON.parse(data);
                for(var i = 0; i< jsonData.length; i++){
                    $("#task-title").append("<div>"+jsonData[i].name +"</div><button>Claim task</button>");
                }


            },
            error: function(request, options, error) {
                alert("Error");
            }
        });
    }
});


