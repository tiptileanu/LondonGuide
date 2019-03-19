package mic.alliwanna.be.londonguide;

public class POI {
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
}
