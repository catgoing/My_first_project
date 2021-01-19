package frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import dao.Member_DAO;
import panels.LoginPanel;

public class Signin extends JFrame{

	public static String ids;
	String id, pw;
	String MNAME, RTIME, RSIT;
	String name;
	Member_DAO memdao = new Member_DAO();

	TextField IDT, PASSWORDT;
	JLabel label, idl, psl;

	JButton signinbtn;
	JButton loginbt, signupbt;
	JButton modibt, reserv;

	public Signin() {

		String id = "아이디";
		String ps = "비밀번호";

		setLayout(null);

		IDT = new TextField();
		PASSWORDT = new TextField();

		idl = new JLabel();
		idl.setText(id);
		psl = new JLabel();
		psl.setText(ps);

		idl.setBounds(130, 104, 55, 10);
		psl.setBounds(130, 135, 55, 20);


		IDT.setBounds(190, 100, 100, 20);
		PASSWORDT.setBounds(190, 135, 100, 20);

		signinbtn = new JButton("로그인");
		signinbtn.setBounds(165, 200, 90, 23);



		label = new JLabel("Login");
		label.setBounds(180,30,130,50);
		label.setOpaque(true);
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 28));

		this.add(IDT);
		this.add(PASSWORDT);
		this.add(signinbtn);
		this.add(label);
		this.add(idl);
		this.add(psl);

		setSize(400,300);
		setLocation(750, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		signinbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ids = IDT.getText();
				pw = PASSWORDT.getText();
				
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