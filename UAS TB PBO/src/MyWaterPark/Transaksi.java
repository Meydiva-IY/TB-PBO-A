package MyWaterPark;

import java.sql.SQLException;

public interface Transaksi {
    // Method method
    void newTransaction() throws SQLException;
    void viewTransaction() throws SQLException;
    void updateTransaction() throws SQLException;
    void deleteTransaction() throws SQLException;
    void searchTransaction() throws SQLException;
}
