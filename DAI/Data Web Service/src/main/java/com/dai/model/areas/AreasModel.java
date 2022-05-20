package com.dai.model.areas;

import com.dai.shared.Area;

import java.util.List;
import java.util.concurrent.Future;

public interface AreasModel {
    Area create(Area area) throws Exception;
    Area read(int id) throws Exception;
    List<Area> getAll();

    Area update(Area area);

    Area delete(int id);
}
