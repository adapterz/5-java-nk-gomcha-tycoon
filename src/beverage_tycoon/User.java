package beverage_tycoon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class User extends JFrame { //유저의 정보를 담는 클래스
    static String userName;

    public User(){
        defaultScreen();
        name();
    }

    public void name(){
        //이름 설정 가이드 섹션
        JPanel nameGuideSection = new JPanel();
        JLabel nameGuide
                = new JLabel("<HTML><body style='text-align:center;'>사장님의 이름을<br> 입력해주세요</body></HTML>");
        Font nameGuideTitleFont = new Font("S-Core Dream", Font.BOLD, 40);
        nameGuide.setFont(nameGuideTitleFont);
        nameGuideSection.setBounds(0,140, 478,100);
        nameGuideSection.setBackground(Color.white);

        //이름 입력 섹션
        JPanel nameInputSection = new JPanel();
        JTextField inputUserName = new JTextField(5);
        inputUserName.setPreferredSize(new Dimension(40, 40));
        nameInputSection.setBounds(87,260, 303,50);
        nameInputSection.setBackground(Color.white);

        //이름 제출 버튼 섹션
        JPanel nameSubmitSection = new JPanel();
        JButton submitBtn = new JButton("이름 입력 완료!");
        Font btnTitleFont = new Font("S-Core Dream", Font.PLAIN, 20);
        submitBtn.setPreferredSize(new Dimension(150,40));
        nameSubmitSection.setBounds(140,310,200,50);
        nameSubmitSection.setBackground(Color.white);
        submitBtn.setFont(btnTitleFont);

        //모든 섹션 프레임에 add
        nameSubmitSection.add(submitBtn);
        nameGuideSection.add(nameGuide);
        nameInputSection.add(inputUserName);

        add(nameSubmitSection);
        add(nameGuideSection);
        add(nameInputSection);

        //이름 제출 버튼 이벤트
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == submitBtn){
                    userName = inputUserName.getText();
                    new Menu();
                    setVisible(false);
                }
            }
        });
    }

    public final void defaultScreen(){ //기본 프레임 세팅 - 상수 파일로 만들어서 import 필요
        Intro.ImagePanel roofImage
                = new Intro.ImagePanel(new ImageIcon("src/images/tycoonRoof.png").getImage());
        roofImage.setBounds(0,-10,478,111);
        getContentPane().add(roofImage);
        roofImage.setLayout(null);

        setTitle("GOMCHA TYCOON");
        setSize(478,888);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //캐릭터 이미지
        Intro.ImagePanel mainCharacter
                = new Intro.ImagePanel(new ImageIcon("src/images/tycoonCharacter.png")
                .getImage().getScaledInstance(480,600, Image.SCALE_SMOOTH));
        mainCharacter.setBounds(0,370,480,540);
        getContentPane().add(mainCharacter);
        mainCharacter.setLayout(null);
    }
}
