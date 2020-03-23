package mareu.adriansng.maru;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.DummyReunionList;
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
        List<Reunion> expectedReunions = DummyReunionList.DUMMY_REUNION;
        assertEquals(reunions,expectedReunions);
    }

    @Test
    public void addReunionList(){
                service.getReunions().clear();
                service.addReunion(Reunion.newReunion(service.getReunionSize()+1,1,"Arnaud","15h00","05/02/2020",service.getPersonParticipant()));
                Reunion newReunion = service.getReunions().get(0);
                assertEquals(1,service.getReunions().size());
                assertTrue(DummyReunionList.DUMMY_REUNION.stream().map(Reunion::getIdMeetingRoom).collect(Collectors.toList()).contains(newReunion.getIdMeetingRoom()));
                assertTrue(DummyReunionList.DUMMY_REUNION.stream().map(Reunion::getHour).collect(Collectors.toList()).contains(newReunion.getHour()));
                assertTrue(DummyReunionList.DUMMY_REUNION.stream().map(Reunion::getDate).collect(Collectors.toList()).contains(newReunion.getDate()));
                assertTrue(DummyReunionList.DUMMY_REUNION.stream().map(Reunion::getNameOrganizer).collect(Collectors.toList()).contains(newReunion.getNameOrganizer()));
                assertTrue(DummyReunionList.DUMMY_REUNION.stream().map(Reunion::getPersonParticipant).collect(Collectors.toList()).contains(newReunion.getPersonParticipant()));
    }

    @Test
    public void deleteReunionList(){
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }
}