package mareu.adriansng.maru.service_api;

import java.util.ArrayList;
import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

public class DummyReunionApiService implements ReunionApiService {

    private final List<Reunion> reunions = DummyReunionList.generateReunion();
    private final List<Reunion> filterReunions = new ArrayList<>();
    private final List<MeetingRoom> meetingRoom = DummyReunionList.DUMMY_MEETING_ROOM;
    private int idMeetingRoom;
    private final List<MeetingRoom> availabilityMeetingRoom = new ArrayList<>();
    private final List<Person> personList = DummyReunionList.DUMMY_PERSON;

    // REUNION LIST
    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    // ID REUNION
    @Override
    public int getReunionSize() {
        return reunions.size() - 1;
    }

    @Override
    public Reunion getReunion(int id) {
        return reunions.get(id);
    }

    // MEETING ROOM
    @Override
    public String getNameMeetingRome(int idMeetingRoom) {
        return meetingRoom.get(idMeetingRoom).getNameRoom();
    }

    @Override
    public int getColorAvatarMeetingRoom(int idMeetingRoom) {
        return meetingRoom.get(idMeetingRoom).getColorAvatar();
    }

    @Override
    public List<MeetingRoom> getMeetingRoom() {
        return meetingRoom;
    }

    // LIST PERSON
    @Override
    public List<Person> getPersonParticipant() {
        return personList;
    }

    // FILTER
    @Override
    public void getFilterMeetingRoom(int idMeetingRoomFilter) {
        filterReunions.clear();
        for (Reunion n : reunions) {
            if (n.getIdMeetingRoom() == idMeetingRoomFilter) {
                filterReunions.add(n);
            }
        }
    }

    @Override
    public void getFilterDate(String dateReunion) {
        filterReunions.clear();
        for (Reunion n : reunions) {
            if (n.getDate().equals(dateReunion)) {
                filterReunions.add(n);
            }
        }
    }

    @Override
    public void getFilterMeetingAndDate(int idMeetingRoomFilter, String dateReunion) {
        filterReunions.clear();
        for (Reunion n : reunions) {
            if (n.getIdMeetingRoom() == idMeetingRoomFilter && n.getDate().equals(dateReunion)) {
                filterReunions.add(n);
            }
        }
    }

    @Override
    public List<Reunion> getFilterReunions() {
        return filterReunions;
    }

    // AVAILABILITY
    @Override
    public List<MeetingRoom> getListMeetingRoomAvailability() {
        availabilityMeetingRoom.clear();
        for (MeetingRoom meetingRoom : meetingRoom) {
            if (meetingRoom.getAvailability()) {
                availabilityMeetingRoom.add(meetingRoom);
            }
        }
        return availabilityMeetingRoom;
    }

    @Override
    public void getAvailabilityMeetingRoom(String date, String hour, int nbMinute) {
            String startDate = DateUtils.formatTimeDate( date , hour);
            long startDateLong = DateUtils.formatTimeLong(DateUtils.addMinute(startDate,-nbMinute));
            long endDateLong = DateUtils.formatTimeLong(DateUtils.addMinute(startDate,nbMinute));
            for (Reunion reunion : reunions) {
                String timeReunion = DateUtils.formatTimeDate(reunion.getDate(), reunion.getHour());
                long timeReunionLong = DateUtils.formatTimeLong(timeReunion);
                if (timeReunionLong > startDateLong  && timeReunionLong < endDateLong  || startDateLong == timeReunionLong || endDateLong== timeReunionLong ) {
                    idMeetingRoom = reunion.getIdMeetingRoom();
                    meetingRoom.get(idMeetingRoom).setAvailability(false);
                }
            }
    }

    @Override
    public void getResetAvailabilityMeetingRoom() {
        for (Reunion reunion : reunions) {
            idMeetingRoom = reunion.getIdMeetingRoom();
            meetingRoom.get(idMeetingRoom).setAvailability(true);
        }
    }

    // ACTIONS
    @Override
    public void addReunion(Reunion newReunion) {
        reunions.add(newReunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }
}
