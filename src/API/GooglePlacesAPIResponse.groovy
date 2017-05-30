package API

class GooglePlacesAPIResponse implements Response {
    RequestSender requestSender
    Request request

    GooglePlacesAPIResponse(Request request, RequestSender requestSender) {
        this.requestSender = requestSender
        this.request = request
    }

    @Override
    def data() {
        return requestSender.getResponse(request)
    }

    @Override
    String nextPageToken(Object data) {
        return data.next_page_token
    }

    @Override
    String status(def data) {
        return data.status
    }
}
