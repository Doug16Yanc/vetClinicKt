package services

import entities.Examination
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
        fun doExamination(){
            Utility.printMessage("EXAMINATION PAGE")
            println("Quantity of examinations:")
            var quantity = sc.nextInt()

            do {
                println("Description of this examination:")
                var examDescription = sc.nextLine()
                var value = doCalculation()
                val examination = Examination(examDescription, value)
                exams.add(examination);
                quantity--
            } while(quantity > 0)
        }
        override fun doCalculation(): Double {
            var value = 0.0
            value += 150.00
            return value
        }

    }
}