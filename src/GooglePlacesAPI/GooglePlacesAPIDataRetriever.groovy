package GooglePlacesAPI

import interfaces.DataRetriever
import interfaces.ReceivedData
import interfaces.RequestSender
import interfaces.GETRequest

class GooglePlacesAPIDataRetriever implements DataRetriever {
    GETRequest request
    RequestSender sender

    GooglePlacesAPIDataRetriever(GETRequest request, RequestSender sender) {
        this.request = request
        this.sender = sender
    }

    @Override
    ReceivedData retrieve() {
        def data = sender.getResponse(request)
        def nextPageToken = data.nextPageToken
        request = new GooglePlacesAPIGETRequest(request, nextPageToken)
        return data
    }
}
