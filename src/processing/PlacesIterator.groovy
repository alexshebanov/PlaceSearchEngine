package processing

import interfaces.DataRetriever
import interfaces.ReceivedData


class PlacesIterator implements DataIterator {
    DataRetriever retriever
    ReceivedData data

    PlacesIterator(DataRetriever retriever) {
        this.retriever = retriever
    }

    @Override
    boolean hasNext() {
        if ((data.nextPageToken != null))
            return true
        else return false
    }

    @Override
    ReceivedData next() {
        data = retriever.retrieve()
    }
}
