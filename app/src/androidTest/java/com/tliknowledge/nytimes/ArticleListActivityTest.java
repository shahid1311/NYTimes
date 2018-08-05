package com.tliknowledge.nytimes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tliknowledge.nytimes.ui.activities.ArticleListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Shahid
 */

@RunWith(AndroidJUnit4.class)
public class ArticleListActivityTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityRule = new ActivityTestRule<>(ArticleListActivity.class);

    @Test
    public void checkViewsDisplay() {

        onView(withId(R.id.swipe_refresh_articles))
                .check(matches(isDisplayed()));

        onView(withId(R.id.rv_articles))
                .check(matches(isDisplayed()));

        onView(withId(R.id.action_filter))
                .check(matches(isDisplayed()));

        //The following code will fail as the data is yet not fetched. Add a delay and then try
        /*onView(withId(R.id.tv_published_date))
                .check(matches(isDisplayed()));*/
    }
}
