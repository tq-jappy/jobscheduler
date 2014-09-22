package jobscheduler.manager.resource.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jobscheduler.manager.entity.JobUnit;
import jobscheduler.manager.service.JobService;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@Path("/api/v1/jobs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JobResource {

    @Inject
    private JobService jobService;

    @GET
    public List<JobUnit> getJobs() {
        return jobService.findAll();
    }

    @GET
    @Path("{id}")
    public JobUnit getJob(@PathParam("id") int id) {
        return jobService.findById(id);
    }

    @POST
    public JobUnit postJob(JobUnit jobUnit) {
        return jobService.create(jobUnit);
    }

    @PUT
    @Path("{id}")
    public JobUnit putJob(@PathParam("id") int id, JobUnit jobUnit) {
        return jobService.update(jobUnit);
    }

    @DELETE
    @Path("{id}")
    public void deleteJob(@PathParam("id") int id) {
        jobService.delete(id);
    }
}
