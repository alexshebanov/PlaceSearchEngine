package processing

import interfaces.ReceivedData
import interfaces.RequestURL
import interfaces.RequestSender

class GooglePlacesIterator implements DataIterator {
    RequestURL request
    RequestSender sender
    ReceivedData data

    GooglePlacesIterator(RequestURL request, RequestSender sender) {
        this.request = request
        this.sender = sender
    }

    @Override
    boolean hasNext() {
        if ((data.nextPageToken != null))
            return true
        else return false
    }

    @Override
    def next() {
        data = sender.getResponse(request)
        def nextPageToken = data.nextPageToken
        request.properties.pagetoken = nextPageToken
        return data
    }
}
