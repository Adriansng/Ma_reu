package mareu.adriansng.maru;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.ListReunionActivity;
import mareu.adriansng.maru.utils.DeleteViewAction;
import mareu.adriansng.maru.utils.SpinnerSelect;
import mareu.adriansng.maru.utils.TimeAndDataPicker;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static mareu.adriansng.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.anything;
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
     * When we add and delete an item, the item is no more shown
     */

    @Test
    public void AddingAndDeleteReunion() {

        int ITEMS_COUNT = 3;
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.list_reunion_add_btn)).perform(click());

         // Given: We launch AddReunionActivity
        //Calendar ITEM_HOUR_DATE= 1579512600000;
        String ITEM_ORGANIZER = "Name";
        String ITEM_SUBJECT= "Subject";
        // Date reunion
        TimeAndDataPicker.setDate(R.id.add_date_btn,2020,5,25);
        // Hour reunion
        TimeAndDataPicker.setTime(R.id.add_hour_btn,10,15);
        // Name organizer
        onView(allOf(withId(R.id.add_name_organizer_edit_txt), isDisplayed())).perform(replaceText(ITEM_ORGANIZER));
        // Subject
        onView(allOf(withId(R.id.add_subject_reunion_edit), isDisplayed())).perform(replaceText(ITEM_SUBJECT)); // EditText info reunion
        // Room
        SpinnerSelect.setSpinner(R.id.add_roomReunion_spinner,2); // "Meeting Room B" selected
        // Validate reunion

        onView(withId(R.id.add_validate_btn)).check(matches(allOf(isEnabled(), isClickable()))).perform(
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return isEnabled(); // no constraints, they are checked above
                    }

                    @Override
                    public String getDescription() {
                        return "click plus button";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }
                }
        );
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT+1));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, new DeleteViewAction()));
        // Then : the number of element is 2
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }


    @Test
    public void FilterDateReunion() {
        // 3 reunions in the list, one of which is dated 5/25/20
        int ITEMS_COUNT = 3;
        // Check list with two meetings that have two identical dates
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        // Date
        TimeAndDataPicker.setDate(R.id.filter_date_btn,2020,5,25);
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check number is 1
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void FilterRoomReunion() {
        // 3 reunions in the list,one of which happens in room B
        int ITEMS_COUNT = 3;
        // Check list with two meetings that have two identical meeting room
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        // Room
        SpinnerSelect.setSpinnerDialog(R.id.filter_room_spinner, 2);
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void FilterRoomAndDateReunion() {
        int ITEMS_COUNT = 3;
        // Check list with two meetings that have two identical meeting room and date
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        //Date
        TimeAndDataPicker.setDate(R.id.filter_date_btn,2020,5,12);
        //Room
        SpinnerSelect.setSpinnerDialog(R.id.filter_room_spinner,2);
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void FilterResetReunion() {
        // 3 reunions in the list, one of which is dated 5/25/20
        int ITEMS_COUNT = 3;
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(withId(R.id.action_filter)).perform(click());
        TimeAndDataPicker.setDate(R.id.filter_date_btn,2020,5,25);
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        // Check
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT-2));
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        onView(allOf(withId(R.id.action_filter),isDisplayed())).perform(click());
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        // Check reset list
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
    }
}
