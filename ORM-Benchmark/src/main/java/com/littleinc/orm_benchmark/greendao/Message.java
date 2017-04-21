package com.littleinc.orm_benchmark.greendao;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import com.littleinc.orm_benchmark.greendao.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "MESSAGE".
 */
@Entity(active = true)
public class Message {

    @Id(autoincrement = true)
    private Long id;
    private String content;
    private long client_id;
    private int created_at;
    private double sorted_by;

    @Index
    private long command_id;
    private long sender_id;
    private long channel_id;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient MessageDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "id")
    })
    private List<User> readers;

    @Generated
    public Message() {
    }

    public Message(Long id) {
        this.id = id;
    }

    @Generated
    public Message(Long id, String content, long client_id, int created_at, double sorted_by, long command_id, long sender_id, long channel_id) {
        this.id = id;
        this.content = content;
        this.client_id = client_id;
        this.created_at = created_at;
        this.sorted_by = sorted_by;
        this.command_id = command_id;
        this.sender_id = sender_id;
        this.channel_id = channel_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMessageDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public double getSorted_by() {
        return sorted_by;
    }

    public void setSorted_by(double sorted_by) {
        this.sorted_by = sorted_by;
    }

    public long getCommand_id() {
        return command_id;
    }

    public void setCommand_id(long command_id) {
        this.command_id = command_id;
    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<User> getReaders() {
        if (readers == null) {
            __throwIfDetached();
            UserDao targetDao = daoSession.getUserDao();
            List<User> readersNew = targetDao._queryMessage_Readers(id);
            synchronized (this) {
                if(readers == null) {
                    readers = readersNew;
                }
            }
        }
        return readers;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetReaders() {
        readers = null;
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}
