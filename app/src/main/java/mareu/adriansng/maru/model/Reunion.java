package mareu.adriansng.maru.model;

import java.util.List;
import java.util.Objects;

public class Reunion {


    /** Identifier */
    private int id;

    /** Meeting room */
    private  int idMeetingRoom;

    /** Name organizer */
    private  String nameOrganizer;

    /** Hour */
    private  String hour;

    /** Date */
    private  String date;

    /** Address mail participants */
    private List<Person> personParticipant;

    /**
     * @param id
     * @param idMeetingRoom
     * @param nameOrganizer
     * @param hour
     * @param date
     * @param personParticipant
     */

    public Reunion (int id, int idMeetingRoom, String nameOrganizer, String hour, String date, List<Person> personParticipant){
        this.id = id;
        this.idMeetingRoom = idMeetingRoom;
        this.nameOrganizer = nameOrganizer;
        this.hour = hour;
        this.date= date;
        this.personParticipant = personParticipant;
    }

    public static Reunion newReunion(int id, int idMeetingRoom, String nameOrganizer, String hour, String date, List<Person> personParticipant) {
        return new Reunion(id,idMeetingRoom,nameOrganizer,hour, date,personParticipant);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public  int getIdMeetingRoom() {
        return idMeetingRoom;}

    public void setIdMeetingRoom(int meetingRoom) {
        this.idMeetingRoom = idMeetingRoom;
    }

    public  String getNameOrganizer() {
        return nameOrganizer;
    }

    public  void setNameOrganizer(String nameOrganizer) {
        this.nameOrganizer = nameOrganizer;
    }

    public  String getHour() { return hour; }

    public  void setHour(String hourDate) { this.hour = hourDate; }

    public  String getDate() {
        return date;
    }

    public  void setDate(String date) {
        this.date = date;
    }

    public  List<Person> getPersonParticipant() {
        return personParticipant;
    }

    public  void setPersonParticipant(List<Person> addressMailList) {
        this.personParticipant = personParticipant;
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
