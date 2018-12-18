$(document).ready(function() {
 $("#validate").click(function(event){
     //on vide le résultat précédent
    $("#results").empty();
    //récupération de l'id du compte pour l'envoyer au webService
    var id = '${savingAccount.id}' || '${currentAccount.id}';
    console.log(id);
    var send = {
        id : id
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
        if(object.isOK) {
            $("#results").append($("<h5 id='response_OK'>"+object.message+"</h5>"));
        } else {
            $("#results").append($("<h5 id='response_notOK'>"+object.message+"</h5>"));
        }
    });
 });
})