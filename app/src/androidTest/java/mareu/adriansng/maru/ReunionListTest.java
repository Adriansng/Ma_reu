package mareu.adriansng.maru;

import android.content.Context;
import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mareu.adriansng.maru.ui_reunion_list.ListReunionActivity;
import mareu.adriansng.maru.utils.ChangeText;
import mareu.adriansng.maru.utils.DeleteViewAction;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static mareu.adriansng.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
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
        // Given: We launch AddReunionActivity
        // This is fixed
        int ITEMS_COUNT = 3;
        //Calendar ITEM_HOUR_DATE= 1579512600000;
        String ITEM_ORGANIZER = "Name";
        String ITEM_SUBJECT= "Subject";
        String ITEM_DATE="01/01/01";
        String ITEM_HOUR="10H00";
        // When perform a click on launch activity
        onView(withId(R.id.list_reunion_add_btn)).perform(click());
        // Date reunion
        onView(allOf(withId(R.id.add_date_txt), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_DATE));
        // Hour reunion
        onView(allOf(withId(R.id.add_hour_txt), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_HOUR));
        // Name organizer
        onView(allOf(withId(R.id.add_name_organizer_edit_txt), isDisplayed())).perform(replaceText(ITEM_ORGANIZER));
        // Subject
        onView(allOf(withId(R.id.add_subject_reunion_edit), isDisplayed())).perform(replaceText(ITEM_SUBJECT)); // EditText info reunion
        // Room
        onView(allOf(withId(R.id.add_roomReunion_spinner), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        // Validate reunion
        onView(allOf(withId(R.id.add_validate_btn), isDisplayed())).perform(click());
        // Then: the number of element is 4
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void FilterDateReunion() {
        int ITEMS_COUNT = 3;
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        // Click DatePicker
        onView(allOf(withId(R.id.add_date_btn),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 5, 25));
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check number is 1
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void FilterRoomReunion() {
        int ITEMS_COUNT = 3;
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        // Click DatePicker
        onView(allOf(withId(R.id.add_roomReunion_spinner), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
        onView(allOf(withId(R.id.filter_validate_btn), isDisplayed())).perform(click());
        //Check number is 1
        onView(allOf(withId(R.id.list_reunion_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void FilterResetReunion() {
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
       onView(allOf(withId(R.id.filter_cancel_btn), isDisplayed())).perform(click());
    }
}
