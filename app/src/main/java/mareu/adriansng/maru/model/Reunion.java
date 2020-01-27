package mareu.adriansng.maru.model;

import java.util.ArrayList;
import java.util.Objects;

public class Reunion {

    /** Identifier */
    private Integer id;

    /** Meeting room */
    private String meetingRoom;

    /** Name organizer */
    private String organizer;

    /** Hour and Date */
    private String hourDate;

    /** Address mail participants */
    private ArrayList addressMailList;

    /**
     * @param id
     * @param meetingRoom
     * @param organizer
     * @param hourDate
     * @param addressMailList
     */

    public Reunion (Integer id, String meetingRoom, String organizer, String hourDate, ArrayList addressMailList){
        this.id = id;
        this.meetingRoom = meetingRoom;
        this.organizer = organizer;
        this.hourDate = hourDate;
        this.addressMailList= addressMailList;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getMeetingRoom() { return meetingRoom; }

    public void setMeetingRoom(String meetingRoom) { this.meetingRoom = meetingRoom; }

    public String getOrganizer() { return organizer; }

    public void setOrganizer(String organizer) { this.organizer = organizer; }

    public String getHourDate() { return hourDate; }

    public void setHourDate(String hourDate) { this.hourDate = hourDate; }

    public ArrayList getAddressMailList() { return addressMailList; }

    public void setAddressMailList(ArrayList addressMailList) { this.addressMailList = addressMailList; }

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
