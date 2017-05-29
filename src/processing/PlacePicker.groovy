package processing

import API.Request
import API.RequestSender
import API.ResponseValidator
import entity.ResultObject

class PlacePicker {
    Request request
    RequestSender requestSender
    DistanceCalculator sorter
    ResponseValidator validator
    def placesList = []

    PlacePicker(Request request, RequestSender requestSender, DistanceCalculator sorter, ResponseValidator validator) {
        this.request = request
        this.requestSender = requestSender
        this.sorter = sorter
        this.validator = validator
    }

    def result() {
        /*We send only 3 requests, cause Google Place API responds with
        only 60 unique places (20 places on each page)*/
        for (int i = 0; i < 3; i++) {
            def data = requestSender.getResponse(request)
            if (!validator.available(data))
                return new ResultObject(data.status, placesList)
            def results = sorter.dataWithCalculatedDistance(data, request.properties.location)
            placesList.addAll(results)
            def nextPageToken = data.next_page_token
//            request.requestProperties.nextPageToken = nextPageToken
            request.properties.pagetoken = nextPageToken
            sleep(2000)
        }
        placesList.sort { it.distance }
        placesList.unique { it.placeId }
        return new ResultObject('OK', placesList)
    }
}
