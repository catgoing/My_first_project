package vo;

import java.util.Date;

public class Movie_VO {
	
	private String no, name, info, 
					seats, price, peopNum,
					type, date, time;
	private Date release;
	private double grade;


	public Movie_VO() {}
	
	public Movie_VO(String no, String name, String info, String seats, String price,
			String peopNum) {
		super();
		this.no = no;
		this.name = name;
		this.info = info;
		this.seats = seats;
		this.price = price;
		this.peopNum = peopNum;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
		
	}

	public String getName() {
		return name;
		
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPeopNum() {
		return peopNum;
	}

	public void setPeopNum(String peopNum) {
		this.peopNum = peopNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double Grade) {
		this.grade = Grade;
	}

	
	
	

}
