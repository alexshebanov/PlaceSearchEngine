package GooglePlacesAPI

import entity.Place
import interfaces.ReceivedData

class GooglePlacesAPIReceivedData implements ReceivedData {
    def results
    String status
    String nextPageToken

    GooglePlacesAPIReceivedData(def data) {
        results = []
        for (def result : data.results) {
            def name = result.name
            def vicinity = result.vicinity
            def latitude = result.geometry.location.lat
            def longitude = result.geometry.location.lng
            def placeId = result.place_id
            results.add(new Place(name, vicinity, 0, placeId, latitude, longitude))
        }
        status = data.status
        nextPageToken = data.next_page_token
    }
}
