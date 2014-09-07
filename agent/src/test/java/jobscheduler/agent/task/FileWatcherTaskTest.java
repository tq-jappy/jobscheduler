package jobscheduler.agent.task;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jobscheduler.agent.guice.AgentModule;
import jobscheduler.agent.guice.TaskFactory;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author t_endo
 *
 */
public class FileWatcherTaskTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void test() throws Exception {
        Injector injector = Guice.createInjector(new AgentModule());
        TaskFactory factory = injector.getInstance(TaskFactory.class);

        FileWatcherTask task = factory.create(tempFolder.getRoot().getPath());

        ExecutorService exec = Executors.newFixedThreadPool(2);
        Future<String> future = exec.submit(task);

        exec.submit(() -> {
            try {
                File file = new File(tempFolder.getRoot(), "aaa");
                Files.createFile(file.toPath());
            } catch (Exception e) {
            }
        });

        String actual = future.get();
        System.out.println(actual);
    }
}
