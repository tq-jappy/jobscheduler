package jobscheduler.manager.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

/**
 * コマンド実行ジョブパラメータ
 * 
 * @author t_endo
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CommandJobParameter extends JobParameter {

    String command;

    int nodeId;
}
