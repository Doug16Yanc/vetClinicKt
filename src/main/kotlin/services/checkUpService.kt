package services

import entities.Animal
import utils.Utility

class checkUpService {
    companion object{
        val consultations : MutableList<Animal> = ArrayList()
        fun getCheckUp(): MutableList<Animal> {
            return consultations
        }
        fun performCheckUp(){
            Utility.printMessage("CONSULTATION")
            getCheckUp()
            println("\n")
        }
        fun listConsultation(){

        }
    }
}