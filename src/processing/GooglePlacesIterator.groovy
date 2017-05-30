package processing

import API.Request
import API.RequestSender

class GooglePlacesIterator implements DataIterator {
    Request request
    RequestSender requestSender
    def data

    GooglePlacesIterator(Request request, RequestSender requestSender) {
        this.request = request
        this.requestSender = requestSender
    }

    @Override
    boolean hasNext() {
        if ((data.next_page_token != null))
            return true
        else return false
    }

    @Override
    def next() {
        data = requestSender.getResponse(request)
        def nextPageToken = data.next_page_token
        request.properties.pagetoken = nextPageToken
        return data
    }
}
