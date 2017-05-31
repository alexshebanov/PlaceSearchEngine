package GooglePlacesAPI

import interfaces.RequestURL

class GooglePlacesAPIRequestURL implements RequestURL {
    final String urlBase
    final def properties
    String nextPageToken

    GooglePlacesAPIRequestURL(RequestProperties requestProperties) {
        properties = [:]
        urlBase = requestProperties.urlBase
        properties.put('location', requestProperties.location.toString())
        properties.put('key', requestProperties.APIKey)
        nextPageToken = ''
    }

    GooglePlacesAPIRequestURL(GooglePlacesAPIRequestURL previousURL, String nextPageToken) {
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
