package com.iamwxc.bot;

import com.forte.component.forcoolqhttpapi.CoolQHttpApp;
import com.forte.component.forcoolqhttpapi.CoolQHttpConfiguration;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;

/**
 * Class description goes here.
 * <p>
 * If you see this sentence, nothing ambiguous.
 * </p>
 *
 * @author CC
 * @version 1.0
 */
public class RunBot implements CoolQHttpApp {

    public void before(CoolQHttpConfiguration configuration) {
        configuration.setJavaPort(15514);
        configuration.setServerPath("/coolq");
    }

    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {

    }

}
