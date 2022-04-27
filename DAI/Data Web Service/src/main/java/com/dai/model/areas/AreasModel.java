package com.dai.model.areas;

import com.dai.shared.Area;

import java.util.List;
import java.util.concurrent.Future;

public interface AreasModel {
    Future<Area> create(Area area);
    Future<Area> read(int id);
    List<Area> getAll();

}
