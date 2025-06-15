package Services.Wędki;

public class SpiningRods extends FishingRods {

    private String przyneta;

    public SpiningRods(int id, String kolowrotek, String wedzisko, String zylka, double stan, Enum rodzaj_wedki, double cena_wypozyczenia, String przyneta) {
        super(id, kolowrotek, wedzisko, zylka, stan, rodzaj_wedki, cena_wypozyczenia);
        this.przyneta = przyneta;
    }

    public String getPrzyneta() {
        return przyneta;
    }

    public void setPrzyneta(String przyneta) {
        this.przyneta = przyneta;
    }
}
