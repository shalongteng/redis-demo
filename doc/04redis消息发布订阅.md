#redis消息订阅发布  
       简介：redis消息订阅发布讲解，基础使用，发布订阅类似于信息管道，用来进行系统之间消息解耦，
       类似于mq，rabbitmq、rocketmq、kafka、activemq,
       主要有消息发布者和消息订阅者。比如运用于：订单支付成功，会员系统加积分、钱包进行扣钱操作、发货系统（下发商品）
    
       1、PUBLISH channel message
           将信息message发送到指定的频道channel。返回收到消息的客户端数量 
    
       2、SUBSCRIBE channel
           订阅给指定频道的信息
    
       3、UNSUBSCRIBE
           取消订阅指定的频道，如果不指定，则取消订阅所有的频道。
    
       redis的消息订阅发布和mq对比？
           答：redis发布订阅功能比较薄弱但比较轻量级，mq消息持久化，数据可靠性比较差，无后台功能可msgId、msgKey进行查询消息    
