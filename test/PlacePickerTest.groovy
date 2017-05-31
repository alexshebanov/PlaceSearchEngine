import GooglePlacesAPI.GooglePlacesAPIDataRetriever
import GooglePlacesAPI.GooglePlacesAPIGETRequest
import GooglePlacesAPI.GooglePlacesAPIRequestSender
import GooglePlacesAPI.GooglePlacesAPIResponseValidator
import GooglePlacesAPI.RequestProperties
import entity.Location
import processing.DistanceCalculator
import processing.PlacePicker
import processing.PlacesIterator

class PlacePickerTest extends GroovyTestCase {

    void testCase() {
        def latitude = 48.465007
        def longitude = 35.046517
        def config = new ConfigSlurper().parse(new File('properties.groovy').toURI().toURL())
        def location = new Location(latitude, longitude)
        def requestProperties = new RequestProperties(location,
                config.googlePlacesAPIKey, config.GooglePlacesUrlBase)
        def request = new GooglePlacesAPIGETRequest(requestProperties)
        def sortedData = new PlacePicker(
                new DistanceCalculator(), new GooglePlacesAPIResponseValidator(),
                new PlacesIterator(new GooglePlacesAPIDataRetriever(request, new GooglePlacesAPIRequestSender())), location).result()
        assertEquals('FrenchCarousel','ChIJ6dAIj-fi20AR29Pcj8RGa2A', sortedData.placesList[0].placeId)
    }
}
