package AFD;

import java.util.*;

public class ListaTranzitii {
    private ArrayList<Tranzitie> lista;
    private int x;
    ListaTranzitii(){
        this.lista=new ArrayList<>();
    }
    void adaugaTranzitie(Tranzitie tr){
        this.lista.add(tr);
    }
    public Tranzitie gasesteTranzitie(String stare, char simbol) throws CustomException{
        for (int i=0; i<this.lista.size(); i++){
            Tranzitie tmp = this.lista.get(i);
            if (tmp.getSt_inceput().equals(stare) && tmp.getSimbol()==simbol)
                return tmp;
        }
        throw new CustomException("Nu exista tranzitia " +stare +
                " cu simbolul: " + simbol);
    }
}
