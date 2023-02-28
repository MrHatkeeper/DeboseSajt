import java.sql.DriverManager
import kotlin.math.pow

fun main() {
    val jdbcUrl = "jdbc:mariadb://localhost:3306/mesto"
    val connection = DriverManager.getConnection(jdbcUrl, "root", "admin")
    val input = inetAton(readln())
    val startT = System.currentTimeMillis()


    val query =
        connection.prepareStatement("SELECT city, ip_end_num FROM dbip_lookup_educa where $input between ip_start_num and ip_end_num order by ip_start_num desc limit 1")
    val result = query.executeQuery()


    while (result.next()) {
           println(result.getString("city"))
    }


    val aa = (System.currentTimeMillis() - startT)
    println(aa.toDouble()/1000)
}

fun inetAton(ip: String): Long {
    var output = 0.0
    val holder = ip.split(".")

    for (i in holder.indices) output += holder[i].toDouble() * 256.0.pow(holder.size - 1 - i)

    return output.toLong()
}

