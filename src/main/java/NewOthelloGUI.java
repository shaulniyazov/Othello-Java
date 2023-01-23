package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class NewOthelloGUI extends JFrame {
    private NewOthelloModel model = new NewOthelloModel();
    MyButton[][] buttons= new MyButton[8][8];

    NewOthelloGUI(){
        this.setTitle("Othello");
        this.setSize(400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                panel.add(buttons[i][j] = new MyButton(new Point(j,i)));
                buttons[i][j].addActionListener(actionListener);
                buttons[i][j].setBackground(new Color(6, 189, 98));
            }
        }

        startGame(buttons);

        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    private void startGame(MyButton[][] buttons){
        buttons[3][3].setText(NewOthelloModel.CellState.White.toString());
        model.setPointState(3,3, NewOthelloModel.CellState.White);

        buttons[4][4].setText(NewOthelloModel.CellState.White.toString());
        model.setPointState(4,4, NewOthelloModel.CellState.White);

        buttons[4][3].setText(NewOthelloModel.CellState.Black.toString());
        model.setPointState(4,3, NewOthelloModel.CellState.Black);

        buttons[3][4].setText(NewOthelloModel.CellState.Black.toString());
        model.setPointState(3,4, NewOthelloModel.CellState.Black);


    }

    private ActionListener actionListener = e -> {
        MyButton b = (MyButton) e.getSource();

        if(model.whoseTurn().equals(NewOthelloModel.CellState.Black)
                && model.isMoveLegal(b.point.y,b.point.x, NewOthelloModel.CellState.Black)){
            b.setText(NewOthelloModel.CellState.Black.toString());
            model.setPointState(b.point.y,b.point.x, NewOthelloModel.CellState.Black);

            model.whoseTurn = true;
            updateBoard();

        }
        else if(model.whoseTurn().equals(NewOthelloModel.CellState.White)
                && model.isMoveLegal(b.point.y,b.point.x, NewOthelloModel.CellState.White)){
            b.setText(NewOthelloModel.CellState.White.toString());
            model.setPointState(b.point.y,b.point.x, NewOthelloModel.CellState.White);

            model.whoseTurn = false;
            updateBoard();

        }
//        else if(model.whoseTurn().equals(NewOthelloModel.CellState.White)
//            model.makeMove();

    };

    private void updateBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                NewOthelloModel.CellState c = model.getCellState(i,j);
                buttons[i][j].setText(c.toString());
            }
        }
    }

}
