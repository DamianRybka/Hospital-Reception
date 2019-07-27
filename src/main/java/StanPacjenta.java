import java.util.Random;

public enum StanPacjenta {
    LEKKI(10),
    POWAŻNY(5),
    KRYTYCZNY(1);

    private Integer priorytet;

    StanPacjenta(Integer priorytet) {
        this.priorytet = priorytet;
    }

    public Integer getPriorytet() {
        return priorytet;
    }

    public static StanPacjenta wylosuj(){
        return values()[new Random().nextInt(3)];
    }

}
