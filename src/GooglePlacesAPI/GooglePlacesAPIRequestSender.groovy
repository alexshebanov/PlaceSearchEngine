package GooglePlacesAPI

import groovy.json.JsonSlurper
import interfaces.ReceivedData
import interfaces.GETRequest
import interfaces.RequestSender

class GooglePlacesAPIRequestSender implements RequestSender {

    ReceivedData getResponse(GETRequest request) {
        def content = request.url().toURL().getText()
        def parsedContent = new JsonSlurper().parseText(content)
        new GooglePlacesAPIReceivedData(parsedContent)
    }
}
