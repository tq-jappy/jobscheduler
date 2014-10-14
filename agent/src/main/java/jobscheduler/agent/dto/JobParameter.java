package jobscheduler.agent.dto;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * @author t_endo
 *
 */
@Data
@Builder
public class JobParameter {

    /** ファイルパス */
    String path;

    /** 実行コマンド */
    String command;

    /** コマンド実行ユーザ */
    String user;
}
