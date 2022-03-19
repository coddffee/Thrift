using System;
using System.Net;
using Thrift.Protocol;
using Thrift.Transport;
using Thrift.Transport.Client;
using com.coddffee.service;
using com.coddffee.enums;

namespace ThriftClient {
    internal class Programs {
        public static void Main(String[] args) {
            try {
                TTransport transport = new TSocketTransport("localhost", 9090, null, 0);
                TProtocol protocol = new TBinaryProtocol(transport);
                // 创建客户端
                PersonService.Client client = new PersonService.Client(protocol);
                // 打开连接
                transport.OpenAsync();
                String message;
                // 调用服务
                client.newPerson(1, "Tony", Gender.MALE);
                Console.WriteLine("please input method : ");
                while (true) {
                    if ((message = Console.ReadLine()) != null) {
                        if (message.Contains("getId")) {
                            Console.WriteLine("id is : " + client.getId().Result);
                        } else if (message.Contains("getName")) {
                            Console.WriteLine("name is : " + client.getName().Result);
                        } else if (message.Contains("getGender")) {
                            Console.WriteLine("gender is : " + client.getGender().Result);
                        } else if (message.Contains("printPerson")) {
                            client.printPerson();
                        } else if (message.Contains("exit")) {
                            transport.Close();
                            Console.WriteLine("client closed.");
                            return;
                        }
                        Console.WriteLine("please input method : ");
                    }
                }
            } catch (Exception e) {
                Console.WriteLine(e.Message);
            }
        }
    }
}