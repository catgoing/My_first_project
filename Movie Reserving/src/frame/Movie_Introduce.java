package frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dao.Movie_DAO;
import vo.Movie_VO;

public class Movie_Introduce extends JFrame{

	JPanel panel;
	JLabel label1, text1, star;
	JTextArea text2;
	ImageIcon[] img = new ImageIcon[3];
	JButton btn1;
	Movie_VO mv = new Movie_VO();
	Movie_DAO mdao = new Movie_DAO();
	final String img0 = "img/parasite.jpg";
	final String img1 = "img/demon_slayer.png";
	final String img2 = "img/wonder.jpg";


	public Movie_Introduce(int movieNo) {

		mdao.setMovie(movieNo + 1);

		setSize(600, 400);
		setTitle("영화정보");
		panel = new JPanel(null);


		img[0] = new ImageIcon(img0);
		img[1] = new ImageIcon(img1);
		img[2] = new ImageIcon(img2);

		btn1 = new JButton(img[movieNo]);
		btn1.setBounds(330, 80, 230, 250);
		btn1.setContentAreaFilled(false); 

		label1 = new JLabel(" " + mdao.movie.getName());
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 30));
		label1.setForeground(Color.black);
		label1.setBounds(40, 2, 520, 70); 
		label1.setOpaque(true);
		label1.setForeground(Color.white);
		label1.setBackground(Color.black);

		text1 = new JLabel("<html>장르  :  " + mdao.movie.getType() + "<br>개봉일  :  " + mdao.movie.getRelease() + "</html>");
		text1.setBounds(40, 80, 280, 44); 
		text1.setFont(new Font(text1.getFont().getName(), Font.PLAIN, 14));
		text1.setOpaque(true);
		text1.setBackground(Color.LIGHT_GRAY);

		text2 = new JTextArea(mdao.movie.getInfo());
		text2.setLineWrap(true);
		text2.setBounds(40, 150, 280, 180); 
		text2.setFont(new Font(text2.getFont().getName(), Font.PLAIN, 11));
		text2.setOpaque(true);
		text2.setBackground(Color.white);

		
		star = new JLabel("★ " + mdao.movie.getGrade());
		star.setBounds(40, 110, 280, 50); 
		star.setFont(new Font(text1.getFont().getName(), Font.PLAIN, 13));
		star.setOpaque(true);
		star.setBackground(Color.LIGHT_GRAY);
		star.setForeground(Color.red);

		panel.add(label1);
		panel.add(text1);
		panel.add(text2);
		panel.add(btn1);
		panel.add(star);

		add(panel);
		setVisible(true);


	}

}
