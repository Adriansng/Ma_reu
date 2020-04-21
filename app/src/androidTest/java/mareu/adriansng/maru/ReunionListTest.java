package mareu.adriansng.maru;

import android.content.Context;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.ListReunionActivity;
import mareu.adriansng.maru.utils.ChangeText;
import mareu.adriansng.maru.utils.DeleteViewAction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static mareu.adriansng.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ReunionListTest {

    private ReunionApiService service;

    @Rule
    public ActivityTestRule<ListReunionActivity> mActivityRule =
            new ActivityTestRule<>(ListReunionActivity.class);

    @Before
    public void setUp() {
        ListReunionActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();

        assertEquals("mareu.adriansng.maru", appContext.getPackageName());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */

    @Test
    public void reunionList() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void DeleteReunion() {
        // Given : We remove the element at position 1
        // This is fixed
        int ITEMS_COUNT = 3;
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of element is 2
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void AddingReunion() {

        int ITEMS_COUNT = 3;
        final Reunion reunion1 = new Reunion(1, 1, "name1", "11h00", "date1", service.getPersonParticipant(), "");
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        service.addReunion(reunion1);
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT+1));


        //**
        // Given: We launch AddReunionActivity
        // This is fixed
       // int ITEMS_COUNT = 3;
        //Calendar ITEM_HOUR_DATE= 1579512600000;
       // String ITEM_ORGANIZER = "Name";
       // String ITEM_SUBJECT= "Subject";
        //String ITEM_DATE="01/01/01";
       // String ITEM_HOUR="10H00";
        // When perform a click on launch activity
        //onView(withId(R.id.list_reunion_add_btn)).perform(click());
        // Date reunion
        //onView(allOf(withId(R.id.add_date_txt), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_DATE));
        // Hour reunion
        //onView(allOf(withId(R.id.add_hour_txt), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_HOUR));
        // Name organizer
        //onView(allOf(withId(R.id.add_name_organizer_edit_txt), isDisplayed())).perform(replaceText(ITEM_ORGANIZER));
        // Subject
        //onView(allOf(withId(R.id.add_subject_reunion_edit), isDisplayed())).perform(replaceText(ITEM_SUBJECT)); // EditText info reunion
        // Room
        //onView(allOf(withId(R.id.add_roomReunion_spinner), isDisplayed())).perform(click());
        //onView(allOf(withId(R.id.item_spinner_room_txt),isDisplayed() withText("Meeting Room A"))).perform(click());
        // Validate reunion
        //onView(allOf(withId(R.id.add_validate_btn), isDisplayed())).perform(click());
        // Then: the number of element is 4
        //onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void FilterDateReunion() {
        // 3 reunions in the list, one of which is dated 5/25/20
        int ITEMS_COUNT = 3;
        String ITEM_DATE="5/25/20";
        // Check list with two meetings that have two identical dates
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        // Date choose
        onView(allOf(withId(R.id.filter_date_btn), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_DATE));
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check number is 2
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void FilterRoomReunion() {
        // 3 reunions in the list,one of which happens in room H (int 8)
        int ITEMS_COUNT = 3;
        // Check list with two meetings that have two identical meeting room
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        // Room choose
        service.getFilterMeetingRoom(5);
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void FilterRoomAndDateReunion() {
        int ITEMS_COUNT = 3;
        String ITEM_DATE="5/25/20";
        final Reunion reunion1 = new Reunion(1, 1, "name1", "10h30", "02/01/01", service.getPersonParticipant(), "");
        final Reunion reunion2 = new Reunion(2, 5, "name2", "11h00", "01/01/01", service.getPersonParticipant(), "");
        final Reunion reunion3 = new Reunion(3, 5, "name3", "12h00", "01/01/01", service.getPersonParticipant(), "");

        service.getReunions().clear();
        service.addReunion(reunion1);
        service.addReunion(reunion2);
        service.addReunion(reunion3);
        // Check list with two meetings that have two identical meeting room and date
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        //Date and room choose
        onView(allOf(withId(R.id.filter_date_btn), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_DATE));
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void FilterResetReunion() {
        // 3 reunions in the list, one of which is dated 5/25/20
        int ITEMS_COUNT = 3;
        String ITEM_DATE="5/25/20";
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(withId(R.id.action_filter)).perform(click());
        onView(allOf(withId(R.id.filter_date_txt), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_DATE));
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        // Check reset list
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));

    }
}
