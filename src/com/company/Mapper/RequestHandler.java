package com.company.Mapper;

import java.net.Socket;
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
