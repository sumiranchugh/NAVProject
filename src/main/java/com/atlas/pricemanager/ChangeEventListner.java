package com.atlas.pricemanager;

import com.atlas.events.Event;


/**
 * Created by schug2 on 11/17/2015.
 */
public interface ChangeEventListner<T extends Event> {



    void processChange(T event);
}
