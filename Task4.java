import java.util.Scanner;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Task4 {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static double getExchangeRate(String initialCurrency, String finalCurrency) throws IOException {
        String urlString = API_URL + initialCurrency;
        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner reader = new Scanner(url.openStream());
        StringBuilder line = new StringBuilder();
        while (reader.hasNext()) {
            line.append(reader.nextLine());
        }
        reader.close();

        JSONObject json = new JSONObject(line.toString());
        return json.getJSONObject("rates").getDouble(finalCurrency);
    }

    public static double currencyConvert(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("enter the initial currency: ");
        String initialCurrency = scan.nextLine().toUpperCase();

        System.out.print("enter the final currency: ");
        String finalCurrency = scan.nextLine().toUpperCase();

        System.out.print("enter the amount to convert: ");
        double amount = scan.nextDouble();

        try {
            double exchangeRate = getExchangeRate(initialCurrency, finalCurrency);
            double amountConverted = currencyConvert(amount, exchangeRate);
            System.out.printf("converted amount: %.2f %s", amountConverted, finalCurrency);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            scan.close();
        }
    }
}
