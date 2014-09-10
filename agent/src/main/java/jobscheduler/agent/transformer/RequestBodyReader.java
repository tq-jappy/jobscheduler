package jobscheduler.agent.transformer;

import java.io.IOException;
import java.io.UncheckedIOException;

import spark.Request;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@link Request} の読み込み
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
     */
    public static <T> T readFrom(Request request, Class<T> valueType) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(request.body(), valueType);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
