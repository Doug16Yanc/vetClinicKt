package services

import entities.Animal
import entities.CheckUp
import entities.Examination
import entities.Treatment
import enumerations.TreatmentType
import utils.Utility
import java.util.*

class TreatmentService {
    companion object{
        val sc = Scanner(System.`in`)
        fun defineTreatment(animal: Animal,  checkUp : CheckUp, examination : Examination){
            Utility.printMessage("TREATMENT DEFINITION")
            println("Treatment type:\n C/c - Clinical\n\n H/h - Home\n\n")
            var optionTreament = sc.nextLine()
            when(optionTreament.lowercase(Locale.getDefault())){
                "c" -> {
                    doClinicalTreatment(animal, checkUp, examination)
                }
                "h" -> {
                    doHomeTreatment(animal, checkUp, examination)
                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no existent.\n")
                }
            }
        }
        fun doClinicalTreatment(animal : Animal,  checkUp : CheckUp, examination : Examination){
            Utility.printMessage("The description of the clinical treatment:\n")
            var description = sc.nextLine()
            Utility.printMessage("This documentation proof the treatment of the animal thus described of the\n" +
                                "animal named ${animal.animalName}, ${animal.animalId}, belonging to client by\n" +
                                "name ${animal.client.clientName}, id : ${animal.client.clientId}")
            val treatment = Treatment(description, TreatmentType.CLINICAL)
            PaymentService.doPayment(animal, checkUp, examination, treatment, 1)
        }
        fun doHomeTreatment(animal : Animal, checkUp : CheckUp, examination : Examination){
            TreatmentType.HOME
            Utility.printMessage("The description of the home treatment:\n")
            var description = sc.nextLine()
            Utility.printMessage("This documentation proof the treatment of the animal thus described of the\n" +
                    "animal named ${animal.animalName}, ${animal.animalId}, belonging to client by\n" +
                    "name ${animal.client.clientName}, id : ${animal.client.clientId}")
            val treatment = Treatment(description, TreatmentType.HOME)
            PaymentService.doPayment(animal, checkUp, examination, treatment, 1)
        }

    }
}