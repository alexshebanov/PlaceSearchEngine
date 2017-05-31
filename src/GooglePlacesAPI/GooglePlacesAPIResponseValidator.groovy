package GooglePlacesAPI

import interfaces.ResponseValidator

class GooglePlacesAPIResponseValidator implements ResponseValidator {

    @Override
    boolean available(result) {
        return result.status == 'OK'
    }
}
