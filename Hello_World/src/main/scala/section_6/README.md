# Testing actors 6-1

# Testing parent-child relationship 6-2

# Testing finite state machine FSM 6-3
Revisited

(db operation)
Disconnected -------- connect ----> Connected (db operation)
    ^                                    ^
    |                                    | 
    --------------------------------------

# Multinode testing 6-4
Multinode testing means we want to run coordinated test on multpile actors on different JVMs

The multi node parts
* test kit
** Test conductor
*** Multi-node spec
**** Sbt multionde JVM