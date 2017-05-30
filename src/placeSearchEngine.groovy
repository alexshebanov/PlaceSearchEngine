import API.GooglePlacesAPIRequest
import API.GooglePlacesAPIResponse
import API.GooglePlacesAPIResponseValidator
import API.RequestProperties
import API.RequestSender
import entity.Location
import entity.ResultObject
import groovy.json.JsonOutput
import processing.DistanceCalculator
import processing.GooglePlacesIterator
import processing.PlacePicker
import processing.ResultHandler

def config = new ConfigSlurper().parse(new File('properties.groovy').toURI().toURL())

def cliBuilder = new CliBuilder(usage: 'placePicker -l location -c count',
        header: '\nAvailable options (use -h for help):\n')
cliBuilder.with {
    l(longOpt: 'location', 'Location : \'longitude,latitude\' (without space after comma)', args: 1, required: true)
    c(longOpt: 'count', 'PlacesCount (not required. 1 is default value)', args: 1, required: false)
    h(longOpt: 'help', 'Usage Information', required: false)
}

def opt = cliBuilder.parse(args)
if (!opt) return
if (opt.h) cliBuilder.usage()

def location
try {
    location = new Location(opt.l)
} catch (RuntimeException e) {
    println('error: Invalid location parameter')
    return JsonOutput.toJson(new ResultObject('INVALID REQUEST', null))
}

def count = opt.c
if (!count)
    count = 1
else {
    try {
        int countValue = count.toInteger()
    } catch (NumberFormatException e) {
        println('error: Invalid count value')
        return JsonOutput.toJson(new ResultObject('INVALID REQUEST', null))
    }
}

def requestProperties = new RequestProperties(location, config.googlePlacesAPIKey, config.GooglePlacesUrlBase)

def request = new GooglePlacesAPIRequest(requestProperties)

def sortedData = new PlacePicker(
        new DistanceCalculator(), new GooglePlacesAPIResponseValidator(),
        new GooglePlacesIterator(request, new GooglePlacesAPIResponse(request, new RequestSender())), location).result()

def result = new ResultHandler().getResult(sortedData, count as int)

def JSONOutput = JsonOutput.toJson(result)
