package com.iamwxc.bot.seller.admin;

import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.anno.depend.Depend;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.beans.types.KeywordMatchType;
import com.forte.qqrobot.listener.ListenContext;
import com.forte.qqrobot.sender.MsgSender;
import com.iamwxc.bot.seller.Good;
import com.iamwxc.bot.seller.GoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ManageGoodListener {

    @Autowired
    private GoodDAO goodDAO;



    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = "链接：https://pan.baidu.com/", code = "1290959722", keywordMatchType = KeywordMatchType.STARTS_WITH)
    public void newGood(PrivateMsg msg, MsgSender sender, ListenContext context) {
        context.setGlobal("bdyurl", msg.getMsg());
        sender.SENDER.sendPrivateMsg(msg.getQQ(), "输入 名字：xxx，设置文件名");
    }

    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = "名字：", code = "1290959722", keywordMatchType = KeywordMatchType.STARTS_WITH)
    public void setGoodName(PrivateMsg msg, MsgSender sender,ListenContext context) {
        String bdyurl = (String) context.getGlobal("bdyurl");
        String name = msg.getMsg().substring(3);
        if (bdyurl != null && bdyurl.startsWith("链接：https://pan.baidu.com/")) {
            Good good = new Good();
            good.setBaiduYunInfo(bdyurl);
            good.setGoodName(name);
            goodDAO.save(good); // ????
            context.setGlobal("bdyurl", "$");
            sender.SENDER.sendPrivateMsg(msg.getQQ(), "新增成功，文件名：" + name);
        } else
            sender.SENDER.sendPrivateMsg(msg.getQQ(), "新增失败");
    }

}