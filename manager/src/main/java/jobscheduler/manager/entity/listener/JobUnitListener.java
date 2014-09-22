package jobscheduler.manager.entity.listener;

import jobscheduler.manager.entity.JobUnit;
import lombok.SneakyThrows;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author t_endo
 */
public class JobUnitListener implements EntityListener<JobUnit> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SneakyThrows
    public void preInsert(JobUnit entity, PreInsertContext<JobUnit> context) {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(entity.getParameters());
        entity.setParametersJson(json);
    }
}
