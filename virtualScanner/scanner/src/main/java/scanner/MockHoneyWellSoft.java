package scanner;
import com.google.gson.*;
import java.util.Random;
import java.io.*;

public class MockHoneyWellSoft {
    public static int fscanner = 0;
    public static String ScannerReport(String msg){
        Gson conv = new Gson();
        ScannerMessage scn = conv.fromJson(msg, ScannerMessage.class);
        //System.out.println(msg);
        Integer x;

        if(scn.ScannerNum() == fscanner)
        {
            x = 1;
        }
        else
        {
            Random rd = new Random();
            x = rd.nextInt(1000);
            if (x < 200) x = 1;
            if (x >= 200) x = 999;
        }
        try{
                File nf = new File("./ScannerInput.log");
                FileWriter f = new FileWriter(nf, true);
                f.append(msg + "\n");
                f.close();
            }
            catch(IOException e){
                System.out.println("Error while making log file");
                System.out.println(e.toString());
         }
            
            
        SoftMessage ms = new SoftMessage(scn.BoxID(), scn.ScannerNum(), x);
        return conv.toJson(ms);
        
    }
    public static void DestionationReport(String str){
        Gson conv = new Gson();
        try{
            File nf = new File("./Destination.log");
            FileWriter f = new FileWriter(nf, true);
            f.write(str + "\n");
            f.close();
        }
        catch(IOException e){
            System.out.println("Error while making log file");
        }
        DestinationMessage obj = conv.fromJson(str, DestinationMessage.class);
    }
}
