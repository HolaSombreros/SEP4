package com.dai.dao.area;

import com.dai.shared.Area;
import com.dai.shared.User;

import java.util.List;
import java.util.concurrent.Future;

public interface AreaDao {
    Future<Area> create(Area area);
    Future<Area> read(int id);
    Future<Area> update(Area area);
    Future<Area> getAreaByHardwareId(String id);
    Future<List<Area>> getAll();
    Future<Area> readByNameAndBarn(String name, int barnId);
    Future<Area> delete(int id);
}
