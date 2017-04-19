$(document).ready(function () {

    var buttonCheck = document.getElementById("check");

    buttonCheck.onclick = function(){
        $.ajax({
            method:"POST",
            url:"http://localhost:8080/check?username=Kermit",
            success: function(data){
                var json = JSON.parse(data);
                for(var i = 0; i< json.length; i++){
                    $("#task-title").append("<div>"+json[i].name +"</div><button>Claim task</button>");
                    for(var j=0; j< json[0].myFormList.length; j++){
                        if (json[0].myFormList[j].type == "string"){
                            $("#task-form").append("<input id=" + json[0].myFormList[j].id + " name="+ json[0].myFormList[j].name + " type=text/><br>")
                        }else{
                            $("#task-form").append("<input id=" + json[0].myFormList[j].id + " name="+ json[0].myFormList[j].name + " type=" + json[0].myFormList[j].type  +"/><br>")
                        }
                    }
                }


            },
            error: function(request, options, error) {
                alert("Error");
            }
        });
    }
});


