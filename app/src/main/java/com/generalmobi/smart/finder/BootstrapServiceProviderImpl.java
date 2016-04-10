
package com.generalmobi.smart.finder;

import android.accounts.AccountsException;
import android.app.Activity;


import com.generalmobi.smart.finder.core.BootstrapService;

import java.io.IOException;

import retrofit.RestAdapter;

/**

 */
public class BootstrapServiceProviderImpl implements BootstrapServiceProvider {

    private RestAdapter restAdapter;
     public BootstrapServiceProviderImpl(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
     }

    /**
     * Get service for configured key provider
     * <p/>
     * This method gets an auth key and so it blocks and shouldn't be called on the main thread.
     *
     * @return bootstrap service
     * @throws IOException
     * @throws AccountsException
     */
    @Override
    public BootstrapService getService(final Activity activity)
            throws IOException, AccountsException {
        // The call to keyProvider.getAuthKey(...) is what initiates the login screen. Call that now.

        return new BootstrapService(restAdapter);
    }
}
