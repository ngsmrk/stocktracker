package connector;

import domain.StockQuote;
import exceptions.StockQuoteRetrievalException;
import parser.StockQuoteParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * HTTP based implementation of StockQuoteRetriever.
 *
 * @author Initial: amark
 * @version 1.0
 */
public class HttpStockQuoteRetriever implements StockQuoteRetriever {

    /**
     * The base URL.
     */
    private String baseURL;

    /**
     * Instance of parser implementation to use.
     */
    private StockQuoteParser parser;

    private String proxyProtocol;

    private int proxyPort;

    private String proxyHost;

    private String proxyUser;

    private String proxyPassword;

    public HttpStockQuoteRetriever() {
        super();
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public void setParser(StockQuoteParser parser) {
        this.parser = parser;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setProxyProtocol(String proxyProtocol) {
        this.proxyProtocol = proxyProtocol;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public StockQuote getStockQuote(String symbol) throws StockQuoteRetrievalException {

        String correctURL = baseURL.replace("<SYMBOL>", symbol);

        BufferedReader in = null;
        try {
            URL url = new URL(correctURL);
            URLConnection connection = openConnection(url);

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String decodedString = in.readLine();
            return parser.parse(decodedString);

        } catch (IOException ioe) {

            throw new StockQuoteRetrievalException("Unable to retrieve quote for symbol: " + symbol, ioe);

        } finally {

            try {

                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {

                // TODO log as warning
                e.printStackTrace();
            }
        }
    }


    /**
     * Connect to URL.
     *
     * @param url
     * @return URLConnection
     * @throws IOException
     */
    private URLConnection openConnection(URL url) throws IOException {

        URLConnection connection;
        Proxy proxy = setProxySettingsIfSpecified();
        if (proxy != null) {
            connection = url.openConnection(proxy);
        } else {
            connection = url.openConnection();
        }

        return connection;
    }

    /**
     * Set up proxy settings for use when making http connections.
     * <br/> Will return a java.net.Proxy instance if proxyProtocol, proxyHost and proxyPort have been specified.
     *
     * @return Proxy instance or null.
     */
    private Proxy setProxySettingsIfSpecified() {

        Proxy proxy = null;

        if (isNotBlank(proxyProtocol)) {

            SocketAddress socketAddress = new InetSocketAddress(proxyHost, proxyPort);
            proxy = new Proxy(Proxy.Type.HTTP, socketAddress);


            // if user and pwd are specified need to set up Basic Authentication
            if (isNotBlank(proxyUser)) {

                BasicAuthenticator authenticator = new BasicAuthenticator(proxyUser, proxyPassword);
                Authenticator.setDefault(authenticator);
            }
        }

        return proxy;
    }

    /**
     * Check input is not null or blank.
     * TODO will not handle other whitespace characters such as tab.
     *
     * @param input
     * @return true if the string is not null and is not entirely made up of spaces.
     */
    private boolean isNotBlank(String input) {
        return input != null && input.trim().length() > 0;
    }

    /**
     * Inner class for handling proxy authentication based on a username and password.
     */
    private class BasicAuthenticator extends Authenticator {
        private String username;
        private String password;

        private BasicAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password.toCharArray());
        }
    }
}
