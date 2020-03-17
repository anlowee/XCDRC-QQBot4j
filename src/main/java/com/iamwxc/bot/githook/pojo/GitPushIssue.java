package com.iamwxc.bot.githook.pojo;

import lombok.Data;

import java.util.List;

/**
 * Class description goes here.
 * <p>
 * If you see this sentence, nothing ambiguous.
 * </p>
 *
 * @author CC
 * @version 1.0
 */
@Data
public class GitPushIssue {

    private GitSender sender;
    private List<GitCommit> commits;

}
