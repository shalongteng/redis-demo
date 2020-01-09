package com.xdclass.mobile.xdclassmobileredis.autowired;

import org.springframework.stereotype.Component;

/**
 * @Classname HashTest
 * @Date 2020/1/9 17:41
 * @Created by Gavin
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Component
public class HashTest implements Test1{

    @Override
    public String hello() {
        return "hash";
    }
}
