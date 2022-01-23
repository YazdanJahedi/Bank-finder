package Data;

public class Bank {
    protected Coordinates coordinates;
    protected String bankName;

    public Bank(Coordinates coordinates, String bankName) {
        this.coordinates = coordinates;
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "coordinates=" + coordinates +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
