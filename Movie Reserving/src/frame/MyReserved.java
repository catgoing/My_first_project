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

import dao.Member_DAO;
import dao.Movie_DAO;




///////////////////////////admin
public class MyReserved extends JFrame implements ActionListener{

	Choice movieName;   // 영화목록
	JTable table;
	JPanel panel, modifypanel, info;
	JScrollPane jscp;
	JLabel label, name, id1, password1, name1, phone1;
	JButton modifybt, refresh, cancel;
	Container cont;
	Movie_DAO movdao = new Movie_DAO();
	String header[] = {"예매번호", "영화", "상영일", "시간", "좌석"};
	String data[][];
	DefaultTableModel tmodel;
	DefaultTableCellRenderer center_ali;
	String user_id;
	

	public MyReserved(String user_id) {
		
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
		
		data = movdao.reserved_ID(this.user_id);
		
		tmodel = new DefaultTableModel(data, header);
		table = new JTable(tmodel);
		table.setEnabled(false);
		
		tbAliCenter();

		info = new JPanel();
		modifybt = new JButton("예매수정");
		cancel = new JButton("예매취소");
		refresh = new JButton("새로고침");
	
		jscp = new JScrollPane(table);
		jscp.setPreferredSize(new Dimension(500, 200));
		jscp.setLocation(100, 100);

		info.setBounds(100, 100, 400, 200);
		info.add(jscp);
		info.add(modifybt);
		info.add(cancel);
		info.add(refresh);

		cont = new Container();
		cont = getContentPane();
		cont.add(panel, BorderLayout.NORTH);
		cont.add(info, BorderLayout.CENTER);

		
		modifybt.addActionListener(this);
		cancel.addActionListener(this);
		refresh.addActionListener(this);

		setVisible(true);

		

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		  if(e.getSource() == modifybt) {
			new ModifyReserv().reserv(this.user_id);
			
		} 
		  else  if(e.getSource() == cancel) {
			new Cancel().cancel(this.user_id);

		} 
		  
		else if(e.getSource() == refresh) {
			data = movdao.reserved_ID(this.user_id);
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
