Normal actor

Let's imagine we have a normal actor that increments a counter each time a msg is received
What if it crashes, then we loose state.

We need to presist state. -> Db of file

Akka persistence:
We store events from which the state can be stored

Sender -> Actor -> Event -> DB (Event)

Only persistend events are replayed


Persistent Actor --> Persistent View (deprecated in 2.4 -> replace with persistent query)
          \             /
           \           /
            \         /
            
              Journal (for prod : JDBC or cassandra) / Snapshot Store (used to increase performance time)   