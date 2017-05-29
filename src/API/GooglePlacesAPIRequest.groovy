package API

class GooglePlacesAPIRequest implements Request {
    final String urlBase
    def properties

    GooglePlacesAPIRequest(RequestProperties requestProperties) {
        properties = [:]
        urlBase = requestProperties.urlBase
        properties.put('location', requestProperties.geoLocation)
        properties.put('key', requestProperties.APIKey)
        properties.put('pagetoken' , '')
    }

    String url() {
        String url = urlBase
        properties.each{ parameter, value -> url += "&${parameter}=${value}" }
        return url
    }
}
