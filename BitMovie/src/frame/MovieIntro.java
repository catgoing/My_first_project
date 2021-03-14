package frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dao.MovieDAO;
import vo.MovieVO;

public class MovieIntro extends JFrame{

	JPanel panel;
	JLabel label, text1, star, pic;
	JTextArea text2;
	
	MovieVO mv = new MovieVO();
	MovieDAO mdao = new MovieDAO();
	
	
	public MovieIntro(int movieNo) {

		mdao.setMovie(movieNo + 1);

		setSize(600, 400);
		setTitle("영화정보");
		panel = new JPanel(null);

		pic = new JLabel(Main.img[movieNo]);
		pic.setBounds(330, 80, 230, 250);
 

		label = new JLabel(" " + mdao.movie.getName());
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 30));
		label.setForeground(Color.black);
		label.setBounds(40, 2, 520, 70); 
		label.setOpaque(true);
		label.setForeground(Color.white);
		label.setBackground(Color.black);

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

		panel.add(label);
		panel.add(text1);
		panel.add(text2);
		panel.add(pic);
		panel.add(star);

		add(panel);
		setVisible(true);


	}

}
