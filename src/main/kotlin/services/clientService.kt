package services

import application.doFirstInteraction
import entities.Animal
import entities.Client
import enumerations.animalSituation
import enumerations.clientStatus
import utils.Utility
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class clientService {
    companion object{
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
        val sc = Scanner(System.`in`)
        val clients : MutableList<Client> = ArrayList()
        val animalClients : MutableList<Animal> = ArrayList()
        fun getClientList(): MutableList<Client> {
            return clients
        }
        fun getAnimalClientList(): MutableList<Animal> {
            return animalClients
        }
        fun interactsFirstClient() {
            sc.nextLine()
            Utility.printMessage("FIRST INTERACTION")
            println("Are you already registered in the system?\n Y/y - yes \n N/n - not\n\n")
            var option = sc.nextLine()

            when(option.lowercase(Locale.getDefault())){
                "y" -> {
                    loginClient()
                }
                "n" -> {
                    recordClient()
                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no-existent.\n")
                }
            }
        }
        fun generateId(): Int {
            var num = 0

            var enter = Random.nextInt(100000, 1000000)
            var help = true

            while (enter.toInt() != 1) {
                for (i in 0 until clients.size) {
                    if (enter == clients[i].clientId) {
                        help = false
                    }
                }

                if (help) {
                    return enter
                } else {
                    enter = Random.nextInt(10, 1000000)
                }
            }

            return enter
        }
        fun recordClient(){
            Utility.printMessage("CUSTOMER REGISTRATION")
            var id = generateId()
            println("Name:")
            var name = sc.nextLine()
            println("Address:")
            var address = sc.nextLine()
            println("Email:")
            var email = sc.nextLine()
            println("Postal code:")
            var postalcode = sc.nextInt()
            sc.nextLine()
            println("Username to login in system:")
            var username = sc.nextLine()
            println("Password to login in system:")
            var password = sc.nextLine()

            val client = Client(id, name, address, email, postalcode, username, password, clientStatus.REGISTERED)

            clients.add(client)

            proofRegisterCustomer(client)
        }

        fun proofRegisterCustomer(client : Client){
            Utility.printMessage("PROOF OF REGISTRATION\n\n" +
                    "           CLIENT DATA             \n\n" +
                    "           > Id : ${client.clientId}\n" +
                    "           > Name : ${client.clientName}\n" +
                    "           > Address : ${client.clientAddress}\n" +
                    "           > Email : ${client.clientEmail}\n" +
                    "           > Postal Code : ${client.postalCode}\n\n" +
                    "            OPERATION DATA             \n\n" +
                    "           > Date/time : ${formattedDateTime}\n" +
                    "           > Operation ID : ${UUID.randomUUID()}\n\n")
            doFirstInteraction()

        }
        fun loginClient(){
            var chances = 3
            Utility.printMessage("CUSTOMER LOGIN")
            println("Enter your id :")
            var id = sc.nextInt()

            val customerFound = clients.find {it.clientId == id}

            if (customerFound != null){
                Utility.printMessage("Customer found successfully!\n")
                println("Please, enter your username and password")

                do{
                    sc.nextLine()
                    println("Username :")
                    var username = sc.nextLine()
                    println("Password :")
                    var password = sc.nextLine()

                    val customerAuthenticated = clients.find {it.username == username && it.password == password}

                    if (customerAuthenticated != null){
                        interactsClient(customerAuthenticated)
                        break;
                    }
                    else{

                        Utility.printMessage("Username or password not recognized. Chances = {$chances}")
                        chances--
                    }
                    if (chances == 0){
                        doFirstInteraction()
                        break
                    }
                } while (chances > 0)
            }
            else {
                Utility.printMessage("Customer not found.\n")
                doFirstInteraction()
            }
        }
        fun interactsClient(client: Client){
            Utility.printMessage("PAGE CUSTOMER\n\n" +
                    "Welcome, dearest ${client.clientName}\n")
            println("       CLIENT \n\n" +
                    "    1 - Query your data\n" +
                    "    2 - Change your data\n" +
                    "    3 - Remove your registration\n" +
                    "       ANIMAL      \n" +
                    "    4 - Go to animal features\n\n" +
                    "       SYSTEM MANAGEMENT   \n\n" +
                    "    5 - Return to the top  \n" +
                    "    6 - Logout             \n")
                var option = sc.nextInt()
            when(option){
                1 -> {
                    queryData(client)
                }
                2 -> {
                    changeData(client)
                }
                3 -> {
                    removeRegistration(client)
                }
                4 -> {
                    animalService.interactsAnimal(client, animalClients)
                }
                5 -> {
                    doFirstInteraction()

                }
                6 -> {
                    Utility.printMessage("\"It was a pleasure spending these simple\n" +
                            "moments with you, see you later!\n")
                    System.exit(0)
                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no existent.\n")
                    interactsClient(client)
                }
            }

        }
        fun queryData(client : Client){
            Utility.printMessage("QUERY CLIENT DATA\n\n" +
                    "           CLIENT DATA             \n\n" +
                    "           > Id : ${client.clientId}\n" +
                    "           > Name : ${client.clientName}\n" +
                    "           > Address : ${client.clientAddress}\n" +
                    "           > Email : ${client.clientEmail}\n" +
                    "           > Postal Code : ${client.postalCode}\n" +
                    "           > Username : ${client.username}\n")
            if (client.status == clientStatus.ACTIVE){
                getAnimalClientList()
            }
        }
        fun changeData(client : Client){
            Utility.printMessage("CHANGE DATA")
            Utility.printMessage("Dearest, you´re allowed to change your address, " +
                                "zip code, email, username and password.\n        ")
            println("Select :\n" +
                    "A/a - address :\n" +
                    "Z/z - zip code :\n" +
                    "E/e - email :\n" +
                    "U/u - username :\n" +
                    "P/p - password :\n")
            var option = sc.nextLine()
            when(option.lowercase(Locale.getDefault())){
                "a" -> {
                    println("New address:")
                    val address = sc.nextLine()
                    client.clientAddress = address
                }
                "z" -> {
                    println("New zip code:")
                    val zipcode = sc.nextInt()
                    client.postalCode = zipcode
                }
                "e" -> {
                    println("New email:")
                    val email = sc.nextLine()
                    client.clientEmail = email
                }
                "u" -> {
                    println("New username:")
                    val username = sc.nextLine()
                    client.username = username
                }
                "p" -> {
                    println("New password:")
                    val password = sc.nextLine()
                    client.password = password
                }
                else -> {
                    Utility.printMessage("Sorry, however this option´s no-existent.\n")
                    changeData(client)
                }
            }
            interactsClient(client)
        }
        fun removeRegistration(client: Client){
            Utility.printMessage("REGISTRATION REMOVAL\n\n")
            var validation = false
            for (animal in animalClients) {
                if (animal.status != animalSituation.HEALTHY || animal.status != animalSituation.CURED) {
                    validation = true
                }
            }
            if (validation){
                Utility.printMessage("Sorry, request impossible to fulfill.\n")
                println("Id \t Name \t Sex \t Status\n\n")
                for (animal in clientService.animalClients) {
                    println("${animal.animalId} - ${animal.animalName} - \n" +
                            "${animal.animalSex} - ${animal.status}\n\n")
                }
                println("\n")
            }
            else {
                var number = 2
                var password : String
                Utility.printMessage("Confirm your password:")
                do{
                    println("Password:")
                    password = sc.nextLine()
                    number--
                } while (number > 0)
                val correctPassword = clients.find {it.password == password}

                if (correctPassword != null){
                    Utility.printMessage("Removal successfully!\n")
                    clients.remove(correctPassword)
                }
                else {
                    println("Password not recognized.\n")
                }
            }

        }
    }
}