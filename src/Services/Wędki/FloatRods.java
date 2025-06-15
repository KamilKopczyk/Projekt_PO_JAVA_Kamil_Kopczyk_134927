package Services.Wędki;

public class FloatRods extends FishingRods{

    private String spławik;

    public FloatRods(int id, String kolowrotek, String wedzisko, String zylka, double stan, Enum rodzaj_wedki, double cena_wypozyczenia, String spławik) {
        super(id, kolowrotek, wedzisko, zylka, stan, rodzaj_wedki, cena_wypozyczenia);
        this.spławik = spławik;
    }

    public String getSpławik() {
        return spławik;
    }

    public void setSpławik(String spławik) {
        this.spławik = spławik;
    }
}
