# Redis Centos7搭建以及Redis三种启动方式

1、 手把手mac环境下centos7安装过程（windows同理）
        安装vmware虚拟机
        centos官网：http://isoredirect.centos.org/centos/8/isos/x86_64/CentOS-8-x86_64-1905-dvd1.iso
        选择清华镜像：http://mirrors.tuna.tsinghua.edu.cn/centos/7.5.1804/isos/x86_64/CentOS-7-x86_64-DVD-1804.iso

2、 手把手centos7环境下redis4.0安装

     1、解决could not retrieve mirrorlist，
           1.sudo vim /etc/sysconfig/network-scripts/ifcfg-ens33 

　　        2.将ONBOOT改为yes，wq!保存退出

            3.重新启动网络  $ service network restart

     2、 安装wget yum install wget

     3、 下载redis安装包
         wget http://download.redis.io/releases/redis-4.0.6.tar.gz

     4、 解压压缩包
         tar -zxvf redis-4.0.6.tar.gz

     5、yum install gcc

     6、跳转到redis解压目录下 cd redis-4.0.6

     7、编译安装 make MALLOC=libc　　

     8、测试是否安装成功

        cd src  ./redis-server

3、 redis三种启动方式以及其中的使用区别
    1、直接启动

    2、通过指定配置文件启动

    3.使用redis启动脚本设置开机自启动
       linux配置开启自启动  /etc/init.d

       1、启动脚本 redis_init_script 位于Redis的 /utils/ 目录下

       2、mkdir /etc/redis
          cp redis.conf /etc/redis/6379.conf

       3、将启动脚本复制到/etc/init.d目录下，本例将启动脚本命名为redisd（通常都以d结尾表示是后台自启动服务）。

         cp redis_init_script /etc/init.d/redisd

       4、设置为开机自启动，直接配置开启自启动 chkconfig redisd on 发现错误： service redisd does not support chkconfig

            解决办法，在启动脚本开头添加如下注释来修改运行级别：


              #!/bin/sh
              # chkconfig:   2345 90 10

       5、设置为开机自启动服务器
           chkconfig redisd on
           #打开服务
           service redisd start
           #关闭服务
           service redisd stop

4、 ssh的安装过程

    1、 检查CentOS7是否安装了openssh-server，在终端中输入  yum list installed | grep openssh-server
              此处显示已经安装了  openssh-server，如果又没任何输出显示表示没有安装，通过输入yum install openssh-server安装

    2、 找到了  /etc/ssh/  目录下的sshd服务配置文件 sshd_config，用Vim编辑器打开

        将文件中，关于监听端口、监听地址前的 # 号去除

          Port 22
          ListenAddress 0.0.0.0
          ListerAddress ::
          PermiRootLogin yes
          PasswordAuthentication yes

    3、开启sshd服务，输入 sudo service sshd start
        检查  sshd  服务是否已经开启，输入ps -e | grep sshd

    4、使用ip addr查看地址

    5、为了免去每次开启 CentOS 时，都要手动开启  sshd 服务，
         将 sshd 服务添加至自启动列表中，输入systemctl enable sshd.service

    6、通过本机工具进行连接





