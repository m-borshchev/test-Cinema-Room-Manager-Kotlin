fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readln().toInt()

    val cinema = MutableList(rows) { MutableList(seatsPerRow) { 'S' } }
    var currentIncome = 0
    var ticketsSold = 0

    fun printCinema() {
        println("\nCinema:")
        print("  ")
        for (seat in 1..seatsPerRow) print("$seat ")
        println()
        for (row in 1..rows) {
            print("$row ")
            for (seat in 1..seatsPerRow) {
                print("${cinema[row - 1][seat - 1]} ")
            }
            println()
        }
    }

    fun totalIncome(): Int {
        val totalSeats = rows * seatsPerRow
        return if (totalSeats <= 60) {
            totalSeats * 10
        } else {
            val frontHalf = rows / 2
            val backHalf = rows - frontHalf
            (frontHalf * seatsPerRow * 10) + (backHalf * seatsPerRow * 8)
        }
    }

    fun buyTicket() {
        while (true) {
            println("Enter a row number:")
            val row = readln().toIntOrNull()
            println("Enter a seat number in that row:")
            val seat = readln().toIntOrNull()

            if (row == null || seat == null || row !in 1..rows || seat !in 1..seatsPerRow) {
                println("Wrong input!")
                continue
            }
            if (cinema[row - 1][seat - 1] == 'B') {
                println("That ticket has already been purchased!")
                continue
            }
            val price = if (rows * seatsPerRow <= 60 || row <= rows / 2) 10 else 8
            println("Ticket price: \$$price")
            cinema[row - 1][seat - 1] = 'B'
            currentIncome += price
            ticketsSold++
            break
        }
    }

    fun showStats() {
        val total = rows * seatsPerRow
        val percent = ticketsSold.toDouble() / total * 100
        println("Number of purchased tickets: $ticketsSold")
        println("Percentage: %.2f%%".format(percent))
        println("Current income: \$$currentIncome")
        println("Total income: \$${totalIncome()}")
    }

    while (true) {
        println("""
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
        """.trimIndent())
        when (readln()) {
            "1" -> printCinema()
            "2" -> buyTicket()
            "3" -> showStats()
            "0" -> return
        }
    }
}