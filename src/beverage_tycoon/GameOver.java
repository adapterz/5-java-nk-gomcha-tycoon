package beverage_tycoon;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame{
    public GameOver(){
        defaultScreen();
        gameOverScreen();
    }

    public void defaultScreen(){
        Intro.ImagePanel roofImage
                = new Intro.ImagePanel(new ImageIcon("src/images/tycoonRoof.png").getImage());
        roofImage.setBounds(0,-10,478,111);
        getContentPane().add(roofImage);
        roofImage.setLayout(null);

        setTitle("Game Over");
        setSize(478,888);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void gameOverScreen(){
        Intro.ImagePanel mainCharacter
                = new Intro.ImagePanel(new ImageIcon("src/images/tycoonCharacter.png")
                .getImage().getScaledInstance(480,600, Image.SCALE_SMOOTH));
        mainCharacter.setBounds(0,370,480,540);
        getContentPane().add(mainCharacter);
        mainCharacter.setLayout(null);

        JPanel titleSection = new JPanel();
        JPanel resultSection = new JPanel();
        JLabel gameOver = new JLabel("GAME OVER");
        JLabel gameOverTitle = new JLabel("오늘의 곰차 영업을 종료합니다!");

        Font gameTitleFont = new Font("S-Core Dream", Font.BOLD, 45);
        Font gameStartTitleFont = new Font("S-Core Dream", Font.BOLD, 30);
        gameOver.setFont(gameTitleFont);
        gameOverTitle.setFont(gameStartTitleFont);

        titleSection.setBackground(Color.white);
        titleSection.setBounds(0,200, 478,60);
        resultSection.setBackground(Color.white);
        resultSection.setBounds(0,300, 478,100);

        titleSection.add(gameOver);
        resultSection.add(gameOverTitle);
        add(titleSection);
        add(resultSection);
    }
}
