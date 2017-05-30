package API

import groovy.json.JsonSlurper

class RequestSender {

    def getResponse(Request request) {
        def content = request.url().toURL().getText()
        new JsonSlurper().parseText(content)
    }
}
