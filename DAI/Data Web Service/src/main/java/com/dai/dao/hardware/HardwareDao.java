package com.dai.dao.hardware;

import com.dai.shared.Barn;
import com.dai.shared.Hardware;

import java.util.concurrent.Future;

public interface HardwareDao {
    Future<Hardware> create(int portNumber);
    Future<Hardware> read(int id);
    Hardware update(Hardware hardware);
    void delete(int id);
}
