#1、mybatis-generator 概述
    MyBatis官方提供了逆向工程 mybatis-generator，可针对数据库表自动生成MyBatis执行所需要的代码（如Mapper.java、Mapper.xml、POJO）
    mybatis-generator 有三种用法：命令行、eclipse插件、maven插件。而maven插件的方式比较通用
    
#2、pom.xml中配置plugin
    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
         <!--插件的配置--> 
          <configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
            <!--文件存在是否重写-->
            <overwrite>true</overwrite>
             <!--是否输出信息-->
            <verbose>true</verbose>
        </configuration>
        <dependencies>
            <!--jdbc用来连接数据库-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.11</version>
            </dependency>
        </dependencies>
    </plugin>
    注意：
        1、如果mysql 连接版本是8.0.11 那么 mysql驱动应该使用这个com.mysql.cj.jdbc.Driver
        2、将mysql时区设置为东八  set global time_zone='+8:00';
                         
#3、配置generatorConfig.xml
    javaModelGenerator: pojo
    sqlMapGenerator：mapper.xml
    javaClientGenerator：mapper接口