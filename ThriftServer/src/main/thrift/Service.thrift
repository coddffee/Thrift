namespace java com.coddffee.service

include "Enums.thrift"
include "Entity.thrift"

// 注意，service定义中没有序号
service PersonService {
    i32 getId();
    string getName();
    Enums.Gender getGender();
    void printPerson(1:Entity.Person person);
}