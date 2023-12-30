package services

import entities.Animal
import entities.Client
import entities.Specie
import enumerations.animalSex
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
                        changeDataAnimal()
                    }

                    3 -> {
                        removeAnimal()
                    }

                    4 -> {
                        queryAnimalData(animal)
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

            val animal = Animal(client, id, name, age, sex, Specie(specie))
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
                    "           > Specie of animal : ${animal.animalSpecie.specieName}\n\n" +
                    "           OPERATION DATA              \n\n" +
                    "           > Date and time : ${formattedDateTime}\n" +
                    "           > Operation id : ${UUID.randomUUID()}\n\n")

        }
        fun changeDataAnimal(){

        }
        fun removeAnimal(){

        }
        fun queryAnimalData(animal: MutableList<Animal>){
            Utility.printMessage("ANIMAL DATA")

        }
        fun listAnimals(){

        }
    }
}