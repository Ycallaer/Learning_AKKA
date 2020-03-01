## Actor ref system

ActorRef (actor reference ):Is the interface of the actor instance
  |  \
  v   \
 Actor \
        > Dead letter: When the actor shuts dwon the ActorRef will point to Dead Letter

## Actor Path

               /User
               /   \
              /     \
          Actor C    Actor A
                       |
                       v
                     Actor B : Full path is /user/Actor A/Actor B

Actor Path references the name of the actor. So if an actor is destroyed and recreated the ActorRef
will differ but the actor Path will stay the same

## Actor Selection
Actor selection is a representation for actor
Actor Selection is created from the actor path (so it stays valid in contrast with ActorRef)