package entity

class Location {
    final double latitude
    final double longitude

    Location(double latitude, double longitude) {
        this.latitude = latitude
        this.longitude = longitude
    }

    Location(String location) {
        this.latitude = location.split(",")[0].toDouble()
        this.longitude = location.split(",")[1].toDouble()
    }

    @Override
    String toString() {
        return latitude + ',' + longitude
    }
}
