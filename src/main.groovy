import API.GooglePlacesAPIRequest
import API.GooglePlacesAPIResultChecker
import API.RequestProperties
import API.RequestSender
import entity.ResultObject
import processing.PlacePicker

def config = new ConfigSlurper().parse(new File('APIKey.groovy').toURL())
def placesCount = 1
def requestProperties = new RequestProperties(args[0], placesCount, config.googlePlacesAPIKey, 'json')
def result = new RequestSender(new GooglePlacesAPIRequest(requestProperties)).getResponse()
if (!(new GooglePlacesAPIResultChecker(result).available())) {
    return new ResultObject(result. status, null)
}
//TODO: make data handling
new PlacePicker(result).result()