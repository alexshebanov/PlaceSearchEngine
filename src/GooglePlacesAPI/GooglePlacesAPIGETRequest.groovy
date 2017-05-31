package GooglePlacesAPI

import interfaces.GETRequest

class GooglePlacesAPIGETRequest implements GETRequest {
    final String urlBase
    final def properties
    String nextPageToken

    GooglePlacesAPIGETRequest(RequestProperties requestProperties) {
        properties = [:]
        urlBase = requestProperties.urlBase
        properties.put('location', requestProperties.location.toString())
        properties.put('key', requestProperties.APIKey)
        nextPageToken = ''
    }

    GooglePlacesAPIGETRequest(GooglePlacesAPIGETRequest previousURL, String nextPageToken) {
        urlBase = previousURL.urlBase
        properties = previousURL.properties
        this.nextPageToken = nextPageToken
    }


    String url() {
        String url = urlBase
        properties.each{ parameter, value -> url += "&${parameter}=${value}" }
        url += ('&pagetoken=' + nextPageToken)
        return url
    }
}
