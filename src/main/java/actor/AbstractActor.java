package actor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractActor {

    private String name;
    private Boolean enableRunning;
    private ConcurrentLinkedQueue<Message> inbox = new ConcurrentLinkedQueue<>();
    private ActorMaster master;
    private ExecutorService executor;

    public AbstractActor(String name) {
        this.name = name;
        this.enableRunning = Boolean.TRUE;
        executor = Executors.newSingleThreadExecutor();
    }

    public void start() {

        CompletableFuture.runAsync(executeActorLoop(), executor);
    }

    public void stop() {
        this.enableRunning = Boolean.FALSE;
    }

    public abstract void handle(Message message);

    public void receive(Message message) {
        this.inbox.add(message);
    }

    public String getName() {
        return name;
    }

    public void publish(Message message) {
        master.receive(message);
    }

    protected Runnable executeActorLoop() {
        return new ActorLoop()
                    .actor(this.name)
                    .isRunning(() -> this.enableRunning)
                    .waitIf(() -> inbox.size() == 0)
                    .poller(() -> inbox.poll())
                    .handler(m -> handle(m))
                    .loop();
    }

    protected void setMaster(ActorMaster actorMaster) {
        this.master = actorMaster;
    }

}

