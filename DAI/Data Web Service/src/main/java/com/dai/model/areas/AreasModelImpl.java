package com.dai.model.areas;

import com.dai.shared.Area;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AreasModelImpl implements AreasModel {



    @Override
    public Future<Area> create(Area area) {
        return null;
    }

    @Override
    public Future<Area> read(int id) {
        return null;
    }
}
