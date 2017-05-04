package c

import com.budjb.rabbitmq.consumer.MessageContext

class BConsumer {

    static rabbitConfig = [
        queue: "B_to_C"
    ]

    def handleMessage(String message, MessageContext context) {
        log.info System.getProperty("node.name") + ": message from B: \"" + message + "\""
    }

}
