import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class HospitalEmergencyWard {

    public void start(int actionDuration){
        final LocalDateTime start = LocalDateTime.now();
        final LocalDateTime endOfService = start.plusSeconds(actionDuration);

        final Comparator<Patient> byCondition = Comparator.comparing(patient -> patient.getCondition().getPriority());
        final Comparator<Patient> byTimeOfService = Comparator.comparing(Patient::getTimeOfService);

        final Comparator<Patient> patientPriority = byCondition.thenComparing(byTimeOfService);

        final PriorityBlockingQueue<Patient> patientsQueue = new PriorityBlockingQueue<>(5,patientPriority);

        Registration registration = new Registration(patientsQueue, endOfService);
        Doctor john = new Doctor("John", patientsQueue, endOfService);
        Doctor monica = new Doctor("Monica", patientsQueue, endOfService);

        new Thread(john).start();
        new Thread(monica).start();
        new Thread(registration).start();
    }


}
