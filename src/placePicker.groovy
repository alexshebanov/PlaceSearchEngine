import API.GooglePlacesAPIRequest
import API.GooglePlacesAPIResponseValidator
import API.RequestProperties
import API.RequestSender
import entity.Location
import groovy.json.JsonOutput
import processing.DistanceCalculator
import processing.GooglePlacesIterator
import processing.PlacePicker
import processing.ResultHandler

def config = new ConfigSlurper().parse(new File('properties.groovy').toURL())

def cliBuilder = new CliBuilder(usage: 'placePicker -l location -c count',
        header: '\nAvailable options (use -h for help):\n')
cliBuilder.with {
    l(longOpt: 'location', 'Location : \'longitude,latitude\' (without space after comma)' , args: 1, required: true)
    c(longOpt: 'count', 'PlacesCount (not required. 1 is default value)', args: 1, required: false)
    h(longOpt: 'help', 'Usage Information', required: false)
}

def opt = cliBuilder.parse(args)
if (!opt) return
if (opt.h) cliBuilder.usage()

def location = new Location(opt.l)
def count = opt.c
if (!count) count = 1

def requestProperties = new RequestProperties(location, config.googlePlacesAPIKey, config.GooglePlacesUrlBase)

def request = new GooglePlacesAPIRequest(requestProperties)

def sortedData = new PlacePicker(request, new RequestSender(),
        new DistanceCalculator(), new GooglePlacesAPIResponseValidator(), new GooglePlacesIterator(request, new RequestSender())).result()

def result = new ResultHandler().getResult(sortedData, count as int)

def JSONOutput = JsonOutput.toJson(result)
