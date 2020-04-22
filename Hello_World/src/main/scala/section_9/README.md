# Section 9: Working with common patterns in akka

## Section 9.1: Balancing workloads across nodes
Balancing dispatcher dispatches a message to an actor that is idle.
all actors are shared by the mailbox.

Requestor actor
   |                                |----------
   |                                | Worker 1|
   > Mailbox   -> Mastor Actor  --->| Worker 2|
                                    |----------

Worker 1 will request work from the mastor actor and mastor will 
give worker 1 an assignment. Worker1 will respond when the work is done

## Section 9.2: Throttling messages
Avoids sending messages at a high rate.

* the throttler is based on a timer
* send specifc number of messages after every interval
* if more messages are received, they will be send during the next interval
