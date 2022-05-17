import java.sql.ResultSet;

public interface BillsDAO {


    ResultSet getAllBills();

    ResultSet getBillDetailsById(String billId);

    ResultSet getBillById(String billId);

}
