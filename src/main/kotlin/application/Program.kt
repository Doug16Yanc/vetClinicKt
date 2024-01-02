package application

import entities.CheckUp
import entities.Examination
import enumerations.CheckupStatus
import services.CheckUpService
import services.ClientService
import services.VetService
import utils.Utility
import java.util.*


val sc = Scanner(System.`in`)
fun main() {
    doFirstInteraction();
}
fun doFirstInteraction() {
    Utility.printMessage("      WELCOME TO PAGE DOOGLAHS GESUNDHEITSKLINIK      ")
    println(
        "                   Who are you in this page?                   \n\n" +
                "                   V/v - veterinary                    \n" +
                "                   C/c - client                        \n" +
                "                   O/o - Logout                        \n"
    )
    var option = sc.next()


    when(option.lowercase(Locale.getDefault())){
        "v" -> {
            val checkUp = CheckUp(0.0, CheckupStatus.CONVENTIONAL)
            val examination = Examination("", 0.0)
            VetService.loginVet(checkUp, examination)
        }
        "c" -> {
            ClientService.interactsFirstClient()
        }
        "o" -> {
            Utility.printMessage("\"It was a pleasure spending these simple\n" +
                                 "moments with you, see you later!\n")
            System.exit(0)
        }
        else -> {
            Utility.printMessage("Sorry, however this option´s no-existent.\n")
            System.exit(0)
        }
    }
}
