package processing

class PlacePicker {
    DataRetriever retriever
    DataSorter sorter
    def placesList = []

    PlacePicker(DataRetriever retriever, DataSorter sorter) {
        this.retriever = retriever
        this.sorter = sorter
    }

    def result() {
        //TODO: Find saving time solution
        for (int i = 0; i < 4; i++) {
            def data = retriever.retrieve()
            def results = sorter.sortedByDistanceResults(data, retriever.requestSender.request.requestProperties.geoLocation)
            placesList.addAll(results)
            def nextPageToken = data.next_page_token
            retriever.requestSender.request.requestProperties.nextPageToken = nextPageToken
            sleep(1500)
        }
        placesList.sort { it.distance }
        return placesList
    }
}
