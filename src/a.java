//
//public class Server {
//    ServerSocket serverSocket;
//
//    public void startServer() throws IOException {
//        serverSocket= new ServerSocket(2000);
//        while (true){
//            Socket s= serverSocket.accept();
//            DataInputStream clientStream= new DataInputStream(s.getInputStream());
//            String requestName=clientStream.readUTF();
//            switch (requestName){
//                case "ClientRequestUploadFileService": new ClientRequestUploadFileServiceHandler(s).start();break;
//                case "clientRequestCalculatorService": new clientRequestCalculatorServiceHandler(s).start();break;
//                case "clientRequestDownloadFileService": new clientRequestDownloadFileServiceHandler(s).start();break;
//            }
//        }
//    }
//}
//interface Command {
//    void execute();
//}
//enum CommandEnum implements Command {
//    A {
//        @Override
//        public void execute() {
//            System.out.println("Running command A");
//        }
//    },
//    B {
//        @Override
//        public void execute() {
//            System.out.println("Running command B");
//        }
//    };
//}