package com.xdclass.mobile.xdclassmobileredis.service;

import com.xdclass.mobile.xdclassmobileredis.domain.User;
import com.xdclass.mobile.xdclassmobileredis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
//@CacheConfig的作用：抽取@Cacheable、@CachePut、@CacheEvict的公共属性值
@CacheConfig(cacheNames="userInfoCache") // 本类内方法指定使用缓存时，默认的名称就是userInfoCache
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @CachePut的作用：即调用方法，又更新缓存数据 ，修改了数据库中的数据，同时又更新了缓存！
     * @Caching是@Cacheable、@CachePut、@CacheEvict注解的组合
     * @param u
     * @return
     */
    // 因为必须要有返回值，才能保存到数据库中，如果保存的对象的某些字段是需要数据库生成的，
    //那保存对象进数据库的时候，就没必要放到缓存了
    @CachePut(key="#p0.id")  //#p0表示第一个参数
    //必须要有返回值，否则没数据放到缓存中
    public User insertUser(User u){
        this.userMapper.insert(u);
        //u对象中可能只有只几个有效字段，其他字段值靠数据库生成，比如id
        return this.userMapper.find(u.getId());
    }


    @CachePut(key="#p0.id")
    public User updateUser(User u){
        this.userMapper.update(u);
        //可能只是更新某几个字段而已，所以查次数据库把数据全部拿出来全部
        return this.userMapper.find(u.getId());
    }

    /**
     *1、 @Cacheable 会先查询缓存，
     *    如果缓存中存在，则不执行service方法
     *    如果缓存不存在，将会查询出来放到缓存中去。
     /2、 spel：表达式
     *    方法参数的名字，可以直接#参数名，也可以使用#p0或#a0的形式，0代表参数的索引	#name、#a0、#p0
     *    方法执行后的返回值	#result
     *    当前被调用的方法名	#root.methodName
     *    当前被调用的方法	#root.method.name
     *    当前被调用的目标对象类	#root.targetClass
     *    当前被调用的方法的参数列表	#root.args[0]
     *    当前方法调用使用的缓存列表	#root.caches[0].name
     *3、@Cacheable的几个属性详解：
     *       cacheNames/value：指定缓存组件的名字
     *       key：缓存数据使用的key,可以用它来指定。默认使用方法参数的值，一般不需要指定
     *       keyGenerator：作用和key一样，二选一
     *
     *       cacheManager和cacheResolver作用相同：指定缓存管理器，二选一
     *       condition：指定符合条件才缓存，比如：condition="#id>3"
     *       也就是说传入的参数id>3才缓存数据
     *       unless：否定缓存，当unless为true时不缓存，可以获取方法结果进行判断
     *       sync：是否使用异步模式
     * @param id
     * @return
     */
    @Nullable
    @Cacheable(key="#p0")
    public User findById(String id){
        System.err.println("根据id=" + id +"获取用户对象，从数据库中获取");
        Assert.notNull(id,"id不用为空");
        return this.userMapper.find(id);
    }



    @CacheEvict(key="#p0")  //删除缓存名称为userInfoCache,key等于指定的id对应的缓存
    public void deleteById(String id){
        this.userMapper.delete(id);
    }

    //清空缓存名称为userInfoCache（看类名上的注解)下的所有缓存
    //如果数据失败了，缓存时不会清除的
    @CacheEvict(allEntries = true)
    public void deleteAll(){
        this.userMapper.deleteAll();
    }

    /**
     * 如果可以传入NULL值，则标记为@Nullable，如果不可以，则标注为@Nonnull。
     * @param id
     * @return
     */
    @Nullable
    @Cacheable(value = "UserInfoList", keyGenerator = "simpleKeyGenerator")
    public User findByIdTtl(String id){
        System.err.println("根据id=" + id +"获取用户对象，从数据库中获取");
        Assert.notNull(id,"id不用为空");
        return this.userMapper.find(id);
    }

}
