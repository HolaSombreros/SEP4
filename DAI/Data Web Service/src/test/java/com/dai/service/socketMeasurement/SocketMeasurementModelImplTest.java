package com.dai.service.socketMeasurement;

import com.dai.dao.area.AreaDao;
import com.dai.dao.measurement.MeasurementDao;
import com.dai.dao.threshold.ThresholdDao;
import com.dai.helpers.MeasurementValidator;
import com.dai.model.Area;
import com.dai.model.Barn;
import com.dai.model.Measurement;
import com.dai.model.SocketData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.AsyncResult;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SocketMeasurementModelImplTest {
    private SocketMeasurementServiceImpl model;

    @Mock
    private MeasurementDao measurementDao;

    @Mock
    private AreaDao areaDao;

    @Mock
    private MeasurementValidator measurementValidator;
    @Mock
    private ThresholdDao thresholdDao;

    @Test
    public void saveSocketData() throws Exception {
        //Arrange
        when(measurementDao.create(any())).then(i -> new AsyncResult<>(i.getArguments()[0]));
        when(areaDao.readByHardwareId(anyString())).then(i -> new AsyncResult<>(new Area(1, new Barn("Barn"),
                "Area Name", "Description", 100, i.getArgument(0))));

        when(measurementValidator.isCo2ValueValid(anyInt())).thenReturn(true);
        when(measurementValidator.isHumidityValueValid(anyDouble())).thenReturn(true);
        when(measurementValidator.isTemperatureValueValid(anyDouble())).thenReturn(true);

        model = new SocketMeasurementServiceImpl(measurementDao, areaDao, measurementValidator, thresholdDao);

        LocalDateTime now = LocalDateTime.now();
        long nowLinux = now.toEpochSecond(ZoneId.of("Europe/Copenhagen").getRules().getOffset(now));
        SocketData data = new SocketData("rx", "0004A30B002528D3", String.valueOf(nowLinux), "02C100FD022B00320E");

        Measurement result;

        //Act
        result = model.saveSocketData(data);

        //Assert
        assertEquals(555, result.getCo2());
        assertEquals(70.5, result.getHumidity());
        assertEquals(25.3, result.getTemperature());
    }
}
