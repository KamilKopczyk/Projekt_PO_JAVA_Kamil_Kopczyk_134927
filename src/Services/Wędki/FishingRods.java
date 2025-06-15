package Services.Wędki;

public class FishingRods {

private int id_wedki;
private String kolowrotek;
private String wedzisko;
private String zylka;
private double stan;
private Enum rodzaj_wedki;
private double cena_wypozyczenia;

    public FishingRods(int id, String kolowrotek, String wedzisko, String zylka, double stan, Enum rodzaj_wedki, double cena_wypozyczenia) {
        this.id_wedki = id;
        this.kolowrotek = kolowrotek;
        this.wedzisko = wedzisko;
        this.zylka = zylka;
        this.stan = stan;
        this.rodzaj_wedki = rodzaj_wedki;
        this.cena_wypozyczenia = cena_wypozyczenia;
    }

    public int getId_wedki() {
        return id_wedki;
    }

    public void setId_wedki(int id) {
        this.id_wedki = id_wedki;
    }

    public String getKolowrotek() {
        return kolowrotek;
    }

    public void setKolowrotek(String kolowrotek) {
        this.kolowrotek = kolowrotek;
    }

    public String getWedzisko() {
        return wedzisko;
    }

    public void setWedzisko(String wedzisko) {
        this.wedzisko = wedzisko;
    }

    public String getZylka() {
        return zylka;
    }

    public void setZylka(String zylka) {
        this.zylka = zylka;
    }

    public double getStan() {
        return stan;
    }

    public void setStan(double stan) {
        this.stan = stan;
    }

    public Enum getRodzaj_wedki() {
        return rodzaj_wedki;
    }

    public void setRodzaj_wedki(Enum rodzaj_wedki) {
        this.rodzaj_wedki = rodzaj_wedki;
    }

    public double getCena_wypozyczenia() {
        return cena_wypozyczenia;
    }

    public void setCena_wypozyczenia(double cena_wypozyczenia) {
        this.cena_wypozyczenia = cena_wypozyczenia;
    }
}
























