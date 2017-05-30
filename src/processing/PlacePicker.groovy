package processing


import API.ResponseValidator
import entity.Location

class PlacePicker {
    DistanceCalculator sorter
    ResponseValidator validator
    DataIterator iterator
    def placesList
    Location location

    PlacePicker(DistanceCalculator sorter, ResponseValidator validator,
                DataIterator iterator, Location location) {

        this.sorter = sorter
        this.validator = validator
        this.iterator = iterator
        this.location = location
        placesList = []
    }

    def result() {
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

        return placesList
    }
}
