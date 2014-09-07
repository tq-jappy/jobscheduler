package jobscheduler.agent.task;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Callable;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * 
 * @author t_endo
 */
public class FileWatcherTask implements Callable<String> {

    private Path file;

    // @Inject
    private WatchService watcher;

    private WatchKey watchKey;

    @Inject
    public FileWatcherTask(@Assisted String path) throws IOException {
        file = Paths.get(path);

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
    public String call() throws Exception {
        System.out.println("call.");
        String a = null;

        while (watchKey.isValid()) {

            // process events
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                System.out.println(kind);

                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                Object context = event.context();
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    a = context.toString();
                    watchKey.cancel();
                    break;
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    a = context.toString();
                    watchKey.cancel();
                    break;
                }
            }

            boolean valid = watchKey.reset();
            if (!valid) {
                break;
            }
        }

        return a;
    }
}
