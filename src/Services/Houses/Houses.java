package Services.Houses;

public class Houses {

    private int id_domu;
    private int ilu_osobowy;
    private int przy_jakim_lowisku;
    private double cena_wynajmu;

    public Houses(int id_domu, int ilu_osobowy, int przy_jakim_lowisku, double cena_wynajmu) {
        this.id_domu = id_domu;
        this.ilu_osobowy = ilu_osobowy;
        this.przy_jakim_lowisku = przy_jakim_lowisku;
        this.cena_wynajmu = cena_wynajmu;
    }

    public int getId_domu() {
        return id_domu;
    }

    public void setId_domu(int id_domu) {
        this.id_domu = id_domu;
    }

    public int getIlu_osobowy() {
        return ilu_osobowy;
    }

    public void setIlu_osobowy(int ilu_osobowy) {
        this.ilu_osobowy = ilu_osobowy;
    }

    public int getPrzy_jakim_lowisku() {
        return przy_jakim_lowisku;
    }

    public void setPrzy_jakim_lowisku(int przy_jakim_lowisku) {
        this.przy_jakim_lowisku = przy_jakim_lowisku;
    }

    public double getCena_wynajmu() {
        return cena_wynajmu;
    }

    public void setCena_wynajmu(double cena_wynajmu) {
        this.cena_wynajmu = cena_wynajmu;
    }
}
