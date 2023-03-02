import java.sql.DriverManager
import kotlin.math.pow

fun main() {
    val jdbcUrl = "jdbc:mariadb://localhost:3306/db_lookup_educa"
    val connection = DriverManager.getConnection(jdbcUrl, "root", "")
    while (true) {
        val input = inetAton(readln())
        val startT = System.currentTimeMillis()


        val query =
            connection.prepareStatement("SELECT city, stateprov FROM DbIp_Lookup_Educa where $input between ip_start_num and ip_end_num order by ip_start_num desc limit 1")
        val result = query.executeQuery()


        while (result.next()) {
            println(result.getString("city"))
            println(result.getString("stateprov"))
        }


        val aa = (System.currentTimeMillis() - startT)
        println(aa.toDouble() / 1000)

    }
}

fun inetAton(ip: String): Long {
    var output = 0.0
    val holder = ip.split(".")

    for (i in holder.indices) output += holder[i].toDouble() * 256.0.pow(holder.size - 1 - i)

    return output.toLong()
}

