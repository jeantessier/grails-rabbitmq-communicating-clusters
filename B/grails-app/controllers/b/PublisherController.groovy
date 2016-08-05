package b

import grails.converters.*

class PublisherController {

    def rabbitMessagePublisher

    def index() {}

    def send(String messageType, String messageBody) {
        rabbitMessagePublisher.send {
            exchange = grailsApplication.config.rabbit_system.publisher_exchange
            body = getMessageFor(messageType, messageBody)
        }
        flash.message = getMessageFor(messageType, messageBody)

        redirect action: "index"
    }

    def getMessageFor(messageType, messageBody) {
        switch (messageType as MessageType) {
            case MessageType.TEXT:
                return getTextMessage(messageBody)
                break
            case MessageType.JSON:
                return getJsonMessage(messageBody) as String
                break
            case MessageType.MAP:
            default:
                return getMapMessage(messageBody)
                break
        }
    }

    def getTextMessage(messageBody) {
        count + " " + System.getProperty("node.name") + ": " + messageBody
    }

    def getJsonMessage(messageBody) {
        getMapMessage(messageBody) as JSON
    }

    def getMapMessage(messageBody) {
        [
            node: [
                name: System.getProperty("node.name"),
            ],
            body: messageBody,
            count: count,
        ]
    }

    def getCount() {
        if (!request.count) {
            request.count = (servletContext.count ?: 0) + 1
            servletContext.count = request.count
        }
        return request.count
    }

}

enum MessageType {
    TEXT,
    JSON,
    MAP
}
