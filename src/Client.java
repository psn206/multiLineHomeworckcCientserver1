import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 23333);
        boolean stop = false;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            while (!stop) {
                String readServer = in.readLine();
                System.out.println(readServer);
                String readUser = scanner.nextLine();
                if (readUser.equals("end")) {
                    stop = true;
                } else {
                    out.println(readUser);
                    readServer = in.readLine();
                    System.out.println(readServer);
                }
            }
        }
    }
}
