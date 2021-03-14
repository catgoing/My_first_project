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
import dao.MovieDAO;
import vo.MovieVO;


public class Main extends JFrame implements ActionListener{

	JLabel title, underbar;
	JLabel[] movno, border, movname;
	JButton[] reservebt, introbt;
	JButton loginbt, signupbt;
	public static Container cont;
	public static JPanel login;
	public static ImageIcon[] img = new ImageIcon[3];
	final String[] imgstr = { "img/parasite.jpg", "img/demon_slayer.png", "img/wonder.jpg" };
	
	
	MovieDAO movdao = new MovieDAO();
	
	
	MovieVO movvo = new MovieVO();

	public Main() {

		/***************** 레이블 *******************/
		setTitle("★ ★ ★ 영화 그 이상의 감동. BITMOVIE ★ ★ ★");

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

		reservebt = new JButton[3];
		for (int i = 0; i < reservebt.length; i++) {
			img[i] = new ImageIcon(imgstr[i]);
			int n = i;
			reservebt[i] = new JButton(img[i]);
			reservebt[i].setBounds(25 + (i * 320), 100, 320, 400);
			reservebt[i].setContentAreaFilled(false); // 버튼 배경색 없애기
			ActionListener rsv = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reserve(n);
				}
			};
			reservebt[i].addActionListener(rsv);
		}

		introbt = new JButton[3];
		for (int i = 0; i < introbt.length; i++) {
			int n = i;
			introbt[i] = new JButton("영화정보");
			introbt[i].setBounds(142 + (i * 320), 560, 90, 30);
			ActionListener intro = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MovieIntro(n);
				}
			};
			introbt[i].addActionListener(intro);
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

		for (int i = 0; i < movno.length; i++) {
			mainp.add(movno[i]);
			mainp.add(movname[i]);
			mainp.add(border[i]);
			mainp.add(reservebt[i]);
			mainp.add(introbt[i]);
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
			new SignIn();
		}
		else if (e.getSource() == signupbt) {
			new SignUp();
		}

	}

	public void reserve(int movno) {

		if(SignIn.ids != null && !"admin".equals(SignIn.ids)) {
			new Ticket(movno);
		} else if("admin".equals(SignIn.ids)) {
			Admin admin = new Admin();
			admin.mov_Reserv(movno);
		}
		else JOptionPane.showMessageDialog(null, "로그인 후 예매가 가능합니다.");

	}

}
