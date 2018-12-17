package com.maple.demo.myarms.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.maple.demo.myarms.mvp.model.entity.DaoMaster;
import com.maple.demo.myarms.mvp.model.entity.UserEntityDao;

import org.greenrobot.greendao.database.Database;

/**
 * <p>
 * MySQLiteOpenHelper是用来处理数据库升级的逻辑
 * 注意：这里的逻辑并不完善，每生成新的表管理类如{@link UserDao}
 * 就需要在此类方法{@link #onUpgrade}中加入相应的类
 * </p>
 *
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, UserEntityDao.class);
    }
}
