package Data;

public class BankBranch extends Bank{
    private final String branchName;

    public BankBranch(Coordinates coordinates, String bankName, String branchName) {
        super(coordinates , bankName);
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

}
