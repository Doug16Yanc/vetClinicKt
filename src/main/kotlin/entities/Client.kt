package entities

import enumerations.ClientStatus

data class Client(
    var clientId : Int,
    var clientName : String,
    var clientAddress : String,
    var clientEmail : String,
    var postalCode : Int,
    var username : String,
    var password : String,
    var status : ClientStatus
);