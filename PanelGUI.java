import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import src.Game;
import src.Path;
import javax.sound.sampled.*;//sound ko lagi
import java.io.File;

public class PanelGUI extends JPanel {
    private Game game;
    private FrameGUI frame;

    private Image chickenImg;
    private Image chickenDeadImg;
    private Image backgroundImg;

    public PanelGUI(Game game, FrameGUI frame) {

        this.game = game;
        this.frame = frame;
        chickenImg = new ImageIcon("assets/chicken.png").getImage();
        chickenDeadImg = new ImageIcon("assets/chickendead.png").getImage();
        backgroundImg = new ImageIcon("assets/background.png").getImage();
        playBackgroundMusic();

        // keyboard inout ko lagi
        setFocusable(true);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_SPACE) { // spacebar====jump ko lagi

                    if (!game.isStarted()) {
                        return;
                    }
                    game.jump();

                    frame.updateMultiplier();
                    frame.updateBalance();
                    repaint();

                    // game sakesi feri suru garna lai
                    if (game.isGameOver()) {
                        playSound("assets/deadchickesound.wav");
                        JOptionPane.showMessageDialog(frame, "Game Over!");

                        // Reset chicken only
                        game.getChicken().setX(10);
                        game.getChicken().setY(620);
                        game.resetDisplay();
                        frame.updateBalance();
                        frame.updateMultiplier();
                        frame.resetBetUI();

                        repaint();
                    }
                }
            }
        });
    }

    private void playSound(String fileName) {
        try {
            File file = new File(fileName);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();

            clip.open(audioStream);

            System.out.println("Playing: " + fileName);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace(); // IMPORTANT
        }
    }

    private void playBackgroundMusic() {

        try {

            System.out.println("Background music started");

            File file = new File("assets/bgsound.wav");

            AudioInputStream audio = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();

            clip.open(audio);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);//background image

        int startX = 10;
        int startY = 620;
        int chickenY = startY - 80;
        int pathWidth = 100;
        int pathHeight = 20;
        int gap = 30;

        // Draw all paths
        for (int i = 0; i < game.getPaths().size(); i++) {
            int x = startX + i * (pathWidth + gap);

            g.setColor(new Color(139, 69, 19));
            g.fillRect(x, startY, pathWidth, pathHeight);
        }

        // Draw the chicken ONLY ONCE
        if (game.isGameOver()) {
          g.drawImage(chickenDeadImg,
    (int) game.getChicken().getX(),
    chickenY,
    100, 80, this);
        } else {
    g.drawImage(chickenImg,
    (int) game.getChicken().getX(),
    chickenY,100, 80, this);
        }
    }
}
