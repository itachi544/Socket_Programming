/*import org.apache.poi.ss.usermodel.*;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ACM {
static   FileWriter fw1 = null;
 static      String index1 =  "index.txt"
 static       File f1 = new File("C:\\Users\\Anup Kumar\\Documents\\NetBeansProjects\\Tictactoe\\" + index1);
       fw1 = new FileWriter(f1);
        bw1 = new BufferedWriter(fw1);
    
    public static void main(String[] args) {
        String csvFile = "top-1m.csv";
        BufferedReader br = null;
        String line = "";
        int count = 0;
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                check(country[1]);
                count++;
                System.out.println("count  : " + count);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        
    }

    public static void check(String sad) throws IOException {
        String title = "";
        String data = "";
        String website;
        URL u;
        HttpURLConnection conn;
        URL obj;
        InputStream is = null;
        DataInputStream dis;
        String s;
        BufferedWriter bw = null;
        FileWriter fw = null;
        FileWriter titlew = null;
       String index = sad + "index.txt"
        File f = new File("C:\\Users\\Anup Kumar\\Documents\\NetBeansProjects\\Tictactoe\\New folder\\" + index);
        fw = new FileWriter(f);
        bw = new BufferedWriter(fw);
        website = "https://www." + sad;
        boolean redirect = false;
        try {
            obj = new URL(website);
            conn = (HttpURLConnection) obj.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            conn.addRequestProperty("Referer", "google.com");
            System.out.println("Request URL ... " + website);
            int status = conn.getResponseCode();
            System.out.println("status  " + status);
            if (status != HttpURLConnection.HTTP_OK) {
                if (status == HttpURLConnection.HTTP_MOVED_TEMP
                        || status == HttpURLConnection.HTTP_MOVED_PERM
                        || status == HttpURLConnection.HTTP_SEE_OTHER) {
                    redirect = true;
                }
            }
        } catch (IOException ioe) {
            website = "http://www." + sad;
            obj = new URL(website);
            conn = (HttpURLConnection) obj.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            conn.addRequestProperty("Referer", "google.com");
            System.out.println(" catch   Request URL ... " + website);
            int status = conn.getResponseCode();
            System.out.println("status in catch  " + status);
            if (status != HttpURLConnection.HTTP_OK) {
                if (status == HttpURLConnection.HTTP_MOVED_TEMP
                        || status == HttpURLConnection.HTTP_MOVED_PERM
                        || status == HttpURLConnection.HTTP_SEE_OTHER) {
                    redirect = true;

                }
            }
        }
        if (redirect) {
            System.out.println("redirecttttttttttt");
            // get redirect url from "location" header field
            String newUrl = conn.getHeaderField("Location");
            System.out.println("new URL   " + newUrl);
            // get the cookie if need, for login
            String cookies = conn.getHeaderField("Set-Cookie");

            // open the new connnection again
            conn = (HttpURLConnection) new URL(newUrl).openConnection();
            conn.setRequestProperty("Cookie", cookies);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            conn.addRequestProperty("Referer", "google.com");

        }
        dis = new DataInputStream(new BufferedInputStream(conn.getInputStream()));
        while ((s = dis.readLine()) != null) {
            data = " " + s + data;
            bw.write(s);
        }

        data = data.replaceAll("\\s+", " ");
        Pattern p = Pattern.compile("<title>(.*?)</title>");
        Matcher m = p.matcher(data);
        while (m.find() == true) {
            title = m.group(1);
            System.out.println("title   " + m.group(1));
          }
        bw.close();
        fw.close();
        double b = f.length() ;
        System.out.println("         length in b:    " + b);
        
            System.out.println("-------------------------------------------------------------------------------------");
    
    }

}
