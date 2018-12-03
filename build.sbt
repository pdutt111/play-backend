name := "play-backend"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

libraryDependencies += jdbc
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.21"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"

// https://mvnrepository.com/artifact/org.apache.commons/commons-email
libraryDependencies += "org.apache.commons" % "commons-email" % "1.5"

// https://mvnrepository.com/artifact/javax.mail/javax.mail-api
libraryDependencies += "javax.mail" % "javax.mail-api" % "1.6.0"
libraryDependencies += "javax.activation" % "activation" % "1.1.1"

PlayKeys.devSettings := Seq("play.server.http.port" -> "9001")


