package API

class RequestProperties {
    final String geoLocation
    final String APIKey
    final String urlBase

    RequestProperties(String geoLocation, String APIKey, String urlBase) {
        this.geoLocation = geoLocation
        this.APIKey = APIKey
        this.urlBase = urlBase
    }
}
