package services

import application.doFirstInteraction
import entities.CheckUp
import entities.Examination
import entities.Vet
import utils.Utility
import java.util.*
import kotlin.collections.ArrayList

class VetService {
    companion object{
        val sc = Scanner(System.`in`);
        val vets : MutableList<Vet> = ArrayList()
        fun getVetList(): MutableList<Vet> {
            return vets
        }
        init {
            vets.add(Vet("Dooglahs Dr.", "Berlim-Germany", "doog@gmail.com", 4546363, "Doog", "2030"))
            vets.add(Vet("Leonora Dr.", "Madrid-Spain", "leonora@gmail.com", 8685423, "Leon", "3050"))
        }
        fun loginVet(checkUp: CheckUp, examination : Examination){
            var chances = 3
            Utility.printMessage("LOGIN")
            do{
                println("Username : ")
                var username = sc.nextLine()
                println("Password : ")
                var password = sc.nextLine()

                val vetFound = vets.find {it.username == username && it.password == password}

                if (vetFound != null){
                    interactsVet(vetFound, checkUp, examination)
                    break
                }
                else {
                    chances--
                    println("Username or password not recognized! Chances = ${chances - 1}")
                }
            } while(chances > 0)
        }
        fun interactsVet(vet: Vet, checkUp : CheckUp, examination : Examination){
            do {
                Utility.printMessage("Welcome, dearest ${vet.vetName}\n")
                println(    "           1 - Perform consultation    \n" +
                            "           2 - Issue consultation report\n" +
                            "           3 - Logout                  \n"
                )
                var option = sc.nextInt()

                when (option) {
                    1 -> {
                        CheckUpService.doCheckUp(ClientService.animalClients, checkUp, examination)
                    }

                    2 -> {
                        CheckUpService.listConsultation(checkUp)
                    }

                    3 -> {
                        doFirstInteraction()
                        break
                    }

                    else -> {
                        Utility.printMessage(
                            "Sorry, however this option´s no existent. " +
                                    "The return to the beginning will be carried out\n"
                        )
                        doFirstInteraction()
                    }
                }
            } while(true)
        }

    }
}