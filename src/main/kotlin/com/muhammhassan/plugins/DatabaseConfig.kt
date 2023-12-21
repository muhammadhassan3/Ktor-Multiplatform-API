package com.muhammhassan.plugins

import com.muhammhassan.repository.database.scheme.Target
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConfig {
    fun init(){
        val driver = "com.mysql.cj.jdbc.Driver"
        val username="wm69287bns8agrlkt2zb"
        val password = "pscale_pw_El8WAYfMozg8vcPFO9JxEZanyIMZqCb5tiPOFCCTraY"
        val url = "jdbc:mysql://aws.connect.psdb.cloud/review-performance-monitor?sslMode=VERIFY_IDENTITY"
        Database.connect(url, driver, username, password)

        transaction {
            SchemaUtils.create(Target)
        }
    }
}