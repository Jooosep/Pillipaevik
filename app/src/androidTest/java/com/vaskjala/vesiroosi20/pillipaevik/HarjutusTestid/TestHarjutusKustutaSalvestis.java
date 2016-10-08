package com.vaskjala.vesiroosi20.pillipaevik.HarjutusTestid;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import com.vaskjala.vesiroosi20.pillipaevik.PeaActivity;
import com.vaskjala.vesiroosi20.pillipaevik.R;
import com.vaskjala.vesiroosi20.pillipaevik.TestTooriistad;
import com.vaskjala.vesiroosi20.pillipaevik.teenused.Tooriistad;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by mihkel on 1.10.2016.
 */
public class TestHarjutusKustutaSalvestis {
    @Rule
    public ActivityTestRule<PeaActivity> mActivityRule = new ActivityTestRule(
            PeaActivity.class);

    @Before
    public void Seadista_Test() {
        TestTooriistad.MultiFragmentTuvastus(mActivityRule);
    }

    @Test
    public void TestKustutaSalvestis() {
        Context context = InstrumentationRegistry.getTargetContext();
        Resources resources = context.getResources();

        onView(withId(R.id.harjutua_list)).
                perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(resources.getString(R.string.test_teos4_nimi))), click()).atPosition(0));
        onView(allOf(withId(R.id.harjutuslistrida), hasDescendant(withText(R.string.vaikimisisharjutusekirjeldus)))).
                check(ViewAssertions.
                        matches(hasDescendant(allOf(withId(R.id.harjutuslisti_pilt), withEffectiveVisibility(Visibility.VISIBLE)))));

        onView(allOf(withId(R.id.harjutuslistrida), hasDescendant(withText(R.string.vaikimisisharjutusekirjeldus)))).perform(click());
        onView(withId(R.id.SalvestuseRiba)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(withId(R.id.kustutasalvestus)).perform(click());
        onView(withId(android.R.id.button2)).perform(click());
        onView(withId(R.id.kustutasalvestus)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        TestTooriistad.Oota(100);
        onView(withId(R.id.SalvestuseRiba)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)));;
        TestTooriistad.VajutaTagasiKui1Fragment();
        onView(allOf(withId(R.id.harjutuslistrida), hasDescendant(withText(R.string.vaikimisisharjutusekirjeldus)))).
                check(ViewAssertions.
                        matches(hasDescendant(allOf(withId(R.id.harjutuslisti_pilt), withEffectiveVisibility(Visibility.GONE)))));

    }
}