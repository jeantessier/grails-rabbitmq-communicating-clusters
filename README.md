# Rabbit System

Multiple clusters communicate with one another through a RabbitMQ instance.

Clusters `A`, `B`, and `C` each have three nodes.  Each node in a cluster:

- publishes messages to that cluster's exchange.
- reads from queues from each other cluster.

If node `A1` publishes a message, it will be handled by a single node in cluster `B` and a single node in cluster `C`.

If nodes in cluster `A` publish multiple messages, they should be distributed amongst the nodes of cluster `B` and also amongst the nodes of cluster `C`.
