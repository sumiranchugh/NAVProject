package com.atlas.events.eventpublisher;

import com.atlas.events.Event;
import com.atlas.pricemanager.ChangeEventListner;

/**
 * Created by schug2 on 11/18/2015.
 */
public interface Publisher<T extends ChangeEventListner<? extends  Event>, E extends  Event> {

    void attach(T t);
    void publish();
      void setEvent(E e);
}
