package AFD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Automat {
    private String st_init ="q0";
    private String st_finale[];
    private ListaTranzitii lista=new ListaTranzitii();
    private List<String> stari;
    private List<Character> simboluri;
    private String stareIntitialaRegex = "q[1-9][0-9]*|q[0-9]";
    private String stareFinalaRegex = "(((q[1-9][0-9]* )*q[1-9][0-9]*)|q0)";
    private String tranzitieRegex = "((q[1-9][0-9]*)|q0) ([a-z]|[A-Z]) ((q[1-9][0-9]*)|q0)";
    private ExceptieAutomat exceptieAutomat;
    Automat(String nume_fisier) throws Exception{
        exceptieAutomat=new ExceptieAutomat(new ArrayList<>());
        stari=new ArrayList<>();
        simboluri=new ArrayList<>();
        BufferedReader buf =new BufferedReader(new FileReader(nume_fisier));
        this.st_init = buf.readLine();
        if (!st_init.matches(stareIntitialaRegex)) {
            exceptieAutomat.adaugareEroare("Starea initiala nu a fost scrisa corect");
        }
        String stf=buf.readLine();
        this.st_finale=stf.split(" ");
        for (int i=0; i<st_finale.length; i++)
            if (!st_finale[i].matches(stareFinalaRegex))
                exceptieAutomat.adaugareEroare("Starea " + (i+1) + " nu a fost corect scrisa!");
        int cnt=1;
        while(true){
            StringBuilder stringBuilder=new StringBuilder();
            Tranzitie tr=null;
            String linie=buf.readLine();
            if (linie!=null){
                String elems[] = linie.split(" ");
                if (elems.length==3) {
                    tr = new Tranzitie(elems[0], elems[1].charAt(0), elems[2]);
                    stringBuilder.append(elems[0] + " " + elems[1] + " " + elems[2]);
                    if (!stringBuilder.toString().matches(tranzitieRegex)) {
                        System.out.println("AVEM: " + stringBuilder.toString());
                        exceptieAutomat.adaugareEroare("Tranzitia " + (cnt) + " nu este corect scrisa");
                    }
                    if (!simboluri.contains(elems[1].charAt(0)))
                        simboluri.add(elems[1].charAt(0));
                    if (!stari.contains(elems[0]) && !elems[0].equals("-"))
                        stari.add(elems[0]);
                    if (!stari.contains(elems[2]) && !elems[2].equals("-"))
                        stari.add(elems[2]);
                    this.lista.adaugaTranzitie(tr);
                    cnt++;
                }
                else
                    exceptieAutomat.adaugareEroare("Tranzitia " + (cnt) +" nu este corect scrisa");
            } else break;
        }
        displayData();
        String finalMessage =exceptieAutomat.getMessag();
        throw new ExceptieAutomat(finalMessage);
    }

    boolean analizeazaCuvant(String sir) throws CustomException {
        if (sir.equals("")){
            for (int i=0; i<st_finale.length; i++)
                if (st_finale[i].equals(st_init))
                    return true;
            return false;
        }
        Tranzitie tmp = null;
        for (int i = 0; i < sir.length(); i++) {
            if (i == 0) {
                tmp = lista.gasesteTranzitie(st_init, sir.charAt(i));
                System.out.println((char)948+ "("+ tmp.getSt_inceput() + ", " + tmp.getSimbol() + ")" + "=" + tmp.getSt_sf());
                if (tmp == null)
                    return false;
            } else {
                tmp = lista.gasesteTranzitie(tmp.getSt_sf(), sir.charAt(i));
                System.out.println((char)948+ "("+ tmp.getSt_inceput() + ", " + tmp.getSimbol() + ")" + "=" + tmp.getSt_sf());
                if (tmp == null)
                    return false;
            }
        }
        for (String st_final : st_finale)
            if (tmp.getSt_sf().equals(st_final))
                return true;
        throw new CustomException("Cuvantul nu este potrivit");
    }



    public void displayData(){
        System.out.println((char)948 + ":Qx" + (char)931 + "->P(Q)");
        System.out.print("Q={");
        stari.forEach(element -> {
            System.out.print(element);
            if (stari.indexOf(element) < stari.size()-1)
                System.out.print(", ");
        });
        System.out.println("}");
        System.out.print((char)931 + "={");
        simboluri.forEach(element -> {
            System.out.print(element);
            if (simboluri.indexOf(element) <simboluri.size()-1)
                System.out.print(", ");
        });
        System.out.println("}");
    }
}
