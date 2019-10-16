import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Doctor implements Runnable {

    private String name;
    private BlockingQueue<Patient> patientQueue;
    private LocalDateTime endOfService;

    public Doctor(String name, BlockingQueue<Patient> patientQueue, LocalDateTime endOfService) {
        this.name = name;
        this.patientQueue = patientQueue;
        this.endOfService = endOfService;
    }

    private void receivePatient() throws InterruptedException{
        final Patient patient = patientQueue.take();
        long timeOfService = (new Random().nextInt(5)+5)*1000;
        System.out.println("Doctor " + name + " takes care of " + patient);
        Thread.sleep(timeOfService);
        System.out.println("\nDoctor " + name + ", patient served. " + patient + "Time of the service: " + timeOfService);
    }

    public void receivePatients() throws InterruptedException{
        System.out.println("Doctor " + name + " starting duty.");

        while (LocalDateTime.now().isBefore(endOfService) || (LocalDateTime.now().isAfter(endOfService) && !patientQueue.isEmpty())){
            this.receivePatient();
        }
        System.out.println("Doctor " + name + " ends of work.");
    }

    @Override
    public void run() {
        try {
            this.receivePatients();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}