package mic.alliwanna.be.londonguide;

public class Reviews {
    private String nameOfPoi,reviewOfPoi, userId;
    private float ratingOfPoi;


    public Reviews(String nameOfPoi, String reviewOfPoi, String userId, float ratingOfPoi) {
        this.nameOfPoi = nameOfPoi;
        this.reviewOfPoi = reviewOfPoi;
        this.userId = userId;
        this.ratingOfPoi = ratingOfPoi;
    }

    public Reviews() {
    }

    public String getNameOfPoi() {
        return nameOfPoi;
    }

    public void setNameOfPoi(String nameOfPoi) {
        this.nameOfPoi = nameOfPoi;
    }

    public String getReviewOfPoi() {
        return reviewOfPoi;
    }

    public void setReviewOfPoi(String reviewOfPoi) {
        this.reviewOfPoi = reviewOfPoi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getRatingOfPoi() {
        return ratingOfPoi;
    }

    public void setRatingOfPoi(float ratingOfPoi) {
        this.ratingOfPoi = ratingOfPoi;
    }
}