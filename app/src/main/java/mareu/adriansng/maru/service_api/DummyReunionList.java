package mareu.adriansng.maru.service_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;

public class DummyReunionList {
    private MeetingRoom meetingRoom;

    //Generate MeetingRoom
    public static List<MeetingRoom> DUMMY_MEETING_ROOM = Arrays.asList(
            new MeetingRoom(0,"Meeting Room A", true),
            new MeetingRoom(1, "Meeting Room B", true),
            new MeetingRoom(2, "Meeting Room C", true),
            new MeetingRoom(3, "Meeting Room D", true),
            new MeetingRoom(4, "Meeting Room E", true),
            new MeetingRoom(5, "Meeting Room F", true),
            new MeetingRoom(6, "Meeting Room G", true),
            new MeetingRoom(7, "Meeting Room H", true),
            new MeetingRoom(8, "Meeting Room I", true)
    );

    //Generate Person
    public static List<Person> DUMMY_PERSON= Arrays.asList(
            new Person(0,"Pierre","pierre@maReu.com"),
            new Person(1, "Manuel", "manuel@maReu.com"),
            new Person(2, "Bran", "bran@maReu.com")
    );

    private static MeetingRoom MeetingRoom;
    public static List<Reunion> DUMMY_REUNION = Arrays.asList(
            new Reunion(0,DUMMY_MEETING_ROOM.get(1).getNameRoom(), "Henri", "02 12 2020, 10H00",DUMMY_PERSON),
            new Reunion(1, DUMMY_MEETING_ROOM.get(5).getNameRoom(), "Bernard", "02 12 2020, 10H30", DUMMY_PERSON),
            new Reunion(2,DUMMY_MEETING_ROOM.get(8).getNameRoom(), "Arnaud", "05 18 2020, 15H00", DUMMY_PERSON)
     );

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNION);
    }
}

