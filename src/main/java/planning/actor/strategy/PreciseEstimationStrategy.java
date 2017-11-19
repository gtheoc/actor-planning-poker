package planning.actor.strategy;

import domain.Estimation;
import domain.Story;

public class PreciseEstimationStrategy implements EstimationStrategy<Story> {

    @Override
    public Estimation estimate(Story story) {
        return story.getDefaultEstimation();
    }
}
