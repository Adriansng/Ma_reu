package mareu.adriansng.maru.model;

import java.util.Objects;

public class MeetingRoom {

    /** Identifier */
    private int id;

    /**  Name room*/
    private String nameRoom;

    /** Availability */
    private Boolean availability;

    /**Color avatar meeting room */
    private int colorAvatar;

    /**
     * @param id
     * @param nameRoom
     * @param availability
     * @param colorAvatar
     */

    public MeetingRoom (int id, String nameRoom, Boolean availability, int colorAvatar){
        this.id= id;
        this.nameRoom = nameRoom;
        this.availability = availability;
        this.colorAvatar= colorAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getColorAvatar() {
        return colorAvatar;
    }

    public void setColorAvatar(int colorAvatar) {
        this.colorAvatar = colorAvatar;
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
