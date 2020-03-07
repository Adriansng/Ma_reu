package mareu.adriansng.maru.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import mareu.adriansng.maru.service_api.DummyReunionList;

public class Reunion {

    private static final SimpleDateFormat dateFormat=
            new SimpleDateFormat("MM dd yyyy, h");

    /** Identifier */
    private Integer id;

    /** Meeting room */
    private static String meetingRoom;

    /** Name organizer */
    private static String nameOrganizer;

    /** Hour and Date */
    private static String hourDate;

    /** Address mail participants */
    private static List<Person> addressMailList;

    /**
     * @param id
     * @param meetingRoom
     * @param nameOrganizer
     * @param hourDate
     * @param addressMailList
     */

    public Reunion (Integer id, String meetingRoom, String nameOrganizer, String hourDate, List<Person> addressMailList){
        this.id = id;
        Reunion.meetingRoom = meetingRoom;
        Reunion.nameOrganizer = nameOrganizer;
        Reunion.hourDate = hourDate;
        Reunion.addressMailList = addressMailList;
    }

    public static Reunion addReunion() {
        return DummyReunionList.DUMMY_REUNION.get(new Random().nextInt(DummyReunionList.DUMMY_REUNION.size()));
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getMeetingRoom() {
        return meetingRoom;}

    public void setMeetingRoom(String meetingRoom) {
        Reunion.meetingRoom = meetingRoom;
    }

    public String getNameOrganizer() {
        return nameOrganizer;
    }

    public void setNameOrganizer(String nameOrganizer) {
        Reunion.nameOrganizer = nameOrganizer;
    }

    public String getHourDate() { return hourDate; }

    public void setHourDate(String hourDate) { Reunion.hourDate = hourDate; }

    public List<Person> getAddressMailList() {
        return addressMailList;
    }

    public void setAddressMailList(List<Person> addressMailList) {
        Reunion.addressMailList = addressMailList;
    }

    public  Long getDateFormat() {
        try{
            Date date= dateFormat.parse(getHourDate());
            return date != null ? date.getTime() : null;
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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
