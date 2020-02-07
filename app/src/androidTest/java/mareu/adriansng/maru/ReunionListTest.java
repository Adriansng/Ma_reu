package mareu.adriansng.maru;

import android.content.Context;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import mareu.adriansng.maru.ui_reunion_list.ListReunionActivity;
import mareu.adriansng.maru.utils.DeleteViewAction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("mareu.adriansng.maru", appContext.getPackageName());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */

    @Test
    public void reunionList() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_reunions),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void DeleteReunion() {
        // Given : We remove the element at position 1
        // This is fixed
        int ITEMS_COUNT = 1;
        onView(allOf(withId(R.id.list_reunions),isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_reunions),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_reunions),isDisplayed())).check(withItemCount(ITEMS_COUNT -1));
    }

    @Test
    public void AddingReunion() {
        // Given: We launch AddReunionActivity
        // This is fixed
        int ITEMS_COUNT=1;
        String ITEM_MEETING_ROOM= "Reunion A";
        //Calendar ITEM_HOUR_DATE= 1579512600000;
        String ITEM_ORGANIZER="Pascal";
        List<String> ITEM_ADDRESS_MAIL= Arrays.asList("address mail 1","address mail 2","address mail 3");
        // When perform a click on launch activity
        onView(withId(R.id.add_reunion_button)).perform(click());
        // EditText info reunion
        onView(allOf(withId(R.id.info_meeting_room_edit),isDisplayed()).perform(replaceText(ITEM_MEETING_ROOM)));
        //onView(allOf(withId(R.id.info_hour_date_edit),isDisplayed()).perform(replaceText(ITEM_HOUR_DATE)));
        onView(allOf(withId(R.id.info_organizer_edit),isDisplayed()).perform(replaceText(ITEM_ORGANIZER)));
        onView(allOf(withId(R.id.info_address_mail_edit),isDisplayed()).perform(replaceText(String.valueOf(ITEM_ADDRESS_MAIL))));
        // Validate reunion
        onView(allOf(withId(R.id.validate_reunion_btn),isDisplayed()).perform(click()));
        // Then: the number of element is 2
        onView(allOf(withId(R.id.list_reunions),isDisplayed())).check(withItemCount(ITEMS_COUNT +1));
        // Then item meeting room
        onView(allOf(withId(R.id.item_list_reunion_number),withId(1))).check(matches(withText(ITEM_MEETING_ROOM)));
        // Then item hour and date
        //onView(allOf(withId(R.id.item_list_reunion_number),withId(1))).check(matches(withText(ITEM_HOUR_DATE)));
        // Then item organizer
        onView(allOf(withId(R.id.item_list_reunion_number),withId(1))).check(matches(withText(ITEM_ORGANIZER)));
        // Then item address mail
        onView(allOf(withId(R.id.item_list_reunion_mail),withId(1))).check(matches(withText(String.valueOf(ITEM_ADDRESS_MAIL))));
    }




}
