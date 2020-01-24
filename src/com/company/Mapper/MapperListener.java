package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

enum RequestHandler {
    REQUEST_TASK("REQUEST_TASK", REQUEST_TASK::new),
    // CALCULATE("clientRequestCalculator", ClientRequestCalculatorHandler::new),
    o("A", null);
    // ...

    // the String that needs to match to execute this handler
    private String request;
    // creates the runnable if the request string matches
    private Function<Socket, Requester> createRunnable;


    RequestHandler(String r, Function<Socket, Requester> f) {
        request = r;
        createRunnable = f;
    }

    // and this is your handler method
    static void runOnRequestMatch(Socket socket, String request) {
        for (RequestHandler handler : values()) {
            Requester requestHandler;
            requestHandler = request.equals(handler.request) ? handler.createRunnable.apply(socket) : null;
            if (requestHandler != null) {
                requestHandler.request(socket);
                break;
            }
        }
    }
}

interface Requester {
    void request(Socket s);
}

public class MapperListener {
    private final Socket s;
    private String requestName;

    public MapperListener(int portNumber, String requestName) throws IOException {
        s = new Socket(InetAddress.getLocalHost(), portNumber);
        this.requestName = requestName;
    }

    public void run() {
        RequestHandler.runOnRequestMatch(s, requestName);
    }
}

class REQUEST_TASK implements Requester {
    private final String RequestType = "REQUEST_TASK";
    public List<SplitBlockInfo> ml = new ArrayList<>();
    int numberOfInputSplit = 8365;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    REQUEST_TASK(Socket s) {
        try {
            dataInputStream = new DataInputStream(s.getInputStream());
            dataOutputStream = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void request(Socket s) {

        try {
            dataOutputStream.writeUTF(RequestType);
            dataOutputStream.writeInt(numberOfInputSplit);
            while (numberOfInputSplit > 0) {
                SplitBlockInfo sp = new SplitBlockInfo();
                sp.read(dataInputStream);
                ml.add(sp);
                numberOfInputSplit--;
                System.out.println(numberOfInputSplit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientRequestCalculatorHandler implements Runnable {

    public ClientRequestCalculatorHandler(Socket socket) {
    }

    @Override
    public void run() {

    }
}

class ClientRequestUploadFileHandler implements Runnable {
    int x = 2;

    public ClientRequestUploadFileHandler(Socket socket) {
    }

    @Override
    public void run() {

    }
}