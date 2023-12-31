package services

import utils.Utility
import java.util.*

class examinationService {
    companion object{
        val sc = Scanner(System.`in`)
        fun doExamination(){
            Utility.printMessage("EXAMINATION PAGE")
            println("Description of this examination:")
            var examination = sc.nextLine()
        }
    }
}