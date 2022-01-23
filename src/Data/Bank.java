package Data;

public class Bank {
    protected Coordinates coordinates;
    protected String bankName;

    protected Bank(Coordinates coordinates, String bankName) {
        this.coordinates = coordinates;
        this.bankName = bankName;
    }

    protected String getBankName() {
        return bankName;
    }

    protected Coordinates getCoordinates() {
        return coordinates;
    }
}
