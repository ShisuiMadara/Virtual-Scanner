package scanner;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import com.google.gson.*;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;


public class App 
{
    static Integer finalScanner;
    public static String ObjToJson(ScannerMessage msg){
        Gson conv = new Gson();
        return conv.toJson(msg);
    }

    public static String ObjToJson(DestinationMessage msg){
        Gson conv = new Gson();
        return conv.toJson(msg);
    }

    public static SoftMessage JsonToObj(String j){
        Gson conv = new Gson();
        return conv.fromJson(j, SoftMessage.class);
    }
    
    public static void main(String[] args)
        throws IOException
    {
        File outputFile = new File("./output.txt");
        PrintWriter outputWriter = new PrintWriter(outputFile);
        outputWriter.print("");
        outputWriter.close();

        try{
            File nf = new File("./ScannerInput.log");
            nf.createNewFile();
            FileWriter BlankFile = new FileWriter(nf);
            BlankFile.write("");
            BlankFile.close();
            nf = new File("./Destination.log");
            nf.createNewFile();
            BlankFile = new FileWriter("./Destination.log");
            BlankFile.write("");
            BlankFile.close();
        }
        catch(IOException e){

        }

        File inputScannerSample= new File ("./scan.txt");
        FileInputStream scannerSample = new FileInputStream(inputScannerSample);

        File inputObjectSample = new File ("./objectInput.txt");
        FileInputStream objectSample = new FileInputStream(inputObjectSample);

        int ch;
        String object = "";

        
        while((ch = objectSample.read()) != -1)
        {
            char here = (char)ch;
            object += here;
        }

        int ne;
        String scanner = "";

        while((ne = scannerSample.read()) != -1)
        {
            char here = (char)ne;
            scanner += here;
        }

        // System.out.println(scanner);
        // System.out.println(object);

        List <String> tempobjectSequence = Arrays.asList(object.split(","));
        List <String> tempscannerSequence = Arrays.asList(scanner.split(","));

        int n = tempobjectSequence.size();
        int m = tempscannerSequence.size();

        String [] objectSequence = new String [n];
        int [] scannerSequence = new int [m];

        //finalScanner = scannerSequence[m-1];
        
        //System.out.println(scannerSequence[m-1]);

        for(Integer i = 0; i<n; ++i)
        {
            objectSequence[i] = tempobjectSequence.get(i);
        }

        
        for(Integer i = 0; i<m; ++i)
        {
            scannerSequence[i] = Integer.parseInt(tempscannerSequence.get(i));
        }


        HashMap<String, Long>mp = new HashMap<>();

        //storing unique ids for each barcode 

        for(Integer i = 0; i< n; ++i)
        {
            mp.put(objectSequence[i], Long.valueOf(i));
        }

       ConcurrentHashMap<String, Integer> status = new ConcurrentHashMap<>();

        status.put(objectSequence[0], 0);

        //0 --> in line i.e. not in scanner

        //scanner value starts from 1

        Integer i = 1;
        
        HashMap <String, Integer> completed = new HashMap <>();


        Integer iteration = 1;

        MockHoneyWellSoft.fscanner = scannerSequence[m-1];

        while(completed.size() != n)
        {
            //iterating over the parcels in queue and pushing each of them

            Iterator<Map.Entry<String, Integer>> itr1 =status.entrySet().iterator();

            while(itr1.hasNext())
            {
                Map.Entry<String, Integer> entry = itr1.next();
                
                if(completed.containsKey(entry.getKey()))
                {
                    continue;
                }

                if(entry.getValue() + 1 > m)
                {
                    continue;
                }
                status.replace(entry.getKey(), entry.getValue()+1);

            
            }   

            Iterator<Map.Entry<String, Integer>> itr2 =status.entrySet().iterator();

            

            while(itr2.hasNext())
            {
               
                Map.Entry<String, Integer> entry = itr2.next();

              
                Integer scanner_current = entry.getValue()-1;
                String object_current = entry.getKey();

                if(completed.containsKey(object_current))
                {
                   
                    continue;
                }  
                
                ScannerMessage scannermessage = new ScannerMessage(mp.get(object_current), scannerSequence[scanner_current], object_current);

                String msg = ObjToJson(scannermessage);

                String s;
               
                s = MockHoneyWellSoft.ScannerReport(msg);


                SoftMessage sm = JsonToObj(s);

                Long c = sm.CcsID();

                String str = "";

                str += s;

                BufferedWriter writer = new BufferedWriter(new FileWriter("./output.txt", true));
                writer.append("\n");
                writer.append("The interaction for iteration " + iteration + " was: \n");
                writer.append(str);
                writer.append('\n');

                writer.close();

                iteration ++;

                //System.out.println(str);

                if(c == 1)
                { 
                    DestinationMessage dest = new DestinationMessage(mp.get(object_current), scannerSequence[scanner_current], object_current, c);

                    MockHoneyWellSoft.DestionationReport(ObjToJson(dest));
                    
                  
                    completed.put(object_current, 1);

                    //object is removed because the status is completed
                }
                else
                {
                    continue;
                    //onto the next object
                }        

            }
            if(i < n)
            {
                status.put(objectSequence[i], 0);
                i++;
            }


        }

    }
}
