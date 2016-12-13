package c

import com.budjb.rabbitmq.consumer.MessageContext

class BConsumer {

    static rabbitConfig = [
        queue: "B_to_C"
    ]

    def handleMessage(String message, MessageContext context) {
        println System.getProperty("node.name") + ": message from B: \"" + message + "\""
    }

}
