package jsm.autozekrcounter.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mehrdad on 12/9/2016.
 */

public class RequestZekrPackage implements Parcelable {

    private ArrayList<ZekrModel> zekrModelArrayList =  new ArrayList<>();

    public ArrayList<ZekrModel> getZekrModelArrayList() {
        return zekrModelArrayList;
    }

    public void setZekrModelArrayList(ArrayList<ZekrModel> zekrModelArrayList) {
        this.zekrModelArrayList = zekrModelArrayList;
    }

    public void addZekr(ZekrModel zekr) {
        this.zekrModelArrayList.add(zekr) ;
    }

    public int getAllZekrCount() {
        int count=0;
        for (ZekrModel zekrModel : zekrModelArrayList  ) {
            count+=zekrModel.getCount();
        }
        return count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.zekrModelArrayList);
    }

    public RequestZekrPackage() {
    }

    protected RequestZekrPackage(Parcel in) {
        this.zekrModelArrayList = in.createTypedArrayList(ZekrModel.CREATOR);
    }

    public static final Parcelable.Creator<RequestZekrPackage> CREATOR = new Parcelable.Creator<RequestZekrPackage>() {
        @Override
        public RequestZekrPackage createFromParcel(Parcel source) {
            return new RequestZekrPackage(source);
        }

        @Override
        public RequestZekrPackage[] newArray(int size) {
            return new RequestZekrPackage[size];
        }
    };
}
