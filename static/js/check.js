$(document).ready(function () {

    var buttonCheck = document.getElementById("check");
    var currentUser = localStorage.getItem("currentUser");
    buttonCheck.onclick = function(){
        if(currentUser!=null){
            $.ajax({
                method:"POST",
                url:"http://localhost:8080/check?username="+currentUser+"",
                success: function(data){
                    var json = JSON.parse(data);
                    for(var i = 0; i< json.length; i++){
                        $("#task-title").append("<div>"+json[i].name +"</div>");
                        $("#task-form").append("<form action='http://localhost:8080/execute/"+json[i].id+"?username="+currentUser+"' method='POST'><table id='form-task'></table></form>")
                        for(var j=0; j< json[0].myFormList.length; j++){
                            if (json[0].myFormList[j].type == "string"){
                                $("#form-task").append("<tr><td>"+json[0].myFormList[j].name+"</td><td><input id=" + json[0].myFormList[j].id + " name="+ json[0].myFormList[j].name + " type='text'/></td></tr>")
                            }else if(json[0].myFormList[j].type=="boolean"){
                                $("#form-task").append("<tr><td>"+json[0].myFormList[j].name+"</td><td><input id=" + json[0].myFormList[j].id + " name="+ json[0].myFormList[j].name + " type='checkbox'/></td></tr>")
                            }else if(json[0].myFormList[j].type=="long"){
                                $("#form-task").append("<tr><td>"+json[0].myFormList[j].name+"</td><td><input id=" + json[0].myFormList[j].id + " name="+ json[0].myFormList[j].name + " type='long'/></td></tr>")
                            }else{
                                $("#form-task").append("<tr><td>"+json[0].myFormList[j].name+"</td><td><input id=" + json[0].myFormList[j].id + " name="+ json[0].myFormList[j].name + " type='date'/></td></tr>")
                            }
                        }
                        $("#form-task").append("<tr><td><button type='submit'>Submit</button><td></tr>");
                    }
                },
                error: function(request, options, error) {
                    alert("Error");
                }
          });}

    }

    // Get the modal
    var modal = document.getElementById('myModalLogIn');

    // Get the button that opens the modal
    var btn = document.getElementById("loginButton");
    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";
    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };


    var loginSubmit = document.getElementById("loginSubmit");
    loginSubmit.onclick = function (){
        var username = document.getElementById("usernameLogin").value;
        var password = document.getElementById("passwordLogin").value;
        window.localStorage.clear();
        $.ajax({
            method: "POST",
            url : "http://localhost:8080/login",
            data: {"username": username,
                    "password": password},
            success: function(data){
            alert(data.entity);
                alert("Succes");
                localStorage.clear();
                localStorage.setItem('currentUser', data.entity);
            },
            error: function(error){
                alert(error);
            },
            complete: function(){
                modal.style.display="none";
                window.location.reload();
            }
        });
    }

});


