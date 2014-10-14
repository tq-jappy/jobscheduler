package jobscheduler.agent.task;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
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

    private String user;

    private Process process;

    @Inject
    public CommandTask(@Assisted JobParameter param,
            Consumer<Integer> onComplete) throws IOException {
        this.command = param.getCommand();
        this.user = param.getUser();
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

        ProcessBuilder pb = new ProcessBuilder(buildCommands());
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

    /**
     * 実行するコマンドを組み立てる
     * 
     * @return
     */
    private String[] buildCommands() {
        String user = System.getProperty("user.name");
        String os = System.getProperty("os.name").toLowerCase(
                Locale.getDefault());

        if (os.startsWith("windows")) {
            return new String[] { "CMD", "/C", this.command };
        } else {
            if (user.equals(this.user)) {
                return new String[] { "sh", "-c", this.command };
            } else {
                return new String[] { "sudo", "-u", this.user, "sh", "-c",
                        this.command };
            }
        }
    }
}
