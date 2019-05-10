package io.github.wotjd243.findbyhint.mission.ui;

import io.github.wotjd243.findbyhint.mission.domain.MissionVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MissionController {

    @GetMapping("/")
    public void index() {
    }

    @GetMapping("/mission")
    public void mission(Model model) throws IOException {
//        MissionVO missionVO = new MissionVO();
////        missionVO.execute(model);
    }
}
