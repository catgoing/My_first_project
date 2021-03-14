package admin;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.MemberDAO;
import dao.MovieDAO;
import frame.Main;
import frame.MyReserv;
import frame.SignUp;
import frame.Ticket;
import vo.MemberVO;
import vo.MovieVO;

public class Admin extends JFrame implements ActionListener, ItemListener {
	
	Choice movieName;   // 영화목록
	JPanel panel, modifypanel, info;
	JTextArea memlist, memlist2;
	JScrollPane jscp;
	JLabel memMng_label;

	JButton managebt, enter, refresh;
	JButton okbtn, withd;
	JButton mov_id;

	JLabel memUpd_label, memUpd_name, memUpd_id, memUpd_password, memUpd_phone;
	JButton memUpd_okbtn, memUpd_withd;

	JLabel mngMov_ttl, mngMov_id;
	TextField mngMov_idT;
	JButton mngMov_okbtn;
	
	JLabel mngRsv_label, mngRsv_id;
	JButton	mngRsv_okbtn;
	TextField mngRsv_idT;
	
	JLabel movStat_label, movStat_name;
	JTextArea statis, statis2;
	
	public TextField idT, passwordT, nameT, phoneT;
	JTextField searchbar;
	Container cont = new Container();
	Main mv;
	String ttlaud = "총 관객수 : ";
	String ttlsale = "총 매출액 : ";
	int movie_no;
	
	MemberDAO memdao = new MemberDAO();
	MovieDAO movdao = new MovieDAO();
	MovieVO movvo = new MovieVO();
	
	
	public Admin() {
		
	}
	
