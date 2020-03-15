package com.iamwxc;

import com.forte.component.forcoolqhttpapi.CoolQHttpApp;
import com.forte.component.forcoolqhttpapi.CoolQHttpConfiguration;
import com.forte.qqrobot.depend.DependGetter;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;

/**
 * 这个是simple-robot框架的启动器类
 * 使用CQ HTTP API组件进行示例, 实现{@link CoolQHttpApp}接口并进行相应的配置
 * 由于需要整合Spring的依赖管理系统，所以需要Spring的依赖工厂来自定义依赖获取方式
 * 所以自定义依赖获取由构造方法进行提供
 *
 * @author ForteScarlet <[email]ForteScarlet@163.com>
 * @since JDK1.8
 **/
public class QQRunApplication implements CoolQHttpApp {

    /**
     * 自定义的依赖获取器
     */
    private DependGetter dependGetter;

    /**
     * 构造，需要提供一个自定义的依赖获取器
     */
    public QQRunApplication(DependGetter dependGetter) {
        this.dependGetter = dependGetter;
    }


    @Override
    public void before(CoolQHttpConfiguration configuration) {
        //启动之前，进行配置
        configuration.setJavaPort(15514)
                .setServerPath("/coolq")
                //配置自定义的依赖获取器
                .setDependGetter(dependGetter);

        /*
            关于配置的详细信息请查看普通的demo项目或者文档
         */

    }

    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {
        //启动成功之后，步进行任何操作
    }
}
