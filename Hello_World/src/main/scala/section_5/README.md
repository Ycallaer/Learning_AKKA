# Akka remoting

* All examples for far have one actor system running on a local machine
* Everything in akka is designed to work in a distributed setting
* Akka remoting is a communication module, connect akka clusters in a peer-to-peer
fashion

## Section 5.1: Remote akka - Refactored
The original lessons included remote akka but this is deprectated in 2.6 and will
be removed in 2.7

Hence the exercise has been adapted to use `Artery remoting` (https://doc.akka.io/docs/akka/current/remoting-artery.html)

The exercise behavior is kept and a message can still be sent between 2 actors

## Section 5.2: Akka cluster

* Akka cluster provided a fault-tolerant decentralized peer-to-peer cluster membership
service with no single point of failure or single point of bottleneck
* Akka cluster uses the gossip protocal and an automatic failure detector

Some terms:
* Node: Logical member of the cluster (hostname / port)
* cluster: set of nodes. cluster will make 1 node leader
* seed node: a scan that can be configured to look for new nodes joining the cluster

Membership lifecycle:
* Node begins by joining (gossip protocol)
* Up
* Leaving
* Existing
* Removed
* Down: When something unepexted happens