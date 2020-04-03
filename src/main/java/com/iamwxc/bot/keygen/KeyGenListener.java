package com.iamwxc.bot.keygen;

import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;
import org.junit.Test;

/**
 * Class description goes here.
 * <p>
 * If you see this sentence, nothing ambiguous.
 * </p>
 *
 * @author https://github.com/anlowee
 * @version 1.0
 */
@Beans
public class KeyGenListener {

    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = "key", code = "1290959722")
    public void keyGenListen(PrivateMsg msg, MsgSender sender) {
        long time = System.currentTimeMillis();
        StringBuilder cypher = new StringBuilder();
        char key = (char) (time % 17 + 'a');
        cypher.append(key);
        while (time > 0) {
            int cur = (int) (time % 10);
            time /= 10;
            cypher.append((char) (key + cur));
        }
        cypher.append("\r\n友情提示：此key五分钟内有效");
        sender.SENDER.sendPrivateMsg(msg.getQQ(), String.valueOf(cypher));
    }

}
