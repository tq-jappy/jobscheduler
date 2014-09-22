package jobscheduler.manager.bean;

/**
 * ファイル転送ジョブパラメータ
 * 
 * @author t_endo
 */
public class FileWatchJobParameter extends JobParameter {

    String fromPath;

    int fromNodeId;

    String toPath;

    int toNodeId;
}
