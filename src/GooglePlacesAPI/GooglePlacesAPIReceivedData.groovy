package GooglePlacesAPI

import interfaces.ReceivedData

class GooglePlacesAPIReceivedData implements ReceivedData {
    def results
    String status
    String nextPageToken

    GooglePlacesAPIReceivedData(def data) {
        results = data.results
        status = data.status
        nextPageToken = data.next_page_token
    }
}
