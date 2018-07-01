package ru.otus.t1;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

public class JsonObjectWriter {

    ////    JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
////            .add("firstName", person.getFirstName())
////            .add("lastName", person.getLastName())
////            .add("birthdate", new SimpleDateFormat("DD/MM/YYYY")
////                    .format(person.getBirthdate()));
//
    public JsonObject toJson(Object object) throws IllegalAccessException {
        //TODO: TreeMap????
        Map<String, String> objectMap = new TreeMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            objectMap.put(field.getName(), field.get(object).toString());
            field.setAccessible(false);
        }
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        for (String key : objectMap.keySet()) {
            objectBuilder.add(key, objectMap.get(key));
        }

        JsonObject jsonObject = objectBuilder.build();

        return jsonObject;
    }

}
