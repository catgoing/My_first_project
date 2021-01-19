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
import panels.LoginPanel;
import vo.Member_VO;

public class ModifyInfo extends JFrame implements ActionListener{

	JPanel panel;
	JLabel label1, id1, password1, name1, phone1;
	public TextField idT, passwordT, nameT, phoneT;
	JButton okbtn, withd;
	Main_Frame mv;
	Member_VO vo;
	Member_DAO dao = new Member_DAO();


	public ModifyInfo() {

		setSize(500, 400);
		setLocation(750, 200);
		setTitle("정보수정");
		panel = new JPanel(null);

		label1 = new JLabel("MyPage");
		id1 = new JLabel("아이디 ");
		password1 = new JLabel("비밀번호 ");
		name1 = new JLabel("이름 ");
		phone1 = new JLabel("연락처 ");
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 30));
		label1.setForeground(Color.black);

		dao.select(Signin.ids);

		idT = new TextField();
		idT.setText(Signin.ids);
		idT.setEditable(false);

		passwordT = new TextField();
		passwordT.setText(dao.member.getPassword());
		nameT = new TextField();
		nameT.setText(dao.member.getName());
		phoneT = new TextField();
		phoneT.setText(dao.member.getPhone());

		okbtn = new JButton("정보수정"); 
		okbtn.setBounds(130, 270, 100, 30); 
		okbtn.addActionListener(this);

		withd = new JButton("회원탈퇴");
		withd.setBounds(260, 270, 100, 30);
		withd.addActionListener(this);


		label1.setBounds(180, 10, 200, 80);  

		id1.setBounds(130, 100, 55, 20);
		name1.setBounds(130, 130, 55, 20);
		password1.setBounds(130, 160, 55, 20);
		phone1.setBounds(130, 190, 55, 20);

		idT.setBounds(190, 100, 120, 20);
		nameT.setBounds(190, 130, 120, 20);
		passwordT.setBounds(190, 160, 120, 20);
		phoneT.setBounds(190, 190, 120, 20);

		panel.add(label1);
		panel.add(id1);
		panel.add(password1);
		panel.add(name1);
		panel.add(phone1);
		panel.add(idT);
		panel.add(passwordT);
		panel.add(nameT);
		panel.add(phoneT);
		panel.add(okbtn);
		panel.add(withd);
		add(panel);

		setDefaultCloseOperation(Signup.DISPOSE_ON_CLOSE);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okbtn) {
			String id = idT.getText();
			String name = nameT.getText();
			String password = passwordT.getText();
			String phone = phoneT.getText();

			if(Signin.ids == null) {
				JOptionPane.showMessageDialog( null, "로그인되어 있지 않습니다. 다시 로그인해주세요", "error", JOptionPane.ERROR_MESSAGE );
				dispose();
			}
			else {

				if(id.isEmpty() || name.isEmpty() || password.isEmpty() || phone.isEmpty() ) {
					JOptionPane.showMessageDialog( null, "정보를 모두 입력하세요", "error", JOptionPane.ERROR_MESSAGE );
				}else {
					vo = new Member_VO(id, name, password, phone);
					dao.update(vo);
					JOptionPane.showMessageDialog( null, "정보가 수정되었습니다", "success", JOptionPane.INFORMATION_MESSAGE );
					dispose();
				}

			}

		} else if(e.getSource() == withd) {
			int result = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "탈퇴확인", JOptionPane.YES_NO_OPTION);

			if(result == JOptionPane.YES_OPTION) {
				if(Signin.ids == null) {
					JOptionPane.showMessageDialog( null, "로그인되어 있지 않습니다. 다시 로그인해주세요", "error", JOptionPane.ERROR_MESSAGE );
					dispose();
				} else {
					dao.delete(Signin.ids);
					JOptionPane.showMessageDialog(null, "정상적으로 탈퇴처리 되었습니다.");
					dispose();

					new LoginPanel().logout();
				}
			}

		}

	}
}