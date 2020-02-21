package mareu.adriansng.maru.model;

import java.util.Objects;

public class MeetingRoom {

    /** Identifier */
    private int id;

    /**  Name room*/
    private String nameRoom;

    /** Availability */
    private Boolean availability;

    /**
     * @param id
     * @param nameRoom
     * @param availability
      */

    public MeetingRoom (Integer id,String nameRoom,Boolean availability){
        this.id= id;
        this.nameRoom = nameRoom;
        this.availability = availability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingRoom meetingRoom = (MeetingRoom) o;
        return Objects.equals(id, meetingRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
