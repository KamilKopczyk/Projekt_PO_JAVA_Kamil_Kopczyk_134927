package Services.Lakes;

public class FloatLakes extends Lakes{

    private Boolean czy_mozna_lowic_z_lodki;
    private Boolean czy_haczyki_z_zadziorami;

    public FloatLakes(int id_lowiska, double powierzchnia, double max_glebokosc, int liczba_stanowisk, String rodzaj_ryb, Enum rodzaj_lowiska, double cena_wstepu, String godziny_otwarcia, Boolean czy_mozna_lowic_z_lodki, Boolean czy_haczyki_z_zadziorami) {
        super(id_lowiska, powierzchnia, max_glebokosc, liczba_stanowisk, rodzaj_ryb, rodzaj_lowiska, cena_wstepu, godziny_otwarcia);
        this.czy_mozna_lowic_z_lodki = czy_mozna_lowic_z_lodki;
        this.czy_haczyki_z_zadziorami = czy_haczyki_z_zadziorami;
    }

    public Boolean getCzy_mozna_lowic_z_lodki() {
        return czy_mozna_lowic_z_lodki;
    }

    public void setCzy_mozna_lowic_z_lodki(Boolean czy_mozna_lowic_z_lodki) {
        this.czy_mozna_lowic_z_lodki = czy_mozna_lowic_z_lodki;
    }

    public Boolean getCzy_haczyki_z_zadziorami() {
        return czy_haczyki_z_zadziorami;
    }

    public void setCzy_haczyki_z_zadziorami(Boolean czy_haczyki_z_zadziorami) {
        this.czy_haczyki_z_zadziorami = czy_haczyki_z_zadziorami;
    }
}
