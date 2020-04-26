package com.ssrs.framework.data;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ssrs.framework.User;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @Description: mybatis自动填充类
 * @Author: ssrs
 * @CreateDate: 2019/8/25 11:56
 * @UpdateUser: ssrs
 * @UpdateDate: 2019/8/25 11:56
 * @Version: 1.0
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时间
     */
    private final String createTime = "createTime";
    /**
     * 修改时间
     */
    private final String updateTime = "updateTime";
    /**
     * 创建者
     */
    private final String createUser = "createUser";

    /**
     * 修改者
     */
    private final String updateUser = "updateUser";

    @Override
    public void insertFill(MetaObject metaObject) {
        String userName = currentUserName();
        this.strictInsertFill(metaObject, createTime, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, createUser, String.class, userName);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userName = currentUserName();
        this.strictUpdateFill(metaObject, updateTime, LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, updateUser, String.class, userName);
    }

    /**
     * 获取当前用户
     */
    private String currentUserName() {
        if (StrUtil.isNotEmpty(User.getUserName())) {
            return User.getUserName();
        }
        return "unknow";
    }

}
