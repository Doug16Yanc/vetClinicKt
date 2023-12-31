package services

import entities.Animal
import entities.Client
import entities.Specie
import enumerations.animalSex
import enumerations.animalSituation
import enumerations.clientStatus
import utils.Utility
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random

class animalService {
    companion object{
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
        val sc = Scanner(System.`in`)
        fun generateId(): Int {
            var num = 0

            var enter = Random.nextInt(100000, 1000000)
            var help = true

            while (enter.toInt() != 1) {
                for (i in 0 until clientService.animalClients.size) {
                    if (enter == clientService.animalClients[i].animalId) {
                        help = false
                    }
                }

                if (help) {
                    return enter
                } else {
                    enter = Random.nextInt(10, 1000000)
                }
            }

            return enter
        }
        fun interactsAnimal(client: Client, animal: MutableList<Animal>){
            do {
                Utility.printMessage("FIRST INTERACTION ON THIS ANIMAL PAGE\n")
                println(    "           1 - Register animal     \n" +
                            "           2 - Change animal data  \n" +
                            "           3 - remove animal registration\n" +
                            "           4 - Query animal data       \n" +
                            "           5 - List your animals       \n" +
                            "           6 - Schedule a check up     \n" +
                            "           7 - Return to your page     \n"
                )
                var option = sc.nextInt()

                when (option) {
                    1 -> {
                        recordAnimal(client)
                    }

                    2 -> {
                        changeDataAnimal(client)
                    }

                    3 -> {
                        removeAnimal()
                    }

                    4 -> {
                        queryAnimalData()
                    }

                    5 -> {
                        listAnimals()
                    }

                    6 -> {
                        checkUpService.performCheckUp(client, animal)
                    }

                    7 -> {
                        clientService.interactsClient(client)
                        break
                    }

                    else -> {
                        Utility.printMessage("Sorry, however this option´s no-existent.\n")
                    }
                }
            } while(true)
        }
        fun recordAnimal(client: Client){
            Utility.printMessage("ANIMAL REGISTRATION OF ID CLIENT : ${client.clientId}")
            var id = generateId()
            sc.nextLine()
            println("Name of animal:")
            var name = sc.nextLine()
            println("Age:")
            var age = sc.nextInt()
            sc.nextLine()
            println("Sex:\nM/m-Male\nF/f-Female:")
            var sexInput = sc.nextLine()
            var sex = when (sexInput.uppercase(Locale.getDefault())) {
                "M" -> animalSex.MALE
                "F" -> animalSex.FEMALE
                else -> animalSex.NO_IDENTIFICATION
            }
            println("Specie:")
            var specie = sc.nextLine()

            client.status = clientStatus.ACTIVE

            val animal = Animal(client, id, name, age, sex, Specie(specie), animalSituation.HEALTHY)
            clientService.animalClients.add(animal)
            proofAnimalRecord(client, animal)
        }
        fun proofAnimalRecord(client: Client, animal : Animal){
            Utility.printMessage("PROOF OF REGISTRATION ANIMAL\n\n" +
                    "           > Id of client : ${client.clientId}\n" +
                    "           > Name of client : ${client.clientName}\n" +
                    "           > Address : ${client.clientAddress}\n" +
                    "           > Postal code : ${client.postalCode}\n" +
                    "           > Client status : ${client.status}\n\n" +
                    "           ANIMAL DATA         \n\n" +
                    "           > Id of animal : ${animal.animalId}\n" +
                    "           > Name of animal : ${animal.animalName}\n" +
                    "           > Age of animal : ${animal.animalAge}\n" +
                    "           > Sex of animal : ${animal.animalSex}\n" +
                    "           > Specie of animal : ${animal.animalSpecie.specieName}\n" +
                    "           > Status of animal : ${animal.status}\n\n" +
                    "           OPERATION DATA              \n\n" +
                    "           > Date and time : ${formattedDateTime}\n" +
                    "           > Operation id : ${UUID.randomUUID()}\n\n")

        }
        fun changeDataAnimal(client : Client){
            Utility.printMessage("CHANGE ANIMAL DATA")
            Utility.printMessage("Dearest, you´re allowed to change the animal name and, " +
                    "age.\n        ")
            println("Enter animal id:")
            var id = sc.nextInt()

            val foundAnimal = clientService.animalClients.find {it.animalId == id}

            if (foundAnimal != null) {
                println(
                    "Select :\n" +
                            "N/n - Name :\n" +
                            "A/a - Age :\n"
                )
                var option = clientService.sc.nextLine()
                when (option.lowercase(Locale.getDefault())) {
                    "a" -> {
                        println("New name:")
                        val name = sc.nextLine()
                        foundAnimal.animalName = name
                    }

                    "z" -> {
                        println("New age:")
                        val age = sc.nextInt()
                        foundAnimal.animalAge = age
                    }

                    else -> {
                        Utility.printMessage("Sorry, however this option´s no-existent.\n")
                        changeDataAnimal(client)
                    }
                }
                proofAnimalRecord(client, foundAnimal)
            }
            else {
                Utility.printMessage("Animal not found.\n")
            }
        }
        fun removeAnimal(){
            Utility.printMessage("REGISTRATION ANIMAL REMOVAL")
            println("Enter id animal:")
            var id = sc.nextInt()

            val foundAnimal = clientService.animalClients.find {it.animalId == id}

            if (foundAnimal != null) {
                if (foundAnimal.status == animalSituation.HEALTHY || foundAnimal.status == animalSituation.CURED){
                    Utility.printMessage("Removal successfully!\n")
                    clientService.animalClients.remove(foundAnimal)
                }
                else {
                    Utility.printMessage("Sorry, but your pet is not in a position to have its registration removed.\n")
                }
            }
            else {
                Utility.printMessage("Animal not found.\n")
            }
        }
        fun queryAnimalData(){
            Utility.printMessage("ANIMAL DATA")
            println("Enter id animal:")
            var id = sc.nextInt()

            val foundAnimal = clientService.animalClients.find {it.animalId == id}

            if (foundAnimal != null){
                Utility.printMessage("      > Id of animal : ${foundAnimal.animalId}\n" +
                        "                   > Name of animal : ${foundAnimal.animalName}\n" +
                        "                   > Age of animal : ${foundAnimal.animalAge}\n" +
                        "                   > Sex of animal : ${foundAnimal.animalSex}\n" +
                        "                   > Specie of animal : ${foundAnimal.animalSpecie.specieName}\n" +
                        "                   > Status of animal : ${foundAnimal.status}\n")
            }
            else {
                Utility.printMessage("Animal not found.\n")
            }
        }
        fun listAnimals(){
            Utility.printMessage("LIST OF YOUR ANIMALS:")
            clientService.getAnimalClientList()
        }
    }
}