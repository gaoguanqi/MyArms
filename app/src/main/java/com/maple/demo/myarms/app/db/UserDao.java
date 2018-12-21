package com.maple.demo.myarms.app.db;


import android.content.Context;


import com.maple.demo.myarms.mvp.model.entity.UserEntity;
import com.maple.demo.myarms.mvp.model.entity.UserEntityDao;

import java.util.List;


/**
 * <p>
 * UserDao是对用户表处理：
 * 创建一个UserDao类，把增删改查的方法封装起来，提高代码的可复用性，简洁性
 * 注意：所有的表都应该有这样的一个统一的类来管理
 * </p>
 *
 */
public class UserDao {
    private final GreenDaoManager daoManager;
    private static UserDao mUserDao;

    public UserDao() {
        daoManager = GreenDaoManager.getInstance();
    }

    public static UserDao getInstance() {
        if (mUserDao == null) {
            mUserDao = new UserDao();
        }
        return mUserDao;
    }

    /**
     * 插入或替换数据
     *
     * @param userInfo
     * @return
     */
    public boolean insertOrReplaceData(UserEntity userInfo) {
        boolean flag = false;
        try {
            flag = getUserInfoDao().insertOrReplace(userInfo) != -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 插入多条数据  子线程完成
     *
     * @param list
     * @return
     */
    public boolean insertOrReplaceMultiData(final List<UserEntity> list) {
        boolean flag = false;
        try {
            getUserInfoDao().getSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (UserEntity userInfo : list) {
                        daoManager.getDaoSession().insertOrReplace(userInfo);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新数据
     *
     * @param userInfo
     * @return
     */
    public boolean updateUserData(UserEntity userInfo) {
        boolean flag = false;
        try {
            getUserInfoDao().update(userInfo);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据id删除数据
     *
     * @param userInfo
     * @return
     */
    public boolean deleteUserData(UserEntity userInfo) {
        boolean flag = false;
        try {
            getUserInfoDao().delete(userInfo);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有数据
     *
     * @return
     */
    public boolean deleteAllData(Context context) {
        boolean flag = false;
        try {
            getUserInfoDao().deleteAll();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据参数查询
     *
     * @param where
     * @param param
     * @return
     */
    public List<UserEntity> queryUserByParams(String where, String... param) {
        return getUserInfoDao().queryRaw(where, param);
    }

    public UserEntityDao getUserInfoDao() {
        return daoManager.getDaoSession().getUserEntityDao();
    }

    /**
     * 查询用户数据
     *
     * @return User对象
     */
    public UserEntity getUser() {
        return getUserInfoDao().loadAll().get(0);
    }


    /**
     * 查询所有用户数据
     *
     * @return
     */
    public List<UserEntity> getAllUser() {
        return getUserInfoDao().loadAll();
    }


    /**
     * 是否登录
     *
     * @return isLogin
     */
    public boolean isLogin() {
        return !getUserInfoDao().loadAll().isEmpty();
    }

}
