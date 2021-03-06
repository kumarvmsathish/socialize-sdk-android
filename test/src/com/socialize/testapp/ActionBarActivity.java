package com.socialize.testapp;

import android.os.Bundle;
import android.view.View;
import com.socialize.ActionBarUtils;
import com.socialize.Socialize;
import com.socialize.entity.Entity;
import com.socialize.test.R;
import com.socialize.ui.actionbar.ActionBarOptions;

import java.util.concurrent.CountDownLatch;

public class ActionBarActivity extends EmptyActivity {

    public final CountDownLatch launchLock = new CountDownLatch(1);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Socialize.onCreate(this, savedInstanceState);

        Bundle extras = this.getIntent().getExtras();

        if(extras != null) {
            ActionBarOptions options = new ActionBarOptions();
            options.setAddScrollView(true);

            Socialize.getSocialize().setEntityLoader(new EntityLoader());

            View actionBarWrapped = ActionBarUtils.showActionBar(this, R.layout.action_bar_auto, (Entity)extras.get(Socialize.ENTITY_OBJECT), options);

            setContentView(actionBarWrapped);
        }

        launchLock.countDown();
    }

    @Override
    protected void onDestroy() {
        Socialize.onDestroy(this);
        super.onDestroy();
    }
}