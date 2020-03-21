name := "Hello_World"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq (
  "com.typesafe.akka" %% "akka-actor" % "2.6.0",
    // https://mvnrepository.com/artifact/com.typesafe.akka/akka-persistence
  "com.typesafe.akka" %% "akka-persistence" % "2.6.0",
  "com.typesafe.akka" %% "akka-persistence-query" % "2.6.0",
  "org.iq80.leveldb" % "leveldb" % "0.9"

)