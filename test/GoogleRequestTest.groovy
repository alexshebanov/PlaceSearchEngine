import GooglePlacesAPI.GooglePlacesAPIGETRequest
import GooglePlacesAPI.RequestProperties
import entity.Location

class GoogleRequestTest extends GroovyTestCase {
    void testCase() {
        def latitude = 48.465007
        def longitude = 35.046517
        def config = new ConfigSlurper().parse(new File('properties.groovy').toURI().toURL())
        def location = new Location(latitude, longitude)
        def requestProperties = new RequestProperties(location,
                config.googlePlacesAPIKey, config.GooglePlacesUrlBase)
        assertEquals('https://maps.googleapis.com/maps/api/place/nearbysearch/json?rankby=distance' +
                '&location=48.465007,35.046517&key=AIzaSyDqmhXL_JAOiSK88w55d9mDKEtD2QrMgf8&' +
                'pagetoken=', new GooglePlacesAPIGETRequest(requestProperties).url())
    }
}
