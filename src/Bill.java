public class Bill {

    private String billId;
    private String billDate;
    private String billCustomerId;
    private String billCustomerTime;

    public Bill(
            String billId,
            String billDate,
            String billCustomerId,
            String billCustomerTime
    ) {
        this.billId = billId;
        this.billDate = billDate;
        this.billCustomerId = billCustomerId;
        this.billCustomerTime = billCustomerTime;
    }

    public String getBillId() {
        return billId;
    }

    public String getBillDate() {
        return billDate;
    }

    public String getBillCustomerId() {
        return billCustomerId;
    }

    public String getBillCustomerTime() {
        return billCustomerTime;
    }


}
