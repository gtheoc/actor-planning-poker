package planning.actor;

import domain.Estimation;
import domain.Story;
import planning.actor.strategy.EstimationStrategy;

public interface Developer {

    Estimation estimate(Story story, EstimationStrategy strategy);
}
