package processing

import API.Request
import API.RequestSender
import API.ResponseValidator
import entity.Location
import entity.ResultObject

class PlacePicker {
    Request request
    RequestSender requestSender
    DistanceCalculator sorter
    ResponseValidator validator
    DataIterator iterator
    Location location
    def placesList

    PlacePicker(Request request, RequestSender requestSender, DistanceCalculator sorter, ResponseValidator validator,
                DataIterator iterator) {
        this.request = request
        this.requestSender = requestSender
        this.sorter = sorter
        this.validator = validator
        this.iterator = iterator
        placesList = []
    }

    def result() {

        def data = iterator.next()
        while (iterator.hasNext()) {
            if (!validator.available(data))
                break
            def results = sorter.dataWithCalculatedDistance(data, request.properties.location)
            placesList.addAll(results)
            sleep(2000)
            data = iterator.next()
        }

        //Last iteration (fix this)
        if (validator.available(data)) {
            def results = sorter.dataWithCalculatedDistance(data, request.properties.location)
            placesList.addAll(results)
        }

        placesList.sort { it.distance }
        placesList.unique { it.placeId }

        if (!placesList.isEmpty())
            return new ResultObject('OK', placesList)
        else return new ResultObject(data.status, placesList)
    }
}
