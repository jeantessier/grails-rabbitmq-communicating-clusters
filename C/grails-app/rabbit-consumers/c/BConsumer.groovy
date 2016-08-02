package c

class BConsumer {

    static rabbitConfig = [
        queue: "B_to_C"
    ]

    def handleMessage(message) {
        println System.getProperty("node.name") + ": message from B: \"" + message + "\""
    }

}
