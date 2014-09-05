package jobscheduler;

import java.util.concurrent.TimeUnit

import jobscheduler.entity.Job
import jobscheduler.entity.JobParameters
import jobscheduler.entity.Schedule
import jobscheduler.guice.EventModule
import jobscheduler.guice.QuartzModule
import jobscheduler.jobrunner.JobStartRunner
import jobscheduler.quartz.ScheduleStartJob
import jobscheduler.quartz.SchedulerService

import org.junit.Test
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.TriggerBuilder

import com.google.inject.Guice

/**
 * @author t_endo
 *
 */
class JobStartRunnerTest {

    @Test
    public void "aaa"() {
        def injector = Guice.createInjector(new EventModule())
        def runner = injector.getInstance(JobStartRunner.class)

        def schedule = new Schedule()
        schedule.setName("test")

        def jobs = new ArrayList<>()
        for (i in (0..10)) {
            String[] cmd1 = ["c${i}"]
            JobParameters params1 = JobParameters.builder().command(cmd1).build()
            Job job1 = Job.builder().name("j${i}").jobParameters(params1).build()
            jobs.add(job1)
        }

        schedule.setChildrenJob(jobs)
        runner.start(schedule)
    }

    @Test
    public void "bbb"() {
        def injector = Guice.createInjector(new EventModule(), new QuartzModule())
        Scheduler scheduler = injector.getInstance(Scheduler.class)
        SchedulerService schedulerService = injector.getInstance(SchedulerService.class)

        schedulerService.start()

        JobDetail jobDetail = JobBuilder.newJob().withIdentity("A", "B").ofType(ScheduleStartJob.class).build()
        Trigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity("A", "B").build();
        scheduler.scheduleJob(jobDetail, trigger)

        TimeUnit.SECONDS.sleep(1)

        schedulerService.stop()
    }
}
