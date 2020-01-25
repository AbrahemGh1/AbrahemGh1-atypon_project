package com.company.managerNode;

import java.io.IOException;
import java.net.Socket;

enum RequestCommand implements Command {

    REQUEST_TASK {
        @Override
        public void execute(Socket s) throws IOException {
            new uploadTaskHandler(s).start();
        }
    },

    REQUEST_CLOSE {
        @Override
        public void execute(Socket s) throws IOException {
            s.close();
        }
    }
}
