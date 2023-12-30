package services

import entities.Client
import utils.Utility
import java.util.*
import kotlin.random.Random

class animalService {
    companion object{
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
        fun interactsAnimal(client : Client){
            Utility.printMessage("FIRST INTERACTION ON THIS ANIMAL PAGE\n")
            println("           1 - Register animal     \n" +
                    "           2 - Change animal data  \n" +
                    "           3 - remove animal registration\n" +
                    "           4 - Query animal data       \n" +
                    "           5 - List your animals       \n" +
                    "           6 - Return to your page     \n")
            var option = sc.nextInt()

            when(option){
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
                    queryAnimalData()
                }
                5 -> {
                    listAnimals()
                }
                6 -> {
                    clientService.interactsClient(client)
                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no-existent.\n")
                }
            }
        }
        fun recordAnimal(client: Client){
            Utility.printMessage("ANIMAL REGISTRATION OF ID CLIENT : ${client.clientId}")
        }
        fun changeDataAnimal(){

        }
        fun removeAnimal(){

        }
        fun queryAnimalData(){

        }
        fun listAnimals(){

        }
    }
}