package M13_ales_f_TestClient;

import java.io.*;
import java.net.*;
import java.security.KeyStore;
import java.util.*;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;


public class M13_ales_f_TestClient {
    
    // keytool -genkey -v -alias client -keyalg RSA -keystore ./client_test -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass 456456 -keypass 333444
    // keytool -export -alias client -keystore ./client_test -file client_key.cer
    // keytool -import -trustcacerts -alias server -file ./server_key.cer -keystore ./client_test
    private static String CLAU_CLIENT = "C:\\Users\\David\\client_test";
    private static String CLAU_CLIENT_PASSWORD = "333444";

    public static void main(String[] args) throws Exception {
        
        System.setProperty("javax.net.ssl.trustStore", CLAU_CLIENT);
        System.setProperty("javax.net.debug", "ssl,handshake");
        
        SSLContext context = SSLContext.getInstance("TLS");
        KeyStore ks = KeyStore.getInstance("jceks");

        ks.load(new FileInputStream(CLAU_CLIENT), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, CLAU_CLIENT_PASSWORD.toCharArray());
        context.init(kf.getKeyManagers(), null, null);

        SocketFactory factory = context.getSocketFactory();
        Socket socket = factory.createSocket("localhost", 44556);
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            writer.println(line);
            try {
                String response = serverScanner.nextLine();
                System.out.println(response);
            } catch (NoSuchElementException e) {
                System.out.println("Cerrado");
                break;
            }
        }
    }
}
