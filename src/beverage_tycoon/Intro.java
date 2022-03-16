package beverage_tycoon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Intro extends JFrame{
    public Intro(){ // 기본적인 레이아웃만 생성자 내 초기화 하여 구현
        defaultScreen();
        introScreen();

        setTitle("GOMCHA TYCOON");
        setSize(478,888);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Intro();
        Thread bgm = new GameBGM();
        bgm.setDaemon(true); //메인 스레드가 종료되면 그 시점에 Daemon 스레드도 같이 종료되도록 함.
        bgm.start();
    }


    public void defaultScreen(){
        ImagePanel roofImage = new ImagePanel(new ImageIcon("src/images/tycoonRoof.png").getImage());
        roofImage.setBounds(0,-10,478,111);
        getContentPane().add(roofImage);
        roofImage.setLayout(null);
    }

    public void introScreen() { //게임의 인트로 화면 메소드
        ImagePanel mainCharacter = new ImagePanel(new ImageIcon("src/images/tycoonCharacter.png").getImage()
                .getScaledInstance(480,600, Image.SCALE_SMOOTH));
        mainCharacter.setBounds(0,370,480,540);
        getContentPane().add(mainCharacter);
        mainCharacter.setLayout(null);

        JPanel titleSection = new JPanel();
        JPanel startTitleSection = new JPanel();
        //title의 문자에 innerHTML을 넣은 이유 : 내부 글자를 가운데 정렬 하기 위함
        JLabel title = new JLabel("<HTML><body style='text-align:center;'>GOMCHA<br> TYCOON</body></HTML>");
        JLabel startTitle = new JLabel("Press Enter");

        Font gameTitleFont = new Font("S-Core Dream", Font.BOLD, 65);
        Font gameStartTitleFont = new Font("S-Core Dream", Font.BOLD, 40);
        title.setFont(gameTitleFont);
        startTitle.setFont(gameStartTitleFont);

        titleSection.setBackground(Color.white);
        titleSection.setBounds(87,150, 303,150);
        startTitleSection.setBackground(Color.white);
        startTitleSection.setBounds(87,320, 303,50);

        titleSection.add(title);
        startTitleSection.add(startTitle);
        add(titleSection);
        add(startTitleSection);

        //엔터 버튼 누르면 창 전환
        JTextField pressEnter = new JTextField();
        pressEnter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    new User();
                    setVisible(false);
                }
            }
        });
        add(pressEnter);
    }

    static class ImagePanel extends JPanel{ //각 패널에 이미지를 올려주는 클래스
        private final Image img;
        public ImagePanel(Image img){
            this.img = img;
            setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
            setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
            setLayout(null);
        }
        public void paintComponent(Graphics g){
            g.drawImage(img,0,0,null);
        }
    }

}

