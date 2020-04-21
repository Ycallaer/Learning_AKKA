## Section 8.1 : Introduction to akka http
Distribution in AKKA

* Distribute across threads
    * Akka Actors
* Distribute across machines
    * Akka Cluster and Akka remote
* Interact with the external world
    * Akka HTTP

### Akka http
* It is not a web framework it is a toolkit for providing and consuming http services
* Implemented on top of akka actor and akka streams
* has full server and client side http stack

### Akka http structure
* Akka http core
* Akka http
* Akka http testkit
* akka http spray json
* akka http xml

## Section 8.2: Client side api
* connection-level client-side api: lowest level api
* host-level client-side api
* request level client side api: most convenient way