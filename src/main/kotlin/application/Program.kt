package application

import services.clientService
import services.vetService
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
            vetService.loginVet()
        }
        "c" -> {
            clientService.interactsFirstClient()
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
