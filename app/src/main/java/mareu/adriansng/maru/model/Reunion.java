package mareu.adriansng.maru.model;

import java.util.List;
import java.util.Objects;

public class Reunion {

    /**
     * Identifier
     */
    private int id;

    /**
     * Meeting room
     */
    private final int idMeetingRoom;

    /**
     * Name organizer
     */
    private final String nameOrganizer;

    /**
     * Hour
     */
    private final String hour;

    /**
     * Date
     */
    private final String date;

    /**
     * Address mail participants
     */
    private final List<Person> personParticipant;

    /**
     * Address mail participants
     */
    private final String subjectReunion;

    /**
     * @param id ;
     * @param idMeetingRoom ;
     * @param nameOrganizer ;
     * @param hour ;
     * @param date ;
     * @param personParticipant ;
     * @param subjectReunion
     */

    public Reunion(int id, int idMeetingRoom, String nameOrganizer, String hour, String date, List<Person> personParticipant, String subjectReunion) {
        this.id = id;
        this.idMeetingRoom = idMeetingRoom;
        this.nameOrganizer = nameOrganizer;
        this.hour = hour;
        this.date = date;
        this.personParticipant = personParticipant;
        this.subjectReunion = subjectReunion;
    }

    public static Reunion newReunion(int id, int idMeetingRoom, String nameOrganizer, String hour, String date, List<Person> personParticipant, String subjectReunion) {
        return new Reunion(id, idMeetingRoom, nameOrganizer, hour, date, personParticipant, subjectReunion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMeetingRoom() {
        return idMeetingRoom;
    }

    public String getNameOrganizer() {
        return nameOrganizer;
    }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public List<Person> getPersonParticipant() {
        return personParticipant;
    }

    public String getSubjectReunion() { return subjectReunion;}

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
