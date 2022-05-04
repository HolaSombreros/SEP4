package com.dai.dao.area;

import com.dai.shared.Area;
import com.dai.shared.Barn;

import java.util.List;
import java.util.concurrent.Future;

public interface AreaDao {
    Future<Area> create(Area area);
    Future<Area> read(int id);
    Area update(Area area);
    void delete(int id);
    Future<List<Area>> getAll();
}
