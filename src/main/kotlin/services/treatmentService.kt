package services

import entities.Animal
import enumerations.TreatmentType
import utils.Utility
import java.util.*

class TreatmentService {
    companion object{
        val sc = Scanner(System.`in`)
        fun defineTreatment(animal: Animal){
            Utility.printMessage("TREATMENT DEFINITION")
            println("Treatment type:\n C/c - Clinical\n\n H/h - Home\n\n")
            var optionTreament = sc.nextLine()
            when(optionTreament.lowercase(Locale.getDefault())){
                "c" -> {
                    doClinicalTreatment(animal)
                }
                "h" -> {
                    doHomeTreatment(animal)
                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no existent.\n")
                }
            }
        }
        fun doClinicalTreatment(animal : Animal){
            TreatmentType.CLINICAL
            Utility.printMessage("The description of clinical treatment:\n")
            var description = sc.nextLine()
            Utility.printMessage("This documentation proof the treatment of the animal thus described of the\n" +
                                "animal named ${animal.animalName}, ${animal.animalId}, belonging to client by\n" +
                                "name ${animal.client.clientName}, id : ${animal.client.clientId}")

        }
        fun doHomeTreatment(animal : Animal){
            TreatmentType.HOME
            Utility.printMessage("The description of home treatment:\n")
            var description = sc.nextLine()
            Utility.printMessage("This documentation proof the treatment of the animal thus described of the\n" +
                    "animal named ${animal.animalName}, ${animal.animalId}, belonging to client by\n" +
                    "name ${animal.client.clientName}, id : ${animal.client.clientId}")
        }
    }
}