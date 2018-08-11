package ru.otus.t1.executor;

import ru.otus.t1.DataBaseService;
import ru.otus.t1.DataSetMapper;
import ru.otus.t1.model.DataSet;
import ru.otus.t1.model.UserDataSet;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserExecutor {
    private static String INSERT_TEMPLATE = "INSERT INTO users (";
    private static String SELECT_BY_ID = "SELECT * FROM users WHERE id= ?";

    private DataSetMapper dataSetMapper;

    public UserExecutor(DataSetMapper dataSetMapper) {
        this.dataSetMapper = dataSetMapper;
    }

    public static void main(String[] args) {
        UserExecutor executor = new UserExecutor(new DataSetMapper());
        UserDataSet dataSet = new UserDataSet();
        dataSet.setAge(28);
        dataSet.setName("Korolev");
        System.out.println(executor.save(dataSet));

        List<UserDataSet> userDataSet = executor.load(2, UserDataSet.class);
        System.out.println(userDataSet.get(0).getId() + " " + userDataSet.get(0).getName() + " " + userDataSet.get(0).getAge());
    }

    public <T extends DataSet> int save(T user) {
        String query = getQuery(user);
        try (Connection connection = DataBaseService.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return statement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public <T extends DataSet> List<T> load(long id, Class<T> clazz) {
        ResultSet resultSet = null;
        List<T> dataSet = null;
        try (Connection connection = DataBaseService.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            dataSet = dataSetMapper.mapDataSet(resultSet, clazz);
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataSet;
    }

    private <T extends DataSet> String getQuery(T user) {
        Map<String, Object> fieldNameOnValues = dataSetMapper.getDataSetValues(user);
        StringBuilder queryBuilder = new StringBuilder(INSERT_TEMPLATE);
        queryBuilder.append(fieldNameOnValues.keySet().stream().collect(Collectors.joining(", ")));
        queryBuilder.append(") VALUES(");
        final List<String> stringFieldsNames = dataSetMapper.getStringFieldsNames(user.getClass());
        queryBuilder.append(fieldNameOnValues.entrySet().stream().map(x -> {
            if (stringFieldsNames.contains(x.getKey())) {
                return "\'" + x.getValue().toString() + "\'";
            } else return x.getValue().toString();
        }).collect(Collectors.joining(", ")));
        queryBuilder.append(")");
        return queryBuilder.toString();
    }
}
