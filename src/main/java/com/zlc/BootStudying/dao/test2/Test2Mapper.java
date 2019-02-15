package com.zlc.BootStudying.dao.test2;

import com.zlc.BootStudying.po.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author : ZLC
 * @create : 2019-02-15 14:20
 * @desc :
 **/
@Component
public interface Test2Mapper {

    @Select("select * from user")
    User getUser();
}
