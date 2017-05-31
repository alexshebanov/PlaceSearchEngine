package processing

import entity.Place

import static java.lang.Math.*

class DistanceCalculator {

    def dataWithCalculatedDistance(data, location) {
        for (def result : data.results) {
            def latitude = location.latitude
            def longitude = location.longitude
            def distance = sqrt(pow(latitude - result.latitude as double, 2) +
                    pow(longitude - result.longitude as double, 2))
            result.distance = distance
        }
        return data.results
    }
}
