package entity

class Place {
    String name
    String vicinity
    double distance
    String placeId

    Place(String name, String vicinity, double distance, String placeId) {
        this.name = name
        this.vicinity = vicinity
        this.distance = distance
        this.placeId = placeId
    }
}
