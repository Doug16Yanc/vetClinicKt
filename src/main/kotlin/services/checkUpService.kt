package services

import entities.Animal
import entities.CheckUp
import entities.Client
import enumerations.CheckupStatus
import repositories.Calculate
import utils.Utility

import java.util.*

class CheckUpService {
    companion object : Calculate{
        val sc = Scanner(System.`in`)
        val consultations : MutableList<Animal> = ArrayList()
        fun getCheckUp(): MutableList<Animal> {
            return consultations
        }
        fun performCheckUp(client : Client, animal: MutableList<Animal>){

            Utility.printMessage("CONSULTATION\n\n" +
                    "Dearest ${client.clientName}, depending on the case you suspect, mark the\n" +
                    "check up as an emergency and your pet will enter the priority queue.\n")

            println("Enter id animal :")
            var id = sc.nextInt()


            val foundAnimal = ClientService.animalClients.find {it.animalId == id}

            if (foundAnimal != null) {
                sc.nextLine()
                println("Status check up:\n E/e - Emergency\n C/c - Conventional\n")
                var option = sc.nextLine()

                when (option.lowercase(Locale.getDefault())) {
                    "e" -> {
                        consultations.add(0, foundAnimal)
                        Utility.printMessage("The animal, ${foundAnimal.animalId}, " +
                                            "${foundAnimal.animalName} was " +
                                            "referred to emergency.\n")
                        val checkUp = CheckUp(0.0, CheckupStatus.EMERGENCY)

                    }

                    "c" -> {
                        consultations.add(foundAnimal)
                        Utility.printMessage("The animal, ${foundAnimal.animalId}, " +
                                            "${foundAnimal.animalName} was " +
                                            "referred to conventional chech up.\n")
                        val checkUp = CheckUp(0.0, CheckupStatus.CONVENTIONAL)

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
        fun listConsultation(){
            Utility.printMessage("CONSULTATIONS FILE")
            for (consult in consultations){
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
                        "           > Check up status : ${consult.status}\n\n")
            }
            doCheckUp(consultations)
        }
        fun doCheckUp(consultations : MutableList<Animal>){
            if(consultations.isNotEmpty()){
                val firstAnimal = consultations.removeAt(0)
                processCheckUp(firstAnimal)
            }
            else {
                Utility.printMessage("No animal in the consultations queue.\n")
            }
        }
        fun processCheckUp(animal : Animal){
            Utility.printMessage("PROCESS CHECK UP")
            println("Examinations?\n Y/y - Yes\n N/n - Not\n\n")
            var optionExamination = sc.nextLine()

            when(optionExamination.lowercase(Locale.getDefault())){
                "c" -> {
                    ExaminationService.doExamination()
                }
                "h" -> {
                    TreatmentService.defineTreatment(animal)
                }
                else -> {
                    Utility.printMessage("Sorry, however this option's no existent.\n")
                }
            }
        }
        override fun doCalculation(): Double {
            var value = 0.0
            value += 250.00
            return value
        }
    }


}