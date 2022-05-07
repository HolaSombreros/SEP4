package com.dai.dao.barn;

import com.dai.shared.Barn;

import java.util.List;
import java.util.concurrent.Future;

public interface BarnDao {
    Future<Barn> create(Barn name);
    Future<Barn> read(int id);
    Future<Barn> update(Barn barn);
    Future<Barn> delete(int id);
    Future<List<Barn>> getAll();
}
