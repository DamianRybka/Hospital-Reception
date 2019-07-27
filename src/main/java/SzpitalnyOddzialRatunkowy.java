import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class SzpitalnyOddzialRatunkowy {

    public void start(int czasDzialania){
        final LocalDateTime start = LocalDateTime.now();
        final LocalDateTime koniecObslugi = start.plusSeconds(czasDzialania);

        final Comparator<Pacjent> poStanie = Comparator.comparing(pacjent -> pacjent.getStan().getPriorytet());
        final Comparator<Pacjent> poCzasiePrzyjecia = Comparator.comparing(Pacjent::getCzasPrzyjecia);

        final Comparator<Pacjent> priorytetPacjenta = poStanie.thenComparing(poCzasiePrzyjecia);

        final PriorityBlockingQueue<Pacjent> kolejkaPacjentow = new PriorityBlockingQueue<>(5,priorytetPacjenta);

        Rejestracja rejestracja = new Rejestracja(kolejkaPacjentow, koniecObslugi);
        Lekarz jan = new Lekarz("Jan", kolejkaPacjentow, koniecObslugi);
        Lekarz monika = new Lekarz("Monika", kolejkaPacjentow, koniecObslugi);

        new Thread(jan).start();
        new Thread(monika).start();
        new Thread(rejestracja).start();
    }


}
