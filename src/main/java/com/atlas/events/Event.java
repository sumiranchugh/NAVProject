package com.atlas.events;

import com.atlas.pricemanager.ChangeEventListner;

import java.io.Serializable;

/**
 * Created by schug2 on 11/16/2015.
 */
public interface Event extends Serializable {



    String getSource();

    String getDestination();

    EventType getType();

}
