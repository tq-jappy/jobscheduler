package jobscheduler.agent.transformer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author t_endo
 */
public class JsonRequestTransformer {

    public <T> T convert(String body, Class<T> valueType)
            throws JsonMappingException, JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(body, valueType);
    }
}
