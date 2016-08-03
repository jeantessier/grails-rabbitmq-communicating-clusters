package c

class PublisherController {

    def rabbitMessagePublisher

    def index() {
        rabbitMessagePublisher.send {
            exchange = grailsApplication.config.rabbit_system.publisher_exchange
            body = message
        }
	render message
    }

    def getMessage() {
        [
            node: [
                name: System.getProperty("node.name"),
            ],
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
