package com.picmap.app.comment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class SampleTest extends DefaultTest {

	    @AfterClass
	    public static void afterAll() {
	        System.out.println("====== After All =====");
	    }

	    @BeforeClass
	    public static void beforeAll() {
	        System.out.println("==== Before All ====");
	    }

	    @Before
	    public void before() {
	        System.out.println("------- Before --------");
	    }

	    @After
	    public void after() {
	        System.out.println("------- after --------");
	    }
	}


