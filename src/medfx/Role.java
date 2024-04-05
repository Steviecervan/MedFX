/*	Class Contains:
 * 	There has to be some sort of way to make this more secure
 * 	or a bit better. 
 * 
 * 	TODO: REDO THIS!
 */

package medfx;

public class Role {
	private String role;

	//	Get functions
	public String getRole() {
		return role;
	}
	
	//	Set functions
	public void setAsPatient() {
		this.role = "patient";
	}
	
	public void setAsDoctor() {
		this.role = "doctor";
	}
	
	public void setAsNurse() {
		this.role = "nurse";
	}
}
