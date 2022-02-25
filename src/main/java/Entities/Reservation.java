package Entities;

public class Reservation {
    private int idReservation;
    private int idEvent;
    private int idCamp;
    private int idHotel;
    private int idUs;

    public Reservation() {
    }

    public Reservation(int idEvent, int idCamp, int idHotel, int idUs) {
        this.idEvent = idEvent;
        this.idCamp = idCamp;
        this.idHotel = idHotel;
        this.idUs = idUs;
    }

    public Reservation(int idReservation, int idEvent, int idCamp, int idHotel, int idUs) {
        this.idReservation = idReservation;
        this.idEvent = idEvent;
        this.idCamp = idCamp;
        this.idHotel = idHotel;
        this.idUs = idUs;
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
