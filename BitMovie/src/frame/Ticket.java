package frame;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.MovieDAO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import vo.MovieVO;

public class Ticket extends JFrame implements ItemListener, ActionListener{

	Choice movieName, selectTime, adultTicket, childTicket;
	JPanel panel;
	JLabel label, name, time, adult, child, mdate;
	JButton nextbt;
	String idt;
	int index, rNum;
	
	Date selectedDate = new Date();
	Seat seat;
	MovieDAO movdao = new MovieDAO();
	MovieVO movvo = new MovieVO();
	
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	SimpleDateFormat dateform;
	
	public Ticket(int movieNo) {
		
		this.index = movieNo;
		setSize(500, 400);
		setLocation(750, 200);
		setTitle("영화예매");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new JPanel(null);
		label = new JLabel("영화예매");
		name = new JLabel("영화 ");
		mdate = new JLabel("상영일");
		time = new JLabel("상영시간 ");
		adult = new JLabel("어른 ");
		child = new JLabel("청소년 ");
		
		nextbt = new JButton("NEXT");
		nextbt.addActionListener(this);
		
		movieName = new Choice();
		selectTime = new Choice();
		adultTicket = new Choice();
		childTicket = new Choice();
		
		movieName.add(getMovieName(1));
		movieName.add(getMovieName(2));
		movieName.add(getMovieName(3));
		movieName.select(movieNo);
		movieName.addItemListener(this);
		selectTime(movieNo);
					
		
		for (int i = 0; i <= 10; i++) {
			adultTicket.add(String.valueOf(i));
			childTicket.add(String.valueOf(i));
		}
		
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
		label.setForeground(Color.black);
		
		label.setBounds(180, 40, 200, 20);									
		name.setBounds(130, 87, 55, 50);
		mdate.setBounds(130, 127, 55, 50);
		time.setBounds(130, 166, 55, 50);
		adult.setBounds(130, 208, 40, 50);
		child.setBounds(130, 248, 40, 30);
		movieName.setBounds(190, 100, 160, 20);
		datePicker.setBounds(190, 140, 160, 30);
		selectTime.setBounds(190, 180, 160, 20);
		adultTicket.setBounds(190, 220, 70, 20);
		childTicket.setBounds(190, 250, 70, 30);
		nextbt.setBounds(340, 300, 80, 30);
		
		
		panel.add(label);
		panel.add(name);
		panel.add(movieName);
		panel.add(time);
		panel.add(selectTime);
		panel.add(adult);
		panel.add(adultTicket);
		panel.add(child);
		panel.add(childTicket);
		panel.add(nextbt);
		panel.add(mdate);
		panel.add(datePicker);
		
		add(panel);
		setVisible(true);
		

		
	}
	
	public void modify(String idt, int rnum) {
		
		this.idt = idt;
		this.rNum = rnum;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// item에서 index 추가해주기
		String movie_date = "";
		dateform = new SimpleDateFormat("yy/MM/dd");
		selectedDate = (Date) datePicker.getModel().getValue();
		
		if(selectedDate != null) {
			movie_date = dateform.format(selectedDate);
		} 
		
		String movie_time = selectTime.getSelectedItem();
		int adult_ticket = adultTicket.getSelectedIndex();
		int child_ticket = childTicket.getSelectedIndex();

		int adult_price = 10000;					
		int child_price = 9000;
		int total_price = 0;
		
		if(SignIn.ids == null) {
			JOptionPane.showMessageDialog( null, "로그인 후 예매가 가능합니다", "error", JOptionPane.ERROR_MESSAGE );
			dispose();
		}
		
		else {
			
			if((adult_ticket == 0 && child_ticket == 0) || movie_time.equals(null) || "".contentEquals(movie_date))
			{
				JOptionPane.showMessageDialog( null, "정보를 모두 입력하세요", "error", JOptionPane.ERROR_MESSAGE );
			} 
			else {

				if(adult_ticket != 0) total_price += adult_ticket * adult_price;

				if(child_ticket != 0) total_price += child_ticket * child_price;
				
				if(rNum == 0) {
				seat = new Seat(movieName.getItem(index),movie_time, movie_date, 
						adult_ticket + child_ticket, total_price, this);
				seat.select();
				} else {
				seat = new Seat(movieName.getItem(index), movie_time, movie_date, 
						adult_ticket + child_ticket, total_price, this);
				seat.modify(rNum, idt);
				}
				
				this.setVisible(false);
			}
		}

	}

	
	@Override
	public void itemStateChanged(ItemEvent e) {
		selectTime.removeAll();
		
		index = movieName.getSelectedIndex();
		int movieNo = this.index + 1;
		movdao.setMovie(movieNo);
		String time = movdao.movie.getTime();
		String[] timearr = time.split("/");
		
		for (int i = 0; i < timearr.length; i++) {
			selectTime.add(timearr[i]);
		}

	}
	
	public void selectTime(int movieNo) {
		movdao.setMovie(movieNo+1);
		String time = movdao.movie.getTime();
		
		String[] timearr = time.split("/");
		for (int i = 0; i < timearr.length; i++) {
			selectTime.add(timearr[i]);
		}

	}

	
	private String getMovieName(int no) {
		movdao.setMovie(no);
		
		return movdao.movie.getName();
	}
	

	
	
}