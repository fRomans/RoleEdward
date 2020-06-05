package repository.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.execute(update);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public <T> List execQuery(String query, ResultHandler<T> handler) throws SQLException {
        List<T> list = new ArrayList<>();
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        while (result.next()) {
            T value = handler.handle(result); //Оставил для наглядности
            list.add(value);
        }
        result.close();
        stmt.close();
        return list;
    }
}
