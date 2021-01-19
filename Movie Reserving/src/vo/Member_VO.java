package vo;

public class Member_VO {
	
	private String id;
	private String name;
	private String password;
	private String phone;
	
	public Member_VO() {}

	public Member_VO(String id, String name, String password, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + password + "\t" + phone + "\n";
//		return "[id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + "]";
	}
	
	
	
	

}
