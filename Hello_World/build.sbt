name := "Hello_World"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq (
  "com.typesafe.akka" %% "akka-actor" % "2.6.0",
    // https://mvnrepository.com/artifact/com.typesafe.akka/akka-persistence
  "com.typesafe.akka" %% "akka-persistence" % "2.6.0",
  "com.typesafe.akka" %% "akka-persistence-query" % "2.6.0",
  "com.typesafe.akka" %% "akka-remote" % "2.6.0",
  "com.typesafe.akka" %% "akka-cluster" % "2.6.0",
  "com.typesafe.akka" %% "akka-cluster-metrics" % "2.6.0",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.6.0",
  "com.typesafe.akka" %% "akka-cluster-sharding" % "2.6.0",
  "org.iq80.leveldb" % "leveldb" % "0.9",
  "io.netty" % "netty" % "3.10.6.Final",
  "io.aeron" % "aeron-driver" % "1.26.0",
  "io.aeron" % "aeron-client" % "1.26.0",

)