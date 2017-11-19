package actor;

import java.util.Optional;
import java.util.function.*;

public class ActorLoop {

    private final static Long DEFAULT_SLEEP_TIME = Long.valueOf(1000);
    private Long millis;
    private Boolean enableSleep;
    private String name;
    private Consumer<Message> handler;
    private BooleanSupplier shouldSleep;
    private BooleanSupplier isRunning;
    private Supplier<Message> pollMessage;

    public ActorLoop() {
        this.millis = DEFAULT_SLEEP_TIME;
    }

    public Runnable loop() {
        return () -> {
            while (this.isRunning.getAsBoolean()) {
                if (this.shouldSleep.getAsBoolean()) {
                    try {
                        Thread.sleep(millis);
                    } catch (InterruptedException e) {
                        // TODO should use a functional interface to handle exceptions
                        e.printStackTrace();
                    }
                }
                Message m = pollMessage.get();
                if (Optional.ofNullable(m).isPresent()) {
                    handler.accept(m);
                }
            }
        };
    }

    public ActorLoop waitIf(BooleanSupplier test) {
        this.shouldSleep = test;
        return this;
    }

    public ActorLoop isRunning(BooleanSupplier isRunning) {
        this.isRunning = isRunning;
        return this;
    }

    public ActorLoop poller(Supplier pollMessage) {
        this.pollMessage = pollMessage;
        return this;
    }

    public Long getMillis() {
        return millis;
    }

    public ActorLoop setMillis(Long millis) {
        this.millis = millis;
        return this;
    }

    public Boolean getEnableSleep() {
        return enableSleep;
    }

    public void setEnableSleep(Boolean enableSleep) {
        this.enableSleep = enableSleep;
    }

    public String getName() {
        return name;
    }

    public ActorLoop actor(String name) {
        this.name = name;
        return this;
    }

    public ActorLoop handler(Consumer<Message> handler) {
        this.handler = handler;
        return this;
    }
}
