package com.coddffee;

import com.coddffee.handler.PersonServiceHandler;
import com.coddffee.service.PersonService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        PersonServiceHandler handler = new PersonServiceHandler();
        PersonService.Processor<?> processor = new PersonService.Processor<>(handler);
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("server start.");
            server.serve();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
