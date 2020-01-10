#1、apachebench 简介
    ab命令会创建多个并发访问线程，模拟多个访问者同时对某一URL地址进行访问。它的测试目标是基于URL的，它既可以用来测试apache的负载压力，
    也可以测试nginx、lighthttp、tomcat、IIS等其它Web服务器的压力。
    
    ab命令对发出负载的计算机要求很低，它既不会占用很高CPU，也不会占用很多内存。但却会给目标服务器造成巨大的负载，其原理类似CC攻击。
    自己测试使用也需要注意，否则一次上太多的负载。可能造成目标服务器资源耗完，严重时甚至导致死机。
    
#2、ab的安装
    1、
       1）yum install -y httpd-tools
       
    2、参数解释：   
        ab：http请求 
        abs：https请求 
        -n：测试几次 
        -c：模拟多少客户端 
        -T：内容类型。这个一般和-p 一起使用 
        -p：包含POST参数的文件      abs -n 1 -c 1 -p post.txt -T application/x-www-form-urlencoded "https://blog.csdn.net"
        -V检验是否安装成功【V是大写】
        
    3、返回参数解释
        Server Software:                             #测试服务器的名字
        Server Hostname:        localhost            #请求的URL主机名
        Server Port:            8080                 #请求端口   
        Document Path:          /getByCache?id=1     #请求路径
        Document Length:        33 bytes             #HTTP响应数据的正文长度
        
        Concurrency Level:      10                #并发用户数，这是我们设置的参数之一
        Time taken for tests:   0.907 seconds     #所有这些请求被处理完成所花费的总时间 单位秒
        Complete requests:      1000              #总请求数量，这是我们设置的参数之一
        Failed requests:        0                 #表示失败的请求数量
        
        Total transferred:      152000 bytes         #所有请求的响应数据长度总和。包括每个HTTP响应数据的头信息和正文数据的长度
        HTML transferred:       33000 bytes          #所有请求的响应数据中正文数据的总和，就是减去了Total transferred中HTTP响应数据中的头信息的长度
        Requests per second:    1031.58 [#/sec]      #吞吐量（qps）总请求数/处理完成这些请求数所花费的时间
        
        Time per request:       9.694 [ms]             #用户一个请求等待时间，计算公式：处理完成所有请求数所花费的时间/（总请求数/并发用户数）     
        Time per request:       0.969 [ms]             #服务器平均请求等待时间，计算公式：也可以这么统计：Time per request/Concurrency Level
        Transfer rate:          153.06 [Kbytes/sec] received。  #表示这些请求在单位时间内从服务器获取的数据长度，计算公式：Total trnasferred/ Time taken for tests
                                                                 这个统计很好的说明服务器的处理能力达到极限时，其出口宽带的需求量。 
     3、ab -n1000 -c10 http://localhost:8080/getByCache?id=1
        ab -n1000 -c10 http://localhost:8080/getUser?id=1
              
         统计：10个并发的情况下，差距并不太大
             redis qps：1031.58[#/sec] (mean)
             DB qps：   859.04 [#/sec] (mean)
             

             100个并发的情况下 1000个
             redis qps：2407.60 [#/sec] (mean)
             DB qps：2700.15 [#/sec] (mean) 
             
             
             100个并发的情况下，进行10000个请求
             redsi qps: 3403.39 [#/sec] (mean)
             DB qps: 3135.07 [#/sec] (mean)

             500个并发的情况下，进行10000个请求
             redis qps：2428.91 [#/sec] (mean)
             DB qps：1465 [#/sec] (mean)