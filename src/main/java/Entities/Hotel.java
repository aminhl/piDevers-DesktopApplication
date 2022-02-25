package Entities;

public class Hotel {
    private int idHotel;
    private String nomHotel;
    private String adresseHotel;
    private String emailHotel;
    private int etoileHotel;
    private String telephoneHotel;
    private float prixHotel;
    private String imageHotel;

    public Hotel() {
    }

    public Hotel(String nomHotel, String adresseHotel, String emailHotel, int etoileHotel, String telephoneHotel, float prixHotel, String imageHotel) {
        this.nomHotel = nomHotel;
        this.adresseHotel = adresseHotel;
        this.emailHotel = emailHotel;
        this.etoileHotel = etoileHotel;
        this.telephoneHotel = telephoneHotel;
        this.prixHotel = prixHotel;
        this.imageHotel = imageHotel;
    }

    public Hotel(int idHotel, String nomHotel, String adresseHotel, String emailHotel, int etoileHotel, String telephoneHotel, float prixHotel, String imageHotel) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        this.adresseHotel = adresseHotel;
        this.emailHotel = emailHotel;
        this.etoileHotel = etoileHotel;
        this.telephoneHotel = telephoneHotel;
        this.prixHotel = prixHotel;
        this.imageHotel = imageHotel;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getAdresseHotel() {
        return adresseHotel;
    }

    public void setAdresseHotel(String adresseHotel) {
        this.adresseHotel = adresseHotel;
    }

    public String getEmailHotel() {
        return emailHotel;
    }

    public void setEmailHotel(String emailHotel) {
        this.emailHotel = emailHotel;
    }

    public int getEtoileHotel() {
        return etoileHotel;
    }

    public void setEtoileHotel(int etoileHotel) {
        this.etoileHotel = etoileHotel;
    }

    public String getTelephoneHotel() {
        return telephoneHotel;
    }

    public void setTelephoneHotel(String telephoneHotel) {
        this.telephoneHotel = telephoneHotel;
    }

    public String getImageHotel() {
        return imageHotel;
    }

    public void setImageHotel(String imageHotel) {
        this.imageHotel = imageHotel;
    }

    public float getPrixHotel() {
        return prixHotel;
    }

    public void setPrixHotel(float prixHotel) {
        this.prixHotel = prixHotel;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", nomHotel='" + nomHotel + '\'' +
                ", adresseHotel='" + adresseHotel + '\'' +
                ", emailHotel='" + emailHotel + '\'' +
                ", etoileHotel=" + etoileHotel +
                ", telephoneHotel='" + telephoneHotel + '\'' +
                ", imageHotel='" + imageHotel + '\'' +
                ", prixHotel=" + prixHotel +
                '}';
    }
}
