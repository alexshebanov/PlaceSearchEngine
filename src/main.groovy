import API.GooglePlacesAPIRequest
import API.GooglePlacesAPIResponseValidator
import API.RequestProperties
import API.RequestSender
import groovy.json.JsonOutput
import processing.DistanceCalculator
import processing.PlacePicker
import processing.ResultHandler

def config = new ConfigSlurper().parse(new File('properties.groovy').toURL())

def cliBuilder = new CliBuilder(usage: 'main -l location -c count')
cliBuilder.with {
    l(longOpt: 'location', 'Location', args: 1, required: true)
    c(longOpt: 'count', 'PlacesCount', args: 1, required: false)
}

def opt = cliBuilder.parse(args)
def location = opt.l
def count = opt.c
if (!count) count = 1

def requestProperties = new RequestProperties(location, config.googlePlacesAPIKey, config.GooglePlacesUrlBase)

def request = new GooglePlacesAPIRequest(requestProperties)

def sortedData = new PlacePicker(request, new RequestSender(),
        new DistanceCalculator(), new GooglePlacesAPIResponseValidator()).result()

def result = new ResultHandler().getResult(sortedData, count as int)

def JSONOutput = JsonOutput.toJson(result)

println()