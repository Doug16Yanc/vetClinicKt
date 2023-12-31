package entities

import enumerations.animalSex
import enumerations.animalSituation

data class Animal(
    val client: Client,
    var animalId : Int,
    var animalName : String,
    var animalAge : Int,
    var animalSex : animalSex,
    var animalSpecie : Specie,
    var status : animalSituation
);