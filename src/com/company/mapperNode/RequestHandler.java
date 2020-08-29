package com.company.mapperNode;

import com.company.mapperNode.requester.REDUCERS_PORT_REQUEST;
import com.company.mapperNode.requester.Requester;

import java.net.Socket;
import java.util.function.Function;

enum RequestHandler {
    REQUEST_TASK("REQUEST_TASK", TASK_REQUEST::new),
    SHUFFLE_REQUEST("SHUFFLE_REQUEST", SHUFFLE_REQUEST::new),
    REQUEST_REDUCERS_PORT("REQUEST_REDUCERS_PORT", REDUCERS_PORT_REQUEST::new),
    o("A", null);
    // ...

    // the String that needs to match to execute this handler
    private String request;
    // creates the Requester if the request string matches
    private Function<Socket, Requester> createRequest;


    RequestHandler(String r, Function<Socket, Requester> f) {
        request = r;
        createRequest = f;
    }

    //  this handler method
    static void runOnRequestMatch(Socket socket, String request) {
        for (RequestHandler handler : values()) {
            Requester requestHandler;
            requestHandler = request.equals(handler.request) ? handler.createRequest.apply(socket) : null;
            if (requestHandler != null) {
                requestHandler.request(socket);
                break;
            }
        }
    }
}
