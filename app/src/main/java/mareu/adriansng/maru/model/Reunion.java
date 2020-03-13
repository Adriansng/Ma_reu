package mareu.adriansng.maru.model;

import java.util.Objects;

public class Reunion {


    /** Identifier */
    private Integer id;

    /** Meeting room */
    private static int idMeetingRoom;

    /** Name organizer */
    private static String nameOrganizer;

    /** Hour and Date */
    private static String hourDate;

    /** Address mail participants */
    private static String addressMailList;

    /**
     * @param id
     * @param idMeetingRoom
     * @param nameOrganizer
     * @param hourDate
     * @param addressMailList
     */

    public Reunion (Integer id, int idMeetingRoom, String nameOrganizer, String hourDate, String addressMailList){
        this.id = id;
        Reunion.idMeetingRoom = idMeetingRoom;
        Reunion.nameOrganizer = nameOrganizer;
        Reunion.hourDate = hourDate;
        Reunion.addressMailList = addressMailList;
    }

    public static Reunion addNewReunion(int id, int idMeetingRoom, String nameOrganizer, String hourDate, String addressMailList) {
        return new Reunion(id,idMeetingRoom,nameOrganizer,hourDate,addressMailList);
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public int getIdMeetingRoom() {
        return idMeetingRoom;}

    public void setIdMeetingRoom(int meetingRoom) {
        Reunion.idMeetingRoom = idMeetingRoom;
    }

    public String getNameOrganizer() {
        return nameOrganizer;
    }

    public void setNameOrganizer(String nameOrganizer) {
        Reunion.nameOrganizer = nameOrganizer;
    }

    public String getHourDate() { return hourDate; }

    public void setHourDate(String hourDate) { Reunion.hourDate = hourDate; }

    public String getAddressMailList() {
        return addressMailList;
    }

    public void setAddressMailList(String addressMailList) {
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
