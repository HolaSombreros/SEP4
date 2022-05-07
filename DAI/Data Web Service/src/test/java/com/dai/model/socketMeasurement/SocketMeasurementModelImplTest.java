package com.dai.model.socketMeasurement;

import com.dai.Helper;
import com.dai.dao.area.AreaDao;
import com.dai.dao.measurement.MeasurementDao;
import com.dai.shared.Area;
import com.dai.shared.Barn;
import com.dai.shared.Measurement;
import com.dai.shared.SocketData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.AsyncResult;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SocketMeasurementModelImplTest {
    private SocketMeasurementModelImpl model;

    @Mock
    private MeasurementDao measurementDao;

    @Mock
    private AreaDao areaDao;

    @Test
    public void data() {
        assertTrue(true);
    }

        @Test
    public void saveSocketData() throws Exception {
        //Assert
        when(measurementDao.saveMeasurement(anyObject())).then(i -> new AsyncResult<>(i.getArguments()[0]));
        when(areaDao.getAreaByHardwareId(anyString())).then(i -> new AsyncResult<>(new Area(1, new Barn("Barn"), "Area Name", "Description", 100, i.getArgument(0))));
        model = new SocketMeasurementModelImpl(measurementDao, areaDao);

        LocalDateTime now = LocalDateTime.now();
        long nowLinux = now.toEpochSecond(ZoneId.of("Europe/Copenhagen").getRules().getOffset(now));
        SocketData data = new SocketData("rx", "0004A30B002528D3", String.valueOf(nowLinux), "02C100FD022B00320E");

        Measurement result;

        //Act
        result = model.saveSocketData(data);

        //Arrange
        assertEquals(555, result.getCo2());
        assertEquals( 70.5, result.getHumidity());
        assertEquals(25.3, result.getTemperature());
    }
}