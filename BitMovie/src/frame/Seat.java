package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.MovieDAO;

public class Seat extends JFrame implements ActionListener{
	JPanel panel, seat;
	JLabel screen, seats[], peopleL, priceL, movienameL, moviedateL, movietimeL;
	JButton nextbt, prevbt;
	String movieName, movieDate, movieTime, idt;
	int total_price, rnum;
	int people;  // 성인 2하면 하나씩 좌석선택하면서 감소하게끔
	ArrayList<String> seatnum = new ArrayList<String>();
	String[] rsrvedSeat, my_rsrvedSeat;
	MovieDAO dao = new MovieDAO();

	Ticket tck;

	public Seat(String movieN, String movieT, String movieD, int total_people, int total_price, Ticket tck) {

		
		this.tck = tck;
		people = total_people;
		movieName = movieN;
		movieDate = movieD;
		movieTime = movieT;
		this.total_price = total_price;
		String str = dao.reservedSeat(movieN, this.movieDate, movieT); // 영화제목, 영화시간 파라미터 넣어서
			// 예매된 좌석번호 얻는 메서드


	   setSize(500, 400);
	   setLocation(750, 200);
	   setTitle("영화예매");
	   screen = new JLabel("SCREEN");
	   screen.setForeground(Color.black);
	   screen.setBackground(Color.blue);
	   screen.setFont(new Font(screen.getFont().getName(), Font.PLAIN, 20));
	   screen.setBounds(130, 20, 100, 30);

	   movienameL = new JLabel("영화 : " + movieN);
	   moviedateL = new JLabel("상영일: " + movieD);
	   movietimeL = new JLabel("시간 : " + movieT);
	   peopleL = new JLabel("인원 : " + total_people +"명 ");
	   priceL = new JLabel("가격 : " + total_price +"원 ");
	   movienameL.setBounds(350, 80, 140, 30);
	   moviedateL.setBounds(350, 120, 140, 30);
	   movietimeL.setBounds(350, 160, 140, 30);
	   peopleL.setBounds(350, 200, 140, 30);
	   priceL.setBounds(350, 240, 140, 30);
	   nextbt = new JButton("예매하기");
	   prevbt = new JButton("이전으로");
	   nextbt.setBounds(340,316,100,30);
	   prevbt.setBounds(50,316,100,30);
	   nextbt.addActionListener(this);
	   prevbt.addActionListener(this);

	   panel = new JPanel(null);
	   seat = new JPanel(new GridLayout(7, 7)); // (줄, 칸) 7 * 7 좌석으로
	   seat.setBackground(Color.white); // 처음 선택할 때 다 흰색
	   seat.setBounds(30, 60, 300, 230);

	   seats = new JLabel[50];  // 자리수   // for문 돌릴때 0부터 시작해서 -1

	   boolean[] select = new boolean[49]; // 내가 선택한 자릿수  4번자리 선택하면 select[4] = true;

	   for (int i = 0; i < 7; i++) {    // 별찍기 
		   for (int j = 0; j < 7; j++) {
			int z = i * 7 + j;
			 seats[z] = new JLabel(Integer.toString(z+1)); // 라벨 하나에 좌석번호 부여

			 seats[z].addMouseListener(new MouseListener(){ // 라벨 클릭하면 동작하는거


					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(seats[z].getBackground() == Color.red) { // 내가 선택한 좌석
							seats[z].setBackground(Color.WHITE); // 다시 선택하면 흰색으로
							seatnum.remove(seats[z].getText());
							System.out.println(seatnum.toString());
							select[z] = false; // 선택된 좌석 false로
						    people++; // people 하나씩 증가
						}
						else if(seats[z].getBackground()!= Color.gray && people > 0) { // gray는 이미 예매된 좌석
							seats[z].setBackground(Color.red);
							System.out.println(seats[z].getText());
							seatnum.add(seats[z].getText());
							select[z] = true; // 선택할 수 있게 true로 해준다
							people--;  // 한자리 선택했으니까 인원 감소시켜준다

						}
						seats[z].setOpaque(true); // 바뀐 색 적용
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

				});
			 seat.add(seats[z]);

			 }

	}

		if(str != null) {
			rsrvedSeat = str.split(","); // 얻은 좌석번호 ',' 기준으로 나누기
		int[] str2 = new int[rsrvedSeat.length];
		for (int i = 0; i < str2.length; i++) {
			str2[i] = Integer.parseInt(rsrvedSeat[i]);
		}
		for (int q = 0; q < str2.length; q++) { //예매된 좌석 색 회색으로 바꾸기
			seats[str2[q]-1].setBackground(Color.gray);
			seats[str2[q]-1].setOpaque(true);
		}
		}


	    
		panel.add(screen);
		panel.add(nextbt);
		panel.add(prevbt);
		panel.add(movienameL);
		panel.add(moviedateL);
		panel.add(movietimeL);
		panel.add(peopleL);
		panel.add(priceL);



		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 


	}


