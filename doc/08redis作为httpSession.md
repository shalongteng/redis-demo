#redis实现分布式集群环境session共享
    
     1、cookie与session
        1）
          Cookie是什么？ 
            Cookie 是小量信息，由网络服务器发送出来存储在浏览器上，从而下次这位独一无二的访客又回到该网络服务器时，可从该浏览器读回此信息。
            我们可以看到在服务器写的cookie，会通过响应头Set-Cookie的方式写入到浏览器

        2）HTTP协议是无状态的，并非TCP一样进行三次握手，对于一个浏览器发出的多次请求，服务器无法区分是不是同一个浏览器。服务器为了区分不同浏览器会通过一个
          sessionid来区分请求，cookie相对用户是不可见的，用来保存这个sessionid是最好不过了
          
     2、Redis共享session的作用
       微服务自身可以保持无状态，应用实例数量的多少不会影响用户登录状态；
       可实现单点登录的踢出功能，如可以让上次异地登录的用户下线；
   
     2、redis实现分布式集群配置过程：

        1）<dependency>
               <groupId>org.springframework.session</groupId>
               <artifactId>spring-session-data-redis</artifactId>
          </dependency>

        2) @EnableRedisHttpSession 开启redis session缓存


        3)maxInactiveIntervalInSeconds指定缓存的时间
         key---> spring:session:sessions:expires:+‘sessionId’的过期时间  

     3、验证过程
        打开隐身模式清空cookie来验证缓存的时间    
