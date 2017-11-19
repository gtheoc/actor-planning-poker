package planning.actor.strategy;

import domain.Estimation;

public interface EstimationStrategy<T> {

    Estimation estimate(T t);
}
