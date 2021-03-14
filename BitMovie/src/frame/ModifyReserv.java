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

import dao.MemberDAO;
import dao.MovieDAO;
import frame.Main;
import frame.SignUp;
import frame.Ticket;
import vo.MovieVO;
import vo.ReservVO;





public class ModifyReserv extends JFrame implements ActionListener{

	JPanel panel;
	JLabel label, reservNo;
	TextField reservnoT;
	JButton okbt;
	String reservno;
	String user_id;
	MovieDAO movdao = new MovieDAO();


	public ModifyReserv() {

	}

	public void reserv(String user_id) {

		this.user_id = user_id;
		setSize(500, 300);
		setLocation(750, 200);
		setTitle("예매수정");
		panel = new JPanel(null);

		label = new JLabel("예매수정");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 30));
		label.setForeground(Color.black);
		label.setBounds(180, 10, 200, 80);

		reservNo = new JLabel("예매번호를 입력해주세요(-제외, 숫자만 입력) ");
		reservNo.setBounds(140, 80, 270, 20);

		reservnoT = new TextField();
		reservnoT.setBounds(180, 110, 120, 20);

		okbt = new JButton("확인"); 
		okbt.setBounds(190, 150, 100, 30); 
		okbt.addActionListener(this);

		panel.add(label);
		panel.add(reservNo);
		panel.add(reservnoT);
		panel.add(okbt);
		add(panel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == okbt) {


			this.reservno = reservnoT.getText();
			int reservnum = Integer.parseInt(this.reservno);

			if(reservno.isEmpty()) {
				JOptionPane.showMessageDialog( null, "예매번호를 입력해주세요", "error", JOptionPane.ERROR_MESSAGE );
			}else {

				if(movdao.reservedRnum(reservnum, this.user_id) == false) {
					JOptionPane.showMessageDialog(null, "예매내역이 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					int movieno = Integer.parseInt(movdao.movie.getNo());
					Ticket tck = new Ticket(movieno - 1);
					tck.modify(this.user_id, reservnum); 
					dispose();
				}
			} 
		}

	}


}


