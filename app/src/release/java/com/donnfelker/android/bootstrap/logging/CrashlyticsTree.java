package com.experiment.smart.finder.logging;

import android.util.Log;


import timber.log.Timber;

/**
 * A logging implementation which reports 'info', 'warning', and 'error' logs to Crashlytics.
 */
public class CrashlyticsTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }


        if (t != null) {
            if (priority == Log.ERROR) {
             }
        }

    }
}