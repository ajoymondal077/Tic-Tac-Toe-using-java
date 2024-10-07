import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class TicTacToe extends JFrame
{
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel = new JLabel("Player X's turn");
    public TicTacToe()
    {
        setTitle("Tic Tac Toe");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3,3));
        initializeBoard(boardPanel);
        add(boardPanel,BorderLayout.CENTER);
        add(statusLabel,BorderLayout.SOUTH);
    }
    private void initializeBoard(JPanel boardPanel)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial",Font.PLAIN,60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i,j));
                boardPanel.add(buttons[i][j]);
            }
        }
    }
    private class ButtonClickListener implements ActionListener
    {
        private int row,col;
        public ButtonClickListener(int row,int col)
        {
            this.row= row;
            this.col = col;
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(buttons[row][col].getText().equals("-"))
            {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setEnabled(false);
                if(checkWinner())
                {
                    statusLabel.setText("Player "+currentPlayer+" wins!");
                    disableAllButtons();
                }
                else if(isBoardFull())
                {
                    statusLabel.setText("It's a draw");
                }
                else
                {
                    switchPlayer();
                    statusLabel.setText("Player "+currentPlayer+"'s Turn");
                }
            }
        }
    }
    private void switchPlayer()
    {
        currentPlayer =(currentPlayer == 'X')?'O':'X';
    }
    private boolean checkWinner()
    {
        for(int i=0;i<3;i++)
        {
            if(buttons[i][0].getText().equals(String.valueOf(currentPlayer)) && buttons[i][1].getText().equals(String.valueOf(currentPlayer)) && buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
            {
                return true;
            }
            if(buttons[0][i].getText().equals(String.valueOf(currentPlayer)) && buttons[1][i].getText().equals(String.valueOf(currentPlayer)) && buttons[2][i].getText().equals(String.valueOf(currentPlayer)))
            {
                return true;
            }
        }

        if(buttons[0][0].getText().equals(String.valueOf(currentPlayer)) && buttons[1][1].getText().equals(String.valueOf(currentPlayer)) && buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
        {
            return true;
        }
        return false;
    }
    private boolean isBoardFull()
    {
        for(int i =0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(buttons[i][j].getText().equals("-"))
                {
                    return false;
                }
            }
        }
        return true;
    }
    private void disableAllButtons()
    {
        for(int i=0;i<3;i++)
        {
            for(int j =0 ;j<3;j++)
            {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->
        {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}
