package com.iamwxc.controller;

import com.alibaba.fastjson.JSONObject;
import com.forte.component.forcoolqhttpapi.beans.msg.Msg;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.sender.MsgSender;
import com.iamwxc.pojo.GitCommit;
import com.iamwxc.pojo.GitPushIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class GithookController {

    @Autowired
    private MsgSender sender;

    @GetMapping("/githook")
    public void doGET() {
        System.out.println("get");
    }

    @PostMapping("/githook")
    public void doPOST(@RequestBody String jsonString) {
        GitPushIssue gitPushIssue = JSONObject.parseObject(jsonString, GitPushIssue.class);
        String msg = "A new push issue from this guy→";
        msg += gitPushIssue.getSender().getLogin() + "\n";
        int size = gitPushIssue.getCommits().size();
        msg += "totally [" + size + "] commits.\n";
        for (GitCommit commit : gitPushIssue.getCommits()) {
            msg += "🔨🔨🔨🔨🔨🔨🔨🔨🔨🔨\n";
            msg += "Message: " + commit.getMessage() + "\n";
            msg += "Details here: " + commit.getUrl();
        }
        sender.SENDER.sendGroupMsg("598876538", msg);
    }

}
