package com.dai.model.co2;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface Co2Model {
    List<SentMeasurement> getLatestCo2(int areaId) throws Exception;

   List<SentMeasurement> getAllCo2sInDate(int areaId, LocalDate date) throws Exception;
}
