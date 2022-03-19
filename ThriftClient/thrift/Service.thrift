/*
  此IDL专用于定义项目所使用的服务类
*/

namespace netstd com.coddffee.service

// 引用其它thrift文件，注意必须使用""
include "Enums.thrift"
include "Entity.thrift"
include "Exception.thrift"

// service定义中没有序号
service PersonService {
    // 引用其他IDL中定义的类型时必须使用Thrift文件名限定名，即file.type
    Entity.Person newPerson(1:i32 id,2:string name,3:Enums.Gender gender);
    i32 getId();
    string getName();
    Enums.Gender getGender();
    void printPerson() throws (1:Exception.PersonException e);
}