import java.sql.ResultSet;

public interface BillsDAO {


    ResultSet getAllBills();

    ResultSet getBillDetailsById(String billId);

    ResultSet getBillById(String billId);

    MistakeName addBillDetails(String billId, String isbn, int pieces, String customerId);

    MistakeName addGeneralBill(String billId, String customerID);

    MistakeName checkIfBookExists(String isbn);

}
