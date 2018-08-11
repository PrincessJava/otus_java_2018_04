package ru.otus.t1;

import ru.otus.t1.model.DataSet;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class DataSetMapper {

    private static String SET_PREFIX = "set";
    private static String GET_PREFIX = "get";

    public DataSetMapper() {
    }

    public <T extends DataSet> List<T> mapDataSet(ResultSet resultSet, Class<T> clazz)
            throws SQLException, IllegalAccessException, InstantiationException {
        Map<String, Object> resultSetValues = getRowItemValues(resultSet);
        if (resultSetValues.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> dataSet = new ArrayList<>();
        Map<String, Method> setMethods = getAccessMethods(new HashMap<>(), SET_PREFIX, clazz);
        resultSet.beforeFirst();
        while (resultSet.next()) {
            T instance = clazz.newInstance();
            resultSetValues.forEach((k, v) -> {
                try {
                    setMethods.get(k).invoke(instance, v);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            dataSet.add(instance);
        }
        return dataSet;
    }

    public <T extends DataSet> Map<String, Object> getDataSetValues(T dataSet) {
        List<Field> fields = getNonTransientFields(new ArrayList<>(), dataSet.getClass());
        Map<String, Method> getMethods = getAccessMethods(new HashMap<>(), GET_PREFIX, dataSet.getClass());
        Map<String, Object> values = new HashMap<>();
        fields.forEach(field -> {
            try {
                values.put(field.getName(), getMethods.get(field.getName()).invoke(dataSet));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return values;
    }

    private Map<String, Method> getAccessMethods(Map<String, Method> methods, String prefix, Class clazz) {
        Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().startsWith(prefix))
                .forEach(x -> methods.put(x.getName().replaceFirst(prefix, "").toLowerCase(), x));

        if (clazz.getSuperclass() != null && !clazz.isInstance(DataSet.class)) {
            getAccessMethods(methods, prefix, clazz.getSuperclass());
        }
        return methods;
    }

    private List<Field> getNonTransientFields(List<Field> fields, Class clazz) {
        Field[] currentFields = clazz.getDeclaredFields();
        Arrays.stream(currentFields)
                .filter(x -> !x.isAnnotationPresent(Transient.class))
                .forEach(x -> {
                    x.setAccessible(true);
                    fields.add(x);
                    x.setAccessible(false);
                });

        if (clazz.getSuperclass() != null) {
            getNonTransientFields(fields, clazz.getSuperclass());
        }

        return fields;
    }

    private Map<String, Object> getRowItemValues(ResultSet resultSet) throws SQLException {
        ResultSetMetaData md = resultSet.getMetaData();
        int columns = md.getColumnCount();
        Map<String, Object> rows = new HashMap<>();
        while (resultSet.next()) {
            for (int i = 1; i <= columns; ++i) {
                rows.put(md.getColumnName(i), resultSet.getObject(i));
            }
        }
        return rows;
    }


    public List<String> getStringFieldsNames(Class clazz) {
        List<Field> fields = getNonTransientFields(new ArrayList<>(), clazz);
        return fields.stream().filter(x -> x.getType().getTypeName().equals(String.class.getTypeName()))
                .map(Field::getName)
                .collect(Collectors.toList());
    }

}