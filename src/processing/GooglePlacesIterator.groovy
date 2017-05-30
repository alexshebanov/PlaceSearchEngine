package processing

import API.Request
import API.RequestSender
import API.Response

class GooglePlacesIterator implements DataIterator {
    Request request
    Response response
    def data

    GooglePlacesIterator(Request request, Response response) {
        this.request = request
        this.response = response
    }

    @Override
    boolean hasNext() {
        if ((response.nextPageToken(data) != null))
            return true
        else return false
    }

    @Override
    def next() {
        data = response.data()
        def nextPageToken = response.nextPageToken(data)
        request.properties.pagetoken = nextPageToken
        return data
    }
}
