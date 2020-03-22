package com.atcud.icecreamapp.services;

import com.atcud.icecreamapp.entities.Icecream;
import com.atcud.icecreamapp.repositories.IcecreamRepository;
import com.atcud.icecreamapp.services.impl.IcecreamServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class IcecreamServiceMockTest {

    @TestConfiguration
    public static class IcecreamServiceTestConfiguration{
        @Bean
        IcecreamService icecreamService() {
            return new IcecreamServiceImpl();
        }
    }

    @MockBean
    private IcecreamRepository icecreamRepository;

    @Autowired
    private IcecreamService icecreamService;

    @Before
    public void setMockOutput() {
        Icecream icecream1 = new Icecream((long) 1, "Chocolate Icecream", "New Icecream");
        Icecream icecream2 = new Icecream((long) 2, "Vanilla Icecream", "New Icecream");
        List<Icecream> icecreams = new ArrayList<>(Arrays.asList(icecream1, icecream2));
        Mockito.when(icecreamRepository.findAll()).thenReturn(icecreams);
    }


    @DisplayName("Testing Icecream service")
    @Test
    public void testGetAllIcecream() {
        Icecream icecream1 = new Icecream((long) 1, "Chocolate Icecream", "New Icecream");
        Icecream icecream2 = new Icecream((long) 2, "Vanilla Icecream", "New Icecream");
        List<Icecream> expected = new ArrayList<>(Arrays.asList(icecream1, icecream2));
        List<Icecream> actual = icecreamService.getAllIcecreams();
        Assert.assertEquals(actual, expected);
    }
}
