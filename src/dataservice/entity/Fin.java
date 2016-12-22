package dataservice.entity;

/**
 * Created by Y on 2016/12/22.
 */
public class Fin {
    private int id;
    private int cash;
    private int debt;
    private int funds;
    private int fundsRate;
    private int fixedDeposit;
    private int fixedDepositRate;
    private int currentDeposit;
    private int currentDepositRate;
    private int rent;

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }


    public int getFixedDeposit() {
        return fixedDeposit;
    }

    public void setFixedDeposit(int fixedDeposit) {
        this.fixedDeposit = fixedDeposit;
    }

    public int getFixedDepositRate() {
        return fixedDepositRate;
    }

    public void setFixedDepositRate(int fixedDepositRate) {
        this.fixedDepositRate = fixedDepositRate;
    }

    public int getCurrentDeposit() {
        return currentDeposit;
    }

    public void setCurrentDeposit(int currentDeposit) {
        this.currentDeposit = currentDeposit;
    }

    public int getCurrentDepositRate() {
        return currentDepositRate;
    }

    public void setCurrentDepositRate(int currentDepositRate) {
        this.currentDepositRate = currentDepositRate;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getFundsRate() {
        return fundsRate;
    }

    public void setFundsRate(int fundsRate) {
        this.fundsRate = fundsRate;
    }
}
