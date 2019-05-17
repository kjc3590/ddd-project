package io.github.wotjd243.findbyhint.treasure.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class TreasureInventoryTest
{

    @Test
    public void getHintCounter(){
        AtomicInteger hintCounter = new AtomicInteger();

        List<Integer> integerList = new ArrayList<>();
        integerList.add(3);
        integerList.add(3);
        integerList.add(3);
        integerList.add(3);
        integerList.add(3);

        integerList.forEach(hintCounter::addAndGet);

        System.out.println(hintCounter.get());
    }

}