package entity

class ResultObject {
    final String status
    def places = []

    ResultObject(status, places) {
        this.status = status
        this.places = places
    }
}
