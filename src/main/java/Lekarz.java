import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Lekarz implements Runnable {

    private String imie;
    private BlockingQueue<Pacjent> kolejkaPacjentow;
    private LocalDateTime koniecObslugi;

    public Lekarz(String imie, BlockingQueue<Pacjent> kolejkaPacjentow, LocalDateTime koniecObslugi) {
        this.imie = imie;
        this.kolejkaPacjentow = kolejkaPacjentow;
        this.koniecObslugi = koniecObslugi;
    }

    private void przyjmijPacjenta() throws InterruptedException{
        final Pacjent pacjent = kolejkaPacjentow.take();
        long czasObslugi = (new Random().nextInt(5)+5)*1000;
        System.out.println("Lekarz " + imie + " przyjmuje pacjenta. " + pacjent);
        Thread.sleep(czasObslugi);
        System.out.println("\nLekarz " + imie + ", obsłużył pacjenta. " + pacjent + "Czas obsługi: " + czasObslugi);
    }

    public void przyjmujPacjentow() throws InterruptedException{
        System.out.println("Lekarz " + imie + " zaczyna dyżur.");

        while (LocalDateTime.now().isBefore(koniecObslugi) || (LocalDateTime.now().isAfter(koniecObslugi) && !kolejkaPacjentow.isEmpty())){
            this.przyjmijPacjenta();
        }
        System.out.println("Lekarz " + imie + " zakończył pracę.");
    }

    @Override
    public void run() {
        try {
            this.przyjmujPacjentow();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}