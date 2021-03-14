package frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import dao.MemberDAO;
import panels.LoginPanel;

public class SignIn extends JFrame{

	public static String ids;
	String id, pw;
	TextField idT, pwT;
	JLabel label, idL, pwL;
	JButton signinbt, loginbt;
	MemberDAO memdao = new MemberDAO();

	public SignIn() {

		String id = "아이디";
		String pass = "비밀번호";

		setLayout(null);

		idT = new TextField();
		pwT = new TextField();

		idL = new JLabel();
		idL.setText(id);
		pwL = new JLabel();
		pwL.setText(pass);

		idL.setBounds(130, 104, 55, 10);
		pwL.setBounds(130, 135, 55, 20);


		idT.setBounds(190, 100, 100, 20);
		pwT.setBounds(190, 135, 100, 20);

		signinbt = new JButton("로그인");
		signinbt.setBounds(165, 200, 90, 23);

		label = new JLabel("Login");
		label.setBounds(180,30,130,50);
		label.setOpaque(true);
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 28));

		this.add(idT);
		this.add(pwT);
		this.add(signinbt);
		this.add(label);
		this.add(idL);
		this.add(pwL);

		setSize(400,300);
		setLocation(750, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		signinbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ids = idT.getText();
				pw = pwT.getText();
				
				boolean b = memdao.login(ids, pw);
				
				if(!ids.equals("admin") && b == true) {  
					LoginPanel lg = new LoginPanel();
					lg.loginp();
					dispose();
				}
				else if (ids.equals("admin") && b == true) {
					LoginPanel lg = new LoginPanel();
					lg.loginp_Admin();
					dispose();
				}
				
			}
		});
	}

}