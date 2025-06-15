package Services.Wędki;

public class FedderRods extends FishingRods{

    private String podajnik;

    public FedderRods(int id, String kolowrotek, String wedzisko, String zylka, double stan, Enum rodzaj_wedki, double cena_wypozyczenia, String podajnik) {
        super(id, kolowrotek, wedzisko, zylka, stan, rodzaj_wedki, cena_wypozyczenia);
        this.podajnik = podajnik;
    }

    public String getPodajnik() {
        return podajnik;
    }

    public void setPodajnik(String podajnik) {
        this.podajnik = podajnik;
    }
}
