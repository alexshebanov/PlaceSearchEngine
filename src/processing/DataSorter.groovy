package processing

import entity.Place

import static java.lang.Math.*

class DataSorter {

    def sortedByDistanceResults(data, location) {
        def results = []
        for (def d : data.results) {
            def lat = (location as String).split(",")[0].toDouble()
            def lng = (location as String).split(",")[1].toDouble()
            def distance = sqrt(pow(lat - d.geometry.location.lat as double, 2) +
                    pow(lng - d.geometry.location.lng as double, 2))
            results.add(new Place(d.name, d.vicinity, distance))
        }
        results.sort { it.distance }
    }
}
