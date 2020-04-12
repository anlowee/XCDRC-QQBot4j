package com.iamwxc.bot.spider;

import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
public class Bitcoin2USDListener {

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "bitcoin")
    public void bitcoin2UsdListen(GroupMsg msg, MsgSender sender) {
        StringBuffer stringBuffer = new StringBuffer("**比特币/美元当前**").append("\r\n");
        try {
            Document document = Jsoup.connect("https://www.tradingview.com/markets/cryptocurrencies/prices-bitcoin/").execute().parse();
            String last = document.getElementsByClass("tv-data-table__tbody").get(0).child(0).child(1).text();
            String chgPercent = document.getElementsByClass("tv-data-table__tbody").get(0).child(0).child(2).text();
            String chg = document.getElementsByClass("tv-data-table__tbody").get(0).child(0).child(3).text();
            String high = document.getElementsByClass("tv-data-table__tbody").get(0).child(0).child(4).text();
            String low = document.getElementsByClass("tv-data-table__tbody").get(0).child(0).child(5).text();
            String vol = document.getElementsByClass("tv-data-table__tbody").get(0).child(0).child(6).text();
            stringBuffer.append("当前：").append(last).append("\r\n")
                    .append("涨幅（百分比）：").append(chgPercent).append("\r\n")
                    .append("涨幅（数值）：").append(chg).append("\r\n")
                    .append("历史最高：").append(high).append("\r\n")
                    .append("历史最低：").append(low).append("\r\n")
                    .append("VOL：").append(vol);
            sender.SENDER.sendGroupMsg(msg.getGroupCode(), String.valueOf(stringBuffer));
        } catch (IOException e) {
            sender.SENDER.sendGroupMsg(msg.getGroupCode(), "500");
        }
    }

}
