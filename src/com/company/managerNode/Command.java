package com.company.managerNode;

import java.io.IOException;
import java.net.Socket;

interface Command {
    void execute(Socket s) throws IOException;
}
