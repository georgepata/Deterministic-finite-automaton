package AFD;

import java.util.ArrayList;

public class ExceptieAutomat extends Exception{
    private ArrayList<String> erori=new ArrayList<>();
    private String message;
    public ExceptieAutomat(ArrayList<String> erori){
        this.erori=erori;
    }

    public ExceptieAutomat(String finalMessage){
        super(finalMessage);
        this.message=finalMessage;
    }
    public void adaugareEroare(String er){
        this.erori.add(er);
    }
    public int getSize(){
        return this.erori.size();
    }
    public String getMessag(){
        StringBuilder sb=new StringBuilder();
        for (int i=0; i<erori.size(); i++)
            sb.append(erori.get(i)+"\n");
        return sb.toString();
    }
}
