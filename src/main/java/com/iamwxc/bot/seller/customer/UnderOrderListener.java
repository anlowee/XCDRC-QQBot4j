package com.iamwxc.bot.seller.customer;

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
public class UnderOrderListener {

    @Listen(MsgGetTypes.privateMsg)
    @Filter("buy.*")
    public void underOrderListen(PrivateMsg msg, MsgSender sender) {
        String goodsName = msg.getMsg();
        goodsName = goodsName.split("buy")[1];
        if (goodsName == null || goodsName.equals("")) {
            sender.SENDER.sendPrivateMsg(msg.getQQ(), "请输入想要的东西名称喵~");
            return;
        }
        //TODO
        // find good
    }

}
