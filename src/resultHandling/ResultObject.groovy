package resultHandling

class ResultObject {
    final String status
    def places = []

    ResultObject(String status, places) {
        this.status = status
        this.places = places
    }
}
