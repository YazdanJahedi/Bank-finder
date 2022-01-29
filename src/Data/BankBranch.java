package Data;

public class BankBranch extends Bank {
    private final String branchName;

    public BankBranch(Coordinates coordinates, String bankName, String branchName) {
        super(coordinates, bankName);
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "coordinates=" + coordinates +
                ", bankName='" + bankName + '\'' +
                ", branchName='" + branchName + '\'' +
                '}';
    }
}
