package com.dangerlibrary.citrine.server.resource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class UnitApplication extends Application {
    public UnitApplication() {}

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> set = new HashSet<Object>();
        set.add(new UnitResource());
        return set;
    }
}

