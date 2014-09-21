package jobscheduler.manager.service;

import java.util.List;

import jobscheduler.manager.dao.JobDao;
import jobscheduler.manager.entity.Job;
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

    public List<Job> findAll() {
        return jobDao.selectAll();
    }

    public Job findById(int id) {
        return jobDao.selectById(id);
    }

    public Job create(Job job) {
        jobDao.insert(job);
        return job;
    }

    public Job update(Job job) {
        jobDao.update(job);
        return job;
    }

    public void delete(int id) {
        Job job = new Job();
        job.setId(id);
        jobDao.delete(job);
    }
}
