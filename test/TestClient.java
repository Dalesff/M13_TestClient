import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Scanner;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import org.junit.runners.MethodSorters;

/**
 *
 * @author David Ales Fernandez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClient {
    
    private static String CLAU_CLIENT = "C:\\Users\\David\\client_test";
    private static String CLAU_CLIENT_PASSWORD = "333444";
    
    public TestClient() {
    }
    
    @Test
    public void testA_Unkcmd1() throws Exception {
    
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("Hola");
        String resposta = serverScanner.nextLine();
        assertEquals("unknown_command 1", resposta);
        
    }
    
    @Test
    public void testB_CreaUsuari() throws Exception {
    
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("crea_usuari:nomTest:cognom1Test:cognom2Test:emailtest@segvet.com:123:direccioTest:456345:Usuari");
        String resposta = serverScanner.nextLine();
        assertEquals("register_success", resposta);
        
    }
    
    @Test
    public void testC_LoginUsuari() throws Exception {
        
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("login:emailtest@segvet.com:123");
        String resposta = serverScanner.nextLine();
        String[] parts = resposta.split(":");
        assertEquals("login_success" + ":" + parts[1], resposta);
        
        writer.println("logout");
        serverScanner.nextLine();
        
    }

    @Test
    public void testD_ConsultaUsuari() throws Exception {
        
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("login:emailtest@segvet.com:123");
        String resposta = serverScanner.nextLine();
        writer.println("usuari:consulta_usuari");
        resposta = serverScanner.nextLine();
        assertEquals("nomTest:cognom1Test:cognom2Test:emailtest@segvet.com:direccioTest:456345", resposta);
        writer.println("logout");
        
    }
   
    @Test
    public void testE_EditaUsuari() throws Exception {
    
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("login:emailtest@segvet.com:123");
        String resposta = serverScanner.nextLine();
        writer.println("usuari:edita_usuari:nomEditTest:cognom1EditTest:cognom2EditTest:emailtest@segvet.com:123:direccioEditTest:456345:Usuari");
        resposta = serverScanner.nextLine();
        assertEquals("update_success", resposta);
        writer.println("logout");
        
    }
    
    @Test
    public void testF_CreaMascota() throws Exception {
        
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("login:emailtest@segvet.com:123");
        String resposta = serverScanner.nextLine();
        writer.println("mascota:crea_mascota:nomMascotaTest:8:especieMascotaTest:razaMascotaTest:f");
        resposta = serverScanner.nextLine();
        assertEquals("register_success", resposta);
        writer.println("logout");
    }
    
    @Test
    public void testG_ConsultaUsuariEditat() throws Exception {
        
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("login:emailtest@segvet.com:123");
        String resposta = serverScanner.nextLine();
        writer.println("usuari:consulta_usuari");
        resposta = serverScanner.nextLine();
        assertEquals("nomEditTest:cognom1EditTest:cognom2EditTest:emailtest@segvet.com:direccioEditTest:456345", resposta);
        writer.println("logout");
        
    }
    
    @Test
    public void testH_BorraUsuari() throws Exception {
        
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
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner serverScanner = new Scanner(socket.getInputStream());
        
        writer.println("login:emailtest@segvet.com:123");
        String resposta = serverScanner.nextLine();
        writer.println("usuari:borra_usuari");
        resposta = serverScanner.nextLine();
        assertEquals("delete_success",resposta);
        
    }
}
