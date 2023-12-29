package entities

data class Client(
    var clientId : Int,
    var clientName : String,
    var clientAddress : String,
    var clientEmail : String,
    var postalCode : Int
);