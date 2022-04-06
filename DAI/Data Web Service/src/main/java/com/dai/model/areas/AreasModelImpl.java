package com.dai.model.areas;

import com.dai.dao.area.AreaDao;
import com.dai.shared.Area;
import com.dai.shared.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AreasModelImpl implements AreasModel {

    private AreaDao areasDao;
    //private BarnDao barnDao;

    @Autowired
    public AreasModelImpl(AreaDao areasDao ) {
        this.areasDao = areasDao;
    }

    @Override
    public Future<Area> create(Area area) {
        //search for the barn
        Barn barn; //= barnDao.get
        return areasDao.create(1, area.getName(), area.getDescription(), area.getNumberOfPigs() );
    }
    @Override
    public Future<Area> read(int id) {
        return areasDao.read(id);
    }
}
