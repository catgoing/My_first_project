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
import frame.MyReserved;
import frame.Signin;
import frame.Main_Frame;

import admin.Admin;

public class LoginPanel implements ActionListener{
	
	JButton loginbt, signupbt, logoutbt;
	JButton myinfo, reserv, statis, memManage, reservManage;
	JLabel welcom;
	Main_Frame mv;
	public static JPanel login2;
	
	
	public LoginPanel() {
		
	}
	
	public void loginp() {
		login2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		login2.setBackground(Color.lightGray);
 		welcom = new JLabel( Signin.ids + "님 환영합니다");
 		login2.add(welcom);
 		
 		
 		myinfo = new JButton("내정보");
 		myinfo.addActionListener(this);
 
 		reserv = new JButton("예매확인");
 		reserv.addActionListener(this);
 		
 		logoutbt = new JButton("로그아웃");
 		logoutbt.addActionListener(this);
 		
 		login2.add(myinfo);
 		login2.add(reserv);
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

 		
 		myinfo = new JButton("내정보");
 		myinfo.addActionListener(this);
 		
 		statis = new JButton("영화통계");
 		statis.addActionListener(this);
 		
 		memManage = new JButton("회원관리");
 		memManage.addActionListener(this);
 		
 		reservManage = new JButton("예매관리");
 		reservManage.addActionListener(this);
 		
		logoutbt = new JButton("로그아웃");
 		logoutbt.addActionListener(this);
		
 		
 		login2.add(myinfo);
 		login2.add(statis);
 		login2.add(memManage);
 		login2.add(reservManage);
 		login2.add(logoutbt);
 		
     	mv.login.setVisible(false);
     	mv.cont.remove(mv.login);
     	mv.cont.add(login2, BorderLayout.NORTH); // 로그인 이후 상단패널 변경
         
       }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myinfo) {
			new ModifyInfo();
		}
		else if (e. getSource() == reserv) {
			new MyReserved(Signin.ids);
		}
		else if (e. getSource() == logoutbt) {
			Signin.ids = null;
			logout();
		}
		else if (e. getSource() == statis) {
			Admin admin = new Admin();
			admin.mov_Statis();
		}
		else if (e. getSource() == memManage) {
			Admin admin = new Admin();
			admin.mem_Manage();
			
		}
		else if (e. getSource() == reservManage) {
			Admin admin = new Admin();
			admin.mov_Manage();
			
		}

	}	            
 

	
}
