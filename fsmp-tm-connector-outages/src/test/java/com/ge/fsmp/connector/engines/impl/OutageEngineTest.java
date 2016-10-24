package com.ge.fsmp.connector.engines.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ge.fsmp.connector.domain.mashaery.AggOutage;
import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;
import com.ge.fsmp.connector.engines.IOutageEngine;
import com.ge.fsmp.coonector.exceptions.InvalidDataException;

@RunWith(MockitoJUnitRunner.class)
public class OutageEngineTest {

    private IOutageEngine outageEngine;

    @Before
    public void setUp() {
        outageEngine = new OutageEngine();
    }

    @Test(expected = InvalidDataException.class)
    public void testEquipmentException() {

        List<Project> projects = new ArrayList<Project>();
        Project project = new Project("Y", "1234", "12345", "Demo", "123", "CS", "Outage", "2015-11-01T00:00:00",
                "2015-12-01T00:00:00");
        Project project1 = new Project("Y", "1234", "12345", "Demo", null, "CS", "Outage", "2015-11-01T00:00:00",
                "2015-12-01T00:00:00");
        projects.add(project);
        projects.add(project1);

        List<Equipment> equipments = new ArrayList<Equipment>();
        Equipment equipment = new Equipment();
        equipments.add(equipment);

        Map<String, List<Equipment>> esnMap = new HashMap<String, List<Equipment>>();
        esnMap.put("12345", equipments);

        CustomerDetails customer = new CustomerDetails("OMAN LIQUEFIED NATURAL GAS L L C", "SUR", "SUR", "OMAN");
        Map<String, CustomerDetails> customerMap = new HashMap<String, CustomerDetails>();
        customerMap.put("12345", customer);

        Assignments assignmentObj = new Assignments("W01234", "1040450", "2015-11-01T00:00:00", "2015-11-01T00:00:00",
                "12345");
        List<Assignments> assignmentList = new ArrayList<Assignments>();
        assignmentList.add(assignmentObj);

        Map<String, List<Assignments>> assignmentMap = new HashMap<String, List<Assignments>>();
        assignmentMap.put("12345", assignmentList);

        outageEngine.getAggOutages(projects, assignmentMap, customerMap, esnMap);

    }

    @Test
    public void getAggOutages() throws Exception {

        List<Project> projects = new ArrayList<Project>();
        Project project = new Project("Y", "1234", "12345", "Demo", "123", "CS", "Outage", "2015-11-01T00:00:00",
                "2015-12-01T00:00:00");
        Project project1 = new Project("Y", "1234", "12345", "Demo", null, "CS", "Outage", "2015-11-01T00:00:00",
                "2015-12-01T00:00:00");
        projects.add(project);
        projects.add(project1);

        List<Equipment> equipments = new ArrayList<Equipment>();
        Equipment equipment = new Equipment("Val123");
        equipments.add(equipment);

        Map<String, List<Equipment>> esnMap = new HashMap<String, List<Equipment>>();
        esnMap.put("12345", equipments);

        CustomerDetails customer = new CustomerDetails("OMAN LIQUEFIED NATURAL GAS L L C", "SUR", "SUR", "OMAN");
        Map<String, CustomerDetails> customerMap = new HashMap<String, CustomerDetails>();
        customerMap.put("12345", customer);

        Assignments assignmentObj = new Assignments("W01234", "1040450", "2015-11-01T00:00:00", "2015-11-01T00:00:00",
                "12345");
        List<Assignments> assignmentList = new ArrayList<Assignments>();
        assignmentList.add(assignmentObj);

        Map<String, List<Assignments>> assignmentMap = new HashMap<String, List<Assignments>>();
        assignmentMap.put("12345", assignmentList);

        List<AggOutage> aggOutages = outageEngine.getAggOutages(projects, assignmentMap, customerMap, esnMap);

        assertThat(aggOutages.size(), is(1));

        for (AggOutage aggOutage : aggOutages) {

            assertEquals(aggOutage.getId().longValue(), Long.parseLong("12345"));
            assertEquals(aggOutage.getEsn().get(0), "Val123");
            assertEquals(aggOutage.getCustomerName(), "OMAN LIQUEFIED NATURAL GAS L L C");
            assertEquals(aggOutage.getCity(), "SUR");
            assertEquals(aggOutage.getCountry(), "OMAN");
            assertEquals(aggOutage.getDescription(), "Outage");
            assertEquals(aggOutage.getProjectStartDate(),
                    LocalDateTime.parse("2015-11-01T00:00:00").toInstant(ZoneOffset.UTC));
            assertEquals(aggOutage.getProjectCompletionDate(),
                    LocalDateTime.parse("2015-12-01T00:00:00").toInstant(ZoneOffset.UTC));
            assertEquals(aggOutage.getProjectType(), "outage");
            assertEquals(aggOutage.getStatus(), "Y");

        }

    }

}
