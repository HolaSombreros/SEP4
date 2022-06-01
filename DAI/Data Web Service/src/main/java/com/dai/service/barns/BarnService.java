package com.dai.service.barns;

import com.dai.model.Barn;

import java.util.List;

public interface BarnService {
    List<Barn> readAll() throws Exception;
    Barn create(Barn barn) throws Exception;
}
