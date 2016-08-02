package b

class AConsumer {

    static rabbitConfig = [
        queue: "A_to_B"
    ]

    def handleMessage(message) {
        println System.getProperty("node.name") + ": message from A: \"" + message + "\""
    }

}
