package API

class GooglePlacesAPIResultChecker implements ResultChecker {
    final def result

    GooglePlacesAPIResultChecker(result) {
        this.result = result
    }

    @Override
    boolean available() {
        return result.status == 'OK'
    }
}
