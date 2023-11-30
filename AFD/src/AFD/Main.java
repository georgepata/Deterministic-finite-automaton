package AFD;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            Automat automat = new Automat("nume_fisier.txt");
            System.out.println(automat.analizeazaCuvant("abc"));
        } catch (CustomException ex){
            System.out.println("Exceptie: " + ex.getMessage());
        } catch (ExceptieAutomat e){
            System.out.println("Exceptie: " + e.getMessage());
        } catch (Exception e1){
            System.out.println("Exceptie\n" + e1.getMessage());
        }
    }
}
