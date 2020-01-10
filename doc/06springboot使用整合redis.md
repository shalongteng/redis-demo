# 1、springboot项目搭建并编写HelloWorld 
          
    1、创建springboot项目

    2、修改配置文件，springboot需要引入：spring-boot-starter-web

    3、springboot两种启动方式：
      以web启动的项目（war）：比如：用tomcat容器启动项目，提供http接口 
        修改pom.xml
        排除内置的Tomcat容器
        url:https://blog.csdn.net/qq_33512843/article/details/80951741
        
      以jar启动的项目：比如：用java -jar启动 main方法启动，只比如用dubbo提供对外
      
      jsp不能够在jar中使用
#2、springboot整合redis
    Spring Boot 提供了对 Redis 集成的组件包：spring-boot-starter-data-redis，spring-boot-starter-data-redis
    依赖于spring-data-redis 和 lettuce 。Spring Boot 1.0 默认使用的是 Jedis 客户端，2.0 替换成 Lettuce，
    但如果你从 Spring Boot 1.5.X 切换过来，几乎感受不大差异，这是因为 spring-boot-starter-data-redis
    为我们隔离了其中的差异性。
    
    Lettuce 是一个可伸缩线程安全的 Redis 客户端，多个线程可以共享同一个 RedisConnection，
    它利用优秀 netty NIO 框架来高效地管理多个连接。
     
     Redis与SpringBoot整合有两种方式，
     第一种是使用Jedis，它是Redis官方推荐的面向Java的操作Redis的客户端，
     第二种是使用RedisTemplate，它是SpringDataRedis中对JedisApi的高度封装
     
     1、引入bean redisTemplate的使用，类似于：monogoTemplate、jdbcTemplate数据库连接工具

     2、配置步骤:
         1)引入pom依赖，
         <dependency>
              <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-data-redis</artifactId>
         </dependency>

         2）编写redisTemplate类，设置redisConnectFactory

         3）引入配置文件
     3、redisTemplate api讲解
          opsForValue  操作String，Key，Value，包含过期key，setBit位操作等
          opsForSet    操作set
          opsForHash   操作hash
          opsForZset   操作SortSet
          opsForList   操作list队列
#3、Redis的三个框架：Jedis,Redisson,Lettuce
 
     　　Jedis：是Redis的Java实现客户端，提供了比较全面的Redis命令的支持，
     
     　　Redisson：实现了分布式和可扩展的Java数据结构。
     
     　　Lettuce：高级Redis客户端，用于线程安全同步，异步和响应使用，支持集群，Sentinel，管道和编码器。
 
     优点：
     
     　　Jedis：比较全面的提供了Redis的操作特性
     
     　　Redisson：使用者对Redis的关注分离，提供分布式相关操作服务，分布式锁，分布式集合，可通过Redis支持延迟队列
     
     　　Lettuce：主要在一些分布式缓存框架上使用比较多
     
     可伸缩：
     
     Jedis 是直连模式，在多个线程间共享一个 Jedis 实例时是线程不安全的，
     如果想要在多线程环境下使用 Jedis，需要使用连接池，
     每个线程都去拿自己的 Jedis 实例，当连接数量增多时，物理连接成本就较高了。
     
     Redisson：基于Netty框架的事件驱动的通信层，其方法调用是异步的。Redisson的API是线程安全的，
     所以可以操作单个Redisson连接来完成各种操作
 
     Lettuce的连接是基于Netty的，连接实例可以在多个线程间共享，
     所以，一个多线程的应用可以使用同一个连接实例，而不用担心并发线程的数量。
     当然这个也是可伸缩的设计，一个连接实例不够的情况也可以按需增加连接实例。
 
  #4、mybatis缓存讲解
     MyBatis 默认是开启一级缓存的，即同一个 sqlSession 每次查询都会先去缓存中查询，没有数据的话，再去数据库获取数据。
     整合到 Spring 中后，一级缓存就会被关闭。
     
     二级缓存，它的范围是整个 mapper 的，以命名空间进行区分。默认配置就是启用二级缓存的。在mapper.xml文件里添加二级缓存的属性配置： 
      
     <cache />   加上这个标签，二级缓存就会启用，它的默认属性如下
            映射语句文件中的所有 select 语句将会被缓存。
            映射语句文件中的所有 insert,update 和 delete 语句会刷新缓存。
            缓存会使用 Least Recently Used(LRU,最近最少使用的)算法来收回。
            根据时间表(比如 no Flush Interval,没有刷新间隔), 缓存不会以任何时间顺序来刷新。
            缓存会存储列表集合或对象(无论查询方法返回什么)的 1024 个引用。
            缓存会被视为是 read/write(可读/可写)的缓存,意味着对象检索不是共享的,而且可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改。
            
     也可以自定义二级缓存的属性，例如：
        <cache
          eviction="FIFO"
          flushInterval="60000"
          size="512"
          readOnly="true"/>
          
          
#5、springboot cache的使用：可以结合redis、ehcache等缓存
         1、springboot cache的整合步骤：
           
         1）引入pom.xml依赖： 
            <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-cache</artifactId>
            </dependency>

         2）开启缓存注解： @EnableCaching
   
         3）在方法上面加入SpEL，使用注解                  
      
      2、springboot cache的使用：
       
         @CacheConfig(cacheNames="userInfoCache")  在同个redis里面必须唯一
   
         @Cacheable(查) ： 
                     来划分可缓存的方法 - 即，结果存储在缓存中的方法，以便在后续调用（具有相同的参数）时，返回缓存中的值而不必实际执行该方法
        
         @CachePut（修改、增加） ：
                     当需要更新缓存而不干扰方法执行时，可以使用@CachePut注释。也就是说，始终执行该方法并将其结果放入缓存中（根据@CachePut选项）
        
         @CacheEvict（删除） ：
                     对于从缓存中删除陈旧或未使用的数据非常有用，指示缓存范围内的驱逐是否需要执行而不仅仅是一个条目驱逐
                     
      3、springboot cache 存在什么问题，
            第一，生成key过于简单，容易冲突userCache::3
            第二，无法设置过期时间，默认过期时间为永久不过期
            第三，配置序列化方式，默认的是序列化JDK Serialazable
   
          1、springboot cache自定义项
   
                1)自定义KeyGenerator            
                
                2)自定义cacheManager，设置缓存过期时间
   
                3）自定义序列化方式，Jackson    
   
      
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
            

