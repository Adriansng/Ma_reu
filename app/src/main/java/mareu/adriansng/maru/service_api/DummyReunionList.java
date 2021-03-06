package mareu.adriansng.maru.service_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mareu.adriansng.maru.R;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;

public abstract class DummyReunionList {


    //Generate MeetingRoom
    static final List<MeetingRoom> DUMMY_MEETING_ROOM = Arrays.asList(
            new MeetingRoom(0, " Select a room", true, R.color.colorSelectRoom),
            new MeetingRoom(1, "Meeting Room A", true, R.color.colorMeetingRoomA),
            new MeetingRoom(2, "Meeting Room B", true, R.color.colorMeetingRoomB),
            new MeetingRoom(3, "Meeting Room C", true, R.color.colorMeetingRoomC),
            new MeetingRoom(4, "Meeting Room D", true, R.color.colorMeetingRoomD),
            new MeetingRoom(5, "Meeting Room E", true, R.color.colorMeetingRoomE),
            new MeetingRoom(6, "Meeting Room F", true, R.color.colorMeetingRoomF),
            new MeetingRoom(7, "Meeting Room G", true, R.color.colorMeetingRoomG),
            new MeetingRoom(8, "Meeting Room H", true, R.color.colorMeetingRoomH),
            new MeetingRoom(9, "Meeting Room I", true, R.color.colorMeetingRoomI),
            new MeetingRoom(10, "Meeting Room J", true, R.color.colorMeetingRoomJ)
    );

    //Generate Person
    static final List<Person> DUMMY_PERSON = Arrays.asList(
            new Person(0, "Pierre", "pierre@maReu.com"),
            new Person(1, "Manuel", "manuel@maReu.com"),
            new Person(2, "Bran", "bran@maReu.com")
    );

    public static final List<Reunion> DUMMY_REUNION = Arrays.asList(
            new Reunion(0, 2, "Henri", "10H00", "5/12/20", Arrays.asList(DUMMY_PERSON.get(0), DUMMY_PERSON.get(1), DUMMY_PERSON.get(2)), "There will be a recovery meeting on the probability of having a meeting"),
            new Reunion(1, 5, "Bernard", "10H30", "6/11/20", Arrays.asList(DUMMY_PERSON.get(0), DUMMY_PERSON.get(1), DUMMY_PERSON.get(2)), "There will be a recovery meeting on the probability of having a meeting"),
            new Reunion(2, 8, "Arnaud", "15H00", "5/25/20", Arrays.asList(DUMMY_PERSON.get(0), DUMMY_PERSON.get(1), DUMMY_PERSON.get(2)), "There will be a recovery meeting on the probability of having a meeting")
    );

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNION);
    }
}

