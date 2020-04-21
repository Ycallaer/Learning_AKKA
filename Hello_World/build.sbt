name := "Hello_World"

version := "0.1"

scalaVersion := "2.12.6"

val akkaVersion = "2.6.0"

libraryDependencies ++= Seq (
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    // https://mvnrepository.com/artifact/com.typesafe.akka/akka-persistence
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http"   % "10.1.11",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11",

  "org.iq80.leveldb" % "leveldb" % "0.9",
  "io.netty" % "netty" % "3.10.6.Final",
  "io.aeron" % "aeron-driver" % "1.26.0",
  "io.aeron" % "aeron-client" % "1.26.0",
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",
  "org.twitter4j" % "twitter4j-stream" % "4.0.3",
  
)