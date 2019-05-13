package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.infra.HunterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class HunterTest {

    @Mock
    private HunterRepository repository;

    @InjectMocks
    private HunterService hunterService;

    @Test
    public void hunterCreate() {

        String id = "kjc3590";

        Hunter hunter = new Hunter(id, "2222", "김종찬", "/file/file.png", "file.png", 0, 0);

        hunterService.hunterCreate(hunter);

        System.out.println(hunterService.findById(id));
    }

}