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
import vo.MemberVO;

public class SignUp extends JFrame {

	JPanel panel;
	JLabel label, idL, passwordL, nameL, phoneL;
	TextField idT, passwordT, nameT, phoneT;
	JButton okbt, checkbt;

	public SignUp() {

		setSize(500, 400);
		setLocation(750, 200);
		setTitle("회원가입");
		panel = new JPanel(null);

		label = new JLabel("JOIN");
		idL = new JLabel("아이디 ");
		passwordL = new JLabel("비밀번호 ");
		nameL = new JLabel("이름 ");
		phoneL = new JLabel("연락처 ");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 40));
		label.setForeground(Color.black);

		idT = new TextField();
		passwordT = new TextField();
		nameT = new TextField();
		phoneT = new TextField();

		okbt = new JButton("확인"); 
		okbt.setBounds(190, 270, 80, 30); 

		checkbt = new JButton("중복확인");
		checkbt.setBounds(320, 100, 90, 20);

		label.setBounds(180, 10, 200, 80);  

		idL.setBounds(130, 103, 55, 13);
		nameL.setBounds(130, 133, 55, 13);
		passwordL.setBounds(130, 163, 55, 13);
		phoneL.setBounds(130, 193, 55, 13);

		idT.setBounds(190, 100, 120, 20);
		nameT.setBounds(190, 130, 120, 20);
		passwordT.setBounds(190, 160, 120, 20);
		phoneT.setBounds(190, 190, 120, 20);

		panel.add(label);
		panel.add(idL);
		panel.add(passwordL);
		panel.add(nameL);
		panel.add(phoneL);
		panel.add(idT);
		panel.add(passwordT);
		panel.add(nameT);
		panel.add(phoneT);
		panel.add(okbt);
		panel.add(checkbt);

		setDefaultCloseOperation(SignUp.DISPOSE_ON_CLOSE);
		add(panel);


		okbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();
				String id = idT.getText();
				String name = nameT.getText();
				String password = passwordT.getText();
				String phone = phoneT.getText();

				if(id.isEmpty() || name.isEmpty() || password.isEmpty() || phone.isEmpty() ) {
					JOptionPane.showMessageDialog( null, "정보를 모두 입력하세요", "error", JOptionPane.ERROR_MESSAGE );
				}else {
					 if(dao.loginid(id) == true) {
	                       JOptionPane.showMessageDialog(null, "중복된 아이디입니다.", "error", JOptionPane.ERROR_MESSAGE);
	                       okbt.setEnabled(false);}
					 else {
					JOptionPane.showMessageDialog( null, name + "님 가입을 축하드립니다!", "success", JOptionPane.INFORMATION_MESSAGE );
					MemberVO vo = new MemberVO(id, name, password, phone);
					dao.insert(vo);
					dispose();
					 }
				}
			}
		});

		checkbt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();
				String id = idT.getText();
				if(dao.loginid(id) == true) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.", "error", JOptionPane.ERROR_MESSAGE);
					okbt.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog( null, "사용 가능한 아이디입니다", "success", JOptionPane.INFORMATION_MESSAGE );
					okbt.setEnabled(true);
				}
			}
		});


		setVisible(true);


	}


}