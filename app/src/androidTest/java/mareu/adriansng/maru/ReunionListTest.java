package mareu.adriansng.maru;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import mareu.adriansng.maru.ui_reunion_list.ListReunionActivity;
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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
        onView(allOf(withId(R.id.list_reunions), isDisplayed()))
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
        onView(allOf(withId(R.id.list_reunions), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_reunions), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of element is 2
        onView(allOf(withId(R.id.list_reunions), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void AddingReunion() throws  Exception {
        // Given: We launch AddReunionActivity
        // This is fixed
        int ITEMS_COUNT = 3;
        //Calendar ITEM_HOUR_DATE= 1579512600000;
        String ITEM_ORGANIZER = "Pascal";
        List<String> ITEM_ADDRESS_MAIL = Arrays.asList("address mail 1", "address mail 2", "address mail 3");
        // When perform a click on launch activity
        onView(withId(R.id.add_reunion_button)).perform(click());
        // Date reunion
        onView(allOf(withId(R.id.date_add),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 1, 24));
        // Hour reunion
        onView(allOf(withId(R.id.hour_add),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 30));
        // Name organizer
        onView(allOf(withId(R.id.name_organizer), isDisplayed())).perform(replaceText(ITEM_ORGANIZER));
        //onView(allOf(withId(R.id.info_address_mail_edit), isDisplayed()).perform(replaceText(String.valueOf(ITEM_ADDRESS_MAIL))));
        // EditText info reunion
        onView(allOf(withId(R.id.roomReunion), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        // Validate reunion
        onView(allOf(withId(R.id.validate_btn), isDisplayed())).perform(click());
        // Then: the number of element is 4
        onView(allOf(withId(R.id.list_reunions), isDisplayed())).check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void FilterDateReunion() {
        int ITEMS_COUNT = 3;
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        // Click DatePicker
        onView(allOf(withId(R.id.date_add),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 5, 25));
        onView(allOf(withId(R.id.validate_btn_popup), isDisplayed())).perform(click());
        //Check number is 1
        onView(allOf(withId(R.id.list_reunions), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void FilterRoomReunion() {
        int ITEMS_COUNT = 3;
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        // Click DatePicker
        onView(allOf(withId(R.id.roomReunion), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
        onView(allOf(withId(R.id.validate_btn_popup), isDisplayed())).perform(click());
        //Check number is 1
        onView(allOf(withId(R.id.list_reunions), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void FilterResetReunion() {
        // Open the overflow menu OR open the options menu,
        // depending on if the device has a hardware or software overflow menu button.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
       onView(allOf(withId(R.id.cancel_btn_popup), isDisplayed())).perform(click());
    }
}
