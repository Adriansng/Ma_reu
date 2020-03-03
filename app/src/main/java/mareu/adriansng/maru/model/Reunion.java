package mareu.adriansng.maru.model;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import mareu.adriansng.maru.service_api.DummyReunionList;
import mareu.adriansng.maru.service_api.ReunionApiService;

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
    private static List<Person> addressMailList;

    /**
     * @param id
     * @param idMeetingRoom
     * @param nameOrganizer
     * @param hourDate
     * @param addressMailList
     */

    public Reunion (Integer id, int idMeetingRoom, String nameOrganizer, String hourDate, List<Person> addressMailList){
        this.id = id;
        Reunion.idMeetingRoom = idMeetingRoom;
        Reunion.nameOrganizer = nameOrganizer;
        Reunion.hourDate = hourDate;
        Reunion.addressMailList = addressMailList;
    }

    public static Reunion addReunion() {
        return DummyReunionList.DUMMY_REUNION.get(new Random().nextInt(DummyReunionList.DUMMY_REUNION.size()));
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public int getIdMeetingRoom() {
        return idMeetingRoom;}

    public void setIdMeetingRoom(int idMeetingRoom) {
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

    public List<Person> getAddressMailList() {
        return addressMailList;
    }

    public void setAddressMailList(List<Person> addressMailList) {
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
