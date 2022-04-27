package com.dai.model.areas;

import com.dai.Helper;
import com.dai.dao.area.AreaDao;
import com.dai.dao.barn.BarnDao;
import com.dai.exceptions.BadRequestException;
import com.dai.shared.Area;
import com.dai.shared.AreaDTO;
import com.dai.shared.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

@Component
public class AreasModelImpl implements AreasModel {

    private AreaDao areasDao;
    private BarnDao barnDao;

    @Autowired
    public AreasModelImpl(AreaDao areasDao,BarnDao barnDao ) {
        this.areasDao = areasDao;
        this.barnDao = barnDao;
    }

    @Override
    public Future<Area> create(Area area) {
        Barn barn = (Barn) barnDao.read(area.getBarn().getId());

        if(barn!=null)
        {
            return areasDao.create(barn, area.getName(), area.getDescription(), area.getNumberOfPigs() );
        }
        else{
            throw new IllegalStateException("Barn not found");
        }

    }
    @Override
    public Future<Area> read(int id) {
        return areasDao.read(id);
    }

    @Override
    public List<Area> getAll() {
        try{
            return Helper.await(areasDao.getAll());
        }catch (Exception e){
            throw new BadRequestException();
        }
    }
}
