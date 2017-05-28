package processing

import API.RequestSender

class DataRetriever {
    RequestSender requestSender

    DataRetriever(RequestSender requestSender) {
        this.requestSender = requestSender
    }

    def retrieve() {
        requestSender.getResponse()
    }
}
