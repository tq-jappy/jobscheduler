package jobscheduler.agent.transformer;

import spark.ResponseTransformer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
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
