package services

import entities.Animal
import entities.Client
import utils.Utility

import java.util.*

class checkUpService {
    companion object{
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

            val foundAnimal = clientService.animalClients.find {it.animalId == id}

            if (foundAnimal != null) {
                println("Status check up:\n E/e - Emergency\n C/c - Conventional\n")
                var option = sc.nextLine()

                when (option.uppercase(Locale.getDefault())) {
                    "e" -> {
                        consultations.add(0, foundAnimal)
                        Utility.printMessage("The animal, ${foundAnimal.animalId}, " +
                                            "${foundAnimal.animalName} was " +
                                            "referred to emergency.\n")
                    }

                    "c" -> {
                        consultations.add(foundAnimal)
                        Utility.printMessage("The animal, ${foundAnimal.animalId}, " +
                                            "${foundAnimal.animalName} was " +
                                            "referred to emergency.\n")
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

        }
    }
}