import java.util.Random;

public enum PatientCondition {
    LIGHT(10),
    SERIOUS(5),
    CRITIC(1);

    private Integer priority;

    PatientCondition(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }

    public static PatientCondition draw(){
        return values()[new Random().nextInt(3)];
    }

}
