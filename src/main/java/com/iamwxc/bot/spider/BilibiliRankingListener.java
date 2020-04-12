package com.iamwxc.bot.spider;

import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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
public class BilibiliRankingListener {

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "bilibili")
    public void bilibiliRankingListen(GroupMsg msg, MsgSender sender) {
        try {
            Document document = Jsoup.connect("https://www.bilibili.com/ranking").execute().parse();
            Elements rankList = document.getElementsByClass("rank-item");
            StringBuffer stringBuffer = new StringBuffer("**当前全站排行榜TOP10**\r\n");
            for (Element rankItem : rankList.subList(0, 10)) {
                stringBuffer.append("Rank")
                        .append(rankItem.getElementsByClass("num").get(0).text())
                        .append("：")
                        .append(rankItem.getElementsByClass("title").get(0).text())
                        .append("\r\n");
            }
            sender.SENDER.sendGroupMsg(msg.getGroupCode(), String.valueOf(stringBuffer));
        } catch (IOException e) {
            sender.SENDER.sendGroupMsg(msg.getGroupCode(), "500");
        }
    }

}
