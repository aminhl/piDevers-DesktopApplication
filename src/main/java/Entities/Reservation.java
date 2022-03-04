package Entities;

public class Reservation {
    private int idReservation;
    private Evenement ev;
    private Camping camping = new Camping() ;
    private Hotel hotel;
    private Utilisateur utilisateur=new Utilisateur();

    public Reservation(int idReservation, Evenement ev, Camping camping, Utilisateur utilisateur) {
        this.idReservation = idReservation;
        this.ev = ev;
        this.camping = camping;
        this.utilisateur = utilisateur;
    }

    public Reservation(Hotel hotel) {
        this.hotel = hotel;
    }

    public Reservation(int idReservation, Evenement ev, Camping camping, Hotel hotel, Utilisateur utilisateur) {
        this.idReservation = idReservation;
        this.ev = ev;
        this.camping = camping;
        this.hotel = hotel;
        this.utilisateur = utilisateur;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Evenement getEv() {
        return ev;
    }

    public void setEv(Evenement ev) {
        this.ev = ev;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Reservation() {
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", ev=" + ev +
                ", camping=" + camping +
                ", utilisateur=" + utilisateur +
                '}';
    }
}
