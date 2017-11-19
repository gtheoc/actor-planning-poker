package planning.actor;

import domain.Story;

public interface BusinessAnalyst {

    Story pickStory();

    void describeStory(Story story);

}
