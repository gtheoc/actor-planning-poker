package planning.actor;

import actor.AbstractActor;
import actor.Message;
import domain.DeveloperType;
import domain.Estimation;
import domain.Story;
import planning.actor.strategy.EstimationStrategy;
import planning.actor.strategy.LazyEstimationStrategy;
import planning.actor.strategy.PreciseEstimationStrategy;
import planning.actor.strategy.TrivialEstimationStrategy;

public class DeveloperActor extends AbstractActor implements Developer {

    private final DeveloperType developerType;

    public DeveloperActor(String name, DeveloperType developerType) {
        super(name);
        this.developerType = developerType;
    }

    @Override
    public void handle(Message message) {
        if (message.getBody() instanceof Story) {
            Estimation estimation = estimateByType((Story) message.getBody());
            System.out.println(this.getName() + ": this story could be a " + estimation);
        }
    }


    @Override
    public Estimation estimate(Story story, EstimationStrategy strategy) {
        return strategy.estimate(story);
    }

    private Estimation estimateByType(Story story) {
        switch (this.developerType) {
            case JUNIOR:
                return estimate(story, new TrivialEstimationStrategy());
            case EXPERIENCED:
                return estimate(story, new PreciseEstimationStrategy());
            case LAZY:
                return estimate(story, new LazyEstimationStrategy());
            default:
                return estimate(story, new PreciseEstimationStrategy());
        }
    }

}
