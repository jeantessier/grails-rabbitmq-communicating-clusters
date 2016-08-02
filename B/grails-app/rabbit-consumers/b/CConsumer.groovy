package b

class CConsumer {

    static rabbitConfig = [
        queue: "C_to_B"
    ]

    def handleMessage(message) {
        println System.getProperty("node.name") + ": message from C: \"" + message + "\""
    }

}
