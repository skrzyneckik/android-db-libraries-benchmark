package com.littleinc.orm_benchmark.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.littleinc.orm_benchmark.greendao.User;
import com.littleinc.orm_benchmark.greendao.Message;

import com.littleinc.orm_benchmark.greendao.UserDao;
import com.littleinc.orm_benchmark.greendao.MessageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig messageDaoConfig;

    private final UserDao userDao;
    private final MessageDao messageDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        messageDaoConfig = daoConfigMap.get(MessageDao.class).clone();
        messageDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        messageDao = new MessageDao(messageDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Message.class, messageDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        messageDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

}