	public void mem_Manage() {
		
		
		
		setSize(500, 400);
		setLocation(750, 200);
		setTitle("회원관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		
		panel.setBounds(200, 20, 200, 50);

		memMng_label = new JLabel("회원관리");
		memMng_label.setFont(new Font(memMng_label.getFont().getName(), Font.PLAIN, 20));
		memMng_label.setForeground(Color.black);
		memMng_label.setBounds(205, 50, 200, 20);	

		panel.add(memMng_label);

		JPanel searchpanel = new JPanel();
		searchpanel.setBounds(150, 400, 400, 200);
		JLabel ID = new JLabel("ID:");
		searchbar = new JTextField(12);
		enter = new JButton("Enter");
		searchpanel.add(ID);
		searchpanel.add(searchbar);
		searchpanel.add(enter);


		info = new JPanel();
		managebt = new JButton("회원수정");
		refresh = new JButton("새로고침");
		memlist = new JTextArea();
		memlist2 = new JTextArea();
		memlist2.append("ID\t이름\t패스워드\t연락처");

		JScrollPane idname = new JScrollPane(statis2, JScrollPane.VERTICAL_SCROLLBAR_NEVER
											, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		idname.setPreferredSize(new Dimension(400, 20));
		memlist2.setEditable(false);

		jscp = new JScrollPane(memlist);
		jscp.setPreferredSize(new Dimension(400, 200));
		jscp.setLocation(100, 100);

		String meminfo = memdao.select2().toString();
		memlist.append(meminfo);
		memlist.setEditable(false);

		info.setBounds(100, 100, 400, 200);
		info.add(idname, BorderLayout.NORTH);
		info.add(jscp);
		info.add(managebt);
		info.add(refresh);
		
		cont = new Container();
		cont = getContentPane();
		cont.add(panel, BorderLayout.NORTH);
		cont.add(searchpanel, BorderLayout.SOUTH);
		cont.add(info, BorderLayout.CENTER);

		enter.addActionListener(this);
		managebt.addActionListener(this);
		refresh.addActionListener(this);

		setVisible(true);

		
	}
	
	public void mem_Update(String id) {
		
		memdao.select(id);

		setSize(500, 400);
		setLocation(1050, 200);
		setTitle("회원수정");
		panel = new JPanel(null);

		memUpd_label = new JLabel("회원수정");
		memUpd_id = new JLabel("아이디 ");
		memUpd_password = new JLabel("비밀번호 ");
		memUpd_name = new JLabel("이름 ");
		memUpd_phone = new JLabel("연락처 ");
		memUpd_label.setFont(new Font(memUpd_label.getFont().getName(), Font.PLAIN, 30));
		memUpd_label.setForeground(Color.black);


		idT = new TextField();
		passwordT = new TextField();
		nameT = new TextField();
		phoneT = new TextField();
		
		idT.setText(memdao.member.getId());
		passwordT.setText(memdao.member.getPassword());
		nameT.setText(memdao.member.getName());
		phoneT.setText(memdao.member.getPhone());

		memUpd_okbtn = new JButton("수정하기"); 
		memUpd_okbtn.setBounds(130, 270, 100, 30); 

		memUpd_withd = new JButton("회원탈퇴");
		memUpd_withd.setBounds(260, 270, 100, 30);
		
		memUpd_okbtn.addActionListener(this);
		memUpd_withd.addActionListener(this);

		memUpd_label.setBounds(180, 10, 200, 80);  

		memUpd_id.setBounds(130, 100, 55, 20);
		memUpd_name.setBounds(130, 130, 55, 20);
		memUpd_password.setBounds(130, 160, 55, 20);
		memUpd_phone.setBounds(130, 190, 55, 20);

		idT.setBounds(190, 100, 120, 20);
		nameT.setBounds(190, 130, 120, 20);
		passwordT.setBounds(190, 160, 120, 20);
		phoneT.setBounds(190, 190, 120, 20);

		panel.add(memUpd_label);
		panel.add(memUpd_id);
		panel.add(memUpd_password);
		panel.add(memUpd_name);
		panel.add(memUpd_phone);
		panel.add(idT);
		panel.add(passwordT);
		panel.add(nameT);
		panel.add(phoneT);
		panel.add(memUpd_okbtn);
		panel.add(memUpd_withd);
		add(panel);

		
		setDefaultCloseOperation(SignUp.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	
	}
	
	public void mov_Manage() {
		
		setSize(500, 300);
		setLocation(750, 200);
		setTitle("예매관리");
		panel = new JPanel(null);

		mngMov_ttl = new JLabel("예매관리");
		mngMov_ttl.setFont(new Font(mngMov_ttl.getFont().getName(), Font.PLAIN, 30));
		mngMov_ttl.setForeground(Color.black);
		mngMov_ttl.setBounds(180, 10, 200, 80);  

		mngMov_id = new JLabel("아이디 ");
		mngMov_id.setBounds(130, 100, 55, 20);

		mngMov_idT = new TextField();
		mngMov_idT.setBounds(190, 100, 120, 20);

		mngMov_okbtn = new JButton("확인"); 
		mngMov_okbtn.setBounds(190, 150, 100, 30); 
		mngMov_okbtn.addActionListener(this);



		panel.add(mngMov_ttl);
		panel.add(mngMov_id);
		panel.add(mngMov_idT);
		panel.add(mngMov_okbtn);

		add(panel);
		setDefaultCloseOperation(SignUp.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}

	public void mov_Reserv(int movie_no) {
		
		this.movie_no = movie_no;
		setSize(500, 300);
		setLocation(750, 200);
		setTitle("관리자 예매");
		panel = new JPanel(null);

		mngRsv_label = new JLabel("관리자 예매");
		mngRsv_label.setFont(new Font(mngRsv_label.getFont().getName(), Font.PLAIN, 30));
		mngRsv_label.setForeground(Color.black);
		mngRsv_label.setBounds(180, 10, 200, 80);  

		mngRsv_id = new JLabel("아이디 ");
		mngRsv_id.setBounds(130, 100, 55, 20);

		mngRsv_idT = new TextField();
		mngRsv_idT.setBounds(190, 100, 120, 20);

		mngRsv_okbtn = new JButton("확인"); 
		mngRsv_okbtn.setBounds(190, 150, 100, 30); 
		mngRsv_okbtn.addActionListener(this);

		panel.add(mngRsv_label);
		panel.add(mngRsv_id);
		panel.add(mngRsv_idT);
		panel.add(mngRsv_okbtn);
		add(panel);

		setDefaultCloseOperation(SignUp.DISPOSE_ON_CLOSE);
		setVisible(true);

		
	}
	
	public void mov_Statis() {
		//TODO
		
		setSize(500, 400);
		setLocation(750, 200);
		setTitle("영화통계");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel(null);
		movStat_label = new JLabel("영화통계");
		movStat_label.setFont(new Font(movStat_label.getFont().getName(), Font.PLAIN, 20));
		movStat_label.setForeground(Color.black);

		movStat_name = new JLabel("영화제목 ");
		statis = new JTextArea();
				
		movieName = new Choice();
				
		movieName.add(getMovieName(1));
		movieName.add(getMovieName(2));
		movieName.add(getMovieName(3));
		movieName.select(0);
		itemStateSelected(0);
		
		movieName.addItemListener(this);
		
		movStat_label.setBounds(205, 50, 200, 20);									
		movStat_name.setBounds(130, 87, 55, 50);
		movieName.setBounds(190, 100, 160, 20);
		statis.setBounds(125, 140, 250, 100);
		
		panel.add(movStat_label);
		panel.add(movStat_name);
		panel.add(movieName);
		panel.add(statis);
		add(panel);
		setVisible(true);
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == enter) {
			String member = memdao.select3(searchbar.getText());
			if(!"".equals(member)) {
				memlist.setText("");
				memlist.append(member);
			}
		} else if(e.getSource() == managebt) {
			new Admin().mem_Update(searchbar.getText());
			

		} else if(e.getSource() == refresh) {
			String member = memdao.select2().toString();
			if(!"".equals(member)) {
				memlist.setText("");
				memlist.append(member);
			}
		} else if(e.getSource() == memUpd_okbtn) {
			String id = idT.getText();
			String name = nameT.getText();
			String password = passwordT.getText();
			String phone = phoneT.getText();


			if(id.isEmpty() || name.isEmpty() || password.isEmpty() || phone.isEmpty() ) {
				JOptionPane.showMessageDialog( null, "정보를 모두 입력하세요", "error", JOptionPane.ERROR_MESSAGE );
			}else {
				MemberVO vo = new MemberVO(id, name, password, phone);
				memdao.update(vo);
				JOptionPane.showMessageDialog( null, "정보가 수정되었습니다", "success", JOptionPane.INFORMATION_MESSAGE );
				
				dispose();
			}
		} else if(e.getSource() == memUpd_withd) {
			int result = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "탈퇴확인", JOptionPane.YES_NO_OPTION);

			if(result == JOptionPane.YES_OPTION) {
				memdao.delete(idT.getText());
				JOptionPane.showMessageDialog(null, "정상적으로 탈퇴처리 되었습니다.");
				dispose();


			}
		} else if(e.getSource() == mngMov_okbtn) {
			new MyReserv(mngMov_idT.getText());
			
		} else if(e.getSource() == mngRsv_okbtn) {
			memdao.id = mngRsv_idT.getText();
			new Ticket(this.movie_no);
		}

		
	}
	
	public void itemStateSelected(int no) {
		movdao.setMovie(no + 1);
		
		String movieN = movdao.movie.getName();		
		String rsit = movdao.movieStatisAud(movieN);
		int aud = (rsit.length() / 3 + 1);
		String sales = movdao.movieStatisSales(movieN).trim();;
		
		statis.append(ttlaud + aud + "명" + "\n") ;
		statis.append("\n");
		statis.append(ttlsale + sales + "원") ;
		
	}
	
	
	public void itemStateChanged(ItemEvent e) {
		
		int index = movieName.getSelectedIndex();
		movdao.setMovie(index + 1);
		
		String movieN = movdao.movie.getName();		
		String rsit = movdao.movieStatisAud(movieN);
		int aud = (rsit.length() / 3 + 1);
		String sales = movdao.movieStatisSales(movieN).trim();;
		
		statis.setText("");
		statis.append(ttlaud + aud + "명" + "\n") ;
		statis.append("\n");
		statis.append(ttlsale + sales + "원") ;
		
	}
	
	private String getMovieName(int no) {
		movdao.setMovie(no);
		
		return movdao.movie.getName();
	}
	
}


