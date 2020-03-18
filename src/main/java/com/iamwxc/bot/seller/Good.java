package com.iamwxc.bot.seller;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity(name = "my_good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String goodName;

    private String baiduYunInfo;

}
