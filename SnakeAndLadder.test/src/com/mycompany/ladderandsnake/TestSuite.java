package com.mycompany.ladderandsnake;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(

{ MainTest.class, com.mycompany.ladderandsnake.operator.TestSuite.class })
public class TestSuite { // nothing
}
