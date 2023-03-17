import java.sql.SQLException;

public class JDBC_Custom_Exception extends RuntimeException {

    enum ExceptionType{
        SQLException, NullPointer, ClassNotFound, ListIsNull
    }

    ExceptionType type;

    public JDBC_Custom_Exception(String message , ExceptionType type) {
        super(message);
        this.type = type;
    }
}
