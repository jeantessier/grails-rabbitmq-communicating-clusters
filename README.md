# Rabbit System

Multiple clusters communicate with one another through a RabbitMQ instance.

Clusters `A`, `B`, and `C` each have three nodes.  Each node in a cluster:

- publishes messages to that cluster's exchange.
- reads from queues from each other cluster.

If node `A1` publishes a message, it will be handled by a single node in cluster `B` and a single node in cluster `C`.

If nodes in cluster `A` publish multiple messages, they should be distributed amongst the nodes of cluster `B` and also amongst the nodes of cluster `C`.

## Running The System

1. Start RabbitMQ:

       $ ${RABBITMQ_HOME}/sbin/rabbitmq-server

1. Start the clusters

    1. Start the `A` cluster:

           $ for n in 1 2 3
           do
               cd A
               GRAILS_OPTS="-Dnode.name=A$n -Dserver.port=819$n" ./gradlew bootRun
           done

    1. Start the `B` cluster:

           $ for n in 1 2 3
           do
               cd B
               GRAILS_OPTS="-Dnode.name=B$n -Dserver.port=829$n" ./gradlew bootRun
           done

    1. Start the `C` cluster:

           $ for n in 1 2 3
           do
               cd C
               GRAILS_OPTS="-Dnode.name=C$n -Dserver.port=839$n" ./gradlew bootRun
           done

1. Wait for clusters to start.  They are ready when they all show a message saying:

       Grails application running at http://localhost:8?9? in environment: development

1. Send messages

    1. Point your browser to [http://localhost:8191/publisher](http://localhost:8191/publisher)
    1. Repeat
