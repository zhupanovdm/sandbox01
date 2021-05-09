package org.zhupanovdm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class ScheduleTest {

    @Test
    public void testBasic() {
        Schedule<Integer> schedule = new Schedule<>();
        schedule
                .add(21, 22)
                .add(20, 21)
                .add(20, 21)
                .add(10, 11)
                .add(10, 18)
                .add(15, 17)
                .add(19, 20);

        System.out.println(schedule.getTasks());

        List<Schedule.Item<Integer>> composed = schedule.compose();
        System.out.println(composed);

        List<Schedule.Item<Integer>> expected = new LinkedList<>();
        expected.add(new Schedule.Item<>(10, 18));
        expected.add(new Schedule.Item<>(19, 20));
        expected.add(new Schedule.Item<>(20, 21));
        expected.add(new Schedule.Item<>(21, 22));

        Assertions.assertArrayEquals(expected.toArray(), composed.toArray());
    }

}