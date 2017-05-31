package resultHandling

import entity.Place

class ResultHandler {

    def getResult(TemporaryResult temp, int placesCount) {
        if (temp.placesList.isEmpty()) {
            return new ResultObject(temp.status, temp.placesList)
        } else {
            def places = []
            if (placesCount < temp.placesList.size())
                placesCount = temp.placesList.size()
            for (Place place : temp.placesList)
                places.add(place.name + ', ' + place.vicinity)
            return new ResultObject('OK', places)
        }
    }
}
