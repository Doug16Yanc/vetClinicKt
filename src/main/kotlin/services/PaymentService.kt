package services

import entities.Animal
import entities.CheckUp
import entities.Examination
import entities.Treatment
import enumerations.PaymentType
import utils.Utility
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PaymentService {
    companion object{
        val sc = Scanner(System.`in`)
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
        fun doPayment(animal : Animal, checkUp : CheckUp, examination : Examination, treatment: Treatment, quantity : Int) {
            var valueCheck = CheckUpService.doCalculation(1)
            checkUp.value = valueCheck
            var valueExamination = ExaminationService.doCalculation(quantity)
            examination.value = valueExamination
            var sumExpenditure: Double = 0.0
            Utility.printMessage("PAYMENT EXPENDITURES")
            println("Check up  value : US$ ${valueCheck}\n\n")
            println("Examination(s) value : US$ ${valueExamination}\n")
            sumExpenditure = valueCheck + valueExamination
            println("Total expenditure: US$ ${sumExpenditure}\n")
            println("Payment method:\n\n M/m - money\n C/c - card \n B/b - bank transfer\n\n")
            var method = sc.nextLine()
            when (method.lowercase(Locale.getDefault())) {
                "m" -> {
                    PaymentType.CASH
                    println("Enter value of money:")
                    var value = sc.nextDouble()
                    if (value < sumExpenditure) {
                        println("Sorry, this value´s than value of the expenditures.\n")
                    } else if (value > sumExpenditure) {
                        var change = value - sumExpenditure
                        println("Change of money: US$ ${change}\n\n")
                        Utility.printMessage("Thank you and come back anytime.\n")

                    } else {
                        Utility.printMessage("Thank you and come back anytime.\n")
                    }
                }

                "c" -> {
                    PaymentType.CARD
                    println("Insert or tap the card.\n")
                    Utility.printMessage("Thank you and come back anytime.\n")
                }

                "b" -> {
                    PaymentType.BANK_TRANSFER
                    println("Initiate the transfer to the corresponding account code of the clinic.\n")
                    Utility.printMessage("Thank you and come back anytime.\n")
                }

                else -> {
                    Utility.printMessage("Payment method not recognized.\n")
                }
            }
            proofPayment(animal, checkUp, examination, treatment)
        }
        fun proofPayment(animal : Animal, checkUp: CheckUp, examination: Examination, treatment: Treatment){
            Utility.printMessage("PROOF OF PAYMENT CLIENT\n\n" +
                    "           > Id of client : ${animal.client.clientId}\n" +
                    "           > Name of client : ${animal.client.clientName}\n" +
                    "           > Client status : ${animal.client.status}\n\n" +
                    "           ANIMAL DATA         \n\n" +
                    "           > Id of animal : ${animal.animalId}\n" +
                    "           > Name of animal : ${animal.animalName}\n" +
                    "           CHECK UP DATA       \n\n" +
                    "           > Check up status : ${checkUp.status}\n" +
                    "           > Check up value : US$ ${checkUp.value}n\"" +
                    "           EXAMINATION DATA    \n\n" +
                    "           > Examination value : US$ ${examination.value}\n\n" +
                    "           TREATMENT DATA          \n\n" +
                    "           > Treatment type : ${treatment.treatmentType}\n\n" +
                    "           OPERATION DATA              \n\n" +
                    "           > Date and time : ${formattedDateTime}\n" +
                    "           > Operation code : ${UUID.randomUUID()}\n\n" +
                    "The veterinary clinic Dooglahs Gesundheitsklinik thank you for choosing us.\n")
        }
    }
}