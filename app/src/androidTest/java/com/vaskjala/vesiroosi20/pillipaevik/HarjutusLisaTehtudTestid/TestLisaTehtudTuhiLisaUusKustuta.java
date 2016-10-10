package com.vaskjala.vesiroosi20.pillipaevik.HarjutusLisaTehtudTestid;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import com.vaskjala.vesiroosi20.pillipaevik.PeaActivity;
import com.vaskjala.vesiroosi20.pillipaevik.R;
import com.vaskjala.vesiroosi20.pillipaevik.TestTooriistad;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by mihkel on 1.10.2016.
 */
public class TestLisaTehtudTuhiLisaUusKustuta {
    @Rule
    public ActivityTestRule<PeaActivity> mActivityRule = new ActivityTestRule(
            PeaActivity.class);

    @Before
    public void Seadista_Test() {
        TestTooriistad.MultiFragmentTuvastus(mActivityRule);
    }

    @Test
    public void TestLisaTehtudTuhiLisaUusKustuta() {
        if (TestTooriistad.OnMultiFragment()) {


            Context context = InstrumentationRegistry.getTargetContext();
            Resources resources = context.getResources();

            onView(withId(R.id.harjutua_list)).
                    perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(resources.getString(R.string.test_teos3_nimi))), click()).atPosition(0));
            onView(withId(R.id.lisatehtud)).perform(click());
            onView(withId(R.id.alustauut)).perform(click());
            onView(withId(R.id.harjutusekirjeldus))
                    .perform(ViewActions.replaceText(resources.getString(R.string.test_h_ei_salvestu)), ViewActions.closeSoftKeyboard());

            onView(withId(R.id.mikrofoniluliti)).perform(click());
            onView(withId(R.id.kaivitataimernupp)).perform(click());
            TestTooriistad.Oota(3*1000);

            onView(withId(R.id.kustutaharjutus)).perform(click());
            onView(withId(android.R.id.button1)).perform(click());

            onView(withId(R.id.harjutuslist))
                    .check(ViewAssertions.
                            matches(hasDescendant(allOf(withId(R.id.harjutuslist_harjutusekirjeldus), withText("")))));
            onView(Matchers.allOf(withId(R.id.harjutuslistrida), hasDescendant(withText(resources.getString(R.string.test_h_ei_salvestu)))))
                    .check(ViewAssertions.doesNotExist());

            TestTooriistad.KeeraVasakule();

            onView(withId(R.id.harjutusekirjeldus)).check(ViewAssertions.matches(withText("")));
            onView(withId(R.id.harjutuseandmed)).check(ViewAssertions.matches(isDisplayed()));

            onView(withId(R.id.harjutua_list)).
                    perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(resources.getString(R.string.test_teos3_nimi))), click()).atPosition(0));
            onView(withId(R.id.harjutusekirjeldus)).check(ViewAssertions.matches(withText("")));
            onView(withId(R.id.harjutuseandmed)).check(ViewAssertions.matches(isDisplayed()));

            TestTooriistad.KeeraParemale();

            onView(Matchers.allOf(withId(R.id.harjutuslistrida), hasDescendant(withText("")))).perform(click());
            onView(withId(R.id.harjutusekirjeldus)).check(ViewAssertions.matches(withText("")));
            onView(withId(R.id.harjutuseandmed)).check(ViewAssertions.matches(isDisplayed()));

            onView(withId(R.id.harjutua_list)).
                    perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(resources.getString(R.string.test_teos4_nimi))), click()).atPosition(0));
            onView(withId(R.id.harjutua_list)).
                    perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(resources.getString(R.string.test_teos3_nimi))), click()).atPosition(0));

            onView(Matchers.allOf(withId(R.id.harjutuslistrida), hasDescendant(withText(""))))
                    .check(ViewAssertions.doesNotExist());

            onView(withId(R.id.HarjutusTabel)).check(ViewAssertions.matches(isDisplayed()));
        }
    }
}
