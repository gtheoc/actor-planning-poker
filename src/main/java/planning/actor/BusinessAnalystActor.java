package planning.actor;

import actor.AbstractActor;
import actor.Message;
import domain.ActionType;
import domain.Estimation;
import domain.Story;

public class BusinessAnalystActor extends AbstractActor implements BusinessAnalyst {

    public BusinessAnalystActor(String name) {
        super(name);
    }

    @Override
    public void handle(Message message) {
        if (message.getBody() instanceof ActionType) {
            if (readyToPickAStory((ActionType) message.getBody())) {
                describeStory(pickStory());
            }
        }
    }

    @Override
    public Story pickStory() {
        // TODO should pop story from a Backlog Collection of Stories (implemented like a Stack)
        return new Story("Create an endpoint on Customer API", Estimation.M);
    }

    @Override
    public void describeStory(Story story) {
        System.out.println("BA: We should implement \'" + story.getDescription() + "\'");
        publish(new Message(this.getName(), story));
    }

    private Boolean readyToPickAStory(ActionType actionType) {
        return actionType.equals(ActionType.PICK);
    }

}
