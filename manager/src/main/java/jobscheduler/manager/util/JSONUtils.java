package jobscheduler.manager.util;

import io.dropwizard.jackson.Jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author t_endo
 *
 */
public final class JSONUtils {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private static final TypeReference<HashMap<String, Object>> TYPE_REF = new TypeReference<HashMap<String, Object>>() {
    };

    /**
     * 
     * @param value
     * @return
     */
    @SneakyThrows
    public static String encode(Map<String, Object> value) {
        return MAPPER.writeValueAsString(value);
    }

    /***
     * 
     * @param json
     * @return
     * @throws IOException
     */
    @SneakyThrows
    public static Map<String, Object> decodeAsMap(String json) {
        return MAPPER.readValue(json, TYPE_REF);
    }

    private JSONUtils() {
    }
}
