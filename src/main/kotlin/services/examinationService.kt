package services

import entities.CheckUp
import entities.Examination
import entities.Animal
import entities.Treatment
import repositories.Calculate
import utils.Utility
import java.util.*

class ExaminationService {
    companion object : Calculate {
        val sc = Scanner(System.`in`)
        val exams : MutableList<Examination> = ArrayList()
        fun getExaminations() : MutableList<Examination>{
            return exams
        }
        fun doExamination(animal : Animal, checkUp : CheckUp, treatment: Treatment){
            Utility.printMessage("EXAMINATION PAGE")
            println("Quantity of examinations:")
            var quantity = sc.nextInt()
            sc.nextLine()
            var examDescription : String = ""
            do {
                println("Description of this examination:")
                examDescription = sc.nextLine()
                quantity--
            } while(quantity > 0)
            var value = doCalculation(quantity)
            val examination = Examination(examDescription, value)
            exams.add(examination);
            PaymentService.doPayment(animal, checkUp, examination, treatment, quantity)

        }
        override fun doCalculation(number : Int): Double {
            var value = 150.00
            value *= number
            return value
        }

    }
}