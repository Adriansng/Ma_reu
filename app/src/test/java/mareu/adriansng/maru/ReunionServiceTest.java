package mareu.adriansng.maru;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.DummyReunionList;
import mareu.adriansng.maru.service_api.ReunionApiService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ReunionServiceTest {
    private ReunionApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    // GENERATE LIST
    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = DummyReunionList.DUMMY_REUNION;
        assertEquals(reunions, expectedReunions);
    }


    // ACTION
    @Test
    public void addReunionList() {
        final Reunion reunion1= new Reunion(1,1,"name1","hour1","date1",service.getPersonParticipant());
        final Reunion reunion2= new Reunion(2,12,"name2","hour2","date2",service.getPersonParticipant());

        service.getReunions().clear();
        service.addReunion(reunion1);
        service.addReunion(reunion2);
        assertEquals(2, service.getReunions().size());
        assertEquals("Meeting Room A", service.getNameMeetingRome(reunion1.getIdMeetingRoom()));
        assertEquals("name1",reunion1.getNameOrganizer());
        assertEquals("hour1",reunion1.getHour());
        assertEquals("date1",reunion1.getDate());
        assertEquals(service.getPersonParticipant(),reunion1.getPersonParticipant());
    }

    @Test
    public void deleteReunionList() {
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }


    // FILTER
    @Test
    public void filterReunionList() {
        final Reunion reunion1 = new Reunion(1, 1, "name1", "hour1", "date1", service.getPersonParticipant());
        final Reunion reunion2 = new Reunion(2, 9, "name2", "hour2", "date2", service.getPersonParticipant());

        service.getReunions().clear();
        service.addReunion(reunion1);
        service.addReunion(reunion2);
        assertEquals(2, service.getReunions().size());
        //filter room
        service.getFilterMeetingRoom(1);
        assertEquals(1,service.getReunionSize());
        assertNotEquals(2,service.getReunionSize());
        assertEquals(service.getReunion(0).getIdMeetingRoom(),1);
        assertNotEquals(9, service.getReunion(0).getIdMeetingRoom());
        //filter date
        service.getFilterDate("date1");
        assertEquals(1,service.getReunionSize());
        assertEquals(service.getReunion(0).getDate(),"date1");
        assertNotEquals("date2", service.getReunion(0).getDate());
        //filter room and date
        service.getFilterMeetingAndDate(1,"date1");
        assertEquals(1,service.getReunionSize());
        assertEquals(service.getReunion(0).getIdMeetingRoom(),1);
        assertNotEquals(9, service.getReunion(0).getIdMeetingRoom());
        assertEquals(service.getReunion(0).getDate(),"date1");
        assertNotEquals("date2", service.getReunion(0).getDate());
    }

    @Test
    public void availabilityRoom() {
        final Reunion reunion1 = new Reunion(1, 1, "name1", "23h30", "12/31/20", service.getPersonParticipant());

        service.getReunions().clear();
        service.addReunion(reunion1);
        assertEquals(1, service.getReunions().size());
        assertTrue(service.getMeetingRoom().get(1).getAvailability());

        service.getAvailabilityMeetingRoom("12/31/20", "23h30", 45);
        assertFalse(service.getMeetingRoom().get(1).getAvailability());
        service.getResetAvailabilityMeetingRoom();

        service.getAvailabilityMeetingRoom("01/01/21", "0h14", 45);
        assertFalse(service.getMeetingRoom().get(1).getAvailability());
        service.getResetAvailabilityMeetingRoom();

        service.getAvailabilityMeetingRoom("31/12/20", "22h46", 45);
        assertFalse(service.getMeetingRoom().get(1).getAvailability());
        service.getResetAvailabilityMeetingRoom();
    }
}