package jobscheduler;

import java.util.stream.IntStream;

import jobscheduler.entity.Job
import jobscheduler.entity.JobParameters
import jobscheduler.entity.Schedule
import jobscheduler.guice.JobModule
import jobscheduler.jobrunner.JobStartRunner

import org.junit.Test

import com.google.inject.Guice

/**
 * @author t_endo
 *
 */
class JobStartRunnerTest {

    @Test
    public void "aaa"() {
        def injector = Guice.createInjector(new JobModule())
        def runner = injector.getInstance(JobStartRunner.class)

        def schedule = new Schedule()
        schedule.setName("test")

        def jobs = new ArrayList<>()
        for (i in (0..10)) {
            String[] cmd1 = { "a" }
            JobParameters params1 = JobParameters.builder().command(cmd1).build()
            Job job1 = Job.builder().name("j${i}").jobParameters(params1).build()
            System.out.println(cmd1)
            jobs.add(job1)
        }

        schedule.setChildrenJob(jobs)
        runner.start(schedule)
    }
}
