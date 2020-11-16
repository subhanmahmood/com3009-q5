package main;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException {
        String fileDir = "C:\\Users\\subha\\OneDrive - University of Surrey\\Year 3\\COM3009 - Computer Security\\Coursework\\english.txt";

        String ciphertext = "8845741335F4F9760E7A164224B772BD8F2E233E6D277367DE8E1E0DEE1C5DEC7F608A48A942074F3E9956687553DBEA6DEB2DAF19DD4632773CE8FCE0D6A9F3";
        String mod = "9126567939830645823912675655314422851739157402098143792824508050409080069612344436540917503484370589185407537230943184675074770871142377238024542049157223";
        String exp = "65537";

        RsaTool rsa = new RsaTool(mod, exp);

        List<String> words = readFile(fileDir);

        for(String word : words){
            byte[] c = rsa.encrypt(word);
            StringBuilder sb = new StringBuilder();
            for (byte b : c) {
                sb.append(String.format("%02X", b));
            }
            if(ciphertext.equals(sb.toString())){
                System.out.println(word);
            }
    /*
            System.out.println(word);
            System.out.println(sb.toString());
            System.out.println();

     */
        }


    }
}
