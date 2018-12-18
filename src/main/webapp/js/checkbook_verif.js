$(document).ready(function() {
 $(".validate").click(function(event){
	 console.log(event);
     //on vide le résultat précédent
    $("#results").empty();
    //récupération de l'id du compte pour l'envoyer au webService
    var idAccount = event.currentTarget.childNodes[0].innerHTML;
    console.log(idAccount);
    var send = {
        "id" : idAccount
    };
    console.log(send);
    var send = JSON.stringify(send);
    console.log(send);

    $.ajax({
        url:'http://localhost:8080/proxibanquev3/webservices/checkbook',
        type:'POST',
        contentType : 'application/json',
        data: send,
        dataTye:'json'
    }).done(function(object) {
    	console.log(object)
        if(object.ok == true) {
            $("#results").append($("<h5 id='response_OK'>"+object.message+"</h5>"));
        } else {
            $("#results").append($("<h5 id='response_notOK'>"+object.message+"</h5>"));
        }
    	
    });
 });
})