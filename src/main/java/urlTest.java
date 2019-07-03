import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class urlTest {
    public static void main(String[] args) {
        try {
            URL url=new URL("http://api.nbp.pl/api/exchangerates/tables/A");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader inputStreamReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println(connection.getResponseCode());
            System.out.println(inputStreamReader.readLine());



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
