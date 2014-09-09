package jobscheduler.manager.domain;

/**
 * ユニットの種類を表す enum
 * 
 * @author t_endo
 */
public enum UnitType {

    /**
     * ジョブネット
     */
    JOB_NET,

    /**
     * ジョブ: コマンド実行
     */
    COMMAND_JOB,

    /**
     * ジョブ: ファイル監視
     */
    FILE_WATCH_JOB,

    /**
     * ジョブ: ファイル転送
     */
    FILE_TRANSFER_JOB
}
