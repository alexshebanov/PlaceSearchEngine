package API

class GooglePlacesAPIResponseValidator implements ResponseValidator {

    @Override
    boolean available(result) {
        return result.status == 'OK'
    }
}
