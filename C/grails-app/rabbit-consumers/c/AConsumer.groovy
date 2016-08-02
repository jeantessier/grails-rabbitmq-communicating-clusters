package c

class AConsumer {

    static rabbitConfig = [
        queue: "A_to_C"
    ]

    def handleMessage(message) {
        println System.getProperty("node.name") + ": message from A: \"" + message + "\""
    }

}
