package processing

import API.ResponseValidator
import entity.Location
import entity.ResultObject

class PlacePicker {
    DistanceCalculator sorter
    ResponseValidator validator
    DataIterator iterator
    def placesList

    PlacePicker(DistanceCalculator sorter, ResponseValidator validator,
                DataIterator iterator) {

        this.sorter = sorter
        this.validator = validator
        this.iterator = iterator
        placesList = []
    }

    def result() {
        def location = new Location(iterator.request.properties.location)
        def data = iterator.next()
        while (iterator.hasNext()) {
            if (!validator.available(data))
                break
            def results = sorter.dataWithCalculatedDistance(data, location)
            placesList.addAll(results)
            sleep(2000)
            data = iterator.next()
        }

        //Last iteration
        if (validator.available(data)) {
            def results = sorter.dataWithCalculatedDistance(data, location)
            placesList.addAll(results)
        }

        placesList.sort { it.distance }
        placesList.unique { it.placeId }

        if (!placesList.isEmpty())
            return new ResultObject('OK', placesList)
        else return new ResultObject(data.status, placesList)
    }
}
