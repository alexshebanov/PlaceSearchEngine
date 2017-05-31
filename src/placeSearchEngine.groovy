import GooglePlacesAPI.GooglePlacesAPIDataRetriever
import GooglePlacesAPI.GooglePlacesAPIGETRequest
import GooglePlacesAPI.GooglePlacesAPIRequestSender
import GooglePlacesAPI.GooglePlacesAPIResponseValidator
import GooglePlacesAPI.RequestProperties
import entity.Location
import groovy.json.JsonOutput
import processing.DistanceCalculator
import processing.PlacesIterator
import processing.PlacePicker
import resultHandling.ResultHandler
import resultHandling.ResultObject

def config = new ConfigSlurper().parse(new File('properties.groovy').toURI().toURL())

def cliBuilder = new CliBuilder(usage: 'placeSearchEngine -l location -c count',
        header: '\nAvailable options (use -h for help):\n')
cliBuilder.with {
    l(longOpt: 'location', 'Location : \'longitude,latitude\' (without space after comma)', args: 1, required: true)
    c(longOpt: 'count', 'PlacesCount (not required. 1 is default value)', args: 1, required: false)
    h(longOpt: 'help', 'Usage Information', required: false)
}

def opt = cliBuilder.parse(args)
if (!opt) return
if (opt.h) cliBuilder.usage()



def locationParameter = opt.l
def latitude, longitude
try {
    latitude = locationParameter.split(",")[0].toDouble()
    longitude = locationParameter.split(",")[1].toDouble()
} catch (RuntimeException e) {
    println('error : Wrong parameters')
    return new ResultObject('BAD_REQUEST', null)
}
def location = new Location(latitude, longitude)

def count = opt.c
if (!count) count = 1

def requestProperties = new RequestProperties(location,
        config.googlePlacesAPIKey, config.GooglePlacesUrlBase)

def request = new GooglePlacesAPIGETRequest(requestProperties)

def sortedData = new PlacePicker(
        new DistanceCalculator(), new GooglePlacesAPIResponseValidator(),
        new PlacesIterator(new GooglePlacesAPIDataRetriever(request, new GooglePlacesAPIRequestSender())), location).result()

def result = new ResultHandler().getResult(sortedData, count as int)

def JSONOutput = JsonOutput.toJson(result)

