package frame;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.MemberDAO;
import dao.MovieDAO;




///////////////////////////admin
public class MyReserv extends JFrame implements ActionListener{

	Choice movieName;   // 영화목록
	JTable table;
	JPanel panel, modifypanel, info;
	JScrollPane jscp;
	JLabel label, idL, passwordL, nameL, phoneL;
	JButton modifybt, refreshbt, cancelbt;
	Container cont;
	String header[] = {"예매번호", "영화", "상영일", "시간", "좌석"};
	String data[][];
	DefaultTableModel tmodel;
	DefaultTableCellRenderer center_ali;
	String user_id;
	MovieDAO movdao = new MovieDAO();
	

	public MyReserv(String user_id) {
		
		this.user_id = user_id;
		
		setSize(600, 400);
		setLocation(750, 200);
		setTitle("예매내역");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setBounds(200, 20, 200, 50);
		
		label = new JLabel("예매내역");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
		label.setForeground(Color.black);
		label.setBounds(205, 50, 200, 20);	

		panel.add(label);
		
		data = movdao.reservedID(this.user_id);
		
		tmodel = new DefaultTableModel(data, header);
		table = new JTable(tmodel);
		table.setEnabled(false);
		
		tbAliCenter();

		info = new JPanel();
		modifybt = new JButton("예매수정");
		cancelbt = new JButton("예매취소");
		refreshbt = new JButton("새로고침");
	
		jscp = new JScrollPane(table);
		jscp.setPreferredSize(new Dimension(500, 200));
		jscp.setLocation(100, 100);

		info.setBounds(100, 100, 400, 200);
		info.add(jscp);
		info.add(modifybt);
		info.add(cancelbt);
		info.add(refreshbt);

		cont = new Container();
		cont = getContentPane();
		cont.add(panel, BorderLayout.NORTH);
		cont.add(info, BorderLayout.CENTER);

		
		modifybt.addActionListener(this);
		cancelbt.addActionListener(this);
		refreshbt.addActionListener(this);

		setVisible(true);

		

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		  if(e.getSource() == modifybt) {
			new ModifyReserv().reserv(this.user_id);
			
		} 
		  else  if(e.getSource() == cancelbt) {
			new Cancel().cancel(this.user_id);

		} 
		  
		else if(e.getSource() == refreshbt) {
			data = movdao.reservedID(this.user_id);
			tmodel = new DefaultTableModel(data, header);
			table = new JTable(tmodel);
			jscp.getViewport().setView(table);
			jscp.getViewport().repaint();
			tbAliCenter();
			
		}

	}
	
	public void tbAliCenter() {
		center_ali = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		center_ali.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel colmodel = table.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < colmodel.getColumnCount(); i++) {
			colmodel.getColumn(i).setCellRenderer(center_ali);
		}
		
		table.getTableHeader().setReorderingAllowed(false);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		
		for (int i = 0; i < 5; i++) {
			table.getColumnModel().getColumn(i).setResizable(false);
			
		}
		
	}



}
