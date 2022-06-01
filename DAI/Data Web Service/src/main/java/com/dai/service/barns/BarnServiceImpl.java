package com.dai.service.barns;

import com.dai.helpers.Helper;
import com.dai.dao.barn.BarnDao;
import com.dai.model.Barn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BarnServiceImpl implements BarnService {

    private BarnDao barnDao;

    public BarnServiceImpl(BarnDao barnDao) {
        this.barnDao = barnDao;
    }

    @Override
    public List<Barn> readAll() throws Exception {
        return Helper.await(barnDao.readAll());
    }

    @Override
    public Barn create(Barn barn) throws Exception {
        return Helper.await(barnDao.create(barn));
    }
}
