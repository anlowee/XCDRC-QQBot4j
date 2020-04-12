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
 * Get the top 10 master of Codeforce.
 * </p>
 *
 * @author https://github.com/anlowee
 * @version 1.0
 */
@Beans
public class CodeforceRankingListener {

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "codeforce")
    public void codeforceRankingListen(GroupMsg msg, MsgSender sender) {
        try {
            Document document = Jsoup.connect("https://codeforces.com/").execute().parse();
            Elements rankingList = document.getElementsByClass("rtable").get(0).child(0).getElementsByTag("tr");
            StringBuffer stringBuffer = new StringBuffer("**当前CF排名**").append("\r\n");
            boolean flag = false;
            for (Element rankItem : rankingList) {
                if (!flag) {
                    flag = true;
                    continue;
                }
                stringBuffer.append("Rank-")
                        .append(rankItem.child(0).text())
                        .append(": ")
                        .append(rankItem.child(1).text())
                        .append('(')
                        .append(rankItem.child(2).text())
                        .append(')')
                        .append("\r\n");
            }
            stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
            sender.SENDER.sendGroupMsg(msg.getGroupCode(), String.valueOf(stringBuffer));
        } catch (IOException e) {
            sender.SENDER.sendGroupMsg(msg.getGroupCode(), "500");
        }
    }

}
