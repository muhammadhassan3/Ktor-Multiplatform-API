package com.muhammhassan.plugins

import com.muhammhassan.repository.database.scheme.Target
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConfig {
    fun init(username: String, password: String){
        val driver = "com.mysql.cj.jdbc.Driver"
        val url = "jdbc:mysql://aws.connect.psdb.cloud/review-performance-monitor?sslMode=VERIFY_IDENTITY"
        Database.connect(url, driver, username, password)

        transaction {
            SchemaUtils.createMissingTablesAndColumns(Target)
        }
    }
}