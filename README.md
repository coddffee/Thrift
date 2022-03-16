# `README`

### 概述

&emsp;&emsp;这是一个测试`Thrift`框架的项目，`Server`采用`Java`编程，`Client`采用`C#`编程。

### 开始

&emsp;&emsp;首先需要在计算机上安装`Thrift`的`IDL`编译器(参考官方网站[`thrift-download`](https://thrift.apache.org/download))，`Windows`端安装配置：

```xxx
1.从官网下载thrift-xxx.exe文件，将其放置在合适路径
2.将thrift-xxx.exe重命名为thrift.exe(方便命令调用)
3.将thrift.exe所在目录的路径(例如C:\DevKits\Thrift)添加到Path系统环境变量中
4.打开CMD输入thrift -version, 返回版本号则配置成功
```

&emsp;&emsp;`Server`端使用`Maven`构建项目，项目结构为：

```xxx
ThriftServer
|---pom.xml
|---src
    |---main
        |---java
            |---com.coddffee
                |---entity
                |---enums
                |---service
        |---thrift
            |---compile.cmd(用于批量编译)
            |---Enums.thrift
            |---Entity.thrift
            |---Service.thrift
    |---test
```

&emsp;&emsp;`Java`添加`Maven`依赖(可在`Maven`仓库[`maven-repository`](https://mvnrepository.com)中搜寻)：

```xml
<dependency>
    <groupId>org.apache.thrift</groupId>
    <artifactId>libthrift</artifactId>
    <version>0.9.3</version>
    <!-- 注意type应该指定为jar,否则无法找到org.apache.thrift包 -->
    <type>jar</type>
</dependency>
```

&emsp;&emsp;`Server`端编写`IDL`并编译：

`Enums.Thrift`

```xxx
/*
  此IDL专用于定义项目所使用的枚举体
*/

// 对应Java代码中的package com.coddffee.enums;
namespace java com.coddffee.enums

enum Gender {
    MALE = 0;
    FEMALE = 1;
}
```

`Entity.thrift`

```xxx
/*
  此IDL专用于定义项目所使用的实体类
*/

// 对应Java代码中的package com.coddffee.entity;
namespace java com.coddffee.entity

// 引用其它thrift文件
include "Enums.thrift"

struct Person {
    1:i32 required id;
    2:string required name;
    // 注意引用其他IDL中定义的结构体或枚举体时必须使用文件名限定名，即file.type
    3:Enums.Gender gender;
}
```

`Service.thrift`

```xxx
/*
  此IDL专用于定义项目所使用的服务类
*/

// 对应Java代码中的package com.coddffee.service;
namespace java com.coddffee.service

// 引用其它thrift文件
include "Enums.thrift"
include "Entity.thrift"

// 注意，service定义中没有序号
service PersonService {
    i32 getId();
    string getName();
    // 注意引用其他IDL中定义的结构体或枚举体时必须使用文件名限定名，即file.type
    Enums.Gender getGender();
    void printPerson(1:Entity.Person person);
}
```

&emsp;&emsp;编译`IDL`文件：

```xxx
/* 
   将file.thrift编译为java代码放到指定的path目录中，
   具体编译输出路径为<dir>/<namespace>/<file.java>,
   使用"-out <dir>"命令可明确指出输出存放位置，而不会生成"gen-java"文件夹，
   如果"-out"指定的路径与工程中包的根路径重合，则可完美的将生成的代码编译放置到正确位置，
   例如以上IDL的namespace为"com.coddffee.service", 包的根目录为src/main/java, 执行编译命令：
   "thrift -gen java -out ../java PersonService.thrift"
   生成的目录结构为：
   ./
   |---com.coddffee.service
       |---PersonService.java
   由于IDL中的namespace对应Java中的package, 对于以上例子，生成Java代码中的包声明为：
   "package com.coddffee.service;"
   如果编译生成的路径与namespace指定的不一致，需将生成代码手动转移到对应的包中
*/
thrift -gen java -out <dir> <file.thrift>
```

&emsp;&emsp;`Windows`平台下可使用`cmd`文件批量编译：

`compile.cmd`

```cmd
thrift -gen java -out ../java Enums.thrift
thrift -gen java -out ../java Entity.thrift
thrift -gen java -out ../java Service.thrift
```

