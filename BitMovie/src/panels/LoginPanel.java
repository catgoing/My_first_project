package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.ModifyInfo;
import frame.MyReserv;
import frame.SignIn;
import frame.Main;

import admin.Admin;

public class LoginPanel implements ActionListener{
	
	JButton loginbt, signupbt, logoutbt;
	JButton myinfobt, reservbt, statisbt, memManagebt, reservManagebt;
	JLabel welcom;
	Main mv;
	public static JPanel login2;
	
	
	public LoginPanel() {
		
	}
	
	public void loginp() {
		login2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		login2.setBackground(Color.lightGray);
 		welcom = new JLabel( SignIn.ids + "님 환영합니다");
 		login2.add(welcom);
 		
 		
 		myinfobt = new JButton("내정보");
 		myinfobt.addActionListener(this);
 
 		reservbt = new JButton("예매확인");
 		reservbt.addActionListener(this);
 		
 		logoutbt = new JButton("로그아웃");
 		logoutbt.addActionListener(this);
 		
 		login2.add(myinfobt);
 		login2.add(reservbt);
 		login2.add(logoutbt);
 		
 		
 		mv.login.setVisible(false);
     	mv.cont.remove(mv.login);
     	mv.cont.add(login2, BorderLayout.NORTH); // 로그인 이후 상단패널 변경
         
       }
	

	public void logout() { // 로그인 이전 상단패널로 복구
		login2.setVisible(false);
		mv.cont.remove(login2);
    	mv.cont.add(mv.login);
    	mv.login.setVisible(true);
		

	}
	
	
      // Admin Login Panel
	public void loginp_Admin() {
		login2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		login2.setBackground(Color.lightGray);
 		welcom = new JLabel( "관리자 로그인 ");
 		login2.add(welcom);

 		
 		myinfobt = new JButton("내정보");
 		myinfobt.addActionListener(this);
 		
 		statisbt = new JButton("영화통계");
 		statisbt.addActionListener(this);
 		
 		memManagebt = new JButton("회원관리");
 		memManagebt.addActionListener(this);
 		
 		reservManagebt = new JButton("예매관리");
 		reservManagebt.addActionListener(this);
 		
		logoutbt = new JButton("로그아웃");
 		logoutbt.addActionListener(this);
		
 		
 		login2.add(myinfobt);
 		login2.add(statisbt);
 		login2.add(memManagebt);
 		login2.add(reservManagebt);
 		login2.add(logoutbt);
 		
     	mv.login.setVisible(false);
     	mv.cont.remove(mv.login);
     	mv.cont.add(login2, BorderLayout.NORTH); // 로그인 이후 상단패널 변경
         
       }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myinfobt) {
			new ModifyInfo();
		}
		else if (e. getSource() == reservbt) {
			new MyReserv(SignIn.ids);
		}
		else if (e. getSource() == logoutbt) {
			SignIn.ids = null;
			logout();
		}
		else if (e. getSource() == statisbt) {
			Admin admin = new Admin();
			admin.mov_Statis();
		}
		else if (e. getSource() == memManagebt) {
			Admin admin = new Admin();
			admin.mem_Manage();
			
		}
		else if (e. getSource() == reservManagebt) {
			Admin admin = new Admin();
			admin.mov_Manage();
			
		}

	}	            
 

	
}
