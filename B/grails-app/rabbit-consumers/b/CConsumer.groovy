package b

import com.budjb.rabbitmq.consumer.MessageContext

class CConsumer {

    static rabbitConfig = [
        queue: "C_to_B"
    ]

    def handleMessage(String message, MessageContext context) {
        println System.getProperty("node.name") + ": message from C: \"" + message + "\""
    }

}
