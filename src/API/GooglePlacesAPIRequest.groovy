package API

class GooglePlacesAPIRequest implements Request {
    final RequestProperties requestProperties

    GooglePlacesAPIRequest(RequestProperties requestProperties) {
        this.requestProperties = requestProperties
    }

    def url() {
        def url = 'https://maps.googleapis.com/maps/api/place/nearbysearch/' +
                requestProperties.responseType + '?location=' +
                requestProperties.geoLocation + '&rankby=distance&key=' +
                requestProperties.APIKey
        if (requestProperties.nextPageToken != null)
            url += ('&pagetoken=' + requestProperties.nextPageToken)
        println()
        return url
    }
}
