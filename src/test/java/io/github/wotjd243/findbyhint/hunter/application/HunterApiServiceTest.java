package io.github.wotjd243.findbyhint.hunter.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HunterApiServiceTest {

    @Autowired
    private HunterApiService hunterApiService;

    @Test
    public void SendList_가져오기(){

        hunterApiService.getSendListAll().getHunterIds().forEach(hunterId -> System.out.println("hunterId :: '"+hunterId+"'"));

    }


}