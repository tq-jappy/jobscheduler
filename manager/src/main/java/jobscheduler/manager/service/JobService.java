package jobscheduler.manager.service;

import java.util.List;

import jobscheduler.manager.dao.JobDao;
import jobscheduler.manager.entity.JobUnit;
import jobscheduler.manager.guice.persist.DomaTransactionAttribute;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@DomaTransactionAttribute
public class JobService {

    @Inject
    private JobDao jobDao;

    public List<JobUnit> findAll() {
        return jobDao.selectAll();
    }

    public JobUnit findById(int id) {
        return jobDao.selectById(id);
    }

    public JobUnit create(JobUnit jobUnit) {
        jobDao.insert(jobUnit);
        return jobUnit;
    }

    public JobUnit update(JobUnit jobUnit) {
        jobDao.update(jobUnit);
        return jobUnit;
    }

    public void delete(int id) {
        JobUnit jobUnit = new JobUnit();
        jobUnit.setId(id);
        jobDao.delete(jobUnit);
    }
}
