package mic.alliwanna.be.londonguide;

import android.os.Parcel;
import android.os.Parcelable;

public class POI implements Parcelable {
    private String poiName, poiDescription, poiAddress, poiOpeningHours, poiWebsite, poiPhotoUrl, poiBookingUrl, poiType;
    private float poiRating;
    private boolean poiCanBook;

    public POI() {
    }

    public POI(String poiName, String poiDescription, String poiAddress, String poiOpeningHours, String poiWebsite, String poiPhotoUrl, String poiBookingUrl, String poiType, float poiRating, boolean poiCanBook) {
        // Set name of point of interest
        this.poiName = poiName;
        // Set description for point of interest
        this.poiDescription = poiDescription;
        // Set address/postcode for point of interest
        this.poiAddress = poiAddress;
        // set opening hours for point of interest
        this.poiOpeningHours = poiOpeningHours;
        // Set website link for point of interest
        this.poiWebsite = poiWebsite;
        // Set link to  point of interest photo
        this.poiPhotoUrl = poiPhotoUrl;
        // Set link to booking page
        this.poiBookingUrl = poiBookingUrl;
        // Set type of POI(Attraction, museum or park)
        this.poiType = poiType;
        // Rating of point of interest
        this.poiRating = poiRating;
        // Set booking possibility
        this.poiCanBook = poiCanBook;
    }

    protected POI(Parcel in) {
        poiName = in.readString();
        poiDescription = in.readString();
        poiAddress = in.readString();
        poiOpeningHours = in.readString();
        poiWebsite = in.readString();
        poiPhotoUrl = in.readString();
        poiBookingUrl = in.readString();
        poiType = in.readString();
        poiRating = in.readFloat();
        poiCanBook = in.readByte() != 0;
    }

    public static final Creator<POI> CREATOR = new Creator<POI>() {
        @Override
        public POI createFromParcel(Parcel in) {
            return new POI(in);
        }

        @Override
        public POI[] newArray(int size) {
            return new POI[size];
        }
    };

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiDescription() {
        return poiDescription;
    }

    public void setPoiDescription(String poiDescription) {
        this.poiDescription = poiDescription;
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    public String getPoiOpeningHours() {
        return poiOpeningHours;
    }

    public void setPoiOpeningHours(String poiOpeningHours) {
        this.poiOpeningHours = poiOpeningHours;
    }

    public String getPoiWebsite() {
        return poiWebsite;
    }

    public void setPoiWebsite(String poiWebsite) {
        this.poiWebsite = poiWebsite;
    }

    public String getPoiPhotoUrl() {
        return poiPhotoUrl;
    }

    public void setPoiPhotoUrl(String poiPhotoUrl) {
        this.poiPhotoUrl = poiPhotoUrl;
    }

    public String getPoiBookingUrl() {
        return poiBookingUrl;
    }

    public void setPoiBookingUrl(String poiBookingUrl) {
        this.poiBookingUrl = poiBookingUrl;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public float getPoiRating() {
        return poiRating;
    }

    public void setPoiRating(float poiRating) {
        this.poiRating = poiRating;
    }

    public boolean isPoiCanBook() {
        return poiCanBook;
    }

    public void setPoiCanBook(boolean poiCanBook) {
        this.poiCanBook = poiCanBook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poiName);
        dest.writeString(poiDescription);
        dest.writeString(poiAddress);
        dest.writeString(poiOpeningHours);
        dest.writeString(poiWebsite);
        dest.writeString(poiPhotoUrl);
        dest.writeString(poiBookingUrl);
        dest.writeString(poiType);
        dest.writeFloat(poiRating);
        dest.writeByte((byte) (poiCanBook ? 1 : 0));
    }
}
