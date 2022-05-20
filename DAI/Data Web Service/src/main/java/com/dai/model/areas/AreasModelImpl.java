package com.dai.model.areas;

import com.dai.helpers.Helper;
import com.dai.dao.area.AreaDao;
import com.dai.dao.barn.BarnDao;
import com.dai.exceptions.BadRequestException;
import com.dai.shared.Area;
import com.dai.shared.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        Barn barn = Helper.await(barnDao.read(area.getBarn().getId()));

     /*   if(areasDao.readByNameAndBarn(area.getName(), area.getBarn().getId()) != null){
            throw new BadRequestException("Area already exists");
        }*/

        if(barn!=null)
        {
            return Helper.await(areasDao.create(area));
        }
        else{
            throw new BadRequestException("Barn not found");
        }

    }
    @Override
    public Area read(int id) throws Exception {
        try{
            return Helper.await(areasDao.read(id));
        }catch (Exception e){
            throw new BadRequestException("Area with the given id doesn't exist");
        }
    }

    @Override
    public List<Area> getAll() {
        try{
            return Helper.await(areasDao.getAll());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Area update(Area area) {
        try{
            return Helper.await(areasDao.update(area));
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Area delete(int id) {
        try {
            return Helper.await(areasDao.delete(id));
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
