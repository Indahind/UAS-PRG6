package com.polytechnic.astra.ac.id.mycobacobaapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class UserModel implements Serializable, Parcelable {

    private String nim;
    private String password;
    private String nama;
    private String prodi;

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        nim = in.readString();
        password = in.readString();
        nama = in.readString();
        prodi = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "nim='" + nim + '\'' +
                ", password='" + password + '\'' +
                ", nama='" + nama + '\'' +
                ", prodi='" + prodi + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nim);
        dest.writeString(password);
        dest.writeString(nama);
        dest.writeString(prodi);
    }
}
