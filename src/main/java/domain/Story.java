package domain;

public class Story {

    private String description;
    private Estimation defaultEstimation;

    public Story(String description, Estimation commonEstimation) {
        this.description = description;
        this.defaultEstimation = commonEstimation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Estimation getDefaultEstimation() {
        return defaultEstimation;
    }

    public void setDefaultEstimation(Estimation defaultEstimation) {
        this.defaultEstimation = defaultEstimation;
    }
}
