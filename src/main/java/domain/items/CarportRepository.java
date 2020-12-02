package domain.items;

import java.sql.SQLException;
import java.util.List;

public interface CarportRepository {

    List<Carport> findAll() throws DBException;
    Carport find(int parseInt) throws DBException;
    int commitCarport(Carport carport) throws SQLException;

}