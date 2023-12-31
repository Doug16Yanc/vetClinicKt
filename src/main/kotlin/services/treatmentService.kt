package services

import entities.Animal
import enumerations.treatmentType
import utils.Utility
import java.util.*

class treatmentService {
    companion object{
        val sc = Scanner(System.`in`)
        fun doClinicalTreatment(animal : Animal){
            treatmentType.CLINICAL
            println("Examinations?\n Y/y - yes\n\n N/n - not")
            var answer = sc.nextLine()

            when(answer.lowercase(Locale.getDefault())){
                "y" -> {
                    examinationService.doExamination()
                }
                "n" -> {

                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no-existent.\n")
                }
            }
        }
        fun doHomeTreatment(animal : Animal){
            treatmentType.HOME
            println("Examinations?\n Y/y - yes\n\n N/n - not")
            var answer = sc.nextLine()

            when(answer.lowercase(Locale.getDefault())){
                "y" -> {
                    examinationService.doExamination()
                }
                "n" -> {

                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no-existent.\n")
                }
            }
        }
    }
}