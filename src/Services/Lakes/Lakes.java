package Services.Lakes;

public class Lakes {

    private int id_lowiska;
    private double powierzchnia;
    private double max_glebokosc;
    private int liczba_stanowisk;
    private String rodzaj_ryb;
    private Enum rodzaj_lowiska;
    private double cena_wstepu;
    private String godziny_otwarcia;

    public Lakes(int id_lowiska, double powierzchnia, double max_glebokosc, int liczba_stanowisk, String rodzaj_ryb, Enum rodzaj_lowiska, double cena_wstepu, String godziny_otwarcia) {
        this.id_lowiska = id_lowiska;
        this.powierzchnia = powierzchnia;
        this.max_glebokosc = max_glebokosc;
        this.liczba_stanowisk = liczba_stanowisk;
        this.rodzaj_ryb = rodzaj_ryb;
        this.rodzaj_lowiska = rodzaj_lowiska;
        this.cena_wstepu = cena_wstepu;
        this.godziny_otwarcia = godziny_otwarcia;
    }

    public int getId_lowiska() {
        return id_lowiska;
    }

    public void setId_lowiska(int id_lowiska) {
        this.id_lowiska = id_lowiska;
    }

    public double getPowierzchnia() {
        return powierzchnia;
    }

    public void setPowierzchnia(double powierzchnia) {
        this.powierzchnia = powierzchnia;
    }

    public double getMax_glebokosc() {
        return max_glebokosc;
    }

    public void setMax_glebokosc(double max_glebokosc) {
        this.max_glebokosc = max_glebokosc;
    }

    public int getLiczba_stanowisk() {
        return liczba_stanowisk;
    }

    public void setLiczba_stanowisk(int liczba_stanowisk) {
        this.liczba_stanowisk = liczba_stanowisk;
    }

    public String getRodzaj_ryb() {
        return rodzaj_ryb;
    }

    public void setRodzaj_ryb(String rodzaj_ryb) {
        this.rodzaj_ryb = rodzaj_ryb;
    }

    public Enum getRodzaj_lowiska() {
        return rodzaj_lowiska;
    }

    public void setRodzaj_lowiska(Enum rodzaj_lowiska) {
        this.rodzaj_lowiska = rodzaj_lowiska;
    }

    public double getCena_wstepu() {
        return cena_wstepu;
    }

    public void setCena_wstepu(double cena_wstepu) {
        this.cena_wstepu = cena_wstepu;
    }

    public String getGodziny_otwarcia() {
        return godziny_otwarcia;
    }

    public void setGodziny_otwarcia(String godziny_otwarcia) {
        this.godziny_otwarcia = godziny_otwarcia;
    }
}
