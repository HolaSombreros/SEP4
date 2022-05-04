package com.dai.model.areas;

import com.dai.Helper;
import com.dai.dao.area.AreaDao;
import com.dai.dao.barn.BarnDao;
import com.dai.exceptions.BadRequestException;
import com.dai.shared.Area;
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
    public Area create(Area area) throws Exception {
        Barn barn = (Barn) barnDao.read(area.getBarn().getId());

        if(barn!=null)
        {
            return Helper.await(areasDao.create(area));
        }
        else{
            throw new IllegalStateException("Barn not found");
        }

    }
    @Override
    public Area read(int id) throws Exception {
        Area await;
        try{
            await = Helper.await(areasDao.read(id));
        }catch (Exception e){
            throw new Exception("Area with the given id doesn't exist");
        }
        return await;
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
