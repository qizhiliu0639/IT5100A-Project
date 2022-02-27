ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.6"

val AkkaVersion = "2.6.18"
val AkkaHttpVersion = "10.2.8"
val slickVersion = "3.3.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "org.slf4j" % "slf4j-nop" % "1.7.36",
  "com.h2database" % "h2" % "2.1.210",
  "mysql" % "mysql-connector-java" % "8.0.25"
)

lazy val root = (project in file("."))
  .settings(
    name := "FinalProject"
  )

//lazy val slick = taskKey[Seq[File]]("Generate Tables.scala")
//slick := {
//  val dir = (Compile / sourceManaged) value
//  val outputDir = dir / "slick"
//  val url = "jdbc:h2:mem:test;INIT=runscript from 'src/main/sql/create.sql'" // connection info
//  val jdbcDriver = "org.h2.Driver"
//  val slickDriver = "slick.jdbc.H2Profile"
//  val pkg = "demo"
//
//  val cp = (Compile / dependencyClasspath) value
//  val s = streams value
//
//  runner.value.run("slick.codegen.SourceCodeGenerator",
//    cp.files,
//    Array(slickDriver, jdbcDriver, url, outputDir.getPath, pkg),
//    s.log).failed foreach (sys error _.getMessage)
//
//  val file = outputDir / pkg / "Tables.scala"
//
//  Seq(file)
//}