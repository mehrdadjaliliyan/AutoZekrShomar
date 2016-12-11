package jsm.autozekrcounter.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mehrdad on 12/9/2016.
 */

public class ZekrModel implements Parcelable {
    public static final int END_LESS = -1;
    private String text;
    private int count;

    public ZekrModel() {
    }

    public ZekrModel(String text, int count) {
        this.text = text;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeInt(this.count);
    }


    protected ZekrModel(Parcel in) {
        this.text = in.readString();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<ZekrModel> CREATOR = new Parcelable.Creator<ZekrModel>() {
        @Override
        public ZekrModel createFromParcel(Parcel source) {
            return new ZekrModel(source);
        }

        @Override
        public ZekrModel[] newArray(int size) {
            return new ZekrModel[size];
        }
    };
}
