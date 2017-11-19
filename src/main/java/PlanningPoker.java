import actor.ActorMaster;
import domain.DeveloperType;
import planning.actor.BusinessAnalystActor;
import planning.actor.DeveloperActor;

public class PlanningPoker {

    public static void main(String[] args) {

        ActorMaster scrumMaster = ActorMaster.getInstance();

        scrumMaster.register(new BusinessAnalystActor("George"));
        scrumMaster.register(new DeveloperActor("Peter", DeveloperType.EXPERIENCED));
        scrumMaster.register(new DeveloperActor("John", DeveloperType.LAZY));
        scrumMaster.register(new DeveloperActor("Matt", DeveloperType.JUNIOR));

        scrumMaster.start();
    }
}
