package com.generalmobi.smart.helmet;


import com.generalmobi.smart.helmet.ui.BootstrapActivity;
import com.generalmobi.smart.helmet.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AndroidModule.class,
                BootstrapModule.class
        }
)
public interface BootstrapComponent {

    void inject(BootstrapApplication target);
    void inject(MainActivity target);
    void inject(BootstrapActivity target);


}
