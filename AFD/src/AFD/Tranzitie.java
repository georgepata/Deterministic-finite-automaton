package AFD;

public class Tranzitie {
    private String st_inceput;
    private char simbol;
    private String st_sf;
    private String output;
    Tranzitie(String st_inceput, char simbol, String st_sf, String output){
        this.st_inceput=st_inceput;
        this.simbol=simbol;
        this.st_sf=st_sf;
        this.output=output;
    }
    Tranzitie(String st_inceput, char simbol, String st_sf){
        this.st_inceput=st_inceput;
        this.simbol=simbol;
        this.st_sf=st_sf;
    }
    public String getOutput(){
        return output;
    }
    public String getSt_inceput() {
        return st_inceput;
    }

    public char getSimbol() {
        return simbol;
    }

    public String getSt_sf() {
        return st_sf;
    }
}
