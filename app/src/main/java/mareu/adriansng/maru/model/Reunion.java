package mareu.adriansng.maru.model;

import java.util.Objects;

public class Reunion {


    /** Identifier */
    private int id;

    /** Meeting room */
    private static int idMeetingRoom;

    /** Name organizer */
    private static String nameOrganizer;

    /** Hour */
    private static String hour;

    /** Date */
    private static String date;

    /** Address mail participants */
    private static String addressMailList;

    /**
     * @param id
     * @param idMeetingRoom
     * @param nameOrganizer
     * @param hour
     * @param date
     * @param addressMailList
     */

    public Reunion (int id, int idMeetingRoom, String nameOrganizer, String hour, String date, String addressMailList){
        this.id = id;
        Reunion.idMeetingRoom = idMeetingRoom;
        Reunion.nameOrganizer = nameOrganizer;
        Reunion.hour = hour;
        Reunion.date= date;
        Reunion.addressMailList = addressMailList;
    }

    public static Reunion newReunion(int id, int idMeetingRoom, String nameOrganizer, String hour, String date, String addressMailList) {
        return new Reunion(id,idMeetingRoom,nameOrganizer,hour, date,addressMailList);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public static int getIdMeetingRoom() {
        return idMeetingRoom;}

    public void setIdMeetingRoom(int meetingRoom) {
        Reunion.idMeetingRoom = idMeetingRoom;
    }

    public static String getNameOrganizer() {
        return nameOrganizer;
    }

    public static void setNameOrganizer(String nameOrganizer) {
        Reunion.nameOrganizer = nameOrganizer;
    }

    public static String getHour() { return hour; }

    public static void setHour(String hourDate) { Reunion.hour = hourDate; }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        Reunion.date = date;
    }

    public static String getAddressMailList() {
        return addressMailList;
    }

    public static void setAddressMailList(String addressMailList) {
        Reunion.addressMailList = addressMailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(id, reunion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
