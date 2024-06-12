import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener{

    Random random=new Random();
    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton startButton = new JButton("Click to Start");
    JButton[] buttons = new JButton[9];
    boolean p1Turn;
    int count=0;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        startButton.setFont(new Font("Verdana",Font.PLAIN,40));
        startButton.setBackground(new Color(30,25,40));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        textField.setBackground(new Color(30,25,40));
        textField.setForeground(new Color(255,255,255));
        textField.setFont(new Font("Verdana",Font.BOLD,60));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,80);

        
        titlePanel.add(textField);
        frame.add(startButton);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.setVisible(true);


    }

    private void firstTurn() {
        if(random.nextInt(2)==0){
            p1Turn=true;
            textField.setText("X's turn");
        }
        else{
            p1Turn=false;
            textField.setText("O's turn");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
        frame.remove(startButton);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(30,25,40));
        for(int i=0;i<9;i++){
            buttons[i]= new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setBackground(new Color(30,25,40));
            buttons[i].setFont(new Font("Verdana",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textField);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        firstTurn();
        }
        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(p1Turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        p1Turn=false;
                        textField.setText("O's turn");
                        count++;
                        if (!checkWin()){
                        if(count==9){
                            textField.setText("Draw");
                            displayReset();
                        }
                    }
                    }
                }
               else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        p1Turn=true;
                        textField.setText("X's turn");
                        count++;
                        if (!checkWin()){
                            if(count==9){
                                textField.setText("Draw");
                                displayReset();
                            }
                        }
                    }
                }
            }
        }
    }

    private void displayReset() {
        int result = JOptionPane.showConfirmDialog(buttons[7], "Do you want to play again?","Reset",JOptionPane.YES_NO_OPTION);
        if(result==JOptionPane.YES_OPTION){
            frame.dispose();
            reset();
        }
        else{
            frame.dispose();
        }
    }

    private void reset() {
        TicTacToe ticTacToe = new TicTacToe();
    }

    private boolean checkWin() {
        //horizontal
        for (int i=0;i<7;i+=3){
            if ((buttons[i].getText()==buttons[i+1].getText()) && (buttons[i+1].getText()==buttons[i+2].getText()) && (buttons[i].getText()!="")){
                if(buttons[i].getText()=="X"){
                    xWins(i,i+1,i+2);
                }
                else{
                    oWins(i,i+1,i+2);
                }
                return true;
            
            }
        }
        //vertical
        for (int i=0;i<3;i++){
            if ((buttons[i].getText()==buttons[i+3].getText()) && (buttons[i+3].getText()==buttons[i+6].getText()) && (buttons[i].getText()!="")){
                if(buttons[i].getText()=="X"){
                    xWins(i,i+3,i+6);
                }
                else{
                    oWins(i,i+3,i+6);
                }   
                return true;
            }   
        }
        //diagonal
        if ((buttons[0].getText()==buttons[4].getText()) && (buttons[4].getText()==buttons[8].getText()) && (buttons[0].getText()!="")){
            if(buttons[0].getText()=="X"){
                xWins(0, 4, 8);
            }
            else{
                oWins(0,4,8);
            }
            return true;
        }
        if ((buttons[2].getText()==buttons[4].getText()) && (buttons[4].getText()==buttons[6].getText()) && (buttons[2].getText()!="")){
            if(buttons[2].getText()=="X"){
                xWins(2 , 4, 6);
            }
            else{
                oWins(2,4,6);
            }
            return true;
        }
        return false;
}



    private void xWins(int i, int j, int k) {
        buttons[i].setBackground(Color.GREEN);
        buttons[j].setBackground(Color.GREEN);
        buttons[k].setBackground(Color.GREEN);
        buttons[i].setOpaque(true);buttons[j].setOpaque(true);buttons[k].setOpaque(true);

        for (int c=0;c<9;c++){
            if(c!=j && c!=i && c!=k)
            buttons[c].setEnabled(false);
        }
        textField.setText("X wins");       
        displayReset();
    }

    private void oWins(int i, int j, int k) {
        buttons[i].setBackground(Color.GREEN);
        buttons[j].setBackground(Color.GREEN);
        buttons[k].setBackground(Color.GREEN);
        buttons[i].setOpaque(true);buttons[j].setOpaque(true);buttons[k].setOpaque(true);
        for (int c=0;c<9;c++){
            if(c!=j && c!=i && c!=k)
            buttons[c].setEnabled(false);
        }
        textField.setText("O wins");
        displayReset();
    }

    
}
