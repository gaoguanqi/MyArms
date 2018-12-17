package com.maple.demo.myarms.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 个人信息
 *
 */
@Entity
public class UserEntity implements Parcelable {

    // 数据库主键，autoincrement设置自增，只能为 long/ Long 类型
    @Id(autoincrement = true)
    private long id;


    private String phone;

    public UserEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }





    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phone);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    protected UserEntity(Parcel in) {
        this.phone = in.readString();
    }

    @Generated(hash = 192803069)
    public UserEntity(long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
