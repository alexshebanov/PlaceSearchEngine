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
        def data = retriever.retrieve()
        def results = sorter.sortedByDistanceResults(data, retriever.requestSender.request.requestProperties.geoLocation)
        placesList.addAll(results)
        println()
    }
}
