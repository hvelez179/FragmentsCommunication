package com.mac.training.simpleinjection.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RandomAPI implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<Person> persons = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


    protected RandomAPI(Parcel in) {
        if (in.readByte() == 0x01) {
            persons = new ArrayList<Person>();
            in.readList(persons, Person.class.getClassLoader());
        } else {
            persons = null;
        }
        info = (Info) in.readValue(Info.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (persons == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(persons);
        }
        dest.writeValue(info);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RandomAPI> CREATOR = new Parcelable.Creator<RandomAPI>() {
        @Override
        public RandomAPI createFromParcel(Parcel in) {
            return new RandomAPI(in);
        }

        @Override
        public RandomAPI[] newArray(int size) {
            return new RandomAPI[size];
        }
    };
}
