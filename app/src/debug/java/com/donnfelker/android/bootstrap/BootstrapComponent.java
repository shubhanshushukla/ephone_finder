package com.experiment.smart.finder;

import com.experiment.smart.finder.ui.BootstrapActivity;
 import com.experiment.smart.finder.ui.MainActivity;

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
