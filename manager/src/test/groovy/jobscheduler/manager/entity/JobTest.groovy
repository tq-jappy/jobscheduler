package jobscheduler.manager.entity;

import static org.junit.Assert.*
import jobscheduler.manager.bean.CommandJobParameter

import org.junit.Test

import com.fasterxml.jackson.databind.ObjectMapper

class JobTest {

    @Test
    public void test() {
        CommandJobParameter param = CommandJobParameter.builder().nodeId(1).command("test").build()

        Job job = Job.builder().jobParameter(param).build()
        job.setName("a")

        ObjectMapper mapper = new ObjectMapper()

        def jsonParam = mapper.writeValueAsString(param)
        job.setParameters(jsonParam)

        def s = mapper.writeValueAsString(job)
        println s

        def a = mapper.readValue(s, Job.class)
        println(a)
    }
}
