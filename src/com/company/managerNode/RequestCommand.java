package com.company.managerNode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    },

    REQUEST_REDUCERS_PORT {
        @Override
        public void execute(Socket s){
            new REQUEST_REDUCERS_PORT(s).start(s);
        }
    },

}

class REQUEST_REDUCERS_PORT {
    public static List<Integer> REDUCERS_PORT = new ArrayList();

    static {
        REDUCERS_PORT.add(4000);//this should be read from file
    }

    DataInputStream in = null;
    DataOutputStream out = null;
    Socket socket;

    public REQUEST_REDUCERS_PORT(Socket s) {
        socket = s;
        try {
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(Socket s) {
        try {
            for (int i = 0; i < REDUCERS_PORT.size(); i++) {
                out.writeInt(REDUCERS_PORT.get(i));
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
