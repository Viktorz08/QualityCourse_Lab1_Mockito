package com.hotmail.viktorz08.lab1b.repository;

import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;

/**
 * Created by Victor on 12/2/2014.
 */
public class TimeWeaverClassLoader extends SimpleLoadTimeWeaver {

    public TimeWeaverClassLoader() {
        super();
    }

    @Override
    public ClassLoader getInstrumentableClassLoader() {
        return super.getInstrumentableClassLoader().getParent();
    }
}
