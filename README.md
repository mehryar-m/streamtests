## Stream Tests

This is a repository that is barely being maintained and it is solely meant for personal experimentation. 

## Pre-requisite

I use confluent 5.5 for my local development. Therefore, it is critical that you download the platform to get most 
of the code to run. Go [here](https://docs.confluent.io/current/quickstart/ce-quickstart.html) to get it.

I also use the kafka binaries. You can get them [here](https://kafka.apache.org/quickstart)

Otherwise, you are required to have Gradle.


## Getting Started

I usually work through the `Makefile`. 
You need to go and edit the paths to get it working for your machine. 
### start confluent
`make start_confluent`
### start jaeger
`make start_jaeger`
### create topics
`make create_topics_*`
