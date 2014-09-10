package jobscheduler.agent.transformer;

import java.io.IOException;

import spark.Request;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author t_endo
 */
public class RequestBodyReader {

    /**
     * リクエストされたオブジェクトを変換します。
     * 
     * @param request
     * @param valueType
     * @return
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws IOException
     */
    public <T> T readFrom(Request request, Class<T> valueType)
            throws JsonMappingException, JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(request.body(), valueType);
    }
}
