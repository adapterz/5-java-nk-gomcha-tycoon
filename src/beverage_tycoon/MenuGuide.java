package beverage_tycoon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuGuide extends JFrame {
    public MenuGuide(){
        gameGuide();//게임 가이드 섹션
        pressEnter();//다음 페이지 안내 섹션
        gameRuleImage();//룰 설명 이미지 섹션
        defaultScreen();//기본 프레임 세팅

    }

    public void gameGuide(){
        JPanel menuGuideSection = new JPanel();
        JLabel menuGuide = new JLabel("<HTML><body style='text-align:center;'>"+ User.userName +" 사장님!<br> 곰차 운영 규칙입니다.</body></HTML>");
        Font menuGuideTitleFont = new Font("S-Core Dream", Font.BOLD, 40);
        menuGuide.setFont(menuGuideTitleFont);
        menuGuideSection.setBounds(0,140, 478,100);
        menuGuideSection.setBackground(Color.white);
        menuGuideSection.add(menuGuide);
        add(menuGuideSection);
    }

    public void pressEnter(){
        JPanel nextPageSection = new JPanel();
        JLabel nextPage = new JLabel("다음 화면을 보려면 엔터를 누르세요");
        Font nextPageFont = new Font("S-Core Dream", Font.BOLD, 20);
        nextPage.setFont(nextPageFont);
        nextPageSection.setBounds(0,250,478,30);
        nextPageSection.setBackground(Color.white);
        nextPageSection.add(nextPage);
        add(nextPageSection);

        JTextField pressEnter = new JTextField();
        add(pressEnter);
        pressEnter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    new Game();
                    setVisible(false);
                }
            }
        });
    }

    public void gameRuleImage(){
        Intro.ImagePanel menuImage = new Intro.ImagePanel(new ImageIcon("src/images/타이쿤_게임룰_1.png").getImage());
        menuImage.setBounds(50,285,478,540);
        getContentPane().add(menuImage);
        menuImage.setLayout(null);
    }

    public final void defaultScreen(){
        Intro.ImagePanel roofImage = new Intro.ImagePanel(new ImageIcon("src/images/tycoon_roof.png").getImage());
        roofImage.setBounds(0,-10,478,111);
        getContentPane().add(roofImage);
        roofImage.setLayout(null);

        setTitle("GOMCHA TYCOON");
        setSize(478,888);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
