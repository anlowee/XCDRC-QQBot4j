package com.iamwxc.bot.githook.pojo;

import lombok.Data;

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
public class GitRepository {

    private Long id;
    private String name;
    private String fullName;
    private String description;

}
