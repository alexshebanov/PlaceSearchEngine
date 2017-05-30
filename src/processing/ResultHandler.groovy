package processing

import entity.ResultObject

class ResultHandler {
    def getResult(ResultObject sortedData, int placesCount) {
        if (sortedData.status != 'OK') {
            sortedData.places = null
            return sortedData
        } else {
            def places = []
            if (placesCount > sortedData.places.size()) {
                for (int i = 0; i < sortedData.places.size(); i++)
                    places.add(sortedData.places[i].name + ', ' + sortedData.places[i].vicinity)
                return new ResultObject('OK. BUT ONLY ' + sortedData.places.size() +
                        ' PLACES', places)
            } else {
                for (int i = 0; i < placesCount; i++)
                    places.add(sortedData.places[i].name + ', ' + sortedData.places[i].vicinity)
                return new ResultObject('OK', places)
            }
        }
    }
}
