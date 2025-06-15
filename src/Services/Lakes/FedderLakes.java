package Services.Lakes;

public class FedderLakes extends Lakes {

    private boolean czy_mozna_necic_lodka;
    private String zakazane_smaki_zanety;

    public FedderLakes(int id_lowiska, double powierzchnia, double max_glebokosc, int liczba_stanowisk, String rodzaj_ryb, Enum rodzaj_lowiska, double cena_wstepu, String godziny_otwarcia, boolean czy_mozna_necic_lodka, String zakazane_smaki_zanety) {
        super(id_lowiska, powierzchnia, max_glebokosc, liczba_stanowisk, rodzaj_ryb, rodzaj_lowiska, cena_wstepu, godziny_otwarcia);
        this.czy_mozna_necic_lodka = czy_mozna_necic_lodka;
        this.zakazane_smaki_zanety = zakazane_smaki_zanety;
    }

    public boolean isCzy_mozna_necic_lodka() {
        return czy_mozna_necic_lodka;
    }

    public void setCzy_mozna_necic_lodka(boolean czy_mozna_necic_lodka) {
        this.czy_mozna_necic_lodka = czy_mozna_necic_lodka;
    }

    public String getZakazane_smaki_zanety() {
        return zakazane_smaki_zanety;
    }

    public void setZakazane_smaki_zanęty(String zakazane_smaki_zanety) {
        this.zakazane_smaki_zanety = zakazane_smaki_zanety;
    }
}
