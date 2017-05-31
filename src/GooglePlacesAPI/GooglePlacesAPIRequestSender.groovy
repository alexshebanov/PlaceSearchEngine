package GooglePlacesAPI

import groovy.json.JsonSlurper
import interfaces.ReceivedData
import interfaces.RequestURL
import interfaces.RequestSender

class GooglePlacesAPIRequestSender implements RequestSender {

    ReceivedData getResponse(RequestURL request) {
        def content = request.url().toURL().getText()
        def parsedContent = new JsonSlurper().parseText(content)
        new GooglePlacesAPIReceivedData(parsedContent)
    }
}
