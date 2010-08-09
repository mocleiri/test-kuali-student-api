package org.kuali.student.lum.lu.ui.main.client;

import org.kuali.student.common.ui.client.mvc.history.Locations;


public class AppLocations implements Locations{

	public enum Locations{
		CURRICULUM_MANAGEMENT("/HOME/CURRICULUM_HOME"),
		COURSE_PROPOSAL("/HOME/CURRICULUM_HOME/COURSE_PROPOSAL"),
		VIEW_COURSE("/HOME/CURRICULUM_HOME/VIEW_COURSE"),
		VIEW_PROGRAM("/HOME/CURRICULUM_HOME/PROGRAM"),
		PROGRAM_PROPOSAL("/HOME/CURRICULUM_HOME/PROGRAM_PROPOSAL"),
		MANAGE_CLU_SETS("/HOME/CURRICULUM_HOME/CLU_SETS"),
		BROWSE_CATALOG("/HOME/CURRICULUM_HOME/COURSE_CATALOG");
		
		private String location;
		
		private Locations(String location){
			this.location = location;
		}
		
		public String getLocation() {
			return location;
		}
	}

	@Override
	public String getLocation(String enumName) {
		Locations e = Locations.valueOf(enumName);
		if(e != null){
			return e.getLocation();
		}
		else{
			return null;
		}
	}

}
