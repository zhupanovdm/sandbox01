package org.zhupanovdm;

import org.junit.jupiter.api.Test;

class RadioStationIssueTest {

    @Test
    public void testBasic() {

        // TODO: Test design
        RadioStationIssue issue = new RadioStationIssue();
        issue.addStation("KONE", "ID", "NV", "UT");
        issue.addStation("KTWO", "WA", "ID", "MT");
        issue.addStation("KTHREE", "OR", "NV", "CA");
        issue.addStation("KFOUR", "NV", "UT");
        issue.addStation("KFIVE", "CA", "AZ");

        System.out.println(issue.getStations("ID", "NV", "UT", "WA", "MT", "OR", "CA", "CA", "AZ"));
    }

}