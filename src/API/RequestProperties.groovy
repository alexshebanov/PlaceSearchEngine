package API

class RequestProperties {
    final def geoLocation
    final def placesCount
    final def APIKey
    final def responseType
    def nextPageToken

    RequestProperties(geoLocation, placesCount, APIKey, responseType) {
        this.geoLocation = geoLocation
        this.placesCount = placesCount
        this.APIKey = APIKey
        this.responseType = responseType
    }
}
