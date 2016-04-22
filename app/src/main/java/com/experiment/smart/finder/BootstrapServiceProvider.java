package com.experiment.smart.finder;

import android.accounts.AccountsException;
import android.app.Activity;

 import com.experiment.smart.finder.core.BootstrapService;

import java.io.IOException;

public interface BootstrapServiceProvider {
    BootstrapService getService(Activity activity) throws IOException, AccountsException;
}
