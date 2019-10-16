import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class Registration implements Runnable {

    private int patientNumber = 0;
    private BlockingQueue<Patient> patientsQueue;
    private LocalDateTime endOfService;

    public Registration(BlockingQueue<Patient> patientsQueue, LocalDateTime endOfService) {
        this.patientsQueue = patientsQueue;
        this.endOfService = endOfService;
    }

    public void patientRegistration() {
        final Patient patient = new Patient(++patientNumber);
        System.out.println("\n Patient arrived. Patient registering: " + patient);
        patientsQueue.offer(patient);

    }

    public void patientsRegistration() throws InterruptedException {
        System.out.println("Registration begins work. ");
        while (LocalDateTime.now().isBefore(endOfService)) {
            int breakBetweenPatients = new Random().nextInt(5) + 1;
            Thread.sleep(breakBetweenPatients * 1000);
            this.patientRegistration();
        }

        System.out.println("Registration has finished.");

    }


    @Override
    public void run() {
        try {
            this.patientsRegistration();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
