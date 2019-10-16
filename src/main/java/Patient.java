import java.time.LocalDateTime;


public class Patient {

    private PatientCondition condition;
    private int number;
    private LocalDateTime timeOfService;

    public Patient(Integer number) {
        this.number = number;
        this.timeOfService = LocalDateTime.now();
        this.condition = PatientCondition.draw();
    }

    public PatientCondition getCondition() {
        return condition;
    }

    public int getNumber() {
        return number;
    }

    public LocalDateTime getTimeOfService() {
        return timeOfService;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "condition=" + condition +
                ", number=" + number +
                ", timeOfService=" + timeOfService +
                '}';
    }
}
