package c

import com.budjb.rabbitmq.consumer.MessageContext

class AConsumer {

    static rabbitConfig = [
        queue: "A_to_C"
    ]

    def handleMessage(String message, MessageContext context) {
        println System.getProperty("node.name") + ": message from A: \"" + message + "\""
    }

}
