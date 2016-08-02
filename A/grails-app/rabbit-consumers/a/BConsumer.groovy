package a

class BConsumer {

    static rabbitConfig = [
        queue: "B_to_A"
    ]

    def handleMessage(message) {
        println System.getProperty("node.name") + ": message from B: \"" + message + "\""
    }

}
