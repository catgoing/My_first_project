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
import panels.LoginPanel;
import vo.MemberVO;

public class ModifyInfo extends JFrame implements ActionListener{

	JPanel panel;
	JLabel labelL, idL, passwordL, nameL, phoneL;
	TextField idT, passwordT, nameT, phoneT;
	JButton okbt, withdbt;
	MemberDAO memdao = new MemberDAO();
	MovieDAO movdao = new MovieDAO();


	public ModifyInfo() {

		setSize(500, 400);
		setLocation(750, 200);
		setTitle("정보수정");
		panel = new JPanel(null);

		labelL = new JLabel("MyPage");
		idL = new JLabel("아이디 ");
		passwordL = new JLabel("비밀번호 ");
		nameL = new JLabel("이름 ");
		phoneL = new JLabel("연락처 ");
		labelL.setFont(new Font(labelL.getFont().getName(), Font.PLAIN, 30));
		labelL.setForeground(Color.black);

		memdao.select(SignIn.ids);

		idT = new TextField();
		idT.setText(SignIn.ids);
		idT.setEditable(false);

		passwordT = new TextField();
		passwordT.setText(memdao.member.getPassword());
		nameT = new TextField();
		nameT.setText(memdao.member.getName());
		phoneT = new TextField();
		phoneT.setText(memdao.member.getPhone());

		okbt = new JButton("정보수정"); 
		okbt.setBounds(130, 270, 100, 30); 
		okbt.addActionListener(this);

		withdbt = new JButton("회원탈퇴");
		withdbt.setBounds(260, 270, 100, 30);
		withdbt.addActionListener(this);


		labelL.setBounds(180, 10, 200, 80);  

		idL.setBounds(130, 100, 55, 20);
		nameL.setBounds(130, 130, 55, 20);
		passwordL.setBounds(130, 160, 55, 20);
		phoneL.setBounds(130, 190, 55, 20);

		idT.setBounds(190, 100, 120, 20);
		nameT.setBounds(190, 130, 120, 20);
		passwordT.setBounds(190, 160, 120, 20);
		phoneT.setBounds(190, 190, 120, 20);

		panel.add(labelL);
		panel.add(idL);
		panel.add(passwordL);
		panel.add(nameL);
		panel.add(phoneL);
		panel.add(idT);
		panel.add(passwordT);
		panel.add(nameT);
		panel.add(phoneT);
		panel.add(okbt);
		panel.add(withdbt);
		add(panel);

		setDefaultCloseOperation(SignUp.DISPOSE_ON_CLOSE);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okbt) {
			String id = idT.getText();
			String name = nameT.getText();
			String password = passwordT.getText();
			String phone = phoneT.getText();

			if(SignIn.ids == null) {
				JOptionPane.showMessageDialog( null, "로그인되어 있지 않습니다. 다시 로그인해주세요", "error", JOptionPane.ERROR_MESSAGE );
				dispose();
			}
			else {

				if(id.isEmpty() || name.isEmpty() || password.isEmpty() || phone.isEmpty() ) {
					JOptionPane.showMessageDialog( null, "정보를 모두 입력하세요", "error", JOptionPane.ERROR_MESSAGE );
				}else {
					MemberVO vo = new MemberVO(id, name, password, phone);
					memdao.update(vo);
					JOptionPane.showMessageDialog( null, "정보가 수정되었습니다", "success", JOptionPane.INFORMATION_MESSAGE );
					dispose();
				}

			}

		} else if(e.getSource() == withdbt) {
			int result = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "탈퇴확인", JOptionPane.YES_NO_OPTION);

			if(result == JOptionPane.YES_OPTION) {
				if(SignIn.ids == null) {
					JOptionPane.showMessageDialog( null, "로그인되어 있지 않습니다. 다시 로그인해주세요", "error", JOptionPane.ERROR_MESSAGE );
					dispose();
				
				} else {
					
					if(movdao.reservedID(SignIn.ids) != null) {
						JOptionPane.showMessageDialog(null, "예매내역이 있어 탈퇴할 수 없습니다.");
					
					} else {
						memdao.delete(SignIn.ids);
						JOptionPane.showMessageDialog(null, "정상적으로 탈퇴처리 되었습니다.");
						dispose();
						new LoginPanel().logout();
						
					}
				}
			}

		}

	}
}