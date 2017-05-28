package processing

class PlacePicker {
    DataRetriever retriever
    DataSorter sorter
    def placesList = []

    PlacePicker(DataRetriever retriever, DataSorter sorter) {
        this.retriever = retriever
        this.sorter = sorter
    }


}
