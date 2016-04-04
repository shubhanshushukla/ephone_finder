package com.generalmobi.smart.helmet;

import android.accounts.AccountsException;
import android.app.Activity;

 import com.generalmobi.smart.helmet.core.BootstrapService;

import java.io.IOException;

public interface BootstrapServiceProvider {
    BootstrapService getService(Activity activity) throws IOException, AccountsException;
}
