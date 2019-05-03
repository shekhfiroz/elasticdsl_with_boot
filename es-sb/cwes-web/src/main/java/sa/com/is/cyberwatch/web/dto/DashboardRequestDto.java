package sa.com.is.cyberwatch.web.dto;

import java.util.List;

public class DashboardRequestDto {

	private String q;
	private List<String> checkbox;
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public List<String> getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(List<String> checkbox) {
		this.checkbox = checkbox;
	}
	
	
}
