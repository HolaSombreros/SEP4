package com.dai.model.co2;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface Co2Model {
    List<SentMeasurement> getLatest(int areaId) throws Exception;
    List<SentMeasurement> getAllByDate(int areaId, LocalDate date) throws Exception;
    List<SentMeasurement> getAllFromToday(int areaId) throws Exception;
}
