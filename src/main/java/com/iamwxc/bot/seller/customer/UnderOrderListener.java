package com.iamwxc.bot.seller.customer;

import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.beans.types.CQCodeTypes;
import com.forte.qqrobot.beans.types.KeywordMatchType;
import com.forte.qqrobot.listener.ListenContext;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.iamwxc.bot.seller.Good;
import com.iamwxc.bot.seller.GoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
@Component
public class UnderOrderListener {

    @Autowired
    private GoodDAO goodDAO;

    @Listen(MsgGetTypes.privateMsg)
    @Filter("buy!")
    public void queryBuyWhat(PrivateMsg msg, MsgSender sender, ListenContext context) {
        String key = "customer" + msg.getQQ();
        context.setGlobal(key, "waiting");
        List<Good> goodList = goodDAO.findAll();
        StringBuilder goodMsg = new StringBuilder();
        for (Good good : goodList) {
            goodMsg.append("ID-").append(good.getId().toString());
            goodMsg.append("，商品名-").append(good.getGoodName()).append("\r\n");
        }
        goodMsg.append("输入ID-X获取相应商品，X为ID号喵~");
        sender.SENDER.sendPrivateMsg(msg.getQQ(), goodMsg.toString());
    }

    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = "ID-", keywordMatchType = KeywordMatchType.STARTS_WITH)
    public void underOrderListener(PrivateMsg msg, MsgSender sender, ListenContext context, CQCodeUtil cqCodeUtil) {
        String key = "customer" + msg.getQQ();
        if (context.getGlobal(key) != null && context.getGlobal(key).equals("waiting")) {
            try {
                Long id = Long.valueOf(msg.getMsg().substring(3));
                Optional<Good> result = goodDAO.findById(id);
                if (result.isPresent()) {
                    Good good = result.get();
                    String sendMsg = "提取方法：\r\n";
                    sendMsg += good.getBaiduYunInfo() + "\r\n";
                    sendMsg += "如果客官能投喂一瓶快乐水就更开心了呢~\r\n";
                    sendMsg += "不投喂也没关系啦qaq，希望下次贩售姬能帮到您喵~";
                    sender.SENDER.sendPrivateMsg(msg.getQQ(), sendMsg);
                    String cqCode2String = cqCodeUtil.getCQCode(CQCodeTypes.image, "receive.jpg").toString();
                    sender.SENDER.sendPrivateMsg(msg.getQQ(), cqCode2String);
                    context.setGlobal(key, "$");
                } else {
                    sender.SENDER.sendPrivateMsg(msg.getQQ(), "未找到商品，核对一下ID喵~");
                }
            } catch (NumberFormatException e) {
                sender.SENDER.sendPrivateMsg(msg.getQQ(), "输入格式不对喵~，例如ID-2");
            }
        }
    }

}
