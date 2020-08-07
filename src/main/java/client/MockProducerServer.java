package client;

import com.google.gson.Gson;
import com.mehryar.streamtests.balance.Balance;
import com.mehryar.streamtests.customer.Customer;
import com.mehryar.streamtests.customerRelationship.CustomerRelationship;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class MockProducerServer {
    private static final Logger LOG = LoggerFactory.getLogger(MockProducerServer.class);

    private HttpServer httpServer;
    private MockDataProducer mockDataProducer;

    public MockProducerServer(int serverPort) {
        this.mockDataProducer = new MockDataProducer();
        try {
            LOG.info("Creating the Server at port: " + serverPort);
            this.httpServer = HttpServer.create(new InetSocketAddress(serverPort), 0);

        } catch (IOException io) {
            System.out.println("IO Exception from Server Creation" + io);
        }
        assert httpServer != null;
        setupServer(httpServer);
        LOG.info("Server Running at: " + httpServer.getAddress().getHostString() +
                "and PORT:" + httpServer.getAddress().getPort());
    }

    private void setupServer(HttpServer httpServer) {
        assert httpServer != null;

        httpServer.createContext("/produce/account-preference", (httpExchange -> {
            LOG.info("REQUEST RECEIVED: " + httpExchange.getRequestMethod() +
                    " " + httpExchange.getRequestURI().getPath());
            if ("POST".equals(httpExchange.getRequestMethod())) {
                LOG.info("POST REQUEST");
                InputStream inputStream = httpExchange.getRequestBody();
                Boolean success = handleProduce(inputStream, "account-preference");
                processRequest(httpExchange, success);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
        }));

        httpServer.createContext("/produce/customer-account", (httpExchange -> {
            LOG.info("REQUEST RECEIVED: " + httpExchange.getRequestMethod() +
                    " " + httpExchange.getRequestURI().getPath());
            if ("POST".equals(httpExchange.getRequestMethod())) {
                InputStream inputStream = httpExchange.getRequestBody();
                Boolean success = handleProduce(inputStream, "customer-account");
                processRequest(httpExchange, success);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
        }));

        httpServer.createContext("/produce/customer-profile", (httpExchange -> {
            LOG.info("REQUEST RECEIVED: " + httpExchange.getRequestMethod() +
                    " " + httpExchange.getRequestURI().getPath());
            if ("POST".equals(httpExchange.getRequestMethod())) {
                InputStream inputStream = httpExchange.getRequestBody();
                Boolean success = handleProduce(inputStream, "customer-profile");
                processRequest(httpExchange, success);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
        }));

        httpServer.createContext("/produce/balance", (httpExchange -> {
            LOG.info("REQUEST RECEIVED: " + httpExchange.getRequestMethod() +
                    " " + httpExchange.getRequestURI().getPath());
            if ("POST".equals(httpExchange.getRequestMethod())) {
                InputStream inputStream = httpExchange.getRequestBody();
                Boolean success = handleProduce(inputStream, "balance");
                processRequest(httpExchange, success);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
        }));

        httpServer.createContext("/produce/customer", (httpExchange -> {
            LOG.info("REQUEST RECEIVED: " + httpExchange.getRequestMethod() +
                    " " + httpExchange.getRequestURI().getPath());
            if ("POST".equals(httpExchange.getRequestMethod())) {
                InputStream inputStream = httpExchange.getRequestBody();
                Boolean success = handleProduce(inputStream, "customer");
                processRequest(httpExchange, success);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
        }));

        httpServer.createContext("/produce/customer-relationship", (httpExchange -> {
            LOG.info("REQUEST RECEIVED: " + httpExchange.getRequestMethod() +
                    " " + httpExchange.getRequestURI().getPath());
            if ("POST".equals(httpExchange.getRequestMethod())) {
                InputStream inputStream = httpExchange.getRequestBody();
                Boolean success = handleProduce(inputStream, "customer-relationship");
                processRequest(httpExchange, success);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
        }));

        httpServer.createContext("/mock", (httpExchange -> {
            LOG.info("REQUEST RECEIVED: " + httpExchange.getRequestMethod() +
                    " " + httpExchange.getRequestURI().getPath());
            if ("POST".equals(httpExchange.getRequestMethod())) {
                InputStream inputStream = httpExchange.getRequestBody();
                String inputStreamString = readInputStreamToString(inputStream);
                System.out.println(inputStreamString);
                processRequest(httpExchange, true);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
        }));
    }

    private void processRequest(HttpExchange httpExchange, Boolean success) throws IOException {
        if (success) {
            httpExchange.sendResponseHeaders(200, "OK".getBytes().length);
            OutputStream output = httpExchange.getResponseBody();
            output.write("OK".getBytes());
            output.flush();
            httpExchange.close();
        }
        httpExchange.sendResponseHeaders(400, -1);
    }

    public void start() {
        this.httpServer.setExecutor(null); // creates a default executor
        this.httpServer.start();
    }

    private boolean handleProduce(InputStream inputStream, String dataFlag) {
        Gson g = new Gson();
        switch (dataFlag) {
            case "balance":
                LOG.debug("Create Balance Object to Produce");
                Balance balance = g.fromJson(readInputStreamToString(inputStream), Balance.class);
                return mockDataProducer.produceBalance(balance);
            case "customer":
                LOG.debug("Create Balance Object to Produce");
                Customer customer = g.fromJson(readInputStreamToString(inputStream), Customer.class);
                System.out.println(customer);
                return mockDataProducer.produceCustomer(customer);
            case "customer-relationship":
                LOG.debug("Create CustomerRelationship Object to Produce");
                CustomerRelationship customerRelationship =
                        g.fromJson(readInputStreamToString(inputStream), CustomerRelationship.class);
                return mockDataProducer.produceCustomerRelationship(customerRelationship);
            default:
                return false;
        }

    }

    private String readInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuffer = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuffer.append(scanner.nextLine());
        }
        return stringBuffer.toString();
    }
}
