package com.lvn.lab6to8.test;

import com.lvn.lab6to8.phone.Phone;
import com.lvn.lab6to8.phone.PhoneHandler;

import static org.junit.jupiter.api.Assertions.*;

class PhoneHandlerTest {

    private PhoneHandler handler;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        handler = new PhoneHandler();
    }

    @org.junit.jupiter.api.Test
    void sortPhonesByTotalTime() {
        Phone[] sample = {
                new Phone(3, "", "", "", 0, "", 13, 12),
                new Phone(4, "", "", "", 0, "", 13, 112),
                new Phone(0, "", "", "", 0, "", 0, 1),
                new Phone(2, "", "", "", 0, "", 10, 1),
                new Phone(1, "", "", "", 0, "", 1, 4)
        };
        Phone[] actualAsc = handler.sortPhonesByTotalTime(sample, false);
        Phone[] actualDesc = handler.sortPhonesByTotalTime(sample, true);
        for (int i = 0; i < sample.length; i++) {
            assertEquals(i, actualAsc[i].getId());
            assertEquals(sample.length - i - 1, actualDesc[i].getId());
        }
    }

    @org.junit.jupiter.api.Test
    void sortPhonesByName() {
        Phone[] sample = {
                new Phone(3, "Andrianova", "Vasilisa", "Mikhailovna", 0, "", 0, 0),
                new Phone(0, "Abramov", "Roman", "Fedorovich", 0, "", 0, 0),
                new Phone(2, "Andreeva", "Maria", "Timofeevna", 0, "", 0, 0),
                new Phone(1, "Alekseev", "Egor", "Dmitrievich", 0, "", 0, 0),
                new Phone(4, "Anikin", "Roman", "Andreevich", 0, "", 0, 0)
        };
        Phone[] actualAsc = handler.sortPhonesByName(sample, false);
        Phone[] actualDesc = handler.sortPhonesByName(sample, true);
        for (int i = 0; i < sample.length; i++) {
            assertEquals(i, actualAsc[i].getId());
            assertEquals(sample.length - i - 1, actualDesc[i].getId());
        }
    }

    @org.junit.jupiter.api.Test
    void getPhonesByCity() {
        Phone[] sample = {
                new Phone(3, "", "", "", 0, "Mykolaiv", 0, 0),
                new Phone(0, "", "", "", 0, "Khust", 0, 0),
                new Phone(2, "", "", "", 0, "Mykolaiv", 0, 0),
                new Phone(1, "", "", "", 0, "Mykolaiv", 0, 0),
                new Phone(4, "", "", "", 0, "Mykolaiv", 0, 0)
        };
        Phone[] actual = handler.getPhonesByCity(sample, "Khust");
        assertEquals(1, actual.length);
        assertEquals("Khust", actual[0].getCity());
    }

    @org.junit.jupiter.api.Test
    void getCities() {
        Phone[] sample = {
                new Phone(3, "", "", "", 0, "Mykolaiv", 0, 0),
                new Phone(0, "", "", "", 0, "Khust", 0, 0),
                new Phone(2, "", "", "", 0, "Mykolaiv", 0, 0),
                new Phone(1, "", "", "", 0, "Mykolaiv", 0, 0),
                new Phone(4, "", "", "", 0, "Mykolaiv", 0, 0)
        };
        String[] actual = handler.getCities(sample);
        assertEquals(2, actual.length);
        assertNotEquals(actual[0], actual[1]);
    }

    @org.junit.jupiter.api.Test
    void getPhonesWithNumberBetween() {
        Phone[] sample = {
                new Phone(3, "", "", "", 0, "", 0, 0),
                new Phone(4, "", "", "", 0, "", 0, 0),
                new Phone(0, "", "", "", 15, "", 0, 1),
                new Phone(2, "", "", "", 0, "", 0, 0),
                new Phone(1, "", "", "", 0, "", 0, 0)
        };
        Phone[] actual = handler.getPhonesWithNumberBetween(sample, 10, 20);
        assertEquals(1, actual.length);
        assertEquals(0, actual[0].getId());
    }

    @org.junit.jupiter.api.Test
    void getPhonesWithIntercityTime() {
        Phone[] sample = {
                new Phone(3, "", "", "", 0, "", 0, 0),
                new Phone(4, "", "", "", 0, "", 0, 0),
                new Phone(0, "", "", "", 0, "", 0, 1),
                new Phone(2, "", "", "", 0, "", 0, 0),
                new Phone(1, "", "", "", 0, "", 0, 0)
        };
        Phone[] actual = handler.getPhonesWithIntercityTime(sample);
        assertEquals(1, actual.length);
        assertEquals(0, actual[0].getId());
    }

    @org.junit.jupiter.api.Test
    void getPhonesWithIntracityTimeMoreThan() {
        Phone[] sample = {
                new Phone(3, "", "", "", 0, "", 0, 0),
                new Phone(4, "", "", "", 0, "", 0, 0),
                new Phone(0, "", "", "", 0, "", 1, 0),
                new Phone(2, "", "", "", 0, "", 0, 0),
                new Phone(1, "", "", "", 0, "", 0, 0)
        };
        Phone[] actual = handler.getPhonesWithIntracityTimeMoreThan(sample, 0);
        assertEquals(1, actual.length);
        assertEquals(0, actual[0].getId());
    }
}