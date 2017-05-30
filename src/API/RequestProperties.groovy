package API

import entity.Location

class RequestProperties {
    final Location location
    final String APIKey
    final String urlBase

    RequestProperties(Location location, String APIKey, String urlBase) {
        this.location = location
        this.APIKey = APIKey
        this.urlBase = urlBase
    }
}
