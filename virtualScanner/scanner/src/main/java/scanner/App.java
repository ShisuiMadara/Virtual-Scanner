package scanner;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import javax.management.RuntimeErrorException;
import java.util.HashMap;
import com.google.gson.*;

public class App 
{
    
    public static void main(String[] args)
        throws IOException
    {
        FileInputStream scannerSample = new FileInputStream("scanner/src/main/java/scanner/scan.txt");
        FileInputStream objectSample = new FileInputStream("scanner/src/main/java/scanner/objectInput.txt");

        int ch;
        String object = "";

        
        while((ch = scannerSample.read()) != -1)
        {
            char here = (char)ch;
            object += here;
        }

        int ne;
        String scanner = "";

        while((ne = objectSample.read()) != -1)
        {
            char here = (char)ne;
            scanner += here;
        }
        
        List <String> objectSequence = Arrays.asList(object.split(","));
        List <String> scannerSequence = Arrays.asList(scanner.split(","));

        int n = objectSequence.size();
        int m = scannerSequence.size();

        for(int i = 0; i< n; ++i)
        {
            
        }

    }
}
