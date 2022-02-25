package Entities;

public class Reservation {
    private int idReservation;
    private int idEvent;
    private int idCamp;
    private int idHotel;
    private int idUs;
    private int idRandonnee;
    private int idMuseum;

    public int getIdRandonnee() {
        return idRandonnee;
    }

    public void setIdRandonnee(int idRandonnee) {
        this.idRandonnee = idRandonnee;
    }

    public int getIdMuseum() {
        return idMuseum;
    }

    public void setIdMuseum(int idMuseum) {
        this.idMuseum = idMuseum;
    }

    public Reservation() {
    }

    public Reservation(int idEvent, int idCamp, int idHotel, int idUs,int idRandonnee,int idMuseum) {
        this.idEvent = idEvent;
        this.idCamp = idCamp;
        this.idHotel = idHotel;
        this.idUs = idUs;
        this.idRandonnee=idRandonnee;
        this.idMuseum=idMuseum;
    }

    public Reservation(int idReservation, int idEvent, int idCamp, int idHotel, int idUs,int idMuseum,int idRandonnee) {
        this.idReservation = idReservation;
        this.idEvent = idEvent;
        this.idCamp = idCamp;
        this.idHotel = idHotel;
        this.idUs = idUs;
        this.idRandonnee=idRandonnee;
        this.idMuseum=idMuseum;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdCamp() {
        return idCamp;
    }

    public void setIdCamp(int idCamp) {
        this.idCamp = idCamp;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdUs() {
        return idUs;
    }

    public void setIdUs(int idUs) {
        this.idUs = idUs;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", idEvent=" + idEvent +
                ", idCamp=" + idCamp +
                ", idHotel=" + idHotel +
                ", idUs=" + idUs +
                '}';
    }
}
