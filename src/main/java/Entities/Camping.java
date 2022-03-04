package Entities;

public class Camping {
    private int idCamping;
    private String nomCamping;
    private String adresseCamping;
    private String imageCamping;
    private String telephoneCamping;
    private float ratingCamping;
    private String descriptionCamping;

    public Camping() {
    }

    public Camping(String nomCamping, String adresseCamping, String imageCamping, String telephoneCamping, float ratingCamping, String descriptionCamping) {
        this.nomCamping = nomCamping;
        this.adresseCamping = adresseCamping;
        this.imageCamping = imageCamping;
        this.telephoneCamping = telephoneCamping;
        this.ratingCamping = ratingCamping;
        this.descriptionCamping = descriptionCamping;
    }

    public Camping(int idCamping, String nomCamping, String adresseCamping, String imageCamping, String telephoneCamping, float ratingCamping, String descriptionCamping) {
        this.idCamping = idCamping;
        this.nomCamping = nomCamping;
        this.adresseCamping = adresseCamping;
        this.imageCamping = imageCamping;
        this.telephoneCamping = telephoneCamping;
        this.ratingCamping = ratingCamping;
        this.descriptionCamping = descriptionCamping;
    }

    public int getIdCamping() {
        return idCamping;
    }

    public void setIdCamping(int idCamping) {
        this.idCamping = idCamping;
    }

    public String getNomCamping() {
        return nomCamping;
    }

    public void setNomCamping(String nomCamping) {
        this.nomCamping = nomCamping;
    }

    public String getAdresseCamping() {
        return adresseCamping;
    }

    public void setAdresseCamping(String adresseCamping) {
        this.adresseCamping = adresseCamping;
    }

    public String getImageCamping() {
        return imageCamping;
    }

    public void setImageCamping(String imageCamping) {
        this.imageCamping = imageCamping;
    }

    public String getTelephoneCamping() {
        return telephoneCamping;
    }

    public void setTelephoneCamping(String telephoneCamping) {
        this.telephoneCamping = telephoneCamping;
    }

    public float getRatingCamping() {
        return ratingCamping;
    }

    public void setRatingCamping(float ratingCamping) {
        this.ratingCamping = ratingCamping;
    }

    public String getDescriptionCamping() {
        return descriptionCamping;
    }

    public void setDescriptionCamping(String descriptionCamping) {
        this.descriptionCamping = descriptionCamping;
    }

    @Override
    public String toString() {
        return "Camping{" +
                "idCamping=" + idCamping +
                ", nomCamping='" + nomCamping + '\'' +
                ", adresseCamping='" + adresseCamping + '\'' +
                ", imageCamping='" + imageCamping + '\'' +
                ", telephoneCamping='" + telephoneCamping + '\'' +
                ", ratingCamping=" + ratingCamping +
                ", descriptionCamping='" + descriptionCamping + '\'' +
                '}';
    }
}