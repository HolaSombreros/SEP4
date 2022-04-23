package com.dai.model.barns;

import com.dai.shared.Barn;

import java.util.List;

public interface BarnModel {
    List<Barn> getAll() throws Exception;
    Barn create(Barn barn) throws Exception;
}
