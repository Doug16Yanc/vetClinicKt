package services

import application.doFirstInteraction
import entities.Vet
import utils.Utility
import java.util.*
import kotlin.collections.ArrayList

class vetService {
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
        fun loginVet(){
            var chances = 3
            Utility.printMessage("LOGIN")
            do{
                println("Username : ")
                var username = sc.nextLine()
                println("Password : ")
                var password = sc.nextLine()

                val vetFound = vets.find {it.username == username && it.password == password}

                if (vetFound != null){
                    interactsVet(vetFound)
                    break
                }
                else {
                    chances--
                    println("Username or password not recognized! Chances = ${chances - 1}")
                }
            } while(chances > 0)
        }
        fun interactsVet(vet: Vet){
            do {
                Utility.printMessage("Welcome, dearest ${vet.vetName}\n")
                println(
                    "           1 - Perform consultation    \n" +
                            "           2 - Issue consultation report\n" +
                            "           3 - Logout                  \n"
                )
                var option = sc.nextInt()

                when (option) {
                    1 -> {
                        checkUpService.consultations
                    }

                    2 -> {
                        checkUpService.listConsultation()
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