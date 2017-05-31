package GooglePlacesAPI

import interfaces.DataValidator

class GooglePlacesAPIResponseValidator implements DataValidator {

    @Override
    boolean valid(result) {
        return result.status == 'OK'
    }
}
