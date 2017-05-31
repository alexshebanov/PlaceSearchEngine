package processing

import interfaces.DataIterator
import interfaces.DataValidator
import entity.Location
import resultHandling.TemporaryResult

class PlacePicker {
    DistanceCalculator sorter
    DataValidator validator
    DataIterator iterator
    def placesList
    Location location

    PlacePicker(DistanceCalculator sorter, DataValidator validator,
                DataIterator iterator, Location location) {
        this.sorter = sorter
        this.validator = validator
        this.iterator = iterator
        this.location = location
        placesList = []
    }

    def result() {
        def data = iterator.next()
        def status = data.status

        while (iterator.hasNext()) {
            if (!validator.valid(data))
                break
            def results = sorter.dataWithCalculatedDistance(data, location)
            placesList.addAll(results)
            sleep(2000)
            data = iterator.next()
            status = data.status
        }

        if (validator.valid(data)) {
            def results = sorter.dataWithCalculatedDistance(data, location)
            placesList.addAll(results)
        }

        placesList.sort { it.distance }
        placesList.unique { it.placeId }

        return new TemporaryResult(status, placesList)
    }
}
