package com.iamwxc.bot.greeting;

import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;

/**
 * Class description goes here.
 * <p>
 * If you see this sentence, nothing ambiguous.
 * </p>
 *
 * @author CC
 * @version 1.0
 */
@Beans
public class HelloListener {

    @Listen(MsgGetTypes.privateMsg)
    @Filter("hello.*")
    public void helloListen(PrivateMsg msg, MsgSender sender) {
        sender.SENDER.sendPrivateMsg(msg.getQQ(), msg.getMsg());
    }

}