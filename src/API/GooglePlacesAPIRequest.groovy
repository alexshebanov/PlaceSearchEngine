package API

class GooglePlacesAPIRequest implements Request {
    final def url

    GooglePlacesAPIRequest(RequestProperties requestProperties) {
        url = 'https://maps.googleapis.com/maps/api/place/nearbysearch/' +
                requestProperties.responseType + '?location=' +
                requestProperties.geoLocation + '&rankby=distance&key=' +
                requestProperties.APIKey
    }
}
