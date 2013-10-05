import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.Font;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;




public class User implements ActionListener{
	JButton checkAnswer, giveUp, getCeleb;
	JPanel  frame2, frame, centerPanel;
	JFrame window;
	JScrollPane scrollLeftPage, scrollRightPage;
	JTextArea pictureArea;
	JTextField whoDoneIt;
	JLabel title;

	public User(){
		title = new JLabel("Whodunit??: Celebrity Edition");
		title.setFont(new Font("Tahoma", Font.BOLD, 25));
		Color d = new Color (144, 13, 100);
		title.setForeground(d);
		frame = new JPanel(new BorderLayout());
		frame2 = new JPanel(new FlowLayout());
		window = new JFrame("Whodunit??: Celebrity Edition");
		pictureArea = new JTextArea(100, 100);
		d = new Color (151, 27, 182);
		checkAnswer = new JButton("Check answer!");
		giveUp = new JButton("Give up :(");
		getCeleb= new JButton("Get Celeb");
		checkAnswer.setForeground(d);
		d = new Color (149, 20, 37);
		giveUp.setForeground(d);
		giveUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollLeftPage = new JScrollPane(frame, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRightPage = new JScrollPane(frame2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		whoDoneIt= new JTextField(15);
		centerPanel = new JPanel(new GridLayout(3,1));
		d = new Color (253, 228, 225);
		frame.setBackground(d);
		centerPanel.setBackground(d);

		centerPanel.add(whoDoneIt);
		centerPanel.add(checkAnswer);
		centerPanel.add(getCeleb);
		frame.add(title, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(giveUp, BorderLayout.SOUTH);
		whoDoneIt.setText("Who done it?");

		window.add(scrollLeftPage);
		window.add(scrollRightPage);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true, scrollLeftPage, scrollRightPage);
		split.setOneTouchExpandable(true);
		window.getContentPane().add(split);


		//3. set layout and close operation
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//5. listeners
		checkAnswer.addActionListener(this);
		giveUp.addActionListener(this);
		getCeleb.addActionListener(this);

		//6. set size
		split.setDividerLocation(410);
		frame.setSize(100, 100);
		frame2.setSize(200,200);
		window.setSize(1000, 600);


		//7. set visible
		scrollLeftPage.setVisible(true);
		scrollRightPage.setVisible(true);
		frame.setVisible(true);
		frame2.setVisible(true);
		window.setVisible(true);

	}

	//action listeners for the various buttons
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(checkAnswer))
			check();
		else {
			if (arg0.getSource().equals(giveUp))
				giveUp();
			else {
				if (arg0.getSource().equals(getCeleb))
					frame2.add(new miley());
			}
		}
	}
	private void check (){
		//says if they are right or wrong
		if (whoDoneIt.getText().toLowerCase().contains(Parser.name.toLowerCase())){
			JOptionPane.showMessageDialog(null, "That is correct!");
		}

		else {
			JOptionPane.showMessageDialog(null, "Try Again!");
		}
	}
	private void giveUp(){
		//gives the name of the tweeter
		JOptionPane.showMessageDialog(null, "It was: " + Parser.name.toLowerCase());
	}
	public class miley extends JPanel {
		/**
		 * 
		 */
		Parser P = new Parser();
		private static final long serialVersionUID = 266476521759911332L;
		private ImageIcon image=null;

		public miley() {
			frame2.removeAll();
			try {
				URL	imgLink = new URL(P.getImage());
				image = new ImageIcon(ImageIO.read(imgLink));
				add(new JLabel(image));
				frame2.updateUI();
			}
			catch (Exception e){
				e.printStackTrace();
				System.exit(1);
			}    
		}
		/**
		 * 
		 */


		 //private static final long serialVersionUID = 1L;
		//this converts the picture link to an image that can be displayed 


	}
}
