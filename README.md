# RabbitMQ System

Multiple clusters communicate with one another through a RabbitMQ instance.

![Overview](https://github.com/jeantessier/grails-rabbitmq-communicating-clusters/raw/master/overview.png)

Clusters `A`, `B`, and `C` each have three nodes.  Each node in a cluster:

- publishes messages to that cluster's exchange.
- reads from queues from each other cluster.

![Topography](https://github.com/jeantessier/grails-rabbitmq-communicating-clusters/raw/master/topography.png)

If node `A1` publishes a message, it will be handled by a single node in cluster `B` and a single node in cluster `C`.

If nodes in cluster `A` publish multiple messages, they should be distributed amongst the nodes of cluster `B` and also amongst the nodes of cluster `C`.

## Running The System

1. Start RabbitMQ:

If you are using Docker:

1. `docker create --name rabbitmq -p 5672:5672 rabbitmq`
1. `docker start rabbitmq`

Or, if you've installed RabbitMQ locally:

   ``` bash
   ${RABBITMQ_HOME}/sbin/rabbitmq-server
   ```

1. Start the clusters:

   ``` bash
   for cluster in A:819 B:829 C:839
   do
       NAME=${cluster%%:*}
       PORT_PREFIX=${cluster##*:}

       for n in 1 2 3
       do
           (cd ${NAME}; GRAILS_OPTS="-Dnode.name=${NAME}${n} -Dserver.port=${PORT_PREFIX}${n}" ./gradlew bootRun) &
       done
   done
   ```

   You can add clusters by adding to line 1: `for cluster in A:819 B:829 C:839`.

   You can change the size of the clusters by adding to line 6: `for n in 1 2 3`.

1. Wait for clusters to start.  They are ready when they all show a message saying:

   ```
   Grails application running at http://localhost:8?9? in environment: development
   ```

1. Send messages:

    1. Point your browser to [http://localhost:8191/publisher](http://localhost:8191/    publisher)
    1. See traces from clusters `B` and `C` that show a message from `A1`
    1. Repeat (traces should move around the nodes in each cluster)

## Stopping The System

You're on your own.  Good luck!
