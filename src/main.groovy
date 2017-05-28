import API.GooglePlacesAPIRequest
import API.GooglePlacesAPIResultChecker
import API.RequestProperties
import API.RequestSender
import entity.ResultObject
import processing.DataRetriever
import processing.DataSorter
import processing.PlacePicker

def config = new ConfigSlurper().parse(new File('APIKey.groovy').toURL())
def placesCount = 1
def requestProperties = new RequestProperties(args[0], placesCount, config.googlePlacesAPIKey, 'json')
def response = new RequestSender(new GooglePlacesAPIRequest(requestProperties)).getResponse()
if (!(new GooglePlacesAPIResultChecker(response).available())) {
    return new ResultObject(result. status, null)
}
//TODO: make data handling
def smth = new PlacePicker(new DataRetriever(new RequestSender(new GooglePlacesAPIRequest(requestProperties))),
new DataSorter()).result()