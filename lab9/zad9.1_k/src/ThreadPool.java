import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {

    private BlockingQueue threadQueue = null;
    private List<MyThread> threads;
}
