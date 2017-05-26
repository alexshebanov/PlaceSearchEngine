package entity

class ResultObject {
    final def status
    final def places = []

    ResultObject(status, places) {
        this.status = status
        this.places = places
    }
}
