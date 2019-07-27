import java.time.LocalDateTime;


public class Pacjent {

    private StanPacjenta stan;
    private int numer;
    private LocalDateTime czasPrzyjecia;

    public Pacjent(Integer numer) {
        this.numer = numer;
        this.czasPrzyjecia = LocalDateTime.now();
        this.stan = StanPacjenta.wylosuj();
    }

    public Pacjent(Integer numer, LocalDateTime czasPrzyjecia, StanPacjenta stan) {
        this.numer = numer;
        this.czasPrzyjecia = czasPrzyjecia;
        this.stan = stan;
    }

    public StanPacjenta getStan() {
        return stan;
    }

    public int getNumer() {
        return numer;
    }

    public LocalDateTime getCzasPrzyjecia() {
        return czasPrzyjecia;
    }

    @Override
    public String toString() {
        return "Pacjent{" +
                "stan=" + stan +
                ", numer=" + numer +
                ", czasPrzyjecia=" + czasPrzyjecia +
                '}';
    }
}
