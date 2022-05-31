package com.example.farmerama;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.app.Application;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.farmerama.data.persistence.FarmeramaDatabase;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.util.Converters;
import com.example.farmerama.data.util.ValidationUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DateConverterTest {

    private ValidationUser validationUser;
    @Before
    public void setUp() {
        validationUser = new ValidationUser();
    }
    @Test
    public void fromStringToLocalDate() {
        assertEquals(LocalDate.of(2022,5,31),
                Converters.fromString("2022-05-31"));
        assertNotEquals(null, Converters.fromString("2022-05-31"));
    }

    @Test
    public void fromLocalDateToString() {
        assertEquals("2022-07-31", Converters.fromLocalDateTime(
                LocalDate.of(2022,7,31)));
        assertNotEquals("31-05-2022", Converters.fromLocalDateTime(
                LocalDate.of(2022,5,31)));
        assertNotEquals(null, Converters.fromLocalDateTime(
                LocalDate.of(2022,4,7)));
    }
}