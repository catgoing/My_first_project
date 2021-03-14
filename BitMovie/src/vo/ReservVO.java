package vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

public class ReservVO {
	
	String rname, rtime, rseat, rnum;
	Date rdate;
	
	public ReservVO() {}
	
	public ReservVO(String rnum, String rname, Date rdate, String rtime, String rseat) {
		super();
		this.rnum = rnum;
		this.rname = rname;
		this.rdate = rdate;
		this.rtime = rtime;
		this.rseat = rseat;
	}


	public String getRnum() {
		return rnum;
	}


	public void setRnum(String rnum) {
		StringBuilder sb = new StringBuilder();
		sb.append(rnum);
		for (int i = sb.length()-1; i > 0; i--) {
		if(i%3==0){
		sb.insert(i, "-");
		}
		} 
		
		this.rnum = sb.toString();
	}


	public String getRname() {
		return rname;
	}


	public void setRname(String rname) {
		this.rname = rname;
	}


	public Date getRdate() {
		return rdate;
	}


	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}


	public String getRtime() {
		return rtime;
	}


	public void setRtime(String rtime) {
		this.rtime = rtime;
	}


	public String getRseat() {
		return rseat;
	}


	public void setRseat(String rseat) {
		
		String str[] = rseat.split(",");
		StringBuilder sb = new StringBuilder();
		int length = str.length;
		
		ArrayList<Integer> seatlist = new ArrayList<Integer>();
		
		
		for (int i = 0; i < length; i++) {
			seatlist.add(Integer.parseInt(str[i]));
		}
		
		Collections.sort(seatlist); // 숫자로 정렬
		
		for (int i = 0; i < seatlist.size(); i++) {
			sb.append(seatlist.get(i));
			sb.append(",");
		}
			sb.deleteCharAt(sb.length()-1); // sb의 제일 끝 콤마 없애기

		this.rseat = sb.toString();
	}

	
	
	@Override
	public String toString() {
		return rnum + "\t" + rname + "\t" + rdate + "\t" + rtime + "\t" + rseat;
	}
	
	public String toString2() {
		return rnum + "," + rname + "," + rdate + "," + rtime + "," + rseat;
	}
	
	
	

 

}
