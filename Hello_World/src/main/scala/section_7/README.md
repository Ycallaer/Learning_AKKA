# Section 7: Working with akka streams

## Section 7.1 : Intro
Problems in big data processing:
* Blocking: Pull based system (no data to pull)
* back pressure: Push based system

Source:
* Is the input of the stream
* It has the output channel and no input channel

Sink
* Is the endpoint of the stream
* It has the input channel and no output channel
eg: store data in database or sync data in file

Flow:
Use to apply transformations on the incoming data and put the data into a sink
