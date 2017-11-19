package actor;

import domain.ActionType;
import domain.Estimation;
import domain.Story;

import java.util.LinkedHashMap;

public class ActorMaster extends AbstractActor {

    private static final String MASTER_ACTOR_ID = "MASTER";
    private LinkedHashMap<String, AbstractActor> registry = new LinkedHashMap<>();

    private ActorMaster() {
        super(MASTER_ACTOR_ID);
    }

    private static class ActorMasterHelper {
        private static final ActorMaster actorMaster = new ActorMaster();
    }

    public static ActorMaster getInstance() {
        return ActorMasterHelper.actorMaster;
    }

    @Override
    public void start() {
        startActors();
        this.receive(new Message(this.getName(), ActionType.START));
        executeActorLoop().run();
    }

    @Override
    public void handle(Message message) {
        if (message.getBody() instanceof ActionType) {
            if (ActionType.START.equals(message.getBody())) {
                publish(new Message(this.getName(), ActionType.PICK));
            }

        } else if (message.getBody() instanceof Estimation) {
            showMessage(message.getSender(), (Estimation) message.getBody());

        } else if (message.getBody() instanceof Story) {
            publish(message);
        }
    }

    public Boolean register(AbstractActor abstractActor) {
        registry.put(abstractActor.getName(), abstractActor);
        abstractActor.setMaster(this);
        return true;
    }

    @Override
    public void publish(Message message) {
        registry.forEach((name, actor) -> actor.receive(message));
    }

    private void startActors() {
        registry.forEach((name, actor) -> actor.start());
    }

    private void stopActors() {
        registry.forEach((name, actor) -> actor.stop());
    }

    private void stopActor(AbstractActor abstractActor){
        abstractActor.stop();
    }

    private void showMessage(String actorName, Estimation estimation) {

    }
}
