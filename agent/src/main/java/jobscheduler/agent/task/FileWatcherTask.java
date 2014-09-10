package jobscheduler.agent.task;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import jobscheduler.agent.dto.JobParameter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * 
 * @author t_endo
 */
public class FileWatcherTask implements JobTask {

    private Path file;

    // @Inject
    private WatchService watcher;

    private WatchKey watchKey;

    @Inject
    public FileWatcherTask(@Assisted JobParameter param) throws IOException {
        file = Paths.get(param.getPath());

        watcher = FileSystems.getDefault().newWatchService();
        watchKey = file.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY);
    }

    public void stop() {
        watchKey.cancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        System.out.println("call.");

        while (watchKey.isValid()) {

            // process events
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                System.out.println(kind);

                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                // Object context = event.context();
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    watchKey.cancel();
                    break;
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    watchKey.cancel();
                    break;
                }
            }

            boolean valid = watchKey.reset();
            if (!valid) {
                break;
            }
        }
    }
}
