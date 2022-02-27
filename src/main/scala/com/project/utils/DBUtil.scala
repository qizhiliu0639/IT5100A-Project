package com.project.utils

import slick.driver.MySQLDriver.api._
import org.slf4j.LoggerFactory

object DBUtil {

  val logger = LoggerFactory.getLogger(getClass.getSimpleName)
  logger.info("slick test Start")

  val db = Database.forURL(
    url = "jdbc:mysql://localhost:3306/offerDB?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
    driver = "com.mysql.cj.jdbc.Driver",
    user = "root",
    password = "")


}
