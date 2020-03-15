## Different routing strategies in AKKA

1. Round Robin Router

outside world -> Messages -> Round Robin Router -> Actor 1 .. 3 (just as normal round robin)

2. Balancing router
outside -> Message -> Balancing Router -> Actor 1..N 

Will try to redistribute work from busy routees to IDLE routess.
All routees share the same mailbox (same physical machine)

3. Smallest Mailbox Router
outside -> Message -> Smallest -> Actor 1..N

Sends messages to the actor with the fewest messages in the mailbox

4. Broadcast router
Forwards the messages to all of the actors

5. Scatter Gather First Complete Router
will send the message to all the routees and will wait for the first response of the routee.
Only the first response will be send back to the sender.Other replies are discarded

6. Consistent hashing router


7. Tail chopping
Sends a message to 1 routee randomly picked. After a small delay sends the message to second one, then to
the thrid one.
Better latency because we wait for the first response.