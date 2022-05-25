package com.dai.service.areas;

import com.dai.model.Area;

import java.util.List;

public interface AreasService {
    Area create(Area area) throws Exception;
    Area read(int id) throws Exception;
    List<Area> readAll();

    Area update(Area area);

    Area delete(int id);
}
