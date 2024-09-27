import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task3 extends JFrame {
    private JTextField amountField;
    private JComboBox<String> baseCurrencyBox;
    private JComboBox<String> targetCurrencyBox;
    private JLabel resultLabel;
    private JButton convertButton;

    public Task3() {
        
        setTitle("Currency Converter Project");
        setSize(600, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(200, 30));

        JLabel baseCurrencyLabel = new JLabel("Base Currency:");
        baseCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        
        JLabel targetCurrencyLabel = new JLabel("Target Currency:");
        targetCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        
        convertButton = new JButton("Convert");
        convertButton.setBackground(Color.BLUE);
        convertButton.setForeground(Color.WHITE);
        
        resultLabel = new JLabel("Result will appear here");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(baseCurrencyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(baseCurrencyBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(targetCurrencyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;  
        add(targetCurrencyBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;  
        add(convertButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);
  
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }
    private void convertCurrency() {
        String baseCurrency = (String) baseCurrencyBox.getSelectedItem();
        String targetCurrency = (String) targetCurrencyBox.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());
        try {   
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/USD";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();      
            String responseBody = response.toString();
            double baseToUsdRate = extractRate(responseBody, baseCurrency);
            double targetToUsdRate = extractRate(responseBody, targetCurrency);
            double amountInUsd = amount / baseToUsdRate;
            double convertedAmount = amountInUsd * targetToUsdRate;
            resultLabel.setText(String.format("<html><font color='blue'>%.2f %s = %.2f %s</font></html>", amount, baseCurrency, convertedAmount, targetCurrency));
        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }
    private double extractRate(String responseBody, String currency) {
        int start = responseBody.indexOf(currency) + currency.length() + 2; 
        int end = responseBody.indexOf(',', start);
        if (end == -1) end = responseBody.indexOf('}', start);
        String rateString = responseBody.substring(start, end);
        return Double.parseDouble(rateString);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task3().setVisible(true);
            }
        });
    }
}