package mareu.adriansng.maru;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.service_api.ReunionApiService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ReunionServiceTest {
    private ReunionApiService service;

    @Before
    public void setup(){service = DI.getNewInstanceApiService();}

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = ListReunionGenerator.LIST_REUNIONS;
        assertEquals(reunions,expectedReunions);
    }

    @Test
    public void addReunionList(){
                service.getReunions().clear();
                service.addReunion(Reunion.add());
                Reunion newReunion = service.getReunions().get(0);
                assertEquals(1,service.getReunions().size());
                assertTrue(ListReunionGenerator.LIST_REUNIONS.stream().map(Reunion::getMeetingRoom).collect(Collection.Tolist()).contains(newReunion.getMeetingRoom()));
                assertTrue(ListReunionGenerator.LIST_REUNIONS.stream().map(Reunion::getMeetingHourDay).collect(Collection.Tolist()).contains(newReunion.getMeetingHourDay()));
                assertTrue(ListReunionGenerator.LIST_REUNIONS.stream().map(Reunion::getMeetingOrganizer).collect(Collection.Tolist()).contains(newReunion.getMeetingOrganizer()));
                assertTrue(ListReunionGenerator.LIST_REUNIONS.stream().map(Reunion::getMeetingParticipants).collect(Collection.Tolist()).contains(newReunion.getMeetingParticipants()));
    }

    @Test
    public void deleteReunionList(){
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    @Test
    public void detailReunion(){
        Reunion reunion= service.getReunions().get(0);
        reunion.setMeetingRoom("A");
        reunion.setMeetingHourDay("20200120_195945");
        reunion.setMeetingOrganizer("Pascal");
        reunion.setMeetingParticipant("addressmail1","addressmail2","adressmail3");
        assertEquals("A",reunion.getMeetingRoom());
        assertEquals("20200120_195945",reunion.getMeetingHourDate());
        assertEquals("Pascal",reunion.getMeetingOrganizer());
        assertEquals("addressmail1","addressmail2","adressmail3",reunion.getMeetingParticipants());
    }

}