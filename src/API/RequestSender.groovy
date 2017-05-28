package API

import groovy.json.JsonSlurper

class RequestSender {
    Request request

    RequestSender(Request request) {
        this.request = request
    }

    def getResponse() {
        def content = request.url().toURL().getText()
        def result = new JsonSlurper().parseText(content)
    }
}
