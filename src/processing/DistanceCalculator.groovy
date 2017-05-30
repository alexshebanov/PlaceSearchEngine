package processing

import entity.Place

import static java.lang.Math.*

class DistanceCalculator {

    def dataWithCalculatedDistance(data, location) {
        def results = []
        for (def result : data.results) {
            def lat = location.latitude
            def lng = location.longitude
            def distance = sqrt(pow(lat - result.geometry.location.lat as double, 2) +
                    pow(lng - result.geometry.location.lng as double, 2))
            results.add(new Place(result.name, result.vicinity, distance, result.place_id))
        }
        return results
    }
}
