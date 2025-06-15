package Services.Lakes;

public class SpiningLakes extends Lakes{

    private Boolean czy_mozna_lowic_z_lodki;
    private Boolean czy_mozna_zabierac_ryby;
    private String typ_dna;

    public SpiningLakes(int id_lowiska, double powierzchnia, double max_glebokosc, int liczba_stanowisk, String rodzaj_ryb, Enum rodzaj_lowiska, double cena_wstepu, String godziny_otwarcia, Boolean czy_mozna_lowic_z_lodki, Boolean czy_mozna_zabierac_ryby, String typ_dna) {
        super(id_lowiska, powierzchnia, max_glebokosc, liczba_stanowisk, rodzaj_ryb, rodzaj_lowiska, cena_wstepu, godziny_otwarcia);
        this.czy_mozna_lowic_z_lodki = czy_mozna_lowic_z_lodki;
        this.czy_mozna_zabierac_ryby = czy_mozna_zabierac_ryby;
        this.typ_dna = typ_dna;
    }

    public Boolean getCzy_mozna_lowic_z_lodki() {
        return czy_mozna_lowic_z_lodki;
    }

    public void setCzy_mozna_lowic_z_lodki(Boolean czy_mozna_lowic_z_lodki) {
        this.czy_mozna_lowic_z_lodki = czy_mozna_lowic_z_lodki;
    }

    public Boolean getCzy_mozna_zabierac_ryby() {
        return czy_mozna_zabierac_ryby;
    }

    public void setCzy_mozna_zabierac_ryby(Boolean czy_mozna_zabierac_ryby) {
        this.czy_mozna_zabierac_ryby = czy_mozna_zabierac_ryby;
    }

    public String getTyp_dna() {
        return typ_dna;
    }

    public void setTyp_dna(String typ_dna) {
        this.typ_dna = typ_dna;
    }
}
