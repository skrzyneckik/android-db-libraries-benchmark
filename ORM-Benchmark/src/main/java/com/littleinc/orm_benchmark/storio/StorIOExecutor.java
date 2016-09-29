package com.littleinc.orm_benchmark.storio;

import android.content.Context;
import android.util.Log;

import com.littleinc.orm_benchmark.BenchmarkExecutable;
import com.littleinc.orm_benchmark.greendao.GreenDaoExecutor;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.littleinc.orm_benchmark.util.Util.getRandomString;

public class StorIOExecutor implements BenchmarkExecutable {

    private static final String TAG = "StorIOExecutor";

    private StorIOSQLite storIOSQLite;

    @Override
    public String getOrmName() {
        return "StorIO";
    }

    @Override
    public void init(Context context, boolean useInMemoryDb) {
        Log.d(TAG, "Creating DataBaseHelper");
        storIOSQLite = DefaultStorIOSQLite.builder()
            .sqliteOpenHelper(new DataBaseHelper(context, useInMemoryDb))
            .addTypeMapping(User.class, new UserSQLiteTypeMapping())
            .addTypeMapping(Message.class, new MessageSQLiteTypeMapping())
            .build();
    }

    @Override
    public long createDbStructure() throws SQLException {
        long start = System.nanoTime();
        User.createTable(storIOSQLite);
        Message.createTable(storIOSQLite);
        return System.nanoTime() - start;
    }

    @Override
    public long writeWholeData() throws SQLException {
        final List<User> users = new LinkedList<User>();
        for (int i = 0; i < NUM_USER_INSERTS; i++) {
            User newUser = User.newUser(null, getRandomString(10), getRandomString(10));
            users.add(newUser);
        }

        final List<Message> messages = new LinkedList<Message>();
        for (long i = 0; i < NUM_MESSAGE_INSERTS; i++) {
            Message message = Message.newMessage(i, NUM_USER_INSERTS);
            messages.add(message);
        }

        long start = System.nanoTime();

        try {
            storIOSQLite.lowLevel().beginTransaction();

            storIOSQLite.put()
                .objects(users)
                .prepare()
                .executeAsBlocking();

            storIOSQLite.put()
                .objects(messages)
                .prepare()
                .executeAsBlocking();

            storIOSQLite.lowLevel().setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            storIOSQLite.lowLevel().endTransaction();
        }
        return System.nanoTime() - start;
    }

    @Override
    public long readWholeData() throws SQLException {
        long start = System.nanoTime();

        List<Message> messages = storIOSQLite.get()
            .listOfObjects(Message.class)
            .withQuery(Message.QUERY_ALL)
            .prepare()
            .executeAsBlocking();

        Log.d(StorIOExecutor.class.getSimpleName(), "Read, " + messages.size() + " rows");
        return System.nanoTime() - start;
    }

    @Override
    public long dropDb() throws SQLException {
        long start = System.nanoTime();
        User.dropTable(storIOSQLite);
        Message.dropTable(storIOSQLite);
        return System.nanoTime() - start;
    }
}