	public void select() {
		
		panel.add(seat);
		add(panel);
		setVisible(true);

	} 

	public void modify(int rnum, String idt) {
		
		this.rnum = rnum;
		this.idt = idt;
		
		String str = dao.reservedSeat(movieName, movieDate, movieTime); // 영화제목, 영화시간 파라미터 넣어서
		String str3 = dao.reservedSeat2(movieName, movieDate, movieTime, idt); // 영화제목, 영화시간 파라미터 넣어서
		System.out.println(str);
		System.out.println(str3);
		// 예매된 좌석번호 얻는 메서드

		if(str != null) {
			rsrvedSeat = str.split(","); // 얻은 좌석번호 ',' 기준으로 나누기
			int[] str2 = new int[rsrvedSeat.length];
			for (int i = 0; i < str2.length; i++) {
				str2[i] = Integer.parseInt(rsrvedSeat[i]);
			}
			for (int q = 0; q < str2.length; q++) { //예매된 좌석 색 회색으로 바꾸기
				seats[str2[q]-1].setBackground(Color.gray);
				seats[str2[q]-1].setOpaque(true);
			}
		}

		if(str3 != null) {
			my_rsrvedSeat = str3.split(","); // 얻은 좌석번호 ',' 기준으로 나누기
			int[] str4 = new int[my_rsrvedSeat.length];
			for (int i = 0; i < str4.length; i++) {
				str4[i] = Integer.parseInt(my_rsrvedSeat[i]);
			}
			for (int q = 0; q < str4.length; q++) { //내가 전에 예매한 좌석 색 오렌지으로 바꾸기
				seats[str4[q]-1].setBackground(Color.orange);
				seats[str4[q]-1].setOpaque(true);
			}
		}
		
		panel.add(seat);
		add(panel);
		setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == nextbt) {
			if(SignIn.ids == null) {
				JOptionPane.showMessageDialog( null, "로그인되어 있지 않습니다. 다시 로그인해주세요", "error", JOptionPane.ERROR_MESSAGE );
				dispose();
			} else {
				if(people > 0) { // 좌석 선택안했는데 nextbt 누른경우
					JOptionPane.showMessageDialog( null, "좌석을 모두 선택해주세요!", "error", JOptionPane.ERROR_MESSAGE );
				} else {


					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < seatnum.size(); i++) {
						sb.append(seatnum.get(i));
						sb.append(",");
					}
					sb.deleteCharAt(sb.length()-1);

					if(rnum == 0) {
					dao.reservMovie(movieName, movieDate, movieTime, sb.toString(), total_price);
					} 
					else {
						if(SignIn.ids.equals("admin")) {
							dao.updateMovie(movieName, movieDate, movieTime, sb.toString(), rnum, idt);
						} else {
							dao.updateMovie(movieName, movieDate, movieTime, sb.toString(), rnum, SignIn.ids);}
					}
					
					JOptionPane.showMessageDialog(null, "예매되었습니다.");
					dispose();
					tck.dispose();
					
					

				}

			}
		}

		else if (e.getSource() == prevbt) {

			tck.setVisible(true);
			this.setVisible(false);

		}

	}

}