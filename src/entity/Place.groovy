package entity

class Place {
    String name
    String vicinity
    double distance
    String placeId
    double latitude
    double longitude

    Place(String name, String vicinity, double distance, String placeId, double latitude, double longitude) {
        this.name = name
        this.vicinity = vicinity
        this.distance = distance
        this.placeId = placeId
        this.latitude = latitude
        this.longitude = longitude
    }
}
