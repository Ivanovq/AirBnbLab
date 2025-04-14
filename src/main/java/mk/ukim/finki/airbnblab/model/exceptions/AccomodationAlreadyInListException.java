package mk.ukim.finki.airbnblab.model.exceptions;

import mk.ukim.finki.airbnblab.model.Accommodation;

public class AccomodationAlreadyInListException extends Throwable {
    public AccomodationAlreadyInListException(Accommodation accommodation) {
    }
}
