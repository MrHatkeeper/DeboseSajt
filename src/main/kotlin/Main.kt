import java.sql.DriverManager

fun main() {
    val jdbcUrl = "jdbc:mariadb://localhost:3306/mesto"
    val connection = DriverManager.getConnection(jdbcUrl, "root", "admin")

    val startT = System.currentTimeMillis()

    val query = connection.prepareStatement("SELECT id,ip_start, ip_end FROM dbip_lookup_educa")
    val result = query.executeQuery()

    while (result.next()) {
        val start = result.getString("ip_start")
        val end = result.getString("ip_end")
        val id = result.getString("id").toInt()

        val aaa = bogus(start)
        val bbb = bogus(end)

        val a = connection.prepareStatement("update dbip_lookup_educa set ip_start_num = $aaa, ip_end_num = $bbb where id = $id")
        a.executeQuery()
    }


    println(System.currentTimeMillis() - startT)
}

fun ipToBinary(ip: String): Long {
    val holder = ip.replace(".", "")
    return Integer.toBinaryString(holder.toInt()).toLong()
}

fun bogus(num: String): String {
    var output = ""
    val holder = num.split(".")
    for (i in holder) {
        var aaaa = i
        while (aaaa.length < 3) aaaa = "0$aaaa"
        output += aaaa
    }
    return output
}

