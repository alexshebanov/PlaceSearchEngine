package processing

import entity.ResultObject

class ResultHandler {
    def getResult(sortedData, int placesCount) {
        if (sortedData.isEmpty()) {
            return new ResultObject('ERROR', null)
        } else {
            def places = []
            if (placesCount < sortedData.size())
                placesCount = sortedData.size()
            for (int i = 0; i < placesCount; i++)
                places.add(sortedData[i].name + ', ' + sortedData[i].vicinity)
            return new ResultObject('OK', places)
        }
    }
}
