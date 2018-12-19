package backstage;

import utils.Utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

@WebListener
public class FileCreationDaemon implements ServletContextListener {


    public FileCreationDaemon() throws IOException {
    }

    private volatile boolean active = true;

    WatchService watcher = FileSystems.getDefault().newWatchService();
    Path dir = Paths.get(Utils.UN_ZIPPING_PATH);

    Thread myDaemon = new Thread() {

        public void run() {
            while (active) {
                try {

                    System.out.println("Checking directory creation...");
                    dir.register(watcher, ENTRY_CREATE);
                    while (true) {
                        WatchKey key;
                        try {
                            // wait for a key to be available
                            key = watcher.take();
                        } catch (InterruptedException ex) {
                            return;
                        }
                        for ( WatchEvent event : key.pollEvents() ) {
                            System.out.println("New Directory Created");
                            System.out.println(event.context());
                            Utils.setFolderName(event.context().toString());
                        }
                        // IMPORTANT: The key must be reset after processed
                        boolean valid = key.reset();
                        if (!valid) {
                            break;
                        }
                        Thread.sleep(5000);
                    }

                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        myDaemon.setDaemon(true);
        myDaemon.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        active = false;

        try {
            watcher.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Directory watcher was Shutdown");
    }
}
