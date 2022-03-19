package com.coddffee;

import com.coddffee.handler.PersonServiceHandler;
import com.coddffee.service.PersonService;
import org.apache.thrift.transport.TSSLTransportFactory;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        PersonServiceHandler handler = new PersonServiceHandler();
        PersonService.Processor<?> processor = new PersonService.Processor<PersonServiceHandler>(handler);
        try {
            TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
