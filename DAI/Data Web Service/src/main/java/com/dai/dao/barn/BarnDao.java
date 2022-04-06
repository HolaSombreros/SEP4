package com.dai.dao.barn;

import com.dai.shared.Area;
import com.dai.shared.Barn;

import java.util.concurrent.Future;

public interface BarnDao {
    Future<Barn> create(String name);
    Future<Barn> read(int id);
    Barn update(Barn barn);
    void delete(int id);
}
