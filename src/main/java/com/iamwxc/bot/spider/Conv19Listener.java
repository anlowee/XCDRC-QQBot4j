package com.iamwxc.bot.spider;

import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;
import lombok.Data;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class Conv19Listener {

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "conv19")
    public void conv19Listen(GroupMsg msg, MsgSender sender) {
        try {
            String data = Jsoup
                    .connect("https://dashboards-dev.sprinklr.com/data/9043/global-covid19-who-gis.json")
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute()
                    .body();
            int startPos = data.indexOf("rows");
            int endPos = data.indexOf("totals");
            String[] dataBlock = data.substring(startPos + 7, endPos - 3).split("\\[");
            List<DailyDash> dailyDashes = new ArrayList<>();
            String[] preSubDataBlock = dataBlock[1].split(","), subDataBlock;
            String creatTime = data.substring(data.indexOf("created") + 10, data.indexOf("createdTime") - 3);
            StringBuffer stringBuffer = new StringBuffer("最后更新：").append(creatTime).append('\n');
            Long easternMediterraneanDeath = 0L;
            Long easternMediterraneanConfirm = 0L;
            Long europeDeath = 0L;
            Long europeConfirm = 0L;
            Long africaDeath = 0L;
            Long africaConfirm = 0L;
            Long americasDeath = 0L;
            Long americasConfirm = 0L;
            Long southEastAsiaDeath = 0L;
            Long southEastAsiaConfirm = 0L;
            Long westernPacificDeath = 0L;
            Long westernPacificConfirm = 0L;
            Long chinaDeathIncrement = 0L;
            Long chinaTotalDeath = 0L;
            Long chinaConfirmIncrement = 0L;
            Long chinaTotalConfirm = 0L;
            Long usaDeathIncrement = 0L;
            Long usaTotalDeath = 0L;
            Long usaConfirmIncrement = 0L;
            Long usaTotalConfirm = 0L;
            for (int i = 2; i < dataBlock.length; i++) {
                String string = dataBlock[i];
                subDataBlock = string.split(",");
                if (!subDataBlock[1].substring(1, subDataBlock[1].length() - 1).equals(preSubDataBlock[1].substring(1, preSubDataBlock[1].length() - 1))) {
                    DailyDash dailyDash = new DailyDash(preSubDataBlock);
                    dailyDashes.add(dailyDash);
                    switch (dailyDash.getRegion()) {
                        case "EMRO":
                            easternMediterraneanDeath += dailyDash.getTotalDeath();
                            easternMediterraneanConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "AFRO":
                            africaDeath += dailyDash.getTotalDeath();
                            africaConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "AMRO":
                            americasDeath += dailyDash.getTotalDeath();
                            americasConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "EURO":
                            europeDeath += dailyDash.getTotalDeath();
                            europeConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "SEARO":
                            southEastAsiaDeath += dailyDash.getTotalDeath();
                            southEastAsiaConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "WPRO":
                            westernPacificDeath += dailyDash.getTotalDeath();
                            westernPacificConfirm += dailyDash.getTotalConfirm();
                            break;
                    }
                    switch (dailyDash.getNation()) {
                        case "CN":
                            chinaDeathIncrement = dailyDash.getDeathIncrement();
                            chinaConfirmIncrement = dailyDash.getConfirmIncrement();
                            chinaTotalConfirm = dailyDash.getTotalConfirm();
                            chinaTotalDeath = dailyDash.getTotalDeath();
                            break;
                        case "US":
                            usaDeathIncrement = dailyDash.getDeathIncrement();
                            usaConfirmIncrement = dailyDash.getConfirmIncrement();
                            usaTotalConfirm = dailyDash.getTotalConfirm();
                            usaTotalDeath = dailyDash.getTotalDeath();
                            break;
                    }
                }
                else if (i == dataBlock.length - 1) {
                    DailyDash dailyDash = new DailyDash(subDataBlock);
                    dailyDashes.add(dailyDash);
                    switch (dailyDash.getRegion()) {
                        case "EMRO":
                            easternMediterraneanDeath += dailyDash.getTotalDeath();
                            easternMediterraneanConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "AFRO":
                            africaDeath += dailyDash.getTotalDeath();
                            africaConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "AMRO":
                            americasDeath += dailyDash.getTotalDeath();
                            americasConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "EURO":
                            europeDeath += dailyDash.getTotalDeath();
                            europeConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "SEARO":
                            southEastAsiaDeath += dailyDash.getTotalDeath();
                            southEastAsiaConfirm += dailyDash.getTotalConfirm();
                            break;
                        case "WPRO":
                            westernPacificDeath += dailyDash.getTotalDeath();
                            westernPacificConfirm += dailyDash.getTotalConfirm();
                            break;
                    }
                    switch (dailyDash.getNation()) {
                        case "CN":
                            chinaDeathIncrement = dailyDash.getDeathIncrement();
                            chinaConfirmIncrement = dailyDash.getConfirmIncrement();
                            chinaTotalConfirm = dailyDash.getTotalConfirm();
                            chinaTotalDeath = dailyDash.getTotalDeath();
                            break;
                        case "US":
                            usaDeathIncrement = dailyDash.getDeathIncrement();
                            usaConfirmIncrement = dailyDash.getConfirmIncrement();
                            usaTotalConfirm = dailyDash.getTotalConfirm();
                            usaTotalDeath = dailyDash.getTotalDeath();
                            break;
                    }
                }
                preSubDataBlock = subDataBlock;
            }
            stringBuffer.append("东地中海区域：").append("\r\n")
                    .append("累计死亡-").append(easternMediterraneanDeath).append("\r\n")
                    .append("累计确证-").append(easternMediterraneanConfirm)
                    .append("\r\n")
                    .append("欧洲区域：").append("\r\n")
                    .append("累计死亡-").append(europeDeath).append("\r\n")
                    .append("累计确证-").append(europeConfirm).append("\r\n")
                    .append("美洲区域：").append("\r\n")
                    .append("累计死亡-").append(americasDeath).append("\r\n")
                    .append("累计确诊-").append(americasConfirm)
                    .append("\r\n")
                    .append("东南亚区域：").append("\r\n")
                    .append("累计死亡-").append(southEastAsiaDeath).append("\r\n")
                    .append("累计确诊-").append(southEastAsiaConfirm)
                    .append("\r\n")
                    .append("西太平洋区域：").append("\r\n")
                    .append("累计死亡-").append(westernPacificDeath).append("\r\n")
                    .append("累计确证-").append(westernPacificConfirm)
                    .append("\r\n")
                    .append("非洲区域：").append("\r\n")
                    .append("累计死亡-").append(africaDeath).append("\r\n")
                    .append("累计确诊-").append(africaConfirm).append("\r\n")
                    .append("全球累计：").append("\r\n")
                    .append("累计死亡-").append(easternMediterraneanDeath + europeDeath + africaDeath + southEastAsiaDeath + westernPacificDeath + americasDeath).append("\r\n")
                    .append("累计确诊-").append(easternMediterraneanConfirm + europeConfirm + africaConfirm + southEastAsiaConfirm + westernPacificConfirm + americasConfirm).append("\r\n")
                    .append("\uD83C\uDDE8\uD83C\uDDF3情况：").append("\r\n")
                    .append("新增死亡-").append(chinaDeathIncrement).append("\r\n")
                    .append("新增确诊-").append(chinaConfirmIncrement).append("\r\n")
                    .append("累计死亡-").append(chinaTotalDeath).append("\r\n")
                    .append("累计确证-").append(chinaTotalConfirm).append("\r\n")
                    .append("\uD83C\uDDFA\uD83C\uDDF8情况：").append("\r\n")
                    .append("新增死亡-").append(usaDeathIncrement).append("\r\n")
                    .append("新增确诊-").append(usaConfirmIncrement).append("\r\n")
                    .append("累计死亡-").append(usaTotalDeath).append("\r\n")
                    .append("累计确证-").append(usaTotalConfirm).append("\r\n");

            sender.SENDER.sendGroupMsg(msg.getGroupCode(), String.valueOf(stringBuffer));
        } catch (IOException e) {
            sender.SENDER.sendGroupMsg(msg.getGroupCode(), "500");
        }
    }

}

@Data
class DailyDash {
    private Long createTime;
    private String nation;
    private String region;
    private Long deathIncrement;
    private Long totalDeath;
    private Long confirmIncrement;
    private Long totalConfirm;

    public DailyDash(String[] data) {
        createTime = Long.valueOf(data[0]);
        nation = data[1].substring(1, data[1].length() - 1);
        region = data[2].substring(1, data[2].length() - 1);
        deathIncrement = Long.valueOf(data[3]);
        totalDeath = Long.valueOf(data[4]);
        confirmIncrement = Long.valueOf(data[5]);
        totalConfirm = Long.valueOf(data[6].substring(0, data[6].length() - 1));
    }
}