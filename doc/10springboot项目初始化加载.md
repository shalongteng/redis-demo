#springboot项目初始化加载讲解
      场景：将一千万用户白名单load缓存，用户请求的时候判断该用户是否是缓存里面的用户
    
      1、springboot实现初始化加载配置（实现缓存预热）
    
          1、采用实现springboot ApplicationRunner
             该方法仅在SpringApplication.run(…)完成之前调用
    
          2、采用实现InitializingBean
             InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet()方法。
             在spring初始化bean的时候，如果bean实现了InitializingBean接口，
             在对象的所有属性被初始化后之后才会调用afterPropertiesSet()方法
             
          3、采用实现CommandLineRunner接口
             实现run()方法，
             如果存在多个加载的数据，我们也可以使用@Order注解来排序。 
               
      2、初始化同步redis数据    
    
      3、初始化完成再放入请求