
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Client {
static int player;
static int curplayer;
    boolean flag = false;
    boolean flags = true;
    boolean flag1 = true;
boolean flgs = true;
    
    public Client() {
        try {
            
            Socket socket = new Socket("127.0.0.1", 12345);
            PrintWriter outputWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner inputKeyboard = new Scanner(System.in);

            String response = inputReader.readLine();
                System.out.println("You are Player  " + response);
               outputWriter.println(response);
                outputWriter.flush();
                
               int thanks = Integer.parseInt(inputReader.readLine());
               outputWriter.println(response);
                outputWriter.flush();
               while(flgs){
               if(thanks == 1){
                   flgs = false;
                   flag = true;
               }else{
                outputWriter.println(1);
                outputWriter.flush();     
                   flgs = false;
                   flag = true;
               }
               
               }
                
                while(flag )
                {              
                    System.out.println("inside flagg");
               String response2 = inputReader.readLine();
                curplayer = Integer.parseInt(response2);
                    System.out.println("curplayer"     +curplayer);
                outputWriter.println(curplayer);
                outputWriter.flush();
                //Recieve Board
                if(curplayer == 1){
                    System.out.println("inside player 1");  
                    
                outputWriter.println("Thank You");
                outputWriter.flush();
                    
                String response1 = inputReader.readLine();
                 Display(response1);
                System.out.println("Select a  Position       111");
                String text = inputKeyboard.nextLine();
                //send position
                outputWriter.println(text);
                outputWriter.flush();
                
                }else{
                    System.out.println("inside player 2");  
                    
                outputWriter.println("Thank You");
                outputWriter.flush();
                    
                String response1 = inputReader.readLine();
                 Display(response1);
                System.out.println("Select a  Position         2222");
                String text = inputKeyboard.nextLine();
                //send position
                outputWriter.println(text);
                outputWriter.flush();
                }
                
                
                }//flag ends
                
                
                
            
            

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
           new Client();
    }

    public void Display(String response1){
                String x = response1.replace("[" ," ");
                 x = x.replace("]" ," ");               
              String[] x1 = x.split(",");
              System.out.println("      0   1   2");
               for (int i = 0; i < x1.length; i++) {
                  
               System.out.print("  " + x1[i]);
                    if((i+1)%3 == 0){
                        System.out.println("  ");
                    }

                }
    }
    
}
