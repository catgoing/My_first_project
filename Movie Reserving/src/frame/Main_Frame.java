package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import admin.Admin;
import dao.Movie_DAO;
import vo.Movie_VO;


public class Main_Frame extends JFrame implements ActionListener{
	
	JLabel title, underbar;
	JLabel[] movno, border, movname;
	ImageIcon img1, img2, img3;
	JButton[] moviebt1, movintro;
	JButton loginbt, signupbt;
	public static Container cont;
	public static JPanel login;
	Movie_DAO movdao = new Movie_DAO();
	Movie_VO movvo = new Movie_VO();
	
	public Main_Frame() {

		/***************** 레이블 *******************/
		setTitle("★ ★ ★ 영화 그 이상의 감동. BITMOVIE ★ ★ ★");
		img1 = new ImageIcon("img/parasite.jpg");
		img2 = new ImageIcon("img/wonder.jpg");
		img3 = new ImageIcon("img/demon_slayer.png");

		title = new JLabel("<     BIT MOVIE     >");
		title.setSize(318,50);
		title.setLocation(390, 00);
		title.setOpaque(true);
		title.setBackground(Color.white);
		title.setForeground(Color.black);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		
		underbar = new JLabel();
		underbar.setSize(1024, 100);
		underbar.setLocation(0, 610);
		underbar.setOpaque(true);
		underbar.setBackground(Color.lightGray);
		
		movno = new JLabel[3];
		for (int i = 0; i < movno.length; i++) {
			movno[i] = new JLabel("No." + (i+1));
			movno[i].setHorizontalAlignment(JLabel.CENTER);
			movno[i].setOpaque(true);
			movno[i].setBackground(Color.red);
			movno[i].setForeground(Color.white);
			movno[i].setFont(new Font(movno[i].getFont().getName(), Font.PLAIN, 28));
			movno[i].setBounds(35 + (i * 321), 58, 299, 35);
		}
		
		border = new JLabel[3];
		for (int i = 0; i < border.length; i++) {
			border[i] = new JLabel();
			border[i].setHorizontalAlignment(JLabel.CENTER);
			border[i].setOpaque(true);
			border[i].setBackground(Color.black);
			border[i].setBounds(27 + (i * 320), 50, 316, 50);
			
		}
		
		movname = new JLabel[3];
		for (int i = 0; i < movname.length; i++) {
			movdao.setMovie(i + 1);
			movname[i] = new JLabel(movdao.movie.getName());
			movname[i].setBounds(25 + (i * 320), 500, 325, 50);
			movname[i].setHorizontalAlignment(JLabel.CENTER);
			
		}

		/***************** 버튼 *******************/
		
		loginbt = new JButton("Login");
		loginbt.addActionListener(this);
		signupbt = new JButton("회원가입");
		signupbt.addActionListener(this);
		
		moviebt1 = new JButton[3];
		moviebt1[0] = new JButton(img1);
		moviebt1[1] = new JButton(img3);
		moviebt1[2] = new JButton(img2);
		for (int i = 0; i < moviebt1.length; i++) {
			moviebt1[i].setBounds(25 + (i * 320), 100, 320, 400);
			moviebt1[i].setContentAreaFilled(false); // 버튼 배경색 없애기
			moviebt1[i].addActionListener(this);
		}
	
		movintro = new JButton[3];
		for (int i = 0; i < movintro.length; i++) {
			movintro[i] = new JButton("영화정보");
			movintro[i].setBounds(142 + (i * 320), 560, 90, 30);
			movintro[i].addActionListener(this);
		}
		
		/******************* 패널 ********************/
		
		JPanel mainp = new JPanel();

		login = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		login.add(loginbt);
		login.add(signupbt);

		login.setBackground(Color.lightGray);
		login.setVisible(true);
	
		mainp.setBackground(Color.white);
		mainp.setLayout(null);
	
		mainp.add(title);
		mainp.add(underbar);
		
		for (int i = 0; i < movname.length; i++) {
			mainp.add(movname[i]);
		}
		for (int i = 0; i < movno.length; i++) {
			mainp.add(movno[i]);
		}
		for (int i = 0; i < border.length; i++) {
			mainp.add(border[i]);
		}
		for (int i = 0; i < moviebt1.length; i++) {
			mainp.add(moviebt1[i]);
		}
		for (int i = 0; i < movintro.length; i++) {
			mainp.add(movintro[i]);
		}

		
		cont = new Container();
		cont = getContentPane();
		cont.add(mainp, BorderLayout.CENTER);
		cont.add(login, BorderLayout.NORTH);
		
		setBounds(450, 120, 1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == loginbt) {
			new Signin();
		}
		else if (e.getSource() == signupbt) {
			new Signup();
		}
		else if (e.getSource() == moviebt1[0]) {
			reserv(0);
		}
		else if (e.getSource() == moviebt1[1]) {
			reserv(1);
		}
		else if (e.getSource() == moviebt1[2]) {
			reserv(2);
		}
		else if (e.getSource() == movintro[0]) {
			new Movie_Introduce(0);
		}
		else if (e.getSource() == movintro[1]) {
			new Movie_Introduce(1);
		}
		else if (e.getSource() == movintro[2]) {
			new Movie_Introduce(2);
		}
		
		
	}
	
	public void reserv(int movno) {
		
		
		if(Signin.ids != null && !"admin".equals(Signin.ids)) {
			new Ticket(movno);
			} else if("admin".equals(Signin.ids)) {
				Admin admin = new Admin();
				admin.mov_Reserv(movno);
			}
		else JOptionPane.showMessageDialog(null, "로그인 후 예매가가능합니다.");
		
	}

}
