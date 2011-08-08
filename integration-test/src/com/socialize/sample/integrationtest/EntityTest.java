package com.socialize.sample.integrationtest;

import android.widget.TextView;


public class EntityTest extends SocializeRobotiumTest {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		authenticateSocialize();
		
		robotium.clickOnButton("Entity");
		robotium.waitForActivity("EntityActivity", DEFAULT_TIMEOUT_SECONDS);
	}

	public void testCreateEntity() {
		robotium.enterText(0, DEFAULT_ENTITY_URL);
		robotium.enterText(1, "RobotiumEntityTest");
		robotium.clickOnButton("Add Entity");
		waitForSuccess();
	}
	
	public void testGetEntity() {
		// We need create first
		robotium.enterText(0, DEFAULT_ENTITY_URL);
		robotium.enterText(1, "RobotiumEntityTest");
		robotium.clickOnButton("Add Entity");
		waitForSuccess();
		
		String entityKey = getEntityKey();
		
		robotium.clickOnButton("Get Entity");
		
		waitForSuccess();
		
		String entityKey2 = getEntityKey();
		
		assertEquals(entityKey, entityKey2);
	}
	
	private String getEntityKey() {
		
		sleep(2000); // Wait for text field to be updated
		
		TextView txt = (TextView) robotium.getCurrentActivity().findViewById(com.socialize.sample.R.id.txtEntityKeyCreated);
		
		// This is ID, it should be integer
		assertNotNull(txt);
		
		return txt.getText().toString();
	}
}