package com.generalmobi.smart.finder;

import android.accounts.AccountsException;
import android.app.Activity;

 import com.generalmobi.smart.finder.core.BootstrapService;

import java.io.IOException;

public interface BootstrapServiceProvider {
    BootstrapService getService(Activity activity) throws IOException, AccountsException;
}
