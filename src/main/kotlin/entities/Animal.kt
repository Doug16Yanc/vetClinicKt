package entities

data class Animal(
    val client: Client,
    var animalId : Int,
    var animalName : String,
    var animalAge : Int,
    var animalSex : String,
    var animalSpecie : Specie
);