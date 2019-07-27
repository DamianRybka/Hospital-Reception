import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class Rejestracja implements Runnable {

    private int numerPacjenta = 0;
    private BlockingQueue<Pacjent> kolejkaPacjentow;
    private LocalDateTime koniecObslugi;

    public Rejestracja(BlockingQueue<Pacjent> kolejkaPacjentow, LocalDateTime koniecObslugi) {
        this.kolejkaPacjentow = kolejkaPacjentow;
        this.koniecObslugi = koniecObslugi;
    }

    public void rejestrujPacjenta() {
        final Pacjent pacjent = new Pacjent(++numerPacjenta);
        System.out.println("\n Na SOR trafia pacjent. Rejestruje się pacjenta: " + pacjent);
        kolejkaPacjentow.offer(pacjent);

    }

    public void rejestrujPacjentow() throws InterruptedException {
        System.out.println("Rejestracja zaczyna przyjmować pacjentów. ");
        while (LocalDateTime.now().isBefore(koniecObslugi)) {
            int przerwaMiedzyPacjentami = new Random().nextInt(5) + 1;
            Thread.sleep(przerwaMiedzyPacjentami * 1000);
            this.rejestrujPacjenta();
        }

        System.out.println("Rejestracja zakończyła przyjmować pacjentów.");

    }


    @Override
    public void run() {
        try {
            this.rejestrujPacjentow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
