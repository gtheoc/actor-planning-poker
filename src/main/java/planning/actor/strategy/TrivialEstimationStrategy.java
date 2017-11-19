package planning.actor.strategy;

import domain.Estimation;
import domain.Story;

public class TrivialEstimationStrategy implements EstimationStrategy<Story> {

    @Override
    public Estimation estimate(Story story) {
        return Estimation.XS;
    }
}
