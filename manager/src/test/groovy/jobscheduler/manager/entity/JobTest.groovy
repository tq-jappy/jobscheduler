package jobscheduler.manager.entity;

import static org.junit.Assert.*

import org.junit.Test

import com.fasterxml.jackson.databind.ObjectMapper

class JobTest {

    @Test
    public void "シリアライズ -> デシリアライズ変換"() {
        Job job = new Job()
        job.setName("job01")
        job.setMap(["command": "echo hello"])

        ObjectMapper mapper = new ObjectMapper()

        def json = mapper.writeValueAsString(job)
        println json

        def actual = mapper.readValue(json, JobUnit.class)
        println(actual)

        assert actual.getName() == "job01"
        assert actual.getMap() == ["command": "echo hello"]
    }
}
