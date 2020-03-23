package mareu.adriansng.maru.model;

import java.util.List;
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
    private static List<Person> personParticipant;

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
        Reunion.idMeetingRoom = idMeetingRoom;
        Reunion.nameOrganizer = nameOrganizer;
        Reunion.hour = hour;
        Reunion.date= date;
        Reunion.personParticipant = personParticipant;
    }

    public static Reunion newReunion(int id, int idMeetingRoom, String nameOrganizer, String hour, String date, List<Person> personParticipant) {
        return new Reunion(id,idMeetingRoom,nameOrganizer,hour, date,personParticipant);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public  int getIdMeetingRoom() {
        return idMeetingRoom;}

    public void setIdMeetingRoom(int meetingRoom) {
        Reunion.idMeetingRoom = idMeetingRoom;
    }

    public  String getNameOrganizer() {
        return nameOrganizer;
    }

    public  void setNameOrganizer(String nameOrganizer) {
        Reunion.nameOrganizer = nameOrganizer;
    }

    public  String getHour() { return hour; }

    public  void setHour(String hourDate) { Reunion.hour = hourDate; }

    public  String getDate() {
        return date;
    }

    public  void setDate(String date) {
        Reunion.date = date;
    }

    public  List<Person> getPersonParticipant() {
        return personParticipant;
    }

    public  void setPersonParticipant(List<Person> addressMailList) {
        Reunion.personParticipant = personParticipant;
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
