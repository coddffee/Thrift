namespace netstd com.coddffee.entity

include "Enums.thrift"

struct Person {
    1:i32 id;
    2:string name;
    3:Enums.Gender gender;
}