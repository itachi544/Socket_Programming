
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.*;

public class ServerThread {

    static int count = 0;
    static int current_player;
    static int[]  pls = new int[2];
    static int next_player;
    static char[][] board = new char[3][3];

    public ServerThread() {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(12345);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + 12345);
            System.exit(-1);
        }

        while (true) {
            try {

                System.err.println("Waiting for connection");
                Socket s1 = serverSocket.accept();
                System.err.println("Started new connection from " + s1.getPort());
                int port = s1.getPort();
                pls[count] = port;     
                count++;
                ConnectionThread st1 = new ConnectionThread(s1);
                st1.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ServerThread();

    }

    class ConnectionThread extends Thread {

        private Socket socket = null;

        public ConnectionThread(Socket socket) {
            super("ConnectionThread");
            this.socket = socket;
        }
                     
        
        @Override
        public void run() {
            int count1 = 0;
            
            boolean flg = true;
            boolean flag2 = false;
            boolean flag1 = true;

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    board[i][j] = '-';
                }
            }
                try {
                    
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();

                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
                    PrintWriter outputWriter = new PrintWriter(outputStream);
                    System.out.println(pls[0]);
                    System.out.println(pls[1]);
                    
                    
                    outputWriter.println(count);
                    outputWriter.flush();
                    while(flag1){
current_player = Integer.parseInt(inputReader.readLine());
                  
if(current_player == 2){
                      flag1 = false;
                        flag2 = true;
                        outputWriter.println(2);
                        outputWriter.flush();
                    
}else {
    System.out.println("Waiting for player 2");
}
                    }
                          
                    String x = inputReader.readLine();
                    System.out.println("xxx"+x);
                    while (flag2) {
                        
                        
                        current_player = Integer.parseInt(inputReader.readLine());
                        System.out.println("currentplayer"+current_player);
                        if(current_player ==1){
                            System.out.println("inside cur pl 1");
                            
                        outputWriter.println(current_player);
                        outputWriter.flush();
                        //Read Thank You
                        String text1 = inputReader.readLine();
                            System.out.println("text1"+text1);   
                        outputWriter.println(Arrays.deepToString(board));
                        outputWriter.flush();
                         String text = inputReader.readLine();
                            System.out.println("text"+text);
                        String[] words = text.split(" ");
                        int a = Integer.parseInt(words[0]);
                        int b = Integer.parseInt(words[1]);
                        int sa = 0;
                     
                        if (count1%2  == 0) {
                            sa = 2;

                        } else {
                            sa = 1;

                        }
                        placeMark(a, b, sa);
                       next_player = 2;     
                        }else{
                         System.out.println("inside cur pl 2");
                           
                            outputWriter.println(current_player);
                        outputWriter.flush();
                        //Read Thank You
                        String text1 = inputReader.readLine();
                        outputWriter.println(Arrays.deepToString(board));
                        outputWriter.flush();
                         String text = inputReader.readLine();
                        String[] words = text.split(" ");
                        int a = Integer.parseInt(words[0]);
                        int b = Integer.parseInt(words[1]);
                        int sa = 0;
                     
                        if (count1%2  == 0) {
                            sa = 2;

                        } else {
                            sa = 1;

                        }
                        placeMark(a, b, sa);
                       next_player = 1;     
                        }
                            if (checkWin()) {
                            flg = false;            
                            }
                            if (isBoardFull()) {
                            flg = false;            
                            }
                        outputWriter.println(next_player);
                       outputWriter.flush();
                    }//flag2 ends


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean checkWin() {
            return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
        }

        private boolean checkRowsForWin() {
            for (int i = 0; i < 3; i++) {
                if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkColumnsForWin() {
            for (int i = 0; i < 3; i++) {
                if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                    return true;
                }
            }

            return false;

        }

        private boolean checkDiagonalsForWin() {

            return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));

        }

        private boolean checkRowCol(char c1, char c2, char c3) {

            return ((c1 != '-') && (c1 == c2) && (c2 == c3));

        }
 public boolean isBoardFull() {
        boolean isFull = true;
         
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                     isFull = false;
                }
            }
        }
         
        return isFull;
    }
        public boolean placeMark(int row, int col, int x) {
            char currentPlayerMark;
            if (x == 1) {
                currentPlayerMark = 'X';
          System.out.println("1   "+currentPlayerMark  );
            } else {
                currentPlayerMark = '0';

          System.out.println("else   "+currentPlayerMark  );
            }

            if ((row >= 0) && (row < 3)) {

                if ((col >= 0) && (col < 3)) {

                    if (board[row][col] == '-') {

                        board[row][col] = currentPlayerMark;


                        return true;

                    }

                }

            }

            return false;

        }
    }






