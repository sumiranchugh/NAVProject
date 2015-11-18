package com.atlas.events;

import com.atlas.pricemanager.ChangeEventListner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by schug2 on 11/16/2015.
 */
public class HoldingChangeEvent implements Event {

    private String source;
    private String destination;
    private EventType eventType;

    Set<ChangeEventListner<HoldingChangeEvent>> set = new HashSet<>();

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String getSource() {
        return null;
    }

    @Override
    public String getDestination() {
        return null;
    }

    @Override
    public EventType getType() {
        return eventType;
    }


}
