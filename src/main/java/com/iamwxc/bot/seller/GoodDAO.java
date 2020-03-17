package com.iamwxc.bot.seller;

import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.utils.JSONUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface description goes here.
 * <p>
 * If you see this sentence, nothing ambiguous.
 * </p>
 *
 * @author CC
 * @version 1.0
 */
@Repository
public interface GoodDAO extends JpaRepository<Good, Long> {

}
