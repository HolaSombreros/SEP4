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
        //this.barnDao = barnDao;
    }

    @Override
    public Future<Area> create(Area area) {
        Barn barn;
        Area areal;
        //search for the barn
        //barn = barnDao.get(area.getBarn().getId();
        if(!barn==null)
        {
            return areasDao.create(barn.getId(), area.getName(), area.getDescription(), area.getNumberOfPigs() );

        }

    }
    @Override
    public Future<Area> read(int id) {
        return areasDao.read(id);
    }
}
