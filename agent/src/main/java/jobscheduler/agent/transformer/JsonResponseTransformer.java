package jobscheduler.agent.transformer;

import spark.Response;
import spark.ResponseTransformer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@link Response} の変換(JSON文字列にする)
 * 
 * @author t_endo
 */
public class JsonResponseTransformer implements ResponseTransformer {

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(Object model) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(model);
    }
}
