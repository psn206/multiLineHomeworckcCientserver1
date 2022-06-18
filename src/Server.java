import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

     /*
      Я использовал блокирующий ввод-вывод,
     так как нам нужно блокировать на время ожидания операций ввода-вывода,
      и у нас одна операция для пользователя. Параллельное выполнение тут не требуется,
      да и в быстродействии ничего не потеряем. Чем больше порядковой номер тем дольше вычисляем.
   * */

    public static int fibonacciValue(int num) {
        if (num <= 1) {
            return 0;
        } else if (num == 2) {
            return 1;
        } else {
            return fibonacciValue(num - 1) + fibonacciValue(num - 2);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(23333);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                while (true) {
                    out.println("Введите номер члена ряда Фибоначчи: ");
                    String number = in.readLine();

                    out.println("Значение из ряда: " + fibonacciValue(Integer.parseInt(number)));
                }
            }
        }
    }
}
