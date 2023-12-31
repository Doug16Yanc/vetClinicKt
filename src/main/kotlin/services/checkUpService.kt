package services

import entities.*
import enumerations.CheckupStatus
import enumerations.TreatmentType
import repositories.Calculate
import utils.Utility

import java.util.*
import kotlin.collections.ArrayList

class CheckUpService {
    companion object : Calculate{
        val sc = Scanner(System.`in`)
        val consultations : MutableList<Animal> = ArrayList()
        val consultPay : MutableMap<Animal, CheckUp> = HashMap()
        fun getCheckUp(): MutableList<Animal> {
            return consultations
        }
        fun performCheckUp(client : Client, animal: MutableList<Animal>){
            var value = doCalculation(1)
            Utility.printMessage("CONSULTATION\n\n" +
                    "Dearest ${client.clientName}, depending on the case you suspect, mark the\n" +
                    "check up as an emergency and your pet will enter the priority queue.\n")

            println("Enter id animal :")
            var id = sc.nextInt()


            val foundAnimal = ClientService.animalClients.find {it.animalId == id}

            if (foundAnimal != null && foundAnimal.client == client) {
                sc.nextLine()
                println("Status check up:\n E/e - Emergency\n C/c - Conventional\n")
                var option = sc.nextLine()

                when (option.lowercase(Locale.getDefault())) {
                    "e" -> {
                        consultations.add(0, foundAnimal)
                        Utility.printMessage("The animal, ${foundAnimal.animalId}, " +
                                            "${foundAnimal.animalName} was " +
                                            "referred to emergency.\n")
                        val checkUp = CheckUp(value, CheckupStatus.EMERGENCY)

                        val consult = CheckUp(value, CheckupStatus.EMERGENCY)
                        consultPay[foundAnimal] = consult
                    }

                    "c" -> {
                        consultations.add(foundAnimal)
                        Utility.printMessage("The animal, ${foundAnimal.animalId}, " +
                                            "${foundAnimal.animalName} was " +
                                            "referred to conventional chech up.\n")
                        val consult = CheckUp(value, CheckupStatus.CONVENTIONAL)
                        consultPay[foundAnimal] = consult
                    }
                    else -> {
                        Utility.printMessage("Sorry, however this option's no-existent.\n")
                    }
                }
            }
            else {
                Utility.printMessage("Animal not found.\n")
            }

        }
        fun listConsultation(checkUp: CheckUp){
            Utility.printMessage("CONSULTATIONS FILE")
            val treatment = Treatment("", TreatmentType.CLINICAL)
            for (consult in consultations){
                val check = consultPay[consult]
                Utility.printMessage("> Id of client : ${consult.client.clientId}\n" +
                        "           > Name of client : ${consult.client.clientName}\n" +
                        "           > Address : ${consult.client.clientAddress}\n" +
                        "           > Postal code : ${consult.client.postalCode}\n" +
                        "           > Client status : ${consult.client.status}\n\n" +
                        "           ANIMAL DATA         \n\n" +
                        "           > Id of animal : ${consult.animalId}\n" +
                        "           > Name of animal : ${consult.animalName}\n" +
                        "           > Age of animal : ${consult.animalAge}\n" +
                        "           > Sex of animal : ${consult.animalSex}\n" +
                        "           > Specie of animal : ${consult.animalSpecie.specieName}\n" +
                        "           CHECK UP DATA       \n\n" +
                        "           > Check up status : ${checkUp.status}\n\n")

            }
            doCheckUp(consultations, checkUp, examination = Examination("", 0.0), treatment)
        }
        fun doCheckUp(consultations : MutableList<Animal>, checkUp : CheckUp, examination: Examination, treatment: Treatment){
            if(consultations.isNotEmpty()){
                val firstAnimal = consultations.removeAt(0)
                processCheckUp(firstAnimal, checkUp, examination, treatment)
            }
            else {
                Utility.printMessage("No animal in the consultations queue.\n")
            }

        }
        fun processCheckUp(animal : Animal,  checkUp : CheckUp, examination : Examination, treatment : Treatment){
            Utility.printMessage("PROCESS CHECK UP")
            println("In process: ${animal.animalId} by client : ${animal.client.clientId}, ${animal.client.clientName}\n\n")

            println("Examinations?\n Y/y - Yes\n N/n - Not\n\n")
            var optionExamination = sc.nextLine()

            when(optionExamination.lowercase(Locale.getDefault())){
                "y" -> {
                    ExaminationService.doExamination(animal, checkUp, treatment)
                }
                "n" -> {
                    TreatmentService.defineTreatment(animal, checkUp, examination)
                }
                else -> {
                    Utility.printMessage("Sorry, however this option's no existent.\n")
                }
            }
        }
        override fun doCalculation(number : Int): Double {
            val value = 250.00
            return value
        }
    }
}