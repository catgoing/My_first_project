package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.Member_DAO;
import dao.Movie_DAO;
import frame.Main_Frame;
import frame.Signup;
import frame.Ticket;
import vo.Movie_VO;
import vo.Reserv_VO;





public class Cancel extends JFrame implements ActionListener{

	JPanel panel;
	JLabel label1, reservNo;
	public TextField reservnoT;
	JButton okbtn, withd;
	Main_Frame mv;
	String str;
	Movie_DAO movdao = new Movie_DAO();
	public String reservno;
	String user_id;


	public Cancel() {

	}

	public void cancel(String user_id) {

		this.user_id = user_id;

		setSize(500, 300);
		setLocation(750, 200);
		setTitle("예매취소");
		panel = new JPanel(null);

		label1 = new JLabel("예매취소");
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 30));
		label1.setForeground(Color.black);
		label1.setBounds(180, 10, 200, 80);

		reservNo = new JLabel("예매번호를 입력해주세요(-제외, 숫자만 입력) ");
		reservNo.setBounds(140, 80, 270, 20);

		reservnoT = new TextField();
		reservnoT.setBounds(180, 110, 120, 20);

		okbtn = new JButton("확인"); 
		okbtn.setBounds(190, 150, 100, 30); 
		okbtn.addActionListener(this);

		panel.add(label1);
		panel.add(reservNo);
		panel.add(reservnoT);
		panel.add(okbtn);
		add(panel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == okbtn) {


			this.reservno = reservnoT.getText();
			int reservnum = Integer.parseInt(this.reservno);

			if(reservno.isEmpty()) {
				JOptionPane.showMessageDialog( null, "예매번호를 입력하세요", "error", JOptionPane.ERROR_MESSAGE );
			}else {

				if(movdao.reserved_Rnum(reservnum, this.user_id) == false) {
					JOptionPane.showMessageDialog(null, "예매내역이 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
				}

				else {

					String[] answer = {"예매취소", "닫기"};
					int result = JOptionPane.showOptionDialog(null,
							"예매번호: " + movdao.reserv.getRnum() + "\n"
									+ "영화제목: " + movdao.reserv.getRname()+ "\n"
									+ "상영일자: " + movdao.reserv.getRdate() + "\n"
									+ "상영시간: " + movdao.reserv.getRtime() + "\n"
									+ "좌석번호: " + movdao.reserv.getRseat(), "예매확인", JOptionPane.YES_NO_OPTION, 
									JOptionPane.INFORMATION_MESSAGE, 
									null, answer, answer[1]);

					if(result == 0) {
						movdao.cancelmovie(reservnum);
						JOptionPane.showMessageDialog(null, "예매가 취소되었습니다.");
					}
					dispose();
				}


			} 
		}

	}


}


