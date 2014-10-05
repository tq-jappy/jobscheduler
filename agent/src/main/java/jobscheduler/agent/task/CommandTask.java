package jobscheduler.agent.task;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import jobscheduler.agent.dto.JobParameter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * コマンド実行タスク
 * 
 * @author t_endo
 */
public class CommandTask implements JobTask {

    private Consumer<Integer> onComplete;

    private String command;

    private Process process;

    @Inject
    public CommandTask(@Assisted JobParameter param,
            Consumer<Integer> onComplete) throws IOException {
        this.command = param.getCommand();
        this.onComplete = onComplete;
    }

    public void stop() {
        process.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        System.out.println("call.");

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectOutput(new File("stdout.log"));
        pb.redirectError(new File("stderr.log"));

        try {
            process = pb.start();

            int exitValue = process.waitFor();
            onComplete.accept(exitValue);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
