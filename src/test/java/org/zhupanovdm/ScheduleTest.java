package org.zhupanovdm;

import org.junit.jupiter.api.Test;

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

        System.out.println(schedule.items.getList());
        System.out.println(schedule.compose());
    }

}