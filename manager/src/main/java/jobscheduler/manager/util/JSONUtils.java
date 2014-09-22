package jobscheduler.manager.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jobscheduler.manager.entity.JobUnit;
import lombok.SneakyThrows;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author t_endo
 *
 */
public final class JSONUtils {

    /**
     * ジョブユニットのシリアライズJSONデータをマップに変換してセットする。
     * 
     * @param jobUnit
     *            ジョブユニット
     */
    @SneakyThrows
    public static void readAndSetMap(JobUnit jobUnit) {
        Map<String, String> parameters = read(jobUnit.getParametersJson());
        jobUnit.setParameters(parameters);
    }

    /**
     * TODO: 再利用可能なオブジェクトは使いまわす
     * 
     * @param json
     * @return
     * @throws IOException
     */
    public static Map<String, String> read(String json) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);

        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };

        HashMap<String, String> o = mapper.readValue(json, typeRef);
        return o;
    }

    private JSONUtils() {
    }
}
