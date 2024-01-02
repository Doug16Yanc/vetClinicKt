package entities

import enumerations.AnimalSex
import enumerations.AnimalSituation

data class Animal(
    val client: Client,
    var animalId : Int,
    var animalName : String,
    var animalAge : Int,
    var animalSex : AnimalSex,
    var animalSpecie : Specie,
    var status : AnimalSituation
);