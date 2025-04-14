package mk.ukim.finki.airbnblab.model.exceptions;

public class NotEnoughRooms extends Throwable {
    public NotEnoughRooms() {
        super("Smestuvanjeto nema dovolno slobodni sobi");
    }
}
