package com.dai.dao.area;

import com.dai.model.Area;

import java.util.List;
import java.util.concurrent.Future;

public interface AreaDao {
    Future<Area> create(Area area);
    Future<Area> read(int id);
    Future<Area> update(Area area);
    Future<Area> readByHardwareId(String id);
    Future<List<Area>> readAll();
    Future<Area> readByNameAndBarn(String name, int barnId);
    Future<Area> delete(int id);
}
